/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanCustomer;
import sessionBean.sessionbeanPayment;

/**
 *
 * @author User
 */
@WebServlet(name = "servletPayment", urlPatterns = {"/servletPayment"})
public class servletPayment extends HttpServlet {

    @EJB
    private sessionbeanCustomer sessionbeanCustomer;
    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @EJB
    private sessionbeanPayment sessionbeanPayment;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = "";
        String role = "";
        String page = "ManageTools/mtPayment.jsp";
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            username = (String) session.getAttribute("username");
        }
        if (session.getAttribute("role") != null) {
            role = (String) session.getAttribute("role");
        }
        
        String customerName = "";
        List<Payment> listPayment = null;
        List<Customer> listCustomer = null;
        Customer customer = null;
        if (request.getParameter("customerName") != null) {
            customerName = (String)request.getParameter("customerName");
        }
        
        if(role.equals("admin") || role.equals("staff")) {
            page = "ManageTools/mtPayment.jsp";
            listPayment = sessionbeanPayment.getAllPayment();
        }
        else if(role.equals("user")){
            page = "./paymentList.jsp";
            if(!customerName.equals("")) {
                
                customer = sessionbeanCustomer.searchCustomerByCustomerName(customerName);
                listPayment = customer.getPaymentList();
                
                for(Payment pay : listPayment) {
                    System.out.println(pay.getPaymentPK().getCheckNumber());
                }
            }
        }
        else {
            response.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = response.getWriter()) {
                out.println("<h1>Please login to view payment history</h1>");
                out.println("<script src=\"js/bootstrap.min.js\"></script>");
            }
        }
        listCustomer = sessionbeanCustomer.getAllCustomer();
        
        request.setAttribute("customerName", customerName);
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listPayment", listPayment);
        request.getRequestDispatcher(page).include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = "";
        String role = "";
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            username = (String) session.getAttribute("username");
        }
        if (session.getAttribute("role") != null) {
            role = (String) session.getAttribute("role");
        }
        
        String customerName = "";
        String checkNumber = "";
        String amount = "";
        BigDecimal bdAmount = BigDecimal.ZERO;
        if (request.getParameter("customerName") != null) {
            customerName = (String)request.getParameter("customerName");
        }
        if (request.getParameter("checkNumber") != null) {
            checkNumber = (String)request.getParameter("checkNumber");
        }
        if (request.getParameter("amount") != null) {
            amount = (String)request.getParameter("amount");
            bdAmount = new BigDecimal(amount);
        }
        
        if(!(customerName.equals("") || checkNumber.equals("") || amount.equals(""))) {
            Customer customer = sessionbeanCustomer.searchCustomerByCustomerName(customerName);
            Date today = new Date();
            
            customer.setCreditLimit(customer.getCreditLimit().add(bdAmount));
            
            sessionbeanPayment.insertPayment(customer.getCustomerNumber(), checkNumber, today, bdAmount);
            sessionbeanCustomer.updateCustomer(customer);
        }
        
        PrintWriter out = response.getWriter();
        out.println("<script>window.location.href='Payment?customerName=" + customerName + "';</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}

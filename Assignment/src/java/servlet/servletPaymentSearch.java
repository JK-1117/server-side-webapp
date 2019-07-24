/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.Orderdetail;
import entity.Orders;
import entity.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanPayment;
import sessionBean.sessionbeanUser;

/**
 *
 * @author User
 */
@WebServlet(name = "servletPaymentSearch", urlPatterns = {"/servletPaymentSearch"})
public class servletPaymentSearch extends HttpServlet {

    @EJB
    private sessionbeanUser sessionbeanUser;
    @EJB
    private sessionbeanPayment sessionbeanPayment;
   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletPayment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletPayment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        Payment py = new Payment();
        Orders od = new Orders();
        
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        
        String customerNumber = request.getParameter("custno");
        int cn=0;

        
        if (request.getParameter("custno") != null) {
            cn = Integer.parseInt(customerNumber);
        }
        String checkno = request.getParameter("checkno");
        String date = request.getParameter("paymentdate");
        String amount = request.getParameter("amount");
        Date dt = null ;
        
        if(role.equals("")) {
            response.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = response.getWriter()) {
                out.println("<h1>Please login to view orders detail</h1>");
                out.println("<script src=\"js/bootstrap.min.js\"></script>");
            }
        }
        else if(role.equals("user")) {
            }
        try {
               dt= new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(servletOrdersUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(amount);
        } catch (NumberFormatException nef) {
            nef.getMessage();
        }
        sessionbeanPayment.insertPayment(cn,checkno,dt,bd);
        od.setStatus("SHIPPED");
        request.getRequestDispatcher("index.jsp").include(request, response);
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

}

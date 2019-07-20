/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBean.sessionbeanCustomer;

/**
 *
 * @author User
 */
@WebServlet(name = "servletUpdateCustomer", urlPatterns = {"/servletUpdateCustomer"})
public class servletUpdateCustomer extends HttpServlet {

    @EJB
    private sessionbeanCustomer sessionbeanCustomer;
    
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
            out.println("<title>Servlet servletUpdateCustomer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletUpdateCustomer at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String cusNumber = request.getParameter("custno");
        String companyname = request.getParameter("companyname");
        String contactfname = request.getParameter("contfname");
        String contactlname = request.getParameter("contlname");
        String phoneNum = request.getParameter("phone");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("poscode");
        String country = request.getParameter("country");
        String salesrepno = request.getParameter("salesrepno");
        String crdlimit = request.getParameter("crdlimit");
        int salesno = Integer.parseInt(salesrepno);
        BigDecimal bd = null;
        try{
           bd=new BigDecimal(crdlimit);
        }catch(NumberFormatException nef){
            nef.getMessage();
        }
        
        sessionbeanCustomer.updateCustomer(Integer.parseInt(cusNumber), companyname, contactfname, contactlname, phoneNum, addr1, addr2, city, state, postcode, country,salesno,bd);
        response.getWriter().println("<script>window.location.href='Customer';</script>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanCustomer;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletAjaxCustomer", urlPatterns = {"/servletAjaxCustomer"})
public class servletAjaxCustomer extends HttpServlet {

    @EJB
    private sessionbeanCustomer sessionbeanCustomer;

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

        HttpSession session = request.getSession();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String input = "";
        String cmbCompany = "";
        String firstName = "";
        String lastName = "";
        boolean verified = false;
        Customer customer = null;

        input = request.getParameter("input");
        cmbCompany = input.split(",")[0];
        firstName = input.split(",")[1];
        lastName = input.split(",")[2];
        
        customer = sessionbeanCustomer.searchCustomerByCustomerName(cmbCompany);
        
        System.out.println("cmbCompany = " + cmbCompany);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("customer = " + customer);
        System.out.println("customer.getContactFirstName().trim() = " + customer.getContactFirstName().trim());
        System.out.println("customer.getContactLastName().trim() = " + customer.getContactLastName().trim());
        
        if(customer != null) {
            verified = true;
            if(!firstName.trim().toLowerCase().equals(customer.getContactFirstName().trim().toLowerCase())) {
                verified = false;
            }
            if(!lastName.trim().toLowerCase().equals(customer.getContactLastName().trim().toLowerCase())) {
                verified = false;
            }
        }
        
        response.getWriter().write(String.valueOf(verified));
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

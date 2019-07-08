/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanProduct;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletProductUpdate", urlPatterns = {"/servletProductUpdate"})
public class servletProductUpdate extends HttpServlet {

    @EJB
    private sessionbeanProduct sessionbeanProduct;


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
        String operation = "";
        String productCode = "";
        String productName = "";
        String productLine = "";
        String productScale = "";
        String productVendor = "";
        String quantityInStock = "";
        String buyPrice = "";
        String MSRP = "";
        String productDescription = "";
        
        Orders orders = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }

        if (request.getParameter("operation") != null) {
            operation = (String) request.getParameter("operation");
        }
        if (request.getParameter("productCode") != null) {
            productCode = (String) request.getParameter("productCode");
        }
        if (request.getParameter("productName") != null) {
            productName = (String) request.getParameter("productName");
        }
        if (request.getParameter("productLine") != null) {
            productLine = (String) request.getParameter("productLine");
        }
        if (request.getParameter("productScale") != null) {
            productScale = (String) request.getParameter("productScale");
        }
        if (request.getParameter("productVendor") != null) {
            productVendor = (String) request.getParameter("productVendor");
        }
        if (request.getParameter("quantityInStock") != null) {
            quantityInStock = (String) request.getParameter("quantityInStock");
        }
        if (request.getParameter("buyPrice") != null) {
            buyPrice = (String) request.getParameter("buyPrice");
        }
        if (request.getParameter("MSRP") != null) {
            MSRP = (String) request.getParameter("MSRP");
        }
        if (request.getParameter("productDescription") != null) {
            productDescription = (String) request.getParameter("productDescription");
        }
        
        if(operation.equals("Update")) {
            sessionbeanProduct.updateProduct(productCode, productLine, productName, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP);
        }
        else if(operation.equals("New")) {
            sessionbeanProduct.insertProduct(productCode, productLine, productName, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP);
        }
        else if(operation.equals("Delete")) {
            sessionbeanProduct.deleteProduct(productCode);
        }
        
        response.getWriter().println("<script>window.location.href='Product';</script>");
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

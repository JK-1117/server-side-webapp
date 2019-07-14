/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanCart;
import utilities.Cart;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletCart", urlPatterns = {"/servletCart"})
public class servletCart extends HttpServlet {


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
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        String username = "";
        String role = "";
        sessionbeanCart cart = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        if(session.getAttribute("cart") != null) {
            cart = (sessionbeanCart)session.getAttribute("cart");
        }
        else {
            cart = new sessionbeanCart();
            if(username.equals("")) {
                cart.initialize();
            }
            else {
                cart.initialize(username);
            }
            session.setAttribute("cart", cart);
        }
        
        String productCode = "";
        String qtyStr = "";
        int qty = 0;
        
        if(request.getParameter("input") != null) {
            String input = (String)request.getParameter("input");
            productCode = input.split(",")[0];
            qtyStr = input.split(",")[1];
            qty = Integer.valueOf(qtyStr);
        }
        
        if(!productCode.equals("")) {
            cart.setValue(productCode, qty);
            String result = Integer.toString(cart.getTotalQty()) + "," + cart.getTotalAmount().toString();
            response.getWriter().write(result);
        }
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
        sessionbeanCart cart = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        if(session.getAttribute("cart") != null) {
            cart = (sessionbeanCart)session.getAttribute("cart");
        }
        else {
            cart = new sessionbeanCart();
            if(username.equals("")) {
                cart.initialize();
            }
            else {
                cart.initialize(username);
            }
            session.setAttribute("cart", cart);
        }
        
        String operation = "";
        String productCode = "";
        Integer qty = 0;
        
        if(request.getParameter("operation") != null) {
            operation = (String)request.getParameter("operation");
        }
        if(request.getParameter("productCode") != null) {
            productCode = (String)request.getParameter("productCode");
        }
        if(request.getParameter("qty") != null) {
            String qtyStr = (String)request.getParameter("qty");
            qty = Integer.valueOf(qtyStr);
        }
        
        PrintWriter out = response.getWriter();
        if(operation.equals("Edit")) {
            cart.setValue(productCode, qty);
        }
        else if(operation.equals("Remove")) {
            cart.removeItem(productCode, qty);
        }
        else if(operation.equals("AddToCart")) {
            cart.addItem(productCode, qty);
            out.println("<script>window.location.href='Product?productCode=" + productCode + "'</script>");
        }
        else if(operation.equals("BuyNow")) {
            cart.addItem(productCode, qty);
            out.println("<script>window.location.href='Cart'</script>");
        }
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

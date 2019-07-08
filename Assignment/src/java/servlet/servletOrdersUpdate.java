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
import sessionBean.sessionbeanOrder;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletOrdersUpdate", urlPatterns = {"/servletOrdersUpdate"})
public class servletOrdersUpdate extends HttpServlet {

    @EJB
    private sessionbeanOrder sessionbeanOrder;


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
        String orderNumber = "";
        String shippedDate = "";
        String status = "";
        String comments = "";
        
        Orders orders = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }

        if (request.getParameter("orderNumber") != null) {
            orderNumber = (String) request.getParameter("orderNumber");
        }
        if (request.getParameter("shippedDate") != null) {
            shippedDate = (String) request.getParameter("shippedDate");
        }
        if (request.getParameter("status") != null) {
            status = (String) request.getParameter("status");
        }
        if (request.getParameter("comments") != null) {
            comments = (String) request.getParameter("comments");
        }
        
        orders = sessionbeanOrder.searchOrder(Integer.parseInt(orderNumber));
        try {
            orders.setShippedDate(new SimpleDateFormat("dd/MM/yyyy").parse(shippedDate));
        } catch (ParseException ex) {
            Logger.getLogger(servletOrdersUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        orders.setStatus(status);
        orders.setComments(comments);
        
        sessionbeanOrder.updateOrders(orders);
        response.getWriter().println("<script>window.location.href='Order';</script>");
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

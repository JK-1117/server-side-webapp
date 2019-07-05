/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "servletOrdersSearch", urlPatterns = {"/servletOrdersSearch"})
public class servletOrdersSearch extends HttpServlet {

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
        
        String username = "";
        String role = "";
        String orderNumber = "";
        String orderDate = "";
        String requiredDate = "";
        String shippedDate = "";
        String status = "";
        String customerNumber = "";
        
        List<Orders> listOrders = null;
        Orders orders = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }

        if (request.getParameter("no") != null) {
            orderNumber = (String) request.getParameter("no");
        }
        
        if(role.equals("")) {
            response.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = response.getWriter()) {
                out.println("<h1>Please login to view orders detail</h1>");
                out.println("<script src=\"js/bootstrap.min.js\"></script>");
            }
        }
        else {
            if(orderNumber.equals("")) {
                listOrders = sessionbeanOrder.getAllOrders();
                
                request.setAttribute("listOrders", listOrders);
                request.getRequestDispatcher("ManageTools/mtOrderList.jsp").include(request, response);
            }
            else {
                orders = sessionbeanOrder.searchOrder(Integer.parseInt(orderNumber));
                
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("ManageTools/mtOrder.jsp").include(request, response);
            }
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

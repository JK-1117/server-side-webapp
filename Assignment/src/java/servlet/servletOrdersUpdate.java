/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.Orders;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanCart;
import sessionBean.sessionbeanCustomer;
import sessionBean.sessionbeanOrder;
import sessionBean.sessionbeanOrderDetail;
import sessionBean.sessionbeanProduct;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletOrdersUpdate", urlPatterns = {"/servletOrdersUpdate"})
public class servletOrdersUpdate extends HttpServlet {

    @EJB
    private sessionbeanProduct sessionbeanProduct;

    @EJB
    private sessionbeanOrderDetail sessionbeanOrderDetail;

    @EJB
    private sessionbeanCustomer sessionbeanCustomer;

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
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        
        String operation = "";
        String orderNumber = "";
        String shippedDate = "";
        String status = "";
        String comments = "";
        String customerName = "";
        String requiredDate = "";
        String errorInsertOrders = "Your credit limits EXCEEDED!";
        Orders orders = null;
        Customer customer = null;
        sessionbeanCart cart = null;

        if (request.getParameter("operation") != null) {
            operation = (String) request.getParameter("operation");
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
        if (request.getParameter("customerName") != null) {
            customerName = (String) request.getParameter("customerName");
        }
        if (request.getParameter("requiredDate") != null) {
            requiredDate = (String) request.getParameter("requiredDate");
        }
        
        if(operation.equals("Update")) {
            orders = sessionbeanOrder.searchOrder(Integer.parseInt(orderNumber));
            try {
                orders.setShippedDate(new SimpleDateFormat("dd/MM/yyyy").parse(shippedDate));
            } catch (ParseException ex) {
                Logger.getLogger(servletOrdersUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            orders.setStatus(status);
            orders.setComments(comments);

            sessionbeanOrder.updateOrders(orders);
            out.println("<script>window.location.href='Order';</script>");
        }
        else if(operation.equals("New")) {
            customer = sessionbeanCustomer.searchCustomerByCustomerName(customerName);
            cart = (sessionbeanCart)session.getAttribute("cart");
            
            if(cart.getTotalAmount().compareTo(customer.getCreditLimit()) == 1) {
                request.setAttribute("errorInsertOrders", errorInsertOrders);
                out.println("<script>window.location.href='Cart';</script>");
            }
            else {
                Integer newOrderNumber = sessionbeanOrder.getOrderNumber();
                sessionbeanOrder.insertOrders(newOrderNumber, customer ,requiredDate);
                customer.setCreditLimit(customer.getCreditLimit().subtract(cart.getTotalAmount()));
                
                Iterator it = cart.getContents().entrySet().iterator();
                short orderLineNumber = 1;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Product product = (Product)pair.getKey();
                    short quantityOrdered = ((Integer)pair.getValue()).shortValue();
                    short quantityInStock = (short)(product.getQuantityInStock() - quantityOrdered);
                    
                    product.setQuantityInStock(quantityInStock);
                    sessionbeanProduct.updateProduct(product);
                    
                    sessionbeanOrderDetail.insertOrderDetail(newOrderNumber, quantityOrdered, orderLineNumber, orders, product);
                    it.remove(); // avoids a ConcurrentModificationException
                    orderLineNumber++;
                }
                
                out.println("<script>window.location.href='Order';</script>");
            }
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

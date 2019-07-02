/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBean.sessionbeanProduct;
import sessionBean.sessionbeanProductLine;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletProductSearch", urlPatterns = {"/servletProductSearch"})
public class servletProductSearch extends HttpServlet {

    @EJB
    private sessionbeanProductLine sessionbeanProductLine;

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
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String productLine = "";
            List<Product> listProducts = null;

            if (request.getParameter("productLine") != null) {
                productLine = (String) request.getParameter("productLine");
                listProducts = sessionbeanProductLine.getProductList(productLine);
            } else {
                listProducts = sessionbeanProduct.getAllProducts();
            }
            Iterator i = listProducts.iterator();
            int index = 0;
            int productPage = 1;
            out.println("<h3>" + productLine.toUpperCase() + " > PRODUCT</h3>");
            out.println("<small>Showing 10 results</small>");
//TODO: sorting & select productline & probably search
            out.println("<div class=\"tab-content\">");
            out.println("<div id=\"page1\" class=\"tab-pane list-group fade in active\" >");
            while (i.hasNext()) {
                Product pro = (Product) i.next();
                out.println("<a href=\"#\" class=\"list-group-item\" >");
                out.println("<h4 class=\"list-group-item-heading\" >");
                out.println(pro.getProductName());
                out.println("<span class=\"label label-info\">RM" + pro.getMsrp() + "</span>");
                out.println("</h4><p class=\"list-group-item-text\" >");
                out.println(pro.getProductDescription());
                out.println("</p></a>");
                index++;
                if (index == productPage * 10) {
                    out.println("</div>");
                    if (i.hasNext()) {
                        productPage++;
                        out.println("<div id=\"page" + productPage + "\" class=\"tab-pane list-group fade\" >");
                    }
                }
            }
            if (index % 10 > 0) {
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<ul class=\"pagination pull-right\">");
            out.println("<li class=\"active\"><a onclick=\"changePage(this)\" href=\"#\" >1</a></li>");
            for (int j = 2; j <= productPage; j++) {
                out.println("<li><a onclick=\"changePage(this)\" href=\"#\" >" + j + "</a></li>");
            }
            out.println("</ul>");
            out.println("<script src=\"js/productjs.js\"></script>");
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

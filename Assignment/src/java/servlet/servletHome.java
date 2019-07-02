/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ProductLine;
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
import sessionBean.sessionbeanProductLine;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletHome", urlPatterns = {"/servletHome"})
public class servletHome extends HttpServlet {

    @EJB
    private sessionbeanProductLine sessionbeanProductLine;

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
            List<ProductLine> listProductLine = sessionbeanProductLine.getAllProductLine();
            Iterator i = listProductLine.iterator();
            request.setAttribute("listProductLine", listProductLine);
            out.println("<h1>HOME</h1>");

            out.println("<section class=\"row\">");
                out.println("<div class=\"col-sm-12\">");
                    out.println("<h4>Product Line</h4>");
                out.println("</div>");
                out.println("<div class=\"col-sm-12 container\">");
                    out.println("<div class=\"row\" id=\"productLine\">");
                    while (i.hasNext()) {
                        ProductLine proLine = (ProductLine) i.next();
                        out.println("<a href=\"Product?productLine=" + proLine.getProductLine() + "\" class=\"btn btn-default col-sm-3 col-xs-4\">");
                        out.println(proLine.getProductLine());
                        out.println("</a>");
                    }
                    out.println("</div>");
                out.println("</div>");
            out.println("</section>");
            out.println("<script src=\"js/homejs.js\"></script>");
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

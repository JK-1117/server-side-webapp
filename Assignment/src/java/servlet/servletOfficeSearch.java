/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Office;
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
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanOffice;

/**
 *
 * @author IEUser
 */
@WebServlet(name = "servletOfficeSearch", urlPatterns = {"/servletOfficeSearch"})
public class servletOfficeSearch extends HttpServlet {

    @EJB
    private sessionbeanOffice sessionbeanOffice;

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
        PrintWriter out = response.getWriter();
        try {

            List<Office> h = sessionbeanOffice.getAllOffice();
            Iterator i = h.iterator();

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Office</title>");
            out.println("</head>");
            out.println("<body style=\\\"background-color: #ffcccc\\\">");
            out.println("<h1 align=\"center\">Manage Office</h1>");
            out.println("<table border=\"1\" cellspacing=5 cellpadding=5 bgcolor=\"lightblue\" colspan=2 rowspan=2 align=\"center\">");
            out.println("<thead>");
            out.println("<tr style=\"background-color: #ffcc33 \">");
            out.println("<th>");
            out.println("Office Code");
            out.println("</th>");
            out.println("<th>");
            out.println("City");
            out.println("</th>");
            out.println("<th>");
            out.println("Phone");
            out.println("</th>");
            out.println("<th>");
            out.println("Address Line 1");
            out.println("</th>");
            out.println("<th>");
            out.println("Address Line 2");
            out.println("</th>");
            out.println("<th>");
            out.println("State");
            out.println("</th>");
            out.println("<th>");
            out.println("Country");
            out.println("</th>");
            out.println("<th>");
            out.println("Postal Code");
            out.println("</th>");
            out.println("<th>");
            out.println("Territory");
            out.println("</th>");
            out.println("<th>");
            out.println("Update");
            out.println("</th>");
            out.println("<th>");
            out.println("Delete");
            out.println("</th>");
            out.println("</tr>");
            out.println("</thead>");
            while (i.hasNext()) {
                Office at = (Office) i.next();
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<td width =\"20px\" >" + at.getOfficeCode() + "</td>");
                out.println("<td width =\"40px\">" + at.getCity() + "</td>");

                out.println("<td width =\"70px\">" + at.getPhone() + "</td>");
                out.println("<td width =\"70px\">" + at.getAddressLine1() + "</td>");
                out.println("<td width =\"70px\">" + at.getAddressLine2() + "</td>");
                out.println("<td width =\"70px\">" + at.getState() + "</td>");
                out.println("<td width =\"70px\">" + at.getCountry() + "</td>");
                out.println("<td width =\"70px\">" + at.getPostalCode() + "</td>");
                out.println("<td width =\"70px\">" + at.getTerritory() + "</td>");
                out.println("<td>");
                out.println("<a href=\"servletOfficeProcess?officeCode=" + at.getOfficeCode() + "\">Update</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href=\"servletOfficeProcess?officeCode=" + at.getOfficeCode() + "\">Delete</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</tbody>");
            }
            out.println("</table>");
            out.println("<div style= \"margin-left: 400px;margin-top: 80px; background-color: #3333ff; width:40%\">");
            out.println("<form action=\"servletOfficeProcess\" method=\"POST\" >");
            out.println("<fieldset>");
            out.println("<legend>Add Office Details:</legend>");
            out.println("Office Code: <input type=\"text\" name=\"officeCode\" ");
            out.println("/>");
            out.println("<br>City: <input type=\"text\" name=\"city\" ");
            out.println("/>");
            out.println("<br>Phone: <input type=\"text\" name=\"phone\" ");
            out.println("/>");
            out.println("<br>Address Line 1: <input type=\"text\" name=\"addressLine1\" ");
            out.println("/>");
            out.println("<br>Address Line 2 : <input type=\"text\" name=\"addressLine2\" ");
            out.println("/>");
            out.println("<br>State : <input type=\"text\" name=\"state\" ");
            out.println("/>");
            out.println("<br>Country : <input type=\"text\" name=\"country\" ");
            out.println("/>");
            out.println("<br>Postal Code : <input type=\"text\" name=\"postalCode\" ");
            out.println("/>");
            out.println("<br>Territory : <input type=\"text\" name=\"territory\" ");
            out.println("/>");
            out.println("<input type=\"submit\" name=\"ADD\" value=\"ADD\" />");
            out.println("</fieldset>");
            out.println("</form>");
            out.println("</div>");

//            out.println("<h1>Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {

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
//        String officeCode = "";
//        HttpSession session = request.getSession();
//        List<Office> listOffice = null;
//        listOffice = sessionbeanOffice.getAllOffice();
//        if (session.getAttribute("officeCode") != null) {
//            officeCode = (String) session.getAttribute("officeCode");
//        }
//
//        request.setAttribute("listOffice", listOffice);
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
        processRequest(request, response);
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

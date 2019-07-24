/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Employee;
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
import sessionBean.sessionbeanEmployee;

/**
 *
 * @author JW
 */
@WebServlet(name = "servletEmployee", urlPatterns = {"/servletEmployee"})
public class servletEmployee extends HttpServlet {

    @EJB
    private sessionbeanEmployee sessionbeanEmployee;

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
        PrintWriter out = response.getWriter();
        try {

            List<Employee> h = sessionbeanEmployee.getAllEmployee();
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
            out.println("Employee Number");
            out.println("</th>");
            out.println("<th>");
            out.println("Last Name");
            out.println("</th>");
            out.println("<th>");
            out.println("First Name");
            out.println("</th>");
            out.println("<th>");
            out.println("Extension");
            out.println("</th>");
            out.println("<th>");
            out.println("Email");
            out.println("</th>");
            out.println("<th>");
            out.println("Office Code");
            out.println("</th>");
            out.println("<th>");
            out.println("Reports To");
            out.println("</th>");
            out.println("<th>");
            out.println("Job Title");
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
                Employee at = (Employee) i.next();
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<td width =\"20px\" >" + at.getEmployeeNumber() + "</td>");
                out.println("<td width =\"40px\">" + at.getLastName() + "</td>");

                out.println("<td width =\"70px\">" + at.getFirstName() + "</td>");
                out.println("<td width =\"70px\">" + at.getExtension() + "</td>");
                out.println("<td width =\"70px\">" + at.getEmail() + "</td>");
                out.println("<td width =\"70px\">" + at.getOfficeCode().getOfficeCode() + "</td>");
//                out.println("<td width =\"70px\">" + at.getReportsTo() + "</td>");
                Employee em = at.getReportsTo();
                if (em != null) {
                    out.println("<td>" + em.getEmployeeNumber() + "</td>");
                } else {
                    out.println("<td> - </td>");
                }
                out.println("<td width =\"70px\">" + at.getJobTitle() + "</td>");
                out.println("<td>");
                out.println("<a href=\"servletEmployeeProcess?employeeNumber=" + at.getEmployeeNumber() + "\">Update</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href=\"servletEmployeeProcess?employeeNumber=" + at.getEmployeeNumber() + "\">Delete</a>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</tbody>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            out.println("</table>");
            out.println("<div style= \"margin-left: 400px;margin-top: 80px; background-color: #3333ff; width:40%\">");
            out.println("<form action=\"servletOfficeProcess\" method=\"POST\" >");
            out.println("<fieldset>");
            out.println("<legend>Add Office Details:</legend>");
            out.println("Employee Number: <input type=\"text\" name=\"employeeNumber\" ");
            out.println("/>");
            out.println("<br>Last name: <input type=\"text\" name=\"lastName\" ");
            out.println("/>");
            out.println("<br>First Name: <input type=\"text\" name=\"firstName\" ");
            out.println("/>");
            out.println("<br>Extension : <input type=\"text\" name=\"extension\" ");
            out.println("/>");
            out.println("<br>Email : <input type=\"text\" name=\"email\" ");
            out.println("/>");
            out.println("<br>Office Code : <input type=\"text\" name=\"officeCode\" ");
            out.println("/>");
            out.println("<br>Reposts To : <input type=\"text\" name=\"reportsTo\" ");
            out.println("/>");
            out.println("<br>Job Title : <input type=\"text\" name=\"jobTitle\" ");
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

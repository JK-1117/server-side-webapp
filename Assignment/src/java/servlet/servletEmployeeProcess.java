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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBean.sessionbeanEmployee;
import utilities.ValidateManageLogic;

/**
 *
 * @author JW
 */
@WebServlet(name = "servletEmployeeProcess", urlPatterns = {"/servletEmployeeProcess"})
public class servletEmployeeProcess extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletEmployeeProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletEmployeeProcess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {

            String r = request.getParameter("employeeNumber");
            int en = Integer.parseInt(r);

            Employee t = sessionbeanEmployee.searchEmployeeByEmpNumber(en);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Office Process</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<table border=\"1\" style=\"background-color: #ffcc33 \">");
            out.println("<form action=\"servletEmployeeProcess\" method=\"POST\" >");
            out.println("<tbody>");
            out.println("<tr>");

            out.println("<td>");
            out.println("Employee Number");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"employeeNumber\" readonly value=" + en);
            out.println("/>");
            out.println("</td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Last Name");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"lastName\" value=" + t.getLastName());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("First Name");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"firstName\" value=" + t.getFirstName());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Extension");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"extension\" value=" + t.getExtension());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Email");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"email\" value=" + t.getEmail());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Office Code");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"officeCode\" value=" + t.getOfficeCode().getOfficeCode());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Reports To");
            out.println("</td>");
            out.println("<td>");
//            out.println("<input type=\"text\" name=\"reportsTo\" value=" + t.getReportsTo());
            Employee em = t.getReportsTo();
//            out.println("<input type=\"text\" name=\"reportsTo\" value=" + em.getEmployeeNumber()+"/>");
            if (em != null) {
//                    out.println("<td>" + em.getEmployeeNumber() + "</td>");
                out.println("<input type=\"text\" name=\"reportsTo\" value=" + em.getEmployeeNumber() + "/>");
            } else {
//                    out.println("<td> - </td>");
                out.println("<input type=\"text\" name=\"reportsTo\" value=\"reportsTo\"/>");

            }
//            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Job Title");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"jobTitle\" value=" + t.getJobTitle());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<input type=\"submit\" name=\"UPDATE\" value=\"UPDATE\" />");
            out.println("<input type=\"submit\" name=\"DELETE\" value=\"DELETE\" />");
            out.println("</form>");
            out.println("</tbody>");
            out.println("<table>");

//            out.println("<h1>Servlet JPAprocess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        String employeeNumber = request.getParameter("employeeNumber");
        int en = Integer.parseInt(employeeNumber);
        String fname = request.getParameter("lastName");
        String lname = request.getParameter("firstName");
        String extension = request.getParameter("extension");
        String email = request.getParameter("email");
//        Office  = request.geofficeCodetParameter("officeCode");
        Office of = new Office(request.getParameter("OfficeCode"));

        String reportsTo = request.getParameter("reportsTo");
        int rt = Integer.parseInt(reportsTo);
        String jobTitle = request.getParameter("jobTitle");

        //this line is to package the whole values into one array string variable - easier just pass one parameter object
//        String[] s = {officeCode, city, phone, addressLine1, addressLine2};
        //this if else logic is to determine the type of update operation that needs to be done (update/delete/add) 
        try (PrintWriter out = response.getWriter()) {

            //if UPDATE button is clicked
            if (ValidateManageLogic.validateManageTrainer(request).equals("UPDATE")) {
                //call session bean updateTrainer method
                sessionbeanEmployee.updateEmployee(en, fname, lname, extension, email, of, rt, jobTitle);

                //if DELETE button is clicked  
            } else if (ValidateManageLogic.validateManageTrainer(request).equals("DELETE")) {

                //call session bean deleteTrainer method
                sessionbeanEmployee.deleteEmployee(en);

                //if ADD button is clicked
            } else {
                //call session bean addTrainer method
                sessionbeanEmployee.addEmployee(en, fname, lname, extension, email, of, rt, jobTitle);
            }
            //this line is to redirect to notify record has been updated and redirect to another page
            ValidateManageLogic.navigateJS2(out);

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

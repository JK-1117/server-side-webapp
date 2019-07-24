/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Office;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBean.sessionbeanOffice;
import utilities.ValidateManageLogic;

/**
 *
 * @author JW
 */
@WebServlet(name = "servletOfficeProcess", urlPatterns = {"/servletOfficeProcess"})
public class servletOfficeProcess extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletOfficeProcess</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletOfficeProcess at " + request.getContextPath() + "</h1>");
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

            String r = request.getParameter("officeCode");
            Office t = sessionbeanOffice.searchOfficeByCode(r);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Office Process</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<table border=\"1\" style=\"background-color: #ffcc33 \">");
            out.println("<form action=\"servletOfficeProcess\" method=\"POST\" >");
            out.println("<tbody>");
            out.println("<tr>");

            out.println("<td>");
            out.println("Office Code");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"officeCode\" readonly value=" + r);
            out.println("/>");
            out.println("</td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("City");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"city\" value=" + t.getCity());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Phone");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"phone\" value=" + t.getPhone());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Address Line 1");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"addressLine1\" value=" + t.getAddressLine1());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Address Line 2");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"addressLine2\" value=" + t.getAddressLine2());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("State");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"state\" value=" + t.getState());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("Country");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"country\" value=" + t.getCountry());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("Postal Code");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"postalCode\" value=" + t.getPostalCode());
            out.println("/>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("Territory");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" name=\"territory\" value=" + t.getTerritory());
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
        String officeCode = request.getParameter("officeCode");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String postalCode = request.getParameter("postalCode");
        String territory = request.getParameter("territory");

        //this line is to package the whole values into one array string variable - easier just pass one parameter object
//        String[] s = {officeCode, city, phone, addressLine1, addressLine2};

        //this if else logic is to determine the type of update operation that needs to be done (update/delete/add) 
        try (PrintWriter out = response.getWriter()) {

            //if UPDATE button is clicked
            if (ValidateManageLogic.validateManageTrainer(request).equals("UPDATE")) {
                //call session bean updateTrainer method
                sessionbeanOffice.updateOffice(officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory);

                //if DELETE button is clicked  
            } else if (ValidateManageLogic.validateManageTrainer(request).equals("DELETE")) {

                //call session bean deleteTrainer method
                sessionbeanOffice.deleteOffice(officeCode);

                //if ADD button is clicked
            } else {
                //call session bean addTrainer method
                sessionbeanOffice.addOffice(officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory);
            }
            //this line is to redirect to notify record has been updated and redirect to another page
            ValidateManageLogic.navigateJS(out);

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

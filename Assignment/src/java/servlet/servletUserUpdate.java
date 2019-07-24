/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanUser;
import sessionBean.sessionbeanUserRole;

/**
 *
 * @author JK
 */
@WebServlet(name = "servletUserUpdate", urlPatterns = {"/servletUserUpdate"})
public class servletUserUpdate extends HttpServlet {

    @EJB
    private sessionbeanUserRole sessionbeanUserRole;

    @EJB
    private sessionbeanUser sessionbeanUser;


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
        String errorUser = "";
        List<User> listUser = null;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        if(request.getParameter("errorUser") != null) {
            errorUser = (String)request.getParameter("errorUser");
        }
        
        if(role.equals("admin") || role.equals("staff")) {
            listUser = sessionbeanUser.getAllUser();

            request.setAttribute("errorUser",errorUser);
            request.setAttribute("listUser",listUser);
            request.setAttribute("sessionbeanUserRole",sessionbeanUserRole);
            request.getRequestDispatcher("ManageTools/mtUser.jsp").include(request, response);
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
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        
        String operation = "";
        String textUsername = "";
        String textPassword = "";
        String textUserRole = "";
        String errorUser = "";
        
        if(request.getParameter("operation") != null) {
            operation = (String)request.getParameter("operation");
        }
        if(request.getParameter("textUsername") != null) {
            textUsername = (String)request.getParameter("textUsername");
        }
        if(request.getParameter("textPassword") != null) {
            textPassword = (String)request.getParameter("textPassword");
        }
        if(request.getParameter("textUserRole") != null) {
            textUserRole = (String)request.getParameter("textUserRole");
        }
        
        if(operation.equals("New")) {
            User user = sessionbeanUser.searchUser(textUsername);
            if(user == null){
                sessionbeanUser.insertUser(textUsername, textPassword);
                sessionbeanUserRole.insertUserRole(textUsername, textUserRole);
            }
            else{
                errorUser = "user exists";
            }
        }
        else if(operation.equals("Update")) {
            sessionbeanUser.updateUser(textUsername, textPassword);
            sessionbeanUserRole.updateUserRole(textUsername, textUserRole);
        }
        else if(operation.equals("Delete")) {
            sessionbeanUser.deleteUser(textUsername);
            sessionbeanUserRole.deleteUserRole(textUsername, textUserRole);
        }
        else if(operation.equals("ChangePassword")) {
            sessionbeanUser.updateUser(username, textPassword);
        }
        response.getWriter().println("<script>window.location.href='User?errorUser=" + errorUser + "';</script>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
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
@WebServlet(name = "servletLogin", urlPatterns = {"/servletLogin"})
public class servletLogin extends HttpServlet {

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

        HttpSession session = request.getSession();

        String input = "";
        String username = "";
        String password = "";

        input = request.getParameter("input");
        username = input.split(",")[0];
        password = input.split(",")[1];

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (sessionbeanUser.login(username, password)) {
            String role = sessionbeanUserRole.getUserRole(username);

            session.setAttribute("username", username);
            session.setAttribute("role", role);
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
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

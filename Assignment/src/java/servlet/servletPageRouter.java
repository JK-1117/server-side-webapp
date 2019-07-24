/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.Office;
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
import sessionBean.sessionbeanCustomer;
import sessionBean.sessionbeanOffice;
import sessionBean.sessionbeanProduct;
import sessionBean.sessionbeanProductLine;

/**
 *
 * @author JK
 */
@WebServlet(name = "FrameServlet", urlPatterns = {"/Home", "/Product", "/Contact", "/Cart", "/Order", "/Payment", "/Customer", "/Office", "/Employee", "/ProductLine", "/User", "/error"})
public class servletPageRouter extends HttpServlet {

    @EJB
    private sessionbeanOffice sessionbeanOffice;

    @EJB
    private sessionbeanCustomer sessionbeanCustomer;

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
        
        String title = "Kool Store";
        String url = request.getServletPath();
        String page = "home";
        String username = "";
        String role = "";
        List<Customer> listCustomer = null;
        List<Office> listOffice = null;

        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }
        
        if(url.equals("/Home")) {
            page = "/servletHome";
        }
        else if(url.equals("/Product")) {
            page = "/servletProductSearch";
        }
        else if(url.equals("/Contact")) {
            listOffice = sessionbeanOffice.getAllOffice();
            
            request.setAttribute("listOffice", listOffice);
            page = "/contact.jsp";
        }
        else if(url.equals("/Cart")) {
            listCustomer = sessionbeanCustomer.getAllCustomer();
            
            request.setAttribute("listCustomer", listCustomer);
            page = "/cart.jsp";
        }
        else if(url.equals("/Order")) {
            String operation = "";
            if(request.getParameter("operation") != null) {
                operation = request.getParameter("operation");
            }
            if(operation.equals("New")) {
                page = "/servletOrdersUpdate";
            }
            page = "/servletOrdersSearch";
        }
        else if(url.equals("/Payment")) {
            page = "/servletPayment";
        }
        else if(url.equals("/Customer")) {
            page = "/servletCustomerSearch";
        }
        else if(url.equals("/Employee")) {
            page = "/servletEmployee";
        }
        else if(url.equals("/Office")) {
            page = "/servletOfficeSearch";
        }
        else if(url.equals("/ProductLine")) {
            page = "/servletProductLineUpdate";
        }
        else if(url.equals("/User")) {
            page = "/servletUserUpdate";
        }
        else {
            page = "/error.jsp";
        }
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
            out.println("<head>");
                out.println("<title>" + title + "</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
                out.println("<link rel=\"stylesheet\" href=\"css/main.css\">");
                out.println("<script src=\"js/jquery-3.4.1.min.js\" ></script>");
                out.println("</head>");
            out.println("<body>");

                request.getRequestDispatcher("/header.jsp").include(request, response);
                    
                out.println("<div class=\"container main\">");
                    request.getRequestDispatcher(page).include(request, response);
                out.println("</div>");
                    
                request.getRequestDispatcher("/footer.jsp").include(request, response);

            out.println("</body>");
            out.println("<script src=\"js/bootstrap.min.js\"></script>");
        out.println("</html>");
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

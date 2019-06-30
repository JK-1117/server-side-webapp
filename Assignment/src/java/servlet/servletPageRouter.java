/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Product;
import entity.ProductLine;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FrameServlet", urlPatterns = {"/Home", "/Product", "/Contact", "/Profile", "/Order", "/Payment", "/Customer", "/Office", "/Employee", "/ProductLine", "/User"})
public class servletPageRouter extends HttpServlet {

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

        response.setContentType("text/html");
        
        String title = "Kool Store";
        String url = request.getServletPath();
        String page = "home";

        
        if(url.equals("/Home")) {
            page = "home";
            List<ProductLine> listProductLine = sessionbeanProductLine.getAllProductLine();
            request.setAttribute("listProductLine", listProductLine);
        }
        else if(url.equals("/Product")) {
            page = "product";
            List<Product> listProducts = sessionbeanProduct.getAllProducts();
            request.setAttribute("listProducts", listProducts);
        }
        else if(url.equals("/Contact")) {
            page = "contact";
        }
        else if(url.equals("/Profile")) {
            page = "profile";
        }
        else if(url.equals("/Order")) {
            page = "order";
        }
        else if(url.equals("/Payment")) {
            page = "payment";
        }
        else if(url.equals("/Customer")) {
            page = "customer";
        }
        else if(url.equals("/Office")) {
            page = "office";
        }
        else if(url.equals("/Employee")) {
            page = "employee";
        }
        else if(url.equals("/ProductLine")) {
            page = "productline";
        }
        else if(url.equals("/User")) {
            page = "user";
        }
        else {
            page = "error";
        }
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
            out.println("<head>");
                out.println("<title>" + title + "</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\" />");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\" />");
                out.println("<script src=\"js/jquery-3.4.1.min.js\" ></script>");
                out.println("</head>");
            out.println("<body>");

                request.getRequestDispatcher("./header.jsp").include(request, response);
                    
                out.println("<div class=\"container\">");
                    request.getRequestDispatcher("./" + page + ".jsp").include(request, response);
                out.println("</div>");
                    
                request.getRequestDispatcher("./footer.jsp").include(request, response);

            out.println("</body>");
            out.println("<script src=\"js/bootstrap.js ></script>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.Product;
import entity.ProductLine;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBean.sessionbeanCustomer;
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
        String username = "";
        String role = "";
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            username = (String)session.getAttribute("username");
        }
        if(session.getAttribute("role") != null) {
            role = (String)session.getAttribute("role");
        }

        String productLine = "";
        String productCode = "";
        String txtSearch = "";
        String sortBy = "";
        String operation = "";
        List<Product> listProducts = null;
        List<ProductLine> listProductLine = null;
        List<Customer> listCustomer = null;
        Product product = null;

        if (request.getParameter("operation") != null) {
            operation = (String) request.getParameter("operation");
        }
        if (request.getParameter("productCode") != null) {
            productCode = (String) request.getParameter("productCode");
        }
        if (request.getParameter("sortBy") != null) {
            sortBy = (String) request.getParameter("sortBy");
        }
        if (request.getParameter("productLine") != null) {
            productLine = (String) request.getParameter("productLine");
            listProducts = sessionbeanProductLine.getProductList(productLine);
        } else {
            listProducts = sessionbeanProduct.getAllProducts();
        }  
        if (request.getParameter("txtSearch") != null) {
            txtSearch = (String) request.getParameter("txtSearch");
            List<Product> filteredList = new ArrayList<Product>();
            for(Product item : listProducts) {
                if(item.getProductName().toLowerCase().contains(txtSearch.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            listProducts = filteredList;
        }

        if(sortBy.equals("PriceLH")) {
            listProducts.sort(Comparator.comparing(Product::getMsrp));
        }
        else if(sortBy.equals("PriceHL")) {
            listProducts.sort(Comparator.comparing(Product::getMsrp).reversed());
        }
        else {
            listProducts.sort(Comparator.comparing(Product::getMsrp));
        }
        
        listProductLine = sessionbeanProductLine.getAllProductLine();
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("productLine", productLine);
        request.setAttribute("listProducts", listProducts);
        request.setAttribute("listProductLine", listProductLine);
        
        if(role.equals("") || role.equals("user")) {
            if(productCode.equals("")) {
                request.getRequestDispatcher("./productList.jsp").include(request, response);
            }
            else {
                product = sessionbeanProduct.searchProduct(productCode);
                
                request.setAttribute("product", product);
                request.getRequestDispatcher("./product.jsp").include(request, response);
            }
        }
        else {
            if(operation.equals("New")) {
                product = new Product("","","","","",(short)0,BigDecimal.ZERO,BigDecimal.ZERO);
                product.setProductLine(sessionbeanProductLine.searchProductLine("Classic Cars"));
                
                request.setAttribute("operation", "New");
                request.setAttribute("product", product);
                request.getRequestDispatcher("ManageTools/mtProduct.jsp").include(request, response);
            }
            else if(productCode.equals("")) {
                request.getRequestDispatcher("ManageTools/mtProductList.jsp").include(request, response);
            }
            else {
                product = sessionbeanProduct.searchProduct(productCode);
                
                request.setAttribute("operation", "Update");
                request.setAttribute("product", product);
                request.getRequestDispatcher("ManageTools/mtProduct.jsp").include(request, response);
            }
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

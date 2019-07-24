/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JW
 */
public class ValidateManageLogic {
    
    public static String validateManageTrainer(HttpServletRequest request) {
        if (request.getParameter("UPDATE") != null && request.getParameter("UPDATE").equals("UPDATE")) {
            return "UPDATE";
        } else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
            return "DELETE";
        }
        return "ADD";
    }
//this method is used to notify a user that a record has been updated and to redirect to another page

    public static void navigateJS(PrintWriter out) {
        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"servletOfficeSearch\")");
        out.println("</SCRIPT>");
    }
    
    public static void navigateJS2(PrintWriter out) {
        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"servletEmployee\")");
        out.println("</SCRIPT>");
    }
    
}

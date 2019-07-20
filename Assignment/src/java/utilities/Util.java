/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author JK
 */
public class Util {
    
    public String getStatusClass(String status) {
        System.out.println("status = " + status);
        if(status.equals("Shipped")) {
            return "label-success";
        }
        else if(status.equals("Resolved")) {
            return "label-info";
        }
        else if(status.equals("Cancelled")) {
            return "label-default";
        }
        else if(status.equals("On Hold")) {
            return "label-warning";
        }
        else if(status.equals("Disputed")) {
            return "label-danger";
        }
        else if(status.equals("In Process")) {
            return "label-primary";
        }
        else {
            return "error";
        }
    }
}

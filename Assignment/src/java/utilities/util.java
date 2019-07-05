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
public class util {

    public String ConvertSqlDateFormat(String strSource) {
        strSource = strSource.trim();
        String year = strSource.substring(6, 10).toString();
        String month = strSource.substring(3, 5).toString();
        String day = strSource.substring(0, 2).toString();
        String strDate = year + "-" + month + "-" + day;
        return strDate.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Product;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import utilities.Cart;

/**
 *
 * @author JK
 */
@Stateful
@EJB(name="sessionbeanProduct", beanInterface=sessionbeanProduct.class)
public class sessionbeanCart  implements Cart {

    private sessionbeanProduct sessionbeanProduct;
    String username;
    Map<Product, Integer> contents;
    Integer totalQty = 0;
    BigDecimal totalAmount = BigDecimal.ZERO;
    
    public void initialize() {
        try {
            Context ctx = new InitialContext();
            sessionbeanProduct = (sessionbeanProduct)ctx.lookup("java:comp/env/sessionbeanProduct");
        }
        catch(NamingException ex) {
            ex.printStackTrace();
        }
        contents = new LinkedHashMap<Product, Integer>();
    }

    public void initialize(String username) {
        username = username;
        initialize();
    }

    public void addItem(String productCode, Integer qty) {
        Product product = sessionbeanProduct.searchProduct(productCode);
        if(contents.containsKey(product)) {
            Integer oldQty = contents.get(product);
            Integer newQty = oldQty + qty;
            contents.replace(product, newQty);
        }
        else {
            contents.put(product, qty);
        }
        totalQty += qty;
        totalAmount = totalAmount.add(product.getMsrp().multiply(new BigDecimal(qty)));
    }

    public void setValue(String productCode, Integer qty) {
        Product product = sessionbeanProduct.searchProduct(productCode);
        if(contents.containsKey(product)) {
            if(qty > 0) {
                Integer oldQty = contents.get(product);
                contents.replace(product, qty);
                totalQty -= oldQty;
                totalQty += qty;
                totalAmount = totalAmount.subtract(product.getMsrp().multiply(new BigDecimal(oldQty)));
                totalAmount = totalAmount.add(product.getMsrp().multiply(new BigDecimal(qty)));
            }
            else {
                Integer oldQty = contents.get(product);
                totalQty -= oldQty;
                totalAmount = totalAmount.subtract(product.getMsrp().multiply(new BigDecimal(oldQty)));
                contents.remove(product);
            }
        }
        else {
            contents.put(product, qty);
            totalQty += qty;
            totalAmount = totalAmount.add(product.getMsrp().multiply(new BigDecimal(qty)));
        }
    }

    public void removeItem(String productCode, Integer qty) {
        Product product = sessionbeanProduct.searchProduct(productCode);
        if(contents.containsKey(product)) {
            Integer oldQty = contents.get(product);
            Integer newQty = oldQty - qty;
            if(newQty > 0) {
                contents.replace(product, newQty);
            }
            else {
                contents.remove(product);
            }
            totalQty -= qty;
            totalAmount = totalAmount.subtract(product.getMsrp().multiply(new BigDecimal(qty)));
        }
    }

    public Map<Product, Integer> getContents() {
        return contents;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public BigDecimal getTotalAmount() {
        totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        return totalAmount;
    }

    @Remove
    public void remove() {
        contents = null;
    }
}

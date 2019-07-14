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
import entity.Product;
import java.util.Map;
import javax.ejb.Remote;

@Remote
public interface Cart {
    public void initialize();
    public void initialize(String username);
    public void addItem(String productCode, Integer qty);
    public void removeItem(String  productCode, Integer qty);
    public Map<Product, Integer> getContents();
    public void remove();
}
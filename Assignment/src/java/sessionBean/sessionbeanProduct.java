/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.util.logging.Logger;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanProduct {

    private final static Logger LOGGER = Logger.getLogger(sessionbeanProduct.class.getName());
    @EJB
    private sessionbeanProductLine sessionbeanProductLine;

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public List<Product> getAllProducts() {
        Query q = em.createNamedQuery("Product.findAll");

        return q.getResultList();
    }
    
    public Product searchProduct(String productCode) {
        Query q = em.createNamedQuery("Product.findByProductCode");
        
        q.setParameter("productCode", productCode);
        return (Product)q.getSingleResult();
    }
    
    public void updateProduct(Product product) {
        em.merge(product);
    }
    
    public void updateProduct(String productCode, String productLine, String productName, String productScale, String productVendor, String productDescription, String quantityInStock, String buyPrice, String MSRP) {
        Product t = new Product(productCode, productName, productScale, productVendor, productDescription, Short.parseShort(quantityInStock), new BigDecimal(buyPrice), new BigDecimal(MSRP));
        t.setProductLine(sessionbeanProductLine.searchProductLine(productLine));
        
        em.merge(t);
    }
    
    public void insertProduct(String productCode, String productLine, String productName, String productScale, String productVendor, String productDescription, String quantityInStock, String buyPrice, String MSRP) {
        Product t = new Product(productCode, productName, productScale, productVendor, productDescription, Short.parseShort(quantityInStock), new BigDecimal(buyPrice), new BigDecimal(MSRP));
        t.setProductLine(sessionbeanProductLine.searchProductLine(productLine));
        
        try {
            em.persist(t);
        }
        catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE,"Exception: ");
            e.getConstraintViolations().forEach(err->LOGGER.log(Level.SEVERE,err.toString()));
        }
    }
    
    public void deleteProduct(String productCode) {
        Product t = searchProduct(productCode);
        
        em.remove(t);
    }
}

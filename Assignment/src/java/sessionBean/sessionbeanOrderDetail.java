/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanOrderDetail {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Product> getTopSalesProduct() {
        Query q = em.createNamedQuery("Orderdetail.getTopSalesProduct");
        
        return q.setMaxResults(5).getResultList();
    }

    public Long getQuantitySold(Product product) {
        Query q = em.createNamedQuery("Orderdetail.getQuantitySoldByProduct");
        q.setParameter("product", product);
        
        return (Long)q.getSingleResult();
    }
}

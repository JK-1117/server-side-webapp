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
public class sessionbeanProduct {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public List<Product> getAllProducts() {
        Query q = em.createNamedQuery("Product.findAll");

        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

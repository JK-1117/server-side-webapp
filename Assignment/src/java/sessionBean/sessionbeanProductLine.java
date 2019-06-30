/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.ProductLine;
import javax.persistence.Query;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanProductLine {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<ProductLine> getAllProductLine() {
        Query q = em.createNamedQuery("ProductLine.findAll");
        
        return q.getResultList();
    }
    
    
}

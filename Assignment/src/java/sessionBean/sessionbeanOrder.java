/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Orders;
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
public class sessionbeanOrder {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Orders> getAllOrders() {
        Query q = em.createNamedQuery("Orders.findAll");
        
        return q.getResultList();
    }
    
    public Orders searchOrder(int orderNumber) {
        Query q = em.createNamedQuery("Orders.findByOrderNumber");
        
        q.setParameter("orderNumber", orderNumber);
        return (Orders)q.getSingleResult();
    }
}

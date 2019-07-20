/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Customer;
import entity.Orders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import servlet.servletOrdersUpdate;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanOrder {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    
    public List<Orders> getAllOrders() {
        Query q = em.createNamedQuery("Orders.findAll");
        
        return q.getResultList();
    }
    
    public Orders searchOrder(int orderNumber) {
        Query q = em.createNamedQuery("Orders.findByOrderNumber");
        
        q.setParameter("orderNumber", orderNumber);
        return (Orders)q.getSingleResult();
    }
    
    public void updateOrders(Orders orders) {
        em.merge(orders);
    }
    
    public Integer getOrderNumber() {
        Query q = em.createNamedQuery("Orders.getOrderNumber");
        
        return ((Integer)q.getSingleResult() + 1);
    }
    
    public void insertOrders(Integer orderNumber, Customer customerNumber, String requiredDate) {
        Orders orders = new Orders();
        
        orders.setOrderNumber(orderNumber);
        orders.setCustomerNumber(customerNumber);
        try {
            orders.setRequiredDate(new SimpleDateFormat("dd/MM/yyyy").parse(requiredDate));
        } catch (ParseException ex) {
            Logger.getLogger(servletOrdersUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        orders.setOrderDate(new Date());
        orders.setStatus("In Process");
        
        em.persist(orders);
    }
}

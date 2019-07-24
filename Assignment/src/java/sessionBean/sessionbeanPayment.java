/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Payment;
import entity.PaymentPK;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class sessionbeanPayment {
    
    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    
    public List<Payment> getAllPayment() {
        Query q = em.createNamedQuery("Payment.findAll");
        
        return q.getResultList();
    }
    
    public void insertPayment(int custno, String checkno,Date date,BigDecimal amount) {
        Payment py = new Payment();
        PaymentPK pk = new PaymentPK(custno,checkno);
//        py.setCustomer(custno);
        py.setPaymentPK(pk);
        py.setPaymentDate(date);
        py.setAmount(amount);
        em.persist(py);
    }
}

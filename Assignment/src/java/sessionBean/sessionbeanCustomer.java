/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Customer;
import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanCustomer {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    
    public List<Customer> getAllCustomer() {
        Query q = em.createNamedQuery("Customer.findAll");
        
        return q.getResultList();
    }

    public void addCustomer(int no, String name, String cfname, String clname, String phone, String addr1, String addr2, String city, String state, String postcode, String country, int salesrepno, BigDecimal crdlimit) {
        Customer c = new Customer();
        Employee e = em.find(Employee.class, salesrepno);
        c.setCustomerNumber(no);
        c.setCustomerName(name);
        c.setContactFirstName(cfname);
        c.setContactLastName(clname);
        c.setPhone(phone);
        c.setAddressLine1(addr1);
        c.setAddressLine2(addr2);
        c.setCity(city);
        c.setState(state);
        c.setPostalCode(postcode);
        c.setCountry(country);
        c.setSalesRepEmployeeNumber(e);
        c.setCreditLimit(crdlimit);
        em.persist(c);
    }

    public void updateCustomer(int no, String name, String cfname, String clname, String phone, String addr1, String addr2, String city, String state, String postcode, String country, int salesno, BigDecimal crdlimit) {
        Customer c = em.find(Customer.class, no);
        Employee e = em.find(Employee.class, salesno);
        c.setCustomerName(name);
        c.setContactFirstName(cfname);
        c.setContactLastName(clname);
        c.setPhone(phone);
        c.setAddressLine1(addr1);
        c.setAddressLine2(addr2);
        c.setCity(city);
        c.setState(state);
        c.setPostalCode(postcode);
        c.setCountry(country);
        c.setSalesRepEmployeeNumber(e);
        c.setCreditLimit(crdlimit);
        em.merge(c);
    }

    public void deleteCustomer(int cusNumber) {
        Customer c = em.find(Customer.class, cusNumber);
        if (c != null) {
            em.remove(c);
        }
    }
    
    public Customer searchCustomerByCustomerName(String customerName) {
        try {
            Query q = em.createNamedQuery("Customer.findByCustomerName");
            q.setParameter("customerName", customerName);
        
            return (Customer)q.getSingleResult();
        }
        catch(NoResultException ex) {
            return null;
        }
    }
}

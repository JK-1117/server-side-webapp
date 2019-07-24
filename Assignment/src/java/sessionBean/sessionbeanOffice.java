/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.Office;
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
public class sessionbeanOffice {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    
    public List<Office> getAllOffice() {
        Query q = em.createNamedQuery("Office.findAll");
        
        return q.getResultList();
    }

    public void addOffice(String officeCode, String city, String phone, String addrL1, String addrL2, String state, String country, String postalCode, String territory) {
        Office o = new Office();
        o.setOfficeCode(officeCode);
        o.setCity(city);
        o.setPhone(phone);
        o.setAddressLine1(addrL1);
        o.setAddressLine2(addrL2);
        o.setState(state);
        o.setCountry(country);
        o.setPostalCode(postalCode);
        o.setTerritory(territory);
        em.persist(o);
    }

    public void updateOffice(String officeCode, String city, String phone, String addrL1, String addrL2, String state, String country, String postalCode, String territory) {
//        Customer c = em.find(Customer.class, no);
        Office o = em.find(Office.class, officeCode);
        o.setOfficeCode(officeCode);
        o.setCity(city);
        o.setPhone(phone);
        o.setAddressLine1(addrL1);
        o.setAddressLine2(addrL2);
        o.setState(state);
        o.setCountry(country);
        o.setPostalCode(postalCode);
        o.setTerritory(territory);
        em.merge(o);
    }

    public void deleteOffice(String officeCode) {
        Office o = em.find(Office.class, officeCode);
        if (o != null) {
            em.remove(o);
        }
    }

    public Office searchOfficeByCode(String officeCode) {
        try {
            Query q = em.createNamedQuery("Office.findByOfficeCode");
            q.setParameter("officeCode", officeCode);

            return (Office) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}

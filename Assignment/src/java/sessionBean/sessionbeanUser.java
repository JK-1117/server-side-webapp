/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanUser {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public User login(String username, String password) {
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        
        User user = (User)q.getSingleResult();
        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

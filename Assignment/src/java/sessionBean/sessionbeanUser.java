/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import entity.User;
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
public class sessionbeanUser {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;
    
    public List<User> getAllUser() {
        Query q = em.createNamedQuery("User.findAll");
        
        return q.getResultList();
    }
    
    public User searchUser(String username) {
        try {
            Query q = em.createNamedQuery("User.findByUsername");
            q.setParameter("username", username);

            return (User)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User login(String username, String password) {
        User user = searchUser(username);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public void insertUser(String username, String password){
        User t = new User(username, password);
        
        em.persist(t);
    }
    
    public void updateUser(String username, String password) {
        User t = new User(username, password);
        
        em.merge(t);
    }
    
    public void deleteUser(String username) {
        User t = searchUser(username);
        
        em.remove(t);
    }
}

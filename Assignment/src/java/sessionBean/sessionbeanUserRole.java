/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entity.UserRole;
import entity.UserRolePK;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JK
 */
@Stateless
public class sessionbeanUserRole {

    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public String getUserRole(String username) {
        Query q = em.createNamedQuery("UserRole.findByUsername");
        
        q.setParameter("username", username);
        Iterator i = q.getResultList().iterator();
        
        while(i.hasNext()) {
            UserRolePK urk = ((UserRole)i.next()).getUserRolePK();
            
            if(urk.getRole().equals("admin")) {
                return "admin";
            }
            else if(urk.getRole().equals("staff")) {
                return "staff";
            }
            else if(urk.getRole().equals("user")) {
                return "user";
            }
        }
        return "";
    }
}

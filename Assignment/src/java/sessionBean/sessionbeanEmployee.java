/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

//import entity.Customer;
import entity.Employee;
import entity.Office;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JW
 */
@Stateless
public class sessionbeanEmployee {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "AssignmentPU")
    private EntityManager em;

    public List<Employee> getAllEmployee() {
        Query q = em.createNamedQuery("Employee.findAll");

        return q.getResultList();
    }
    

    public void addEmployee(int no, String fname, String lname, String extension, String email, Office officeCode, int reportsTo, String jobTitle) {
        Employee e = new Employee();
//        Employee e = em.find(Employee.class, salesrepno);
        e.setEmployeeNumber(no);
        e.setFirstName(fname);
        e.setLastName(lname);
        e.setExtension(extension);
        e.setEmail(email);
        e.setOfficeCode(officeCode);
        e.setReportsTo(e);
        e.setJobTitle(jobTitle);
        em.persist(e);
    }

    public void updateEmployee(int no, String fname, String lname, String extension, String email, Office officeCode, int reportsTo, String jobTitle) {
//        Customer c = em.find(Customer.class, no);
        Employee e = em.find(Employee.class, no);
        e.setEmployeeNumber(no);
        e.setFirstName(fname);
        e.setLastName(lname);
        e.setExtension(extension);
        e.setEmail(email);
        e.setOfficeCode(officeCode);
        e.setReportsTo(e);
        e.setJobTitle(jobTitle);
        em.merge(e);
    }

    public void deleteEmployee(int empNo) {
        Employee e = em.find(Employee.class, empNo);
        if (e != null) {
            em.remove(e);
        }
    }

    public Employee searchEmployeeByEmpNumber(int no) {
        try {
            Query q = em.createNamedQuery("Employee.findByEmployeeNumber");
            q.setParameter("employeeNumber", no);

            return (Employee) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}

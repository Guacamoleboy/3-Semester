package dk.project.dao;

import dk.project.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAO extends EntityManagerDAO {

    // Attributes
    private static EmployeeDAO employeeDAO;

    // ________________________________________________
    // Constructor

    public EmployeeDAO(EntityManager em) {
        super(em);
    }

    // ________________________________________________
    // Singleton

    public static EmployeeDAO getInstance(EntityManager em) {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO(em);
        }
        return employeeDAO;
    }

    // ________________________________________________

    public List<Employee> getAllEmployees(){
        String JPQL = "SELECT x FROM Employee x";
        TypedQuery<Employee> query = em.createQuery(JPQL, Employee.class);
        return query.getResultList();
    }

    // ________________________________________________

    public List<Employee> getEmployeeBySalery(double salary){
        String JPQL = "SELECT x FROM Employee x WHERE x.salary >= :salary";
        TypedQuery<Employee> query = em.createQuery(JPQL, Employee.class);
        query.setParameter("salary", salary);
        return query.getResultList();
    }

    // ________________________________________________

    public List<Employee> getEmployeeByDepartment(String department){
        String JPQL = "SELECT x FROM Employee x WHERE x.department = :department";
        TypedQuery<Employee> query = em.createQuery(JPQL, Employee.class);
        query.setParameter("department", department);
        return query.getResultList();
    }

    // ________________________________________________

    public List<Employee> getEmployeeFirstnameByLetter(String letter){
        String JPQL = "SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(:letter)";
        TypedQuery<Employee> query = em.createQuery(JPQL, Employee.class);
        query.setParameter("letter", letter + "%");
        return query.getResultList();
    }

    // ________________________________________________

    public Employee updateEmployeeBySalary(int employeeId, double newSalary){
        Employee employee = em.find(Employee.class, employeeId);
        if (employee != null){
            try {
                em.getTransaction().begin();
                employee.setSalary(newSalary);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
        return employee;
    }

    // ________________________________________________

    public int updateEmployeeDepartment(int employeeId, String newDepartment){
        String JPQL = "UPDATE Employee x SET x.department = ?1 WHERE x.id = ?2";
        int updatedCount = 0;

        try {
            em.getTransaction().begin();
            Query query = em.createQuery(JPQL);
            query.setParameter(1, newDepartment);
            query.setParameter(2, employeeId);
            updatedCount = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        return updatedCount;
    }

    // ________________________________________________

    public Employee findById(int employeeId) {
        return em.find(Employee.class, employeeId);
    }

    // ________________________________________________

    public Double getAverageSalary() {
        String JPQL = "SELECT AVG(x.salary) FROM Employee x";
        TypedQuery<Double> query = em.createQuery(JPQL, Double.class);
        return query.getSingleResult();
    }

    // ________________________________________________

    public Double getTotalSalary() {
        String JPQL = "SELECT SUM(x.salary) FROM Employee x";
        TypedQuery<Double> query = em.createQuery(JPQL, Double.class);
        return query.getSingleResult();
    }

}
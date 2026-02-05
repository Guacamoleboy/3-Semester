package dk.project.service;

import dk.project.dao.EmployeeDAO;
import dk.project.entity.Employee;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EmployeeService {

    // Attributes
    private final EmployeeDAO employeeDAO;

    // ________________________________________________________
    // Singleton setup

    public EmployeeService(EntityManager em){
        this.employeeDAO = EmployeeDAO.getInstance(em);
    }

    // ________________________________________________________

    public List<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }

    // ________________________________________________________

    public List<Employee> getEmployeeBySalary(double salary){
        return employeeDAO.getEmployeeBySalery(salary);
    }

    // ________________________________________________________

    public List<Employee> getEmployeeByDepartment(String department){
        return employeeDAO.getEmployeeByDepartment(department);
    }

    // ________________________________________________________

    public List<Employee> getEmployeeFirstnameByLetter(String letter){
        return employeeDAO.getEmployeeFirstnameByLetter(letter);
    }

    // ________________________________________________________

    public Employee updateEmployeeBySalary(int employeeId, double newSalary){
        return employeeDAO.updateEmployeeBySalary(employeeId, newSalary);
    }

    // ________________________________________________________

    public Employee getEmployeeById(int employeeId) {
        return employeeDAO.findById(employeeId);
    }

    // ________________________________________________________

    public int updateEmployeeDepartment(int employeeId, String newDepartment){
        return employeeDAO.updateEmployeeDepartment(1, newDepartment);
    }

    // ________________________________________________________

    public Double getAverageSalary(){
        return employeeDAO.getAverageSalary();
    }

    // ________________________________________________________

    public Double getTotalSalary() {
        return employeeDAO.getTotalSalary();
    }

}
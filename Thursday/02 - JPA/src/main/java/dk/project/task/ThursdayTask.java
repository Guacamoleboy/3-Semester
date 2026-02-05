package dk.project.task;

import dk.project.entity.Employee;
import dk.project.service.EmployeeService;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ThursdayTask {

    // Attributes
    private EmployeeService employeeService;
    private EntityManager em;

    // ______________________________________________________________

    public ThursdayTask(EmployeeService employeeService, EntityManager em) {
        this.employeeService = employeeService;
        this.em = em;
    }

    // ______________________________________________________________

    public void run() {

        // Task 1
        showAllEmployees();
        // Task 2
        showEmployeeBySalary();
        // Task 3
        showEmployeeByDepartment();
        // Task 4
        showEmployeeByLetter();
        // Task 5
        updateSalaryTaskFour(1, 55000);
        // Task 6
        updateEmployeeDepartment(1, "Finance");
        // Task 7
        showAverageSalary();
        // Task 8
        showTotalSalary();

    }

    // ______________________________________________________________

    public void showAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("\n############ TASK 1 ############");
        for (Employee e : employees) {
            System.out.println(e.getFirstName());
        }
    }

    // ______________________________________________________________

    public void showEmployeeBySalary(){
        List<Employee> employees = employeeService.getEmployeeBySalary(65000);
        System.out.println("\n############ TASK 2 ############");
        for (Employee e : employees){
            System.out.println(e.getFirstName() + " has " + e.getSalary());
        }
    }

    // ______________________________________________________________

    public void showEmployeeByDepartment(){
        List<Employee> employees = employeeService.getEmployeeByDepartment("HR");
        System.out.println("\n############ TASK 3 ############");
        for (Employee e : employees){
            System.out.println(e.getFirstName() + " works in department: " + e.getDepartment());
        }
    }

    // ______________________________________________________________

    public void showEmployeeByLetter(){
        List<Employee> employees = employeeService.getEmployeeFirstnameByLetter("j");
        System.out.println("\n############ TASK 4 ############");
        for (Employee e : employees){
            System.out.println(e.getFirstName() + " starts with 'J' ");
        }
    }

    // ______________________________________________________________

    public void updateSalaryTaskFour(int employeeId, double newSalary){
        System.out.println("\n############ TASK 5 ############");

        Employee employeeBefore = employeeService.getEmployeeById(employeeId);
        if (employeeBefore != null){
            System.out.println(employeeBefore.getFirstName() + " has " + employeeBefore.getSalary() + " before update!");
        }

        Employee updatedEmployee = employeeService.updateEmployeeBySalary(employeeId, newSalary);
        if (updatedEmployee != null){
            System.out.println(updatedEmployee.getFirstName() + " now has " + updatedEmployee.getSalary());
        }

    }

    // ______________________________________________________________

    public void updateEmployeeDepartment(int employeeId, String newDepartment){
        System.out.println("\n############ TASK 6 ############");

        // Before
        Employee employeeBefore = employeeService.getEmployeeById(employeeId);
        if (employeeBefore != null){
            System.out.println(employeeBefore.getFirstName() + " worked in " + employeeBefore.getDepartment() + " before update!");
        }

        // Update
        employeeService.updateEmployeeDepartment(employeeId, newDepartment);

        // After
        em.clear();
        Employee employeeAfter = employeeService.getEmployeeById(employeeId);
        if (employeeAfter != null){
            System.out.println(employeeAfter.getFirstName() + " now works in " + employeeAfter.getDepartment());
        }
    }

    // ______________________________________________________________

    public void showAverageSalary(){
        System.out.println("\n############ TASK 7 ############");
        double average = employeeService.getAverageSalary();
        System.out.println("Average Salary across all departments: " + average);
    }

    // ______________________________________________________________

    public void showTotalSalary(){
        System.out.println("\n############ TASK 8 ############");
        double total = employeeService.getTotalSalary();
        System.out.println("Total Salary across all departments: " + total);
    }

}
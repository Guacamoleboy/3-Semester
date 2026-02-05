package dk.project;

import dk.project.service.EmployeeService;
import dk.project.task.ThursdayTask;
import dk.project.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class Main {

    // Attributes

    // _____________________________________________________

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            EmployeeService employeeService = new EmployeeService(em);
            ThursdayTask task = new ThursdayTask(employeeService, em);
            task.run();
        }
        finally {
            em.close();
            JpaUtil.close();
        }

    }
}
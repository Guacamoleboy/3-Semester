package dk.project;

import dk.project.task.TaskOne;
import dk.project.task.TaskThree;
import dk.project.task.TaskTwo;
import dk.project.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class Main {

    // Attributes
    private static final TaskOne taskOne = new TaskOne();
    private static final EntityManager em = JpaUtil.getEntityManager();

    // _____________________________________________________

    public static void main(String[] args) {

        // Run Tasks
        try {
            new TaskOne().runTaskOne();                                             // Task #1
            new TaskTwo().runTaskTwo(em);                                           // Task #2
            new TaskThree().runTaskThree(em);                                       // Unicorn
        }
        finally {
            em.close();                                                             // DB Connection Close
            JpaUtil.close();                                                        // JPA EntityManagerFactory Close
        }

    }
}
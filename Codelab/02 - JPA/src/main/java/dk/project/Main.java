package dk.project;

import dk.project.task.Codelab;
import dk.project.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class Main {

    // Attributes
    private static final Codelab codelab = new Codelab();
    private static final EntityManager em = JpaUtil.getEntityManager();

    // _____________________________________________________

    public static void main(String[] args) {

        // Run Tasks
        try {
            new Codelab().runCodelab(em);                                           // Codelab
        }
        finally {
            em.close();                                                             // DB Connection Close
            JpaUtil.close();                                                        // JPA EntityManagerFactory Close
        }

    }
}
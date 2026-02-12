package dk.project;

import dk.project.config.HibernateConfig;
import dk.project.util.Populator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    // Attributes

    // _______________________________________________

    public static void main(String[] args) {

        // Initial
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // Try-with-resource
        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();
            Populator.populate(em);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }

    }

}
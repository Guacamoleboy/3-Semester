package dk.project.service;

import dk.project.dao.UnicornDAO;
import dk.project.entity.Unicorn;
import jakarta.persistence.EntityManager;

public class UnicornService {

    // Attributes
    private final EntityManager em;
    private final UnicornDAO unicornDAO;

    // _______________________________________________________________

    public UnicornService(EntityManager em){
        this.em = em;
        this.unicornDAO = new UnicornDAO(em);
    }

    // _______________________________________________________________

    public Unicorn save(Unicorn u) {
        em.getTransaction().begin();
        unicornDAO.save(u);
        em.getTransaction().commit();
        return u;
    }

    // _______________________________________________________________

    public Unicorn update(Unicorn u) {
        em.getTransaction().begin();
        Unicorn updated = unicornDAO.update(u);
        em.getTransaction().commit();
        return updated;
    }

    // _______________________________________________________________

    public void delete(int id) {
        em.getTransaction().begin();
        unicornDAO.delete(id);
        em.getTransaction().commit();
    }

}
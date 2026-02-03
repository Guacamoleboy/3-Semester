package dk.project.dao;

import dk.project.entity.Unicorn;
import jakarta.persistence.EntityManager;

public class UnicornDAO {

    // Attributes
    private final EntityManager em;

    // _____________________________________________

    public UnicornDAO(EntityManager em){
        this.em = em;
    }

    // _____________________________________________

    public Unicorn findById(int id){
        Unicorn foundUnicorn = em.find(Unicorn.class, id);
        return foundUnicorn;
    }

    // _____________________________________________

    public void delete(int id) {
        Unicorn unicorn = em.find(Unicorn.class, id);
        if (unicorn != null) {
            em.remove(unicorn);
        }
    }

    // _____________________________________________

    public Unicorn update(Unicorn unicorn){
        return em.merge(unicorn);
    }

    // _____________________________________________

    public Unicorn save(Unicorn unicorn){
        em.persist(unicorn);
        return unicorn;
    }

}

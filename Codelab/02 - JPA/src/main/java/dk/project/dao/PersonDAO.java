package dk.project.dao;

import dk.project.entity.Person;
import jakarta.persistence.EntityManager;

public class PersonDAO {

    // Attributes
    private final EntityManager em;

    // _________________________________________________

    public PersonDAO(EntityManager em){
        this.em = em;
    }

    // _________________________________________________

    public void createPerson(Person person){
        em.persist(person);
    }

}

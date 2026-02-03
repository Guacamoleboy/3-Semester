package dk.project.service;

import dk.project.dao.PersonDAO;
import dk.project.entity.Person;
import jakarta.persistence.EntityManager;

public class PersonService {

    // Attributes
    private final EntityManager em;
    private final PersonDAO personDAO;

    // __________________________________________________

    public PersonService(EntityManager em){
        this.em = em;
        this.personDAO = new PersonDAO(em);
    }

    // __________________________________________________

    public void createPerson(Person person) {

        // Validation
        if(person.getAge() < 0 || person.getAge() > 110) {
            throw new IllegalArgumentException("Ugyldig alder");
        }

        try {
            em.getTransaction().begin();
            personDAO.createPerson(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Fejlkode: " + e);
        }
    }

}

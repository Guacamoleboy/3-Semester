package app.service;

import app.dao.PersonDAO;
import app.entities.Person;
import jakarta.persistence.EntityManager;

public class PersonService {

    // Attributes
    private final PersonDAO personDAO;

    // _____________________________________

    public PersonService(EntityManager em){
        this.personDAO = new PersonDAO(em);
    }

    // _____________________________________

    public void createPerson(Person person){
        validateNotEmpty(person, "Person.person");
        personDAO.create(person);
    }

    // _____________________________________

    public void updatePerson(Person person){
        validateNotEmpty(person, "Person.person");
        personDAO.update(person);
    }

    // _____________________________________

    public void deletePerson(int id){
        validateNotEmpty(id, "Person.id");
        personDAO.deleteById(id);
    }

    // _____________________________________

    public void deletePerson(Person person){
        validateNotEmpty(person, "Person.person");
        personDAO.delete(person);
    }

    // _____________________________________

    public void deleteAll(){
        personDAO.deleteAll();
    }

    // ______________________________________________
    // Validation

    private void validateNotEmpty(Object paramValue, String fieldName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (paramValue instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }


}

package app.dao;

import app.entities.Person;
import jakarta.persistence.EntityManager;

public class PersonDAO extends EntityManagerDAO {

    // Attributes

    // _______________________________________

    public PersonDAO(EntityManager em){
        super(em, Person.class);
    }

    // _______________________________________

    public void createPerson(Person person){
        create(person);
    }

    // _______________________________________

    public void updatePerson(Person person){
        update(person);
    }

    // _______________________________________

    public void deleteById(int id){
        deleteById(id);
    }

    // _______________________________________

    public void deletePerson(Person person){
        delete(person);
    }

    // _______________________________________

    public void deleteAll(){
        deleteAll();
    }


}

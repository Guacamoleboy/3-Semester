package app.dao;

import app.entities.PersonDetail;
import jakarta.persistence.EntityManager;

public class PersonDetailDAO extends EntityManagerDAO {

    // Attributes

    // _____________________________________________

    public PersonDetailDAO(EntityManager em){
        super(em, PersonDetail.class);
    }

    // _____________________________________________

    public void createPersonDetail(PersonDetail personDetail){
        create(personDetail);
    }

    // _____________________________________________

    public void updatePersonDetail(PersonDetail personDetail){
        update(personDetail);
    }

    // _____________________________________________

    public void deletePersonDetail(PersonDetail personDetail){
        delete(personDetail);
    }

    // _____________________________________________

    public void deletePersonDetail(int id){
        deleteById(id);
    }

}
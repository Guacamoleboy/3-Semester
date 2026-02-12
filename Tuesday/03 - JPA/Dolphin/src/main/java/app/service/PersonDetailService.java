package app.service;

import app.dao.PersonDetailDAO;
import app.entities.PersonDetail;
import jakarta.persistence.EntityManager;

public class PersonDetailService {

    // Attributes
    private final PersonDetailDAO personDetailDAO;

    // ______________________________________________

    public PersonDetailService(EntityManager em){
        this.personDetailDAO = new PersonDetailDAO(em);
    }

    // ______________________________________________

    public void createPersonDetail(PersonDetail personDetail){
        validateNotEmpty(personDetail, "PersonDetail.personDetail");
        personDetailDAO.create(personDetail);
    }

    // ______________________________________________

    public void updatePersonDetail(PersonDetail personDetail){
        validateNotEmpty(personDetail, "PersonDetail.personDetail");
        personDetailDAO.update(personDetail);
    }

    // ______________________________________________

    public void deletePersonDetail(PersonDetail personDetail){
        validateNotEmpty(personDetail, "PersonDetail.personDetail");
        personDetailDAO.delete(personDetail);
    }

    // ______________________________________________

    public void deletePersonDetail(int id){
        validateNotEmpty(id, "PersonDetail.id");
        personDetailDAO.deleteById(id);
    }

    // ______________________________________________

    public void deleteAllPersonDetail(){
        personDetailDAO.deleteAll();
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
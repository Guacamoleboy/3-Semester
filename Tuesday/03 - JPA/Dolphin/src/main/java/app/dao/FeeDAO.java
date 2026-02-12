package app.dao;

import app.entities.Fee;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FeeDAO extends EntityManagerDAO {

    // Attributes

    // _____________________________________________

    public FeeDAO(EntityManager em){
        super(em, Fee.class);
    }

    // _____________________________________________

    public void createFee(Fee fee){
        create(fee);
    }

    // _____________________________________________

    public void updateFee(Fee fee){
        update(fee);
    }

    // _____________________________________________

    public void deleteFee(int id){
        deleteById(id);
    }

    // _____________________________________________

    public void deleteFee(Fee fee){
        delete(fee);
    }

    // _____________________________________________

    public void deleteAllFees(){
        deleteAll();
    }

    // _____________________________________________

    public void getFeeById(int id){
        Object o = getById(id);
    }

    // _____________________________________________

    public void getAllFee(){
        List<Object> o = getAll();
    }

    // _____________________________________________

    public List<Fee> getAllFees(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Fee x";
            return em.createQuery(JPQL, Fee.class)
            .getResultList();
        });
    }

}
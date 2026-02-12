package app.service;

import app.dao.FeeDAO;
import app.entities.Fee;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FeeService {

    // Attributes
    private final FeeDAO feeDAO;

    // _____________________________________________

    public FeeService(EntityManager em){
        this.feeDAO = new FeeDAO(em);
    }

    // _____________________________________________

    public void createFee(Fee fee){
        feeDAO.createFee(fee);
    }

    // _____________________________________________

    public void updateFee(Fee fee){
        feeDAO.updateFee(fee);
    }

    // _____________________________________________

    public void deleteFee(int id){
        validateNotEmpty(id, "Fee.id");
        feeDAO.deleteFee(id);
    }

    // _____________________________________________

    public void deleteFee(Fee fee){
        validateNotEmpty(fee, "Fee.fee");
        feeDAO.delete(fee);
    }

    // ______________________________________________

    public void deleteAllFees(){
        feeDAO.deleteAllFees();
    }

    // ______________________________________________

    public Fee getFeeById(int id){
        return feeDAO.getFeeById(id);
    }

    // ______________________________________________

    public List<Fee> getAllFees(){
        List<Fee> fee = feeDAO.getAllFees();
        return fee;
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
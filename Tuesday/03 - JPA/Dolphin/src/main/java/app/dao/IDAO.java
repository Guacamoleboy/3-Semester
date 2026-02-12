package app.dao;

import java.util.List;

public interface IDAO {

    // Contract for all DAO classes + Ease of use for Unit Tests

    // Attributes
    void create(Object o);
    void update(Object o);
    void delete(Object o);
    List<Object> getAll();
    Object getById(int id);
    void deleteById(int id);
    void deleteAll();

}
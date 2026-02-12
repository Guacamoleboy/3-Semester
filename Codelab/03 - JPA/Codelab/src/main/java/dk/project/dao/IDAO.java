package dk.project.dao;

import java.util.List;

public interface IDAO <T> {

    // Contract for all DAO classes + Ease of use for Unit Tests

    // Attributes
    T create(T entity);
    T update(T entity);
    T getById(int id);
    List<T> getAll();
    T delete(T entity);
    T deleteById(int id);
    void deleteAll();

}
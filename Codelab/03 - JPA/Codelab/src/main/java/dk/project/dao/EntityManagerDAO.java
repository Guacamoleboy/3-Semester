package dk.project.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

public class EntityManagerDAO<T> implements IDAO<T> {

    // Attributes
    protected EntityManager em;
    protected Class<T> entityClass;

    // ________________________________________________________

    protected EntityManagerDAO(EntityManager em, Class<T> entityClass){
        this.em = em;
        this.entityClass = entityClass;
    }

    // ________________________________________________________

    @Override
    public T create(T t) {
        return executeQuery(() -> {
            em.persist(t);
            return t;
        });
    }

    // ________________________________________________________

    @Override
    public T update(T t) {
        return executeQuery(() -> em.merge(t));
    }

    // ________________________________________________________

    @Override
    public T delete(T t) {
        return executeQuery(() -> {
            em.remove(t);
            return t;
        });
    }

    // ________________________________________________________

    @Override
    public T deleteById(int id) {
        return executeQuery(() -> {
            T t = em.find(entityClass, id);
            if (t != null) em.remove(t);
            return t;
        });
    }

    // ________________________________________________________

    @Override
    public T getById(int id) {
        return executeQuery(() -> em.find(entityClass, id));
    }

    // ________________________________________________________

    @Override
    public List<T> getAll() {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM " + entityClass.getSimpleName() + " x";
            return em.createQuery(JPQL, entityClass)
            .getResultList();
        });
    }

    // ________________________________________________________

    @Override
    public void deleteAll() {
        executeQuery(() -> {
            String JPQL = "DELETE FROM " + entityClass.getSimpleName();
            em.createQuery(JPQL).executeUpdate();
        });
    }

    // ________________________________________________________
    // Unknown object type query execute using Supplier from java.util

    protected <R> R executeQuery(Supplier<R> query) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            R result = query.get();
            em.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    // ________________________________________________________
    // void execute

    protected void executeQuery(Runnable task) {
        executeQuery(() -> {
            task.run();
            return null;
        });
    }

}
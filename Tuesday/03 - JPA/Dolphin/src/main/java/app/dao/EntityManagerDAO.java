//
//  Protected to limit usage to only subclasses (Direct extend from this DAO)
//  - Guac
//

package app.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

public class EntityManagerDAO implements IDAO {

    // Attributes
    protected EntityManager em;
    protected Class<?> entityClass;

    // ________________________________________________________

    protected EntityManagerDAO(EntityManager em, Class<?> entityClass){
        this.em = em;
        this.entityClass = entityClass;
    }

    // ________________________________________________________

    @Override
    public void create(Object o){
        executeQuery(() ->
            em.persist(o)
        );
    }

    // ________________________________________________________

    @Override
    public void update(Object o){
        executeQuery(() ->
            em.merge(o)
        );
    }

    // ________________________________________________________

    @Override
    public void delete(Object o) {
        executeQuery(() -> {
            em.remove(o);
        });
    }

    // ________________________________________________________

    @Override
    public void deleteById(int id) {
        executeQuery(() -> {
            Object o = em.find(entityClass, id);
            if (o != null) {
                em.remove(o);
            }
        });
    }

    // ________________________________________________________

    @Override
    public Object getById(int id){
        return executeQuery(() -> em.find(entityClass, id));
    }

    // ________________________________________________________
    // Type casted getAll to Object

    @Override
    public List<Object> getAll(){
        String entityToString = entityClass.getSimpleName();
        return (List<Object>) executeQuery(() -> {
            String JPQL = "SELECT x FROM " + entityToString + " x";
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

    protected <T> T executeQuery(Supplier<T> query) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            T result = query.get();
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
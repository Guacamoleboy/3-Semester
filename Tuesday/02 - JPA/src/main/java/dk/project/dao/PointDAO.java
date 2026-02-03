package dk.project.dao;

import dk.project.entity.Point;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PointDAO {

    // Attributes
    private final EntityManager em;

    // ___________________________________________________

    public PointDAO(EntityManager em){
        this.em = em;
    }

    // ___________________________________________________

    public long getPointCount() {
        return em.createQuery
        ("SELECT COUNT(p) FROM Point p", Long.class).getSingleResult();
    }

    // __________________________________________________________________

    public double getAverageX() {
        return em.createQuery
        ("SELECT AVG(p.x) FROM Point p", Double.class).getSingleResult();
    }

    // __________________________________________________________________

    public List<Point> getAllPoints() {
        TypedQuery<Point> query = em.createQuery
        ("SELECT p FROM Point p", Point.class);
        return query.getResultList();
    }

    // __________________________________________________________________

    public void save(Point point) {
        em.persist(point);
    }

    // __________________________________________________________________

    public void deleteAll() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Point").executeUpdate();
        em.getTransaction().commit();
    }

}
package dk.project.service;

import dk.project.dao.PointDAO;
import dk.project.entity.Point;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PointService {

    // Attributes
    private final EntityManager em;
    private final PointDAO dao;

    // __________________________________________________________________

    public PointService(EntityManager em) {
        this.em = em;
        this.dao = new PointDAO(em);
    }

    // __________________________________________________________________

    public void createPoints(int amount) {
        em.getTransaction().begin();

        for (int i = 0; i < amount; i++) {
            dao.save(new Point(i, i));
        }

        em.getTransaction().commit();
    }

    // __________________________________________________________________

    public long getPointCount() {
        return dao.getPointCount();
    }

    // __________________________________________________________________

    public double getAverageX() {
        return dao.getAverageX();
    }

    // __________________________________________________________________

    public List<Point> getAllPoints() {
        return dao.getAllPoints();
    }

}
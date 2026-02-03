package dk.project.dao;

import dk.project.entity.Course;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseDAO {

    // Attributes
    private final EntityManager em;

    // _______________________________________________

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    // _______________________________________________

    public void create(Course course) {
        em.persist(course);
    }

    // _______________________________________________

    public Course find(int id) {
        return em.find(Course.class, id);
    }

    // _______________________________________________

    public void delete(int id) {
        Course course = find(id);
        if (course != null) {
            em.remove(course);
        }
    }

    // _______________________________________________

    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

}
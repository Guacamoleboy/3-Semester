package dk.project.dao;

import dk.project.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAO {

    // Attributes
    private final EntityManager em;

    // _______________________________________________

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    // _______________________________________________

    public void create(Student student) {
        em.persist(student);
    }

    // _______________________________________________

    public Student find(int id) {
        return em.find(Student.class, id);
    }

    // _______________________________________________

    public void delete(int id) {
        Student student = find(id);
        if (student != null) {
            em.remove(student);
        }
    }

    // _______________________________________________

    public List<Student> findAll() {
        return em.createQuery
        ("SELECT s FROM Student s", Student.class).getResultList();
    }

    // _______________________________________________

    public List<Student> findByCourseId(int courseId) {
        TypedQuery<Student> query = em.createQuery
        ("SELECT s FROM Student s WHERE s.course.id = :id", Student.class);
        query.setParameter("id", courseId);
        return query.getResultList();
    }

}
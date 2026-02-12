package dk.project.dao;

import dk.project.entity.Course;
import jakarta.persistence.EntityManager;

public class CourseDAO extends EntityManagerDAO<Course> {

    // Attributes

    // ____________________________________________________

    public CourseDAO(EntityManager em){
        super(em, Course.class);
    }

    // ____________________________________________________

}
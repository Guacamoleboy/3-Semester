package dk.project.dao;

import dk.project.entity.Teacher;
import jakarta.persistence.EntityManager;

public class TeacherDAO extends EntityManagerDAO<Teacher> {

    // Attributes

    // ____________________________________________________

    public TeacherDAO(EntityManager em){
        super(em, Teacher.class);
    }

    // ____________________________________________________

}
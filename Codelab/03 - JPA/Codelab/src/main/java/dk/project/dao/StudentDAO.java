package dk.project.dao;

import dk.project.entity.Student;
import jakarta.persistence.EntityManager;

public class StudentDAO extends EntityManagerDAO<Student>{

    // Attributes

    // ____________________________________________________

    public StudentDAO(EntityManager em){
        super(em, Student.class);
    }

    // ____________________________________________________



}

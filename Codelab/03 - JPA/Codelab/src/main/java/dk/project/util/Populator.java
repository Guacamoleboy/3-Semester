package dk.project.util;

import dk.project.dao.*;
import dk.project.entity.*;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;

public class Populator {

    // Attributes

    // _________________________________________________

    public static void populate(EntityManager em) {

        // Initial
        CourseDAO courseDAO = new CourseDAO(em);
        StudentDAO studentDAO = new StudentDAO(em);
        TeacherDAO teacherDAO = new TeacherDAO(em);

        // Courses
        Course course1 = Course.builder()
                .courseName(CourseName.MATH)
                .description("Mathematics 101")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(6))
                .build();

        Course course2 = Course.builder()
                .courseName(CourseName.SCIENCE)
                .description("Science Fundamentals")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(6))
                .build();

        courseDAO.create(course1);
        courseDAO.create(course2);

        // Students
        Student student1 = Student.builder()
                .name("Alice")
                .email("alice@mail.com")
                .build();

        Student student2 = Student.builder()
                .name("Bob")
                .email("bob@mail.com")
                .build();

        studentDAO.create(student1);
        studentDAO.create(student2);

        // Teachers
        Teacher teacher1 = Teacher.builder()
                .name("Professor X")
                .email("prof.x@mail.com")
                .zoom("https://zoomlink.com/link1")
                .build();

        Teacher teacher2 = Teacher.builder()
                .name("Dr. Y")
                .email("dr.y@mail.com")
                .zoom("https://zoomlink.com/link2")
                .build();

        teacherDAO.create(teacher1);
        teacherDAO.create(teacher2);

        // Summary
        System.out.println("Database populated successfully!");

    }

}
package dk.project.task;

import dk.project.entity.Course;
import dk.project.entity.Student;
import dk.project.service.SchoolService;
import jakarta.persistence.EntityManager;
import java.util.List;

public class Codelab {

    // Attributes

    // ________________________________________________

    public void runCodelab(EntityManager em) {

        // Initial Codelab setup
        SchoolService schoolService = new SchoolService(em);

        System.out.println("########## CODELAB START ##########");

        // Create Course
        Course javaCourse = new Course();
        javaCourse.setCourseName("JAVA - JPA");
        javaCourse.setTeacher("Jesper");
        schoolService.createCourse(javaCourse);

        // Create Course
        Course cyberCourse = new Course();
        cyberCourse.setCourseName("Cybersecurity");
        cyberCourse.setTeacher("Thomas");
        schoolService.createCourse(cyberCourse);

        // Enroll Student
        Student student1 = new Student();
        student1.setName("Diddy");
        student1.setEmail("diddy@party.com");
        schoolService.enrollStudent(student1, javaCourse.getId());

        // Enroll Student
        Student student2 = new Student();
        student2.setName("Kanye");
        student2.setEmail("ye@west.com");
        schoolService.enrollStudent(student2, javaCourse.getId());

        // List Courses + Students
        System.out.println("Kurser: " + schoolService.getAllCourses().size());
        System.out.println("Studerende: " + schoolService.getAllStudents().size());

        // Update Diddy
        student1.setEmail("diddy@epstein.com");
        schoolService.updateStudent(student1);

        // List Students for Course after Update
        List<Student> javaStudentsBefore = schoolService.getStudentsInCourse(javaCourse.getId());
        System.out.println("Studerende på " + javaCourse.getCourseName() + " | " + javaStudentsBefore.size());
        for (Student student : javaStudentsBefore) {
            System.out.println("- " + student.getName());
        }

        // Update Course for Kanye
        schoolService.updateCourseStudent(student2.getId(), cyberCourse.getId());
        System.out.println(student2.getName() + " er nu skiftet til " + cyberCourse.getCourseName());

        // List Students for Course after Update
        List<Student> javaStudentsAfter = schoolService.getStudentsInCourse(javaCourse.getId());
        System.out.println("Studerende på " + javaCourse.getCourseName() + " | " + javaStudentsAfter.size() + " efter skift");
        for (Student student : javaStudentsAfter) {
            System.out.println("- " + student.getName());
        }

        // Get Course for a specific Student
        Course foundCourse = schoolService.getCourseForStudent(student2.getId());
        if (foundCourse != null) {
            System.out.println(student2.getName() + " er tilmeldt: " + foundCourse.getCourseName());
        }

        // Delete Diddy :dead-flower:
        schoolService.deleteStudent(student1.getId());
        System.out.println("Studerende efter sletning af Diddy: " + schoolService.getAllStudents().size());

        // Delete Course
        schoolService.deleteCourse(cyberCourse.getId());
        System.out.println("Kurser tilbage efter sletning af én: " + schoolService.getAllCourses().size());

        // End
        System.out.println("########## CODELAB END ##########");

    }

}
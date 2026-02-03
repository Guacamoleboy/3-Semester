package dk.project.service;

import dk.project.dao.CourseDAO;
import dk.project.dao.StudentDAO;
import dk.project.entity.Course;
import dk.project.entity.Student;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class SchoolService {

    // Attributes
    private final EntityManager em;
    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;

    // _________________________________________________________________

    public SchoolService(EntityManager em) {
        this.em = em;
        this.studentDAO = new StudentDAO(em);
        this.courseDAO = new CourseDAO(em);
    }

    // _________________________________________________________________

    public void createStudent(Student student) {
        try {
            em.getTransaction().begin();
            studentDAO.create(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public void updateStudent(Student student) {
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public void deleteStudent(int id) {
        try {
            em.getTransaction().begin();
            Student student = studentDAO.find(id);
            if (student != null) {
                if (student.getCourse() != null) {
                    student.getCourse().getStudents().remove(student);
                }
                em.remove(student);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public void createCourse(Course course) {
        try {
            em.getTransaction().begin();
            courseDAO.create(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Fejl ved oprettelse af kursus: " + e.getMessage());
        }
    }

    // _________________________________________________________________

    public void updateCourse(Course course) {
        try {
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public void deleteCourse(int id) {
        try {
            em.getTransaction().begin();

            List<Student> students = studentDAO.findByCourseId(id);

            // Reset #DEBUG
            for (Student student : students) {
                student.setCourse(null);
            }
            courseDAO.delete(id);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    // _________________________________________________________________

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    // _________________________________________________________________

    public Course getCourseForStudent(int studentId) {
        Student student = studentDAO.find(studentId);
        return (student != null) ? student.getCourse() : null;
    }

    // _________________________________________________________________

    public List<Student> getStudentsInCourse(int courseId) {
        Course course = courseDAO.find(courseId);

        // Validation
        if (course == null) {
            return new ArrayList<>();
        }

        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : course.getStudents()) {
            if (student.getCourse().getId() == courseId) {
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }

    // _________________________________________________________________

    public void enrollStudent(Student student, int courseId) {
        try {
            em.getTransaction().begin();

            Course course = courseDAO.find(courseId);
            if (course != null) {
                course.addStudent(student);
                studentDAO.create(student);
                em.getTransaction().commit();
            } else {
                System.out.println("Kursus ikke fundet");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // _________________________________________________________________

    public void updateCourseStudent(int studentId, int newCourseId) {
        try {
            em.getTransaction().begin();

            Student student = studentDAO.find(studentId);
            Course newCourse = courseDAO.find(newCourseId);

            if (student != null && newCourse != null) {
                student.setCourse(newCourse);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
package dk.project.service;

import dk.project.dao.StudentDAO;
import dk.project.entity.Student;
import jakarta.persistence.EntityManager;
import java.util.List;

public class StudentService {

    // Attributes
    private final StudentDAO studentDAO;

    // ____________________________________________

    public StudentService(EntityManager em){
        this.studentDAO = new StudentDAO(em);
    }

    // ____________________________________________

    public Student createStudent(Student student){
        validateNotEmpty(student.getName(), "Student.name");
        validateNotEmpty(student.getEmail(), "Student.email");
        return studentDAO.create(student);
    }

    // ____________________________________________

    public Student updateStudent(Student student) {
        validateNotEmpty(student.getName(), "Student.name");
        validateNotEmpty(student.getEmail(), "Student.email");
        return studentDAO.update(student);
    }

    // ____________________________________________

    public Student getStudentById(Integer id) {
        validateNotEmpty(id, "Student.id");
        return studentDAO.getById(id);
    }

    // ____________________________________________

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    // ____________________________________________

    public Student deleteStudent(Integer id) {
        validateNotEmpty(id, "Student.id");
        return studentDAO.deleteById(id);
    }

    // ____________________________________________

    public void deleteAllStudents(){
        studentDAO.deleteAll();
    }

    // ___________________________________________
    // Validation

    private void validateNotEmpty(Object paramValue, String fieldName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (paramValue instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}
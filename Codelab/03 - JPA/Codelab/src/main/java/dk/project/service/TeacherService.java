package dk.project.service;

import dk.project.dao.TeacherDAO;
import dk.project.entity.Teacher;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TeacherService {

    // Attributes
    private final TeacherDAO teacherDAO;

    // ___________________________________________

    public TeacherService(EntityManager em) {
        this.teacherDAO = new TeacherDAO(em);
    }

    // ___________________________________________

    public Teacher createTeacher(Teacher teacher) {
        validateNotEmpty(teacher.getName(), "Teacher.name");
        validateNotEmpty(teacher.getEmail(), "Teacher.email");
        validateNotEmpty(teacher.getZoom(), "Teacher.zoom");
        return teacherDAO.create(teacher);
    }

    // ___________________________________________

    public Teacher updateTeacher(Teacher teacher) {
        validateNotEmpty(teacher.getName(), "Teacher.name");
        validateNotEmpty(teacher.getEmail(), "Teacher.email");
        validateNotEmpty(teacher.getZoom(), "Teacher.zoom");
        return teacherDAO.update(teacher);
    }

    // ___________________________________________

    public Teacher getTeacherById(Integer id) {
        validateNotEmpty(id, "Teacher.id");
        return teacherDAO.getById(id);
    }

    // ___________________________________________

    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAll();
    }

    // ___________________________________________

    public Teacher deleteTeacher(Integer id) {
        validateNotEmpty(id, "Teacher.id");
        return teacherDAO.deleteById(id);
    }

    // ___________________________________________

    public void deleteAllTeachers() {
        teacherDAO.deleteAll();
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
package dk.project.service;

import dk.project.dao.CourseDAO;
import dk.project.entity.Course;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CourseService {

    // Attributes
    private final CourseDAO courseDAO;

    // ___________________________________________

    public CourseService(EntityManager em) {
        this.courseDAO = new CourseDAO(em);
    }

    // ___________________________________________

    public Course createCourse(Course course) {
        validateNotEmpty(course.getCourseName(), "Course.courseName");
        validateNotEmpty(course.getDescription(), "Course.description");
        return courseDAO.create(course);
    }

    // ___________________________________________

    public Course updateCourse(Course course) {
        validateNotEmpty(course.getCourseName(), "Course.courseName");
        validateNotEmpty(course.getDescription(), "Course.description");
        return courseDAO.update(course);
    }

    // ___________________________________________

    public Course getCourseById(Integer id) {
        validateNotEmpty(id, "Course.id");
        return courseDAO.getById(id);
    }

    // ___________________________________________

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    // ___________________________________________

    public Course deleteCourse(Integer id) {
        validateNotEmpty(id, "Course.id");
        return courseDAO.deleteById(id);
    }

    // ___________________________________________

    public void deleteAllCourses() {
        courseDAO.deleteAll();
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
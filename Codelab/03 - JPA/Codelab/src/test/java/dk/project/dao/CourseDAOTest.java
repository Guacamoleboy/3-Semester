package dk.project.dao;

import dk.project.entity.Course;
import dk.project.entity.CourseName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDAOTest extends ADAOTest {

    // Attributes
    private CourseDAO courseDAO;

    // _______________________________________________________

    @BeforeEach
    public void setupDAO() {
        courseDAO = new CourseDAO(em);
    }

    // _______________________________________________________

    @Test
    public void shouldCreateCourse(){
        // Arrange
        Course course = Course.builder()
                .courseName(CourseName.MATH)
                .description("Math")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(14))
                .build();

        // Act
        Course createdCourse = courseDAO.create(course);

        // Assert
        assertNotNull(createdCourse.getId());
    }

    // _______________________________________________________

    @Test
    public void shouldUpdateCourse() {
        // Arrange
        Course course = Course.builder()
                .courseName(CourseName.SCIENCE)
                .description("Science")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(14))
                .build();
        Course created = courseDAO.create(course);

        // Act
        created.setDescription("Advanced Science");
        Course updated = courseDAO.update(created);

        // Assert
        String expectedDescription = "Advanced Science";
        String actualDescription = updated.getDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    // _______________________________________________________

    @Test
    public void shouldGetCourseById() {
        // Arrange
        Course course = Course.builder()
                .courseName(CourseName.ENGLISH)
                .description("English")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(14))
                .build();

        Course created = courseDAO.create(course);

        // Act
        Course fetched = courseDAO.getById(created.getId());

        // Assert
        Integer expectedId = created.getId();
        Integer actualId = fetched.getId();
        assertEquals(expectedId, actualId);
    }

    // _______________________________________________________

    @Test
    public void shouldGetExistingCourseById() {
        // Arrange
        Course course = Course.builder()
                .courseName(CourseName.ENGLISH)
                .description("English")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(3))
                .build();
        Course created = courseDAO.create(course);

        // Act
        Course fetched = courseDAO.getById(created.getId());

        // Assert
        Integer expectedId = created.getId();
        Integer actualId = fetched.getId();
        assertEquals(expectedId, actualId);
    }

    // _______________________________________________________

    @Test
    public void shouldGetNonExistingCourseById() {
        // Arrange
        int nonExistingId = 9999;

        // Act
        Course fetched = courseDAO.getById(nonExistingId);

        // Assert
        assertNull(fetched);
    }

    // _______________________________________________________

    @Test
    public void shouldGetAllCourses() {
        // Arrange
        Course course1 = Course.builder()
                .courseName(CourseName.ART)
                .description("Art")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(2))
                .build();
        Course course2 = Course.builder()
                .courseName(CourseName.MUSIC)
                .description("Music")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(2))
                .build();

        courseDAO.create(course1);
        courseDAO.create(course2);

        // Act
        List<Course> allCourses = courseDAO.getAll();

        // Assert
        assertTrue(allCourses.size() >= 2);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteCourse() {
        // Arrange
        Course course = Course.builder()
                .courseName(CourseName.HISTORY)
                .description("History")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(5))
                .build();

        Course created = courseDAO.create(course);

        // Act
        Course deleted = courseDAO.deleteById(created.getId());
        Course fetchedAfterDelete = courseDAO.getById(created.getId());

        // Assert
        Integer expectedId = created.getId();
        Integer deletedId = deleted.getId();
        assertEquals(expectedId, deletedId);
        assertNull(fetchedAfterDelete);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteAllCourses() {
        // Arrange
        Course course1 = Course.builder().courseName(CourseName.MATH).description("Math").build();
        Course course2 = Course.builder().courseName(CourseName.SCIENCE).description("Science").build();
        courseDAO.create(course1);
        courseDAO.create(course2);

        // Act
        courseDAO.deleteAll();
        List<Course> allCourses = courseDAO.getAll();

        // Assert
        int expectedSize = 0;
        int actualSize = allCourses.size();
        assertEquals(expectedSize, actualSize);

    }

}
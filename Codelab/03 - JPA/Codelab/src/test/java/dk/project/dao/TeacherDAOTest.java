package dk.project.dao;

import dk.project.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeacherDAOTest extends ADAOTest {

    // Attributes
    private TeacherDAO teacherDAO;

    // _______________________________________________________

    @BeforeEach
    public void setupDAO() {
        teacherDAO = new TeacherDAO(em);
    }

    // _______________________________________________________

    @Test
    public void shouldCreateTeacher() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link")
                .build();

        // Act
        Teacher createdTeacher = teacherDAO.create(teacher1);

        // Assert
        assertNotNull(createdTeacher.getId());
    }

    // _______________________________________________________

    @Test
    public void shouldUpdateTeacher() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link")
                .build();
        Teacher createdTeacher = teacherDAO.create(teacher1);

        // Act
        createdTeacher.setEmail("diddy@mail.com");
        Teacher updatedTeacher = teacherDAO.update(createdTeacher);

        // Assert
        String expectedEmail = "diddy@mail.com";
        String actualEmail = updatedTeacher.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    // _______________________________________________________

    @Test
    public void shouldGetTeacherById() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link")
                .build();
        Teacher createdTeacher = teacherDAO.create(teacher1);

        // Act
        Teacher fetchedTeacher = teacherDAO.getById(createdTeacher.getId());

        // Assert
        Integer expectedId = createdTeacher.getId();
        Integer actualId = fetchedTeacher.getId();
        assertEquals(expectedId, actualId);
    }

    // _______________________________________________________

    @Test
    public void shouldGetNonExistingTeacherById() {
        // Arrange
        int nonExistingId = 9999;

        // Act
        Teacher fetchedTeacher = teacherDAO.getById(nonExistingId);

        // Assert
        assertNull(fetchedTeacher);
    }

    // _______________________________________________________

    @Test
    public void shouldGetAllTeachers() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link1")
                .build();
        Teacher teacher2 = Teacher.builder()
                .name("Jonas")
                .email("jonas@mail.com")
                .zoom("https://zoomlink.com/link2")
                .build();
        teacherDAO.create(teacher1);
        teacherDAO.create(teacher2);

        // Act
        List<Teacher> allTeachers = teacherDAO.getAll();

        // Assert
        assertTrue(allTeachers.size() >= 2);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteTeacher() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link")
                .build();
        Teacher createdTeacher = teacherDAO.create(teacher1);

        // Act
        Teacher deletedTeacher = teacherDAO.deleteById(createdTeacher.getId());
        Teacher fetchedAfterDelete = teacherDAO.getById(createdTeacher.getId());

        // Assert
        Integer expectedId = createdTeacher.getId();
        Integer deletedId = deletedTeacher.getId();
        assertEquals(expectedId, deletedId);
        assertNull(fetchedAfterDelete);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteAllTeachers() {
        // Arrange
        Teacher teacher1 = Teacher.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .zoom("https://zoomlink.com/link1")
                .build();
        Teacher teacher2 = Teacher.builder()
                .name("Jonas")
                .email("jonas@mail.com")
                .zoom("https://zoomlink.com/link2")
                .build();
        teacherDAO.create(teacher1);
        teacherDAO.create(teacher2);

        // Act
        teacherDAO.deleteAll();
        List<Teacher> allTeachers = teacherDAO.getAll();

        // Assert
        int expectedSize = 0;
        int actualSize = allTeachers.size();
        assertEquals(expectedSize, actualSize);
    }

}
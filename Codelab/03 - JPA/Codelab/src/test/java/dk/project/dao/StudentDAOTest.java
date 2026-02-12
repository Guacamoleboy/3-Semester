package dk.project.dao;

import dk.project.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDAOTest extends ADAOTest {

    // Attributes
    private StudentDAO studentDAO;

    // _______________________________________________________

    @BeforeEach
    public void setupDAO() {
        studentDAO = new StudentDAO(em);
    }

    // _______________________________________________________

    @Test
    public void shouldCreateStudent() {
        // Arrange
        Student student1 = Student.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .build();

        // Act
        Student createdStudent = studentDAO.create(student1);

        // Assert
        assertNotNull(createdStudent.getId());
    }

    // _______________________________________________________

    @Test
    public void shouldUpdateStudent() {
        // Arrange
        Student student1 = Student.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .build();
        Student createdStudent = studentDAO.create(student1);

        // Act
        createdStudent.setEmail("diddy@mail.com");
        Student updatedStudent = studentDAO.update(createdStudent);

        // Assert
        String expectedEmail = "diddy@mail.com";
        String actualEmail = updatedStudent.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    // _______________________________________________________

    @Test
    public void shouldGetStudentById() {
        // Arrange
        Student student1 = Student.builder()
                .name("Diddy")
                .email("diddy@mail.com")
                .build();
        Student createdStudent = studentDAO.create(student1);

        // Act
        Student fetchedStudent = studentDAO.getById(createdStudent.getId());

        // Assert
        Integer expectedId = createdStudent.getId();
        Integer actualId = fetchedStudent.getId();
        assertEquals(expectedId, actualId);
    }

    // _______________________________________________________

    @Test
    public void shouldGetNonExistingStudentById() {
        // Arrange
        int nonExistingId = 9999;

        // Act
        Student fetchedStudent = studentDAO.getById(nonExistingId);

        // Assert
        assertNull(fetchedStudent);
    }

    // _______________________________________________________

    @Test
    public void shouldGetAllStudents() {
        // Arrange
        Student student1 = Student.builder().name("Diddy").email("diddy@mail.com").build();
        Student student2 = Student.builder().name("Jonas").email("jonas@mail.com").build();
        studentDAO.create(student1);
        studentDAO.create(student2);

        // Act
        List<Student> allStudents = studentDAO.getAll();

        // Assert
        assertTrue(allStudents.size() >= 2);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteStudent() {
        // Arrange
        Student student1 = Student.builder().name("Diddy").email("diddy@mail.com").build();
        Student createdStudent = studentDAO.create(student1);

        // Act
        Student deletedStudent = studentDAO.deleteById(createdStudent.getId());
        Student fetchedAfterDelete = studentDAO.getById(createdStudent.getId());

        // Assert
        Integer expectedId = createdStudent.getId();
        Integer deletedId = deletedStudent.getId();
        assertEquals(expectedId, deletedId);
        assertNull(fetchedAfterDelete);
    }

    // _______________________________________________________

    @Test
    public void shouldDeleteAllStudents() {
        // Arrange
        Student student1 = Student.builder().name("Diddy").email("diddy@mail.com").build();
        Student student2 = Student.builder().name("Jonas").email("jonas@mail.com").build();
        studentDAO.create(student1);
        studentDAO.create(student2);

        // Act
        studentDAO.deleteAll();
        List<Student> allStudents = studentDAO.getAll();

        // Assert
        int expectedSize = 0;
        int actualSize = allStudents.size();
        assertEquals(expectedSize, actualSize);
    }

}
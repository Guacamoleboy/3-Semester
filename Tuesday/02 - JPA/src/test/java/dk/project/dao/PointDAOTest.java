package dk.project.dao;

import dk.project.config.HibernateConfig;
import dk.project.entity.Point;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PointDAOTest {

    // Attributes
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private PointDAO pointDAO;

    // ______________________________________________

    @BeforeAll
    void setup(){
        entityManagerFactory = HibernateConfig.getEntityManagerFactoryForTest();
        entityManager = entityManagerFactory.createEntityManager();
        pointDAO = new PointDAO(entityManager);
    }

    // ______________________________________________

    @AfterAll
    void shutdown(){
        entityManager.close();
        entityManagerFactory.close();
    }

    // ______________________________________________

    @BeforeEach
    void cleanup(){
        pointDAO.deleteAll();
    }

    // ______________________________________________

    @Test
    void testSavePoint() {
        Point p = new Point(10, 20);

        entityManager.getTransaction().begin();
        pointDAO.save(p);
        entityManager.getTransaction().commit();

        assertNotNull(p.getId(), "Should have ID");
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());
    }

    // ______________________________________________

    @Test
    void testGetPointCount() {
        entityManager.getTransaction().begin();
        pointDAO.save(new Point(1, 1));
        pointDAO.save(new Point(2, 2));
        entityManager.getTransaction().commit();

        long count = pointDAO.getPointCount();
        assertEquals(2, count, "Should be 2");
    }

    // ______________________________________________

    @Test
    void testGetAverageX() {
        entityManager.getTransaction().begin();
        pointDAO.save(new Point(2, 5));
        pointDAO.save(new Point(4, 10));
        entityManager.getTransaction().commit();

        double avgX = pointDAO.getAverageX();
        assertEquals(3.0, avgX, "Avg X should be 3.0");
    }

    // ______________________________________________

    @Test
    void testGetAllPoints() {
        entityManager.getTransaction().begin();
        pointDAO.save(new Point(1, 1));
        pointDAO.save(new Point(2, 2));
        pointDAO.save(new Point(3, 3));
        entityManager.getTransaction().commit();

        List<Point> points = pointDAO.getAllPoints();
        assertEquals(3, points.size(), "Should retrieve 3 points");
        assertTrue(points.stream().anyMatch(p -> p.getX() == 2 && p.getY() == 2));
    }

    // ______________________________________________

    @Test
    void testDeleteAll() {
        entityManager.getTransaction().begin();
        pointDAO.save(new Point(1, 1));
        pointDAO.save(new Point(2, 2));
        entityManager.getTransaction().commit();

        pointDAO.deleteAll();
        long count = pointDAO.getPointCount();
        assertEquals(0, count, "All points should be deleted");
    }

}
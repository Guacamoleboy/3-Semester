package dk.project.task;

import dk.project.service.PointService;
import jakarta.persistence.EntityManager;

public class TaskTwo {

    // Attributes

    // _______________________________________________________________

    public void runTaskTwo(EntityManager em) {

        // Initial Setup
        PointService service = new PointService(em);

        // Action
        service.createPoints(1000);

        // Assert
        System.out.println("Total Points: " + service.getPointCount());
        System.out.println("Average X: " + service.getAverageX());
        service.getAllPoints().forEach(System.out::println);

    }

}
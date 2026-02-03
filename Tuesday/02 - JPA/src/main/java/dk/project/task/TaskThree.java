package dk.project.task;

import dk.project.dao.UnicornDAO;
import dk.project.entity.Unicorn;
import jakarta.persistence.EntityManager;

public class TaskThree {

    // Attributes

    // ________________________________________________

    public void runTaskThree(EntityManager em){

        // Initial
        UnicornDAO unicornDAO = new UnicornDAO(em);

        // Create
        Unicorn newUnicorn = new Unicorn();
        newUnicorn.setName("Diddy");
        newUnicorn.setAge(67);
        newUnicorn.setPowerStrength(420);
        Unicorn createdUnicorn = unicornDAO.save(newUnicorn);

        // Read
        Unicorn foundUnicorn = unicornDAO.findById(createdUnicorn.getId());
        System.out.println("Found unicorn: " + foundUnicorn.getName());

        // Update
        foundUnicorn.setAge(68);
        Unicorn updatedUnicorn = unicornDAO.update(foundUnicorn);
        System.out.println("Updated Unicorn Age: " + updatedUnicorn.getAge());

        // Delete
        unicornDAO.delete(createdUnicorn.getId());


    }

}

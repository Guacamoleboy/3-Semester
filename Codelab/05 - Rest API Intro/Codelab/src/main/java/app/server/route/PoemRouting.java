package app.server.route;

import app.controller.PoemController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class PoemRouting {

    // Attributes
    private final EntityManager entityManager;
    private final PoemController poemController;

    // ______________________________________________________________

    public PoemRouting(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
        poemController = new PoemController(entityManager);
    }

    // ______________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/poem", () -> {
                get("/all", poemController::getAll);
                get("/{id}", poemController::getByID);
                //get("/author/{id}", poemController::getByAuthor);
                put("/{id}", poemController::updatePoem);
                post("/create", poemController::createPoem);
                delete("/{id}", poemController::deleteByID);
                delete("/all", poemController::deleteAllPoems);
            });
        };

    }

}

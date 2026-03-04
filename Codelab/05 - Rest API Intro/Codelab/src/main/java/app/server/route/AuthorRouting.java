package app.server.route;


import app.controller.AuthorController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class AuthorRouting {

    // Attributes
    private final EntityManager entityManager;
    private final AuthorController authorController;

    // ______________________________________________________________

    public AuthorRouting(EntityManagerFactory entityManagerFactory){
        entityManager = entityManagerFactory.createEntityManager();
        authorController = new AuthorController(entityManager);
    }

    // ______________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/author", () -> {
                get("/all", authorController::getAll);
                get("/{id}", authorController::getByID);
            });
        };

    }


}

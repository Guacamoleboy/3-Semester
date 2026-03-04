package app.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class Routing {

    // Attributes

    // _______________________________________________________________________

    public static EndpointGroup registerRoutes(EntityManagerFactory entityManagerFactory) {

        // Routings
        PoemRouting poemRouting = new PoemRouting(entityManagerFactory);

        // EndpointGroup Return to server
        return () -> {

            poemRouting.routes().addEndpoints();
            //AuthorRouting.routes().addEndpoints();

        };

    }

    // _______________________________________________________________________

}
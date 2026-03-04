package app.server;

import app.config.HibernateConfig;
import app.server.route.Routing;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class Server {

    // Attributes
    private Javalin app;
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    // ____________________________________________________

    public void start(int port){

        // Singleton check
        if (app != null){
            return;
        }

        // Javalin setup
        app = Javalin.create(config -> {
            config.routes.apiBuilder(Routing.registerRoutes(entityManagerFactory));
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(RuntimeException.class, (e, ctx) -> {
                ctx.status(400).json(e.getMessage());
            });
        }).start(port);

    }

    // ____________________________________________________

    public void stop(){
        if (app != null) {
            app.stop();
            app = null;
        }
    }

}
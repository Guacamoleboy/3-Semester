package app.config;

import app.entity.Author;
import app.entity.Poem;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotation {

    // Attributes

    // ______________________________________________________________________

    public static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Poem.class);
        configuration.addAnnotatedClass(Author.class);
    }

}
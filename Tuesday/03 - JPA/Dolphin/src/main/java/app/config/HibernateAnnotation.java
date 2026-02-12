package app.config;

import app.entities.PersonDetail;
import app.entities.*;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotation {

    // Attributes

    // ______________________________________________________________________

    public static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Fee.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(PersonDetail.class);
        configuration.addAnnotatedClass(Note.class);
    }

}
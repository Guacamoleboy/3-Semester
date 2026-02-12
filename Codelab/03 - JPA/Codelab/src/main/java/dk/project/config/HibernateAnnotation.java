package dk.project.config;

import dk.project.entity.*;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotation {

    // Attributes

    // ______________________________________________________________________

    public static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(Course.class);
    }

}
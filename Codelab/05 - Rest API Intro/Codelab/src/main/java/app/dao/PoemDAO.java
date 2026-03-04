package app.dao;

import app.entity.Poem;
import jakarta.persistence.EntityManager;

public class PoemDAO extends EntityManagerDAO<Poem> {

    // Attributes

    // ______________________________________________________________

    public PoemDAO(EntityManager em ) {
        super(em, Poem.class);
    }

    // _______________________________________________________________________

}
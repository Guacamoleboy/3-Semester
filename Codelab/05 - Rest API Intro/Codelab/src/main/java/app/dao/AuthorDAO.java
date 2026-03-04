package app.dao;

import app.entity.Author;
import jakarta.persistence.EntityManager;

public class AuthorDAO extends EntityManagerDAO<Author>{

    // Attributes

    // _______________________________________________________________________

    public AuthorDAO(EntityManager em ) {
        super(em, Author.class);
    }

}

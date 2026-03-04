package app.controller;

import app.dao.AuthorDAO;
import app.dao.PoemDAO;
import app.entity.Author;
import app.entity.Poem;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class AuthorController {


    // Attributes
    private AuthorDAO authorDAO;

    // ___________________________________________________________

    public AuthorController(EntityManager em) {
        authorDAO = new AuthorDAO(em);
    }

    // ___________________________________________________________

    public void getAll(Context ctx) {
        ctx.json(authorDAO.getAll());
    }

    // ___________________________________________________________

    public void getByID(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Author author = authorDAO.getById(id);
        if (author != null) {
            new IllegalArgumentException("Author with id" + id + " was not found!");
        }

        ctx.status(200).json(author);
    }
}

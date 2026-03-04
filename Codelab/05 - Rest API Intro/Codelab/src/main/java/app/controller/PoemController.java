package app.controller;

import app.config.DotEnv;
import app.dao.PoemDAO;
import app.dto.PoemDTO;
import app.entity.Author;
import app.entity.Poem;
import app.util.ContextHelper;
import app.util.Populator;
import app.util.TryCatchHelper;
import io.javalin.http.Context;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PoemController {

    // Attributes
    private PoemDAO poemDAO;

    // ___________________________________________________________

    public PoemController(EntityManager em) {
        poemDAO = new PoemDAO(em);
        Populator.populate(poemDAO, em);
    }

    // ___________________________________________________________

    public void getAll(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            List<Poem> poems = poemDAO.getAll();
            List<PoemDTO> poemDTOs = new ArrayList<>();
            for (Poem poem : poems) {
                poemDTOs.add(new PoemDTO(poem));
            }
            return poemDTOs;
        }, "Poems fetched successfully!");
    }

    // ___________________________________________________________

    public void getByID(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            int id = ContextHelper.checkPathParamInt(ctx, "id");
            Poem poem = ContextHelper.checkNotNull(poemDAO.getById(id), "Poem with id " + id);

            // Return DTO.. NOT Entity.
            return new PoemDTO(poem);
        }, "Poem fetched successfully!");
    }

    // ___________________________________________________________

    public void deleteAllPoems(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            String userInput = ContextHelper.checkPathParamString(ctx, "password");
            String validation = DotEnv.get("DELETE_ALL_TOKEN");
            if (validation != null && !validation.equals(userInput)) {
                throw new IllegalArgumentException("Invalid delete token!");
            }
            poemDAO.deleteAll();
        }, "All poems deleted successfully!");
    }

    // ___________________________________________________________

    public void updatePoem(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {

            // Entry
            int poemId = ContextHelper.checkPathParamInt(ctx, "id");

            // Poem setup
            Poem poem = poemDAO.getById(poemId);
            ContextHelper.checkNotNull(poem, "Poem with id " + poemId);

            // Optional params
            String title = ContextHelper.checkPathParamString(ctx, "title");
            String content = ContextHelper.checkPathParamString(ctx, "context");
            Integer authorId = ContextHelper.checkPathParamInt(ctx, "author_id");
            LocalDate releaseDate = ContextHelper.checkPathParamDate(ctx, "release_date", "yyyy");

            // Author Setup
            if (authorId != null) {
                Author author = poemDAO.getColumnById(authorId, "author");
                ContextHelper.checkNotNull(author, "Author with id " + authorId);
                poem.setAuthor(author);
            }

            // Optional Check -> set or null
            if (title != null) poem.setTitle(title);
            if (content != null) poem.setContent(content);
            if (releaseDate != null) poem.setRelease(releaseDate);

            // Update
            poemDAO.update(poem);

            // Updated poem return as DTO
            return new PoemDTO(poem);

        }, "Poem updated successfully!");
    }

    // ___________________________________________________________

    public void createPoem(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {

            // Params + Checks using new system
            String title = ContextHelper.checkPathParamString(ctx, "title");
            String content = ContextHelper.checkPathParamString(ctx, "context");
            int authorID = ContextHelper.checkPathParamInt(ctx, "author_id");
            LocalDate releaseDate = ContextHelper.checkPathParamDate(ctx, "release_date", "yyyy");

            // Author + Check
            Author authorReturn = poemDAO.getColumnById(authorID, "author");
            Author author = ContextHelper.checkNotNull(authorReturn, "Author with id " + authorID);

            // Create poem
            Poem poem = Poem.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .release(releaseDate)
                    .build();

            // TryCatchHelper return
            return new PoemDTO(poem);

        }, "Poem created successfully!"); // 200 message
    }

    // ___________________________________________________________

    public void deleteByID(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {

            // Entry
            int poemId = ContextHelper.checkPathParamInt(ctx, "id");

            // Password check
            String userInput = ContextHelper.checkPathParamString(ctx, "password");
            String validation = DotEnv.get("DELETE_ONE_TOKEN");
            if (validation != null && !validation.equals(userInput)) {
                throw new IllegalArgumentException("Invalid delete token!");
            }

            // Fetch & Check
            Poem poem = poemDAO.getById(poemId);
            ContextHelper.checkNotNull(poem, "Poem with id " + poemId);

            // Delete
            poemDAO.deleteById(poemId);

        }, "Poem deleted successfully!");
    }

}
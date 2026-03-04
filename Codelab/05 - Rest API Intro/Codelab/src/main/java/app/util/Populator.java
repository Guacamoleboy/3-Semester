package app.util;

import app.dao.AuthorDAO;
import app.dao.PoemDAO;
import app.entity.Author;
import app.entity.Poem;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class Populator {

    // Attributes

    // _____________________________________________________________________________

    public static void populate(PoemDAO poemDAO, EntityManager entityManager) {

        // Initial DB check to prevent multi running the populator
        if (poemDAO.getById(1) != null) {
            System.out.println("DB already populated.. Skipping. ");
            return;
        }

        // TODO: Har ikke mere tid. Fik ChatGPT til at lave values hurtigt.
        // TODO: Mvh.
        Author frost = new Author();
        frost.setName("Robert");
        frost.setLastName("Frost");
        frost.setBirthDate(LocalDate.of(1874, 3, 26));

        Author dickinson = new Author();
        dickinson.setName("Emily");
        dickinson.setLastName("Dickinson");
        dickinson.setBirthDate(LocalDate.of(1830, 12, 10));

        Author yeats = new Author();
        yeats.setName("W. B.");
        yeats.setLastName("Yeats");
        yeats.setBirthDate(LocalDate.of(1865, 6, 13));

        Author poe = new Author();
        poe.setName("Edgar Allan");
        poe.setLastName("Poe");
        poe.setBirthDate(LocalDate.of(1809, 1, 19));

        // Create authors before movie. IMPORTANT!!!!!!!!
        AuthorDAO authorDAO = new AuthorDAO(entityManager);
        frost = authorDAO.create(frost);
        dickinson = authorDAO.create(dickinson);
        yeats = authorDAO.create(yeats);
        poe = authorDAO.create(poe);

        List<Poem> poems = List.of(
                Poem.builder().title("The Road Not Taken").content("Two roads diverged in a wood, and I...").author(frost).release(LocalDate.of(1916, 8, 1)).build(),
                Poem.builder().title("Stopping by Woods on a Snowy Evening").content("Whose woods these are I think I know...").author(frost).release(LocalDate.of(1923, 1, 1)).build(),
                Poem.builder().title("Hope is the Thing with Feathers").content("“Hope” is the thing with feathers -...").author(dickinson).release(LocalDate.of(1891, 1, 1)).build(),
                Poem.builder().title("Because I could not stop for Death").content("Because I could not stop for Death –...").author(dickinson).release(LocalDate.of(1890, 1, 1)).build(),
                Poem.builder().title("When You Are Old").content("When you are old and grey and full of sleep...").author(yeats).release(LocalDate.of(1892, 1, 1)).build(),
                Poem.builder().title("The Second Coming").content("Turning and turning in the widening gyre...").author(yeats).release(LocalDate.of(1919, 1, 1)).build(),
                Poem.builder().title("Annabel Lee").content("It was many and many a year ago...").author(poe).release(LocalDate.of(1849, 1, 1)).build(),
                Poem.builder().title("The Raven").content("Once upon a midnight dreary, while I pondered, weak and weary...").author(poe).release(LocalDate.of(1845, 1, 1)).build(),
                Poem.builder().title("Fire and Ice").content("Some say the world will end in fire...").author(frost).release(LocalDate.of(1920, 1, 1)).build(),
                Poem.builder().title("Desert Places").content("Snow falling and night falling fast...").author(frost).release(LocalDate.of(1936, 1, 1)).build(),
                Poem.builder().title("Wild Geese").content("You do not have to be good...").author(dickinson).release(LocalDate.of(1890, 1, 1)).build(),
                Poem.builder().title("Sailing to Byzantium").content("That is no country for old men...").author(yeats).release(LocalDate.of(1928, 1, 1)).build(),
                Poem.builder().title("A Dream Within a Dream").content("Take this kiss upon the brow!...").author(poe).release(LocalDate.of(1849, 1, 1)).build(),
                Poem.builder().title("Birches").content("When I see birches bend to left and right...").author(frost).release(LocalDate.of(1916, 1, 1)).build(),
                Poem.builder().title("I Shall Not Care").content("I shall not care at all...").author(dickinson).release(LocalDate.of(1890, 1, 1)).build()
        );

        // Create all
        for (Poem p : poems) {
            poemDAO.create(p);
        }

    }

}
package app.dao;

import app.entities.Note;
import jakarta.persistence.EntityManager;
import java.util.List;

public class NoteDAO extends EntityManagerDAO {

    // Attributes

    // _____________________________________________

    public NoteDAO(EntityManager em){
        super(em, Note.class);
    }

    // _____________________________________________

    public void createNote(Note note){
        create(note);
    }

    // _____________________________________________

    public void updateNote(Note note){
        update(note);
    }

    // _____________________________________________

    public void deleteNote(int id){
        deleteById(id);
    }

    // _____________________________________________

    public void deleteNote(Note note){
        delete(note);
    }

    // _____________________________________________

    public void deleteAllNotes(){
       deleteAll();
    }

    // _____________________________________________

    public Note getNoteById(int id){
        return executeQuery(() -> em.find(Note.class, id));
    }

    // _____________________________________________

    public List<Note> getAllNotes(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Note x";
            return em.createQuery(JPQL, Note.class)
            .getResultList();
        });
    }

}
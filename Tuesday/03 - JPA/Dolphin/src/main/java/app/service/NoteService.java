package app.service;

import app.dao.NoteDAO;
import app.entities.Note;
import jakarta.persistence.EntityManager;
import java.util.List;

public class NoteService {

    // Attributes
    private final NoteDAO noteDAO;

    // _____________________________________________

    public NoteService(EntityManager em){
        this.noteDAO = new NoteDAO(em);
    }

    // _____________________________________________

    public void createNote(Note note){
        noteDAO.createNote(note);
    }

    // _____________________________________________

    public void updateFee(Note note){
        noteDAO.updateNote(note);
    }

    // _____________________________________________

    public void deleteNote(int id){
        validateNotEmpty(id, "Note.id");
        noteDAO.delete(id);
    }

    // _____________________________________________

    public void deleteNote(Note note){
        validateNotEmpty(note, "Note.note");
        noteDAO.delete(note);
    }

    // ______________________________________________

    public void deleteAllNotes(){
        noteDAO.deleteAllNotes();
    }

    // ______________________________________________

    public Note getNoteById(int id){
        return noteDAO.getNoteById(id);
    }

    // ______________________________________________

    public List<Note> getAllNotes(){
        List<Note> note = noteDAO.getAllNotes();
        return note;
    }

    // ______________________________________________
    // Validation

    private void validateNotEmpty(Object paramValue, String fieldName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (paramValue instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}
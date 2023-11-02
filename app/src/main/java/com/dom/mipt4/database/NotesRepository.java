package com.dom.mipt4.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.dom.mipt4.objects.Note;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NotesRepository {

    private final NoteDao NOTE_DAO;
    private LiveData<List<Note>> allNotes;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public NotesRepository(Application application) {
        NotesDatabase db = NotesDatabase.getDatabase(application);
        NOTE_DAO = db.noteDao();
        allNotes = NOTE_DAO.getAllNotes();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Note>> getAllWords() {
        return allNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Note note) {
        NotesDatabase.databaseActionsExecutor.execute(() -> {
            NOTE_DAO.insertAll(note);
        });
    }

    public void delete(Note note) {
        NotesDatabase.databaseActionsExecutor.execute(() -> {
            NOTE_DAO.delete(note);
        });
    }

    public Note getById(int id) {
        //AtomicReference<Note> note = new AtomicReference<>();
        //NotesDatabase.databaseActionsExecutor.execute(() -> {
        //    note.set(NOTE_DAO.loadById(id));
        //});
        //
        //while (note.get() == null) {
        //    Thread.yield();
        //}

        return NOTE_DAO.loadById(id);
    }

}

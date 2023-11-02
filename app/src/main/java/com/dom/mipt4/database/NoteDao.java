package com.dom.mipt4.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.dom.mipt4.objects.Note;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    Single<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE note.id IS (:noteId)")
    Flowable<Note> loadById(int noteId);

    @Insert
    Completable insertAll(Note... notes);

    @Delete
    Completable  delete(Note note);

}

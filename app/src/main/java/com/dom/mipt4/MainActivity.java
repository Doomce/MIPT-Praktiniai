package com.dom.mipt4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.dom.mipt4.database.NotesRepository;
import com.dom.mipt4.elements.NoteElement;
import com.dom.mipt4.objects.Note;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadNotes();
        loadMenu();
    }

    private void loadMenu() {

        NavigationBarView menu = findViewById(R.id.bottomMenu);
        menu.setOnItemSelectedListener((event) -> {

            Intent intent;
            if (event.getItemId() == R.id.addBtn) intent = new Intent(this, AddNoteActivity.class);
            else intent = new Intent(this, AddNoteActivity.class);

            startActivity(intent);
            return true;
        });
    }

    public void loadNotes() {
        NotesRepository repo = new NotesRepository(getApplication());

        try {
            List<Note> notes = new ArrayList<>(Objects.requireNonNull(repo.getAllWords().getValue()));
            //List<Note> notes = new ArrayList<>();
            //notes.add(new Note("SSS", "SSSs"));
            writeNotesUi(notes);
        } catch (NullPointerException | AssertionError ex) {
            Log.d("NOTES", "EMPTY");
            writeNotesUi(null);
        }
    }

    private void writeNotesUi(List<Note> notes) {
        if (notes == null || notes.isEmpty()) {
            findViewById(R.id.messageBox).setVisibility(View.VISIBLE);
            findViewById(R.id.notesListContainer).setVisibility(View.INVISIBLE);
            return;
        }
        LinearLayout noteListElem = findViewById(R.id.notes);
        for (Note note: notes) {
            NoteElement elem = new NoteElement(this, note);
            noteListElem.addView(elem);

        }
    }


}
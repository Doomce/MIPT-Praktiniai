package com.dom.mipt4;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.dom.mipt4.database.NotesRepository;
import com.dom.mipt4.elements.Alert;
import com.dom.mipt4.objects.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        int createdNoteId = getIntent().getIntExtra("noteId", -1);

        ImageButton backBtn = findViewById(R.id.closeBtn);
        backBtn.setOnClickListener((event) -> backToMainActivity());

        if (createdNoteId  < 0) {
            Button saveBtn = findViewById(R.id.saveBtn);
            saveBtn.setOnClickListener((event) -> saveNoteBtnAction());
            return;
        }

        NotesRepository repo = new NotesRepository(getApplication());
        setupForShow(repo.getById(createdNoteId));
    }

    private void saveNoteBtnAction() {
        EditText name = findViewById(R.id.addNoteName);
        EditText desc = findViewById(R.id.addNoteDescription);

        if (name.getText().toString().isEmpty()) {
            new Alert(this, R.string.invalid_title, R.string.invalid_name).show();
            return;
        }

        if (desc.getText().toString().isEmpty()) {
            new Alert(this, R.string.invalid_title, R.string.invalid_desc).show();
            return;
        }

        NotesRepository repo = new NotesRepository(getApplication());
        repo.insert(new Note(name.getText().toString(), desc.getText().toString()));
        Toast.makeText(this, getString(R.string.valid_note), Toast.LENGTH_SHORT).show();
        backToMainActivity();

    }


    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setupForShow(Note note) {
        if (note == null) return;

        EditText name = findViewById(R.id.addNoteName);
        name.setText(note.getName());
        name.setEnabled(false);

        EditText description = findViewById(R.id.addNoteDescription);
        description.setText(note.getName());
        description.setEnabled(false);

        findViewById(R.id.saveBtn).setVisibility(View.INVISIBLE);
    }
}
package com.jworks.mvvm.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.jworks.mvvm.Models.Note;
import com.jworks.mvvm.R;
import com.jworks.mvvm.ViewModels.NoteViewModel;
import com.jworks.mvvm.ViewModels.NoteViewModelFactory;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {
    EditText title, description;
    NumberPicker priority;
    private NoteViewModelFactory factory;
    private NoteViewModel noteViewModel;
    Note noteintent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_clear);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        priority = findViewById(R.id.priority);
        factory = new NoteViewModelFactory(this.getApplication());
        noteViewModel = new ViewModelProvider(this,factory).get(NoteViewModel.class);

        priority.setMinValue(1);
        priority.setMaxValue(5);

         intent= getIntent();

        if(intent.hasExtra("note")){
           noteintent = (Note) intent.getSerializableExtra("note");
            setTitle("Edit Note");
            assert noteintent != null;
            title.setText(noteintent.getTitle());
            description.setText(noteintent.getDescription());
            priority.setValue(noteintent.getPriority());


        }
        else {
            setTitle("Add Note");
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            if(intent.hasExtra("note")){
                updatenote();
            }
            else {
                saveNote();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void updatenote() {
        String title_val = title.getText().toString().trim();
        String desc_val = description.getText().toString().trim();
        int priority_val = priority.getValue();

        if(title_val.isEmpty()||desc_val.isEmpty()){
            Toast.makeText(AddNoteActivity.this,"Enter Title and Note",Toast.LENGTH_SHORT).show();

        }
        else {
            Note note = new Note(title_val,desc_val,priority_val);
            note.setId(noteintent.getId());
            noteViewModel.update(note);
            Toast.makeText(AddNoteActivity.this,"Note updated",Toast.LENGTH_SHORT).show();

            Intent main = new Intent(AddNoteActivity.this, MainActivity.class);
            startActivity(main);
            finish();

        }

    }

    private void saveNote() {
        String title_val = title.getText().toString().trim();
        String desc_val = description.getText().toString().trim();
        int priority_val = priority.getValue();

        if(title_val.isEmpty()||desc_val.isEmpty()){
            Toast.makeText(AddNoteActivity.this,"Enter Title and Note",Toast.LENGTH_LONG).show();

        }
        else {
            Note note = new Note(title_val,desc_val,priority_val);
            noteViewModel.insert(note);
            Toast.makeText(AddNoteActivity.this,"Note Added",Toast.LENGTH_LONG).show();
            finish();

        }



    }
}
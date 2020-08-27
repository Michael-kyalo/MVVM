package com.jworks.mvvm.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jworks.mvvm.Models.Note;
import com.jworks.mvvm.R;

public class ReadNoteActivity extends AppCompatActivity {
    private TextView title, desc;
    private ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        title = findViewById(R.id.title_text);
        desc = findViewById(R.id.description_text);
        edit = findViewById(R.id.edit);

        Intent intent = getIntent();
        final Note note = (Note) intent.getSerializableExtra("note");

        assert note != null;
        title.setText(note.getTitle());
        desc.setText(note.getDescription());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("note",note);
                Intent edit = new Intent(ReadNoteActivity.this, AddNoteActivity.class);
                edit.putExtras(bundle);
                startActivity(edit);
            }
        });
    }
}
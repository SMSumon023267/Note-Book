package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetails extends AppCompatActivity {
    private TextView titleOfNoteDelails,contentOfNoteDetails;
    FloatingActionButton gotoEditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleOfNoteDelails = (TextView) findViewById(R.id.titleOfNoteDetails_TextView_ID);
        contentOfNoteDetails = (TextView) findViewById(R.id.contentFontNoteDetails_TextView_ID);
        gotoEditNote = (FloatingActionButton) findViewById(R.id.gotoEditNote_Floating_ID);

//        Toolbar toolbar = findViewById(R.id.toolBarOfNoteDetails_ID);
//        setSupportActionBar(toolbar);

        gotoEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(v.getContext(),EdiTNoteActivity.class);
                intent.putExtra("title",getIntent().getStringExtra("title"));
                intent.putExtra("content",getIntent().getStringExtra("content"));
                intent.putExtra("noteID",getIntent().getStringExtra("noteID"));
                v.getContext().startActivity(intent);

            }
        });

        titleOfNoteDelails.setText(getIntent().getStringExtra("title"));
        contentOfNoteDetails.setText(getIntent().getStringExtra("content"));
    }

}
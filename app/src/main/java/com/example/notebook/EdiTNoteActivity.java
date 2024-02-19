package com.example.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EdiTNoteActivity extends AppCompatActivity {
    Intent data;
    EditText editTitleOfNote,editContentNote;
    FloatingActionButton saveEditNote;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edi_tnote);

        editTitleOfNote = (EditText) findViewById(R.id.editTitleOfNote_EditText_ID);
        editContentNote = (EditText) findViewById(R.id.editContentFont_EditText_ID);
        saveEditNote = (FloatingActionButton) findViewById(R.id.saveEditNote_Flation_ID);

        data = getIntent();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//        Toolbar toolbar =findViewById(R.id.toolBarOfEditNote_ID);
//        setSupportActionBar(toolbar);

        saveEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = editTitleOfNote.getText().toString();
                String newContent = editContentNote.getText().toString();
                if (newTitle.isEmpty() | newContent.isEmpty()){
                    Toast.makeText(EdiTNoteActivity.this,"Something is Empty ",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid())
                            .collection("MyNotes").document(data.getStringExtra("noteID"));

                    Map<String, String> note = new HashMap<>();
                    note.put("title",newTitle);
                    note.put("content",newContent);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(EdiTNoteActivity.this,"Note Is Update",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EdiTNoteActivity.this,"Failed to Note Update",Toast.LENGTH_SHORT).show();

                        }
                    });

                }


            }
        });

        String noteTitle = data.getStringExtra("title");
        String noteContent = data.getStringExtra("content");
        editTitleOfNote.setText(noteTitle);
        editContentNote.setText(noteContent);

    }
}
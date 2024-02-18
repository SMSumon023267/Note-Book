package com.example.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
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

public class CreateNoteActivity extends AppCompatActivity {
    private EditText noteTitleEditText,noteTextEditText;
    FloatingActionButton saveButtonFloatingAction;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        noteTitleEditText = (EditText) findViewById(R.id.createTitleFont_EditText_ID);
        noteTextEditText = (EditText) findViewById(R.id.createContentFont_EditText_ID);
        saveButtonFloatingAction = (FloatingActionButton) findViewById(R.id.floatingSave_ID);

        Toolbar toolbar = findViewById(R.id.toolBarOfCreateNote_ID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        saveButtonFloatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitleEditText.getText().toString();
                String content = noteTextEditText.getText().toString();
                if (title.isEmpty() | content.isEmpty()){
                    Toast.makeText(CreateNoteActivity.this,"Both field are require",Toast.LENGTH_SHORT).show();
                } else {

                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid())
                            .collection("MyNotes").document();

                    Map<String, String> note = new HashMap<>();
                    note.put("title" ,title);
                    note.put("content",content);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(CreateNoteActivity.this,"Note Create Successful",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateNoteActivity.this,"Failed to Create Note",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(CreateNoteActivity.this,NoteActivity.class));

                        }
                    });


                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}
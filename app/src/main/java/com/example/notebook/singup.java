package com.example.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class singup extends AppCompatActivity {
    private EditText emailSingUpEditText,passwordSingUpEditText;
    private Button singiUpButton;
    private TextView goLoginTextView;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        firebaseAuth = FirebaseAuth.getInstance();

        emailSingUpEditText = (EditText) findViewById(R.id.emailSingUp_EditText_ID);
        passwordSingUpEditText = (EditText) findViewById(R.id.passwordSingUpEditText_ID);
        singiUpButton = (Button) findViewById(R.id.singUpButton_ID);
        goLoginTextView = (TextView) findViewById(R.id.gotLoginActivity_TextView_ID);

        goLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(singup.this,MainActivity.class));
            }
        });
        singiUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSingUp = emailSingUpEditText.getText().toString().trim();
                String passwordSingUp = passwordSingUpEditText.getText().toString().trim();
                if (emailSingUp.isEmpty()){
                    emailSingUpEditText.setError("Please Enter Your Email");
                    emailSingUpEditText.requestFocus();
                    return;
                } else if (passwordSingUp.isEmpty()) {
                    passwordSingUpEditText.setError("Enter Password");
                    passwordSingUpEditText.requestFocus();
                    return;
                }else {
                    firebaseAuth.createUserWithEmailAndPassword(emailSingUp,passwordSingUp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(singup.this,"Sing Up Successful",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(singup.this,"Sing Up Error",Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                        }

                        private void sendEmailVerification() {
                            FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
                            if (firebaseUser!=null){
                                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(singup.this,"Verification Email is Send!",Toast.LENGTH_SHORT).show();
                                        firebaseAuth.signOut();
                                        finish();
                                        startActivity(new Intent(singup.this,MainActivity.class));
                                    } 
                                });
                            }else {
                                Toast.makeText(singup.this,"Failed send Email Verification!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }
}
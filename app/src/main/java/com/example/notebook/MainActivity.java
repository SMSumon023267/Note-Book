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

public class MainActivity extends AppCompatActivity {
    private EditText emailloginEditText,passwordloginEditText;
    private Button loginButton,newUserSingInButton;

    private TextView forgetPasswordTextView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            finish();
            startActivity(new Intent(MainActivity.this,NoteActivity.class));
        }


        emailloginEditText = (EditText) findViewById(R.id.emailloginMailActivity_EditText_ID);
        passwordloginEditText = (EditText) findViewById(R.id.passwordloginMailActivity_EditText_ID);
        loginButton = (Button) findViewById(R.id.loginButton_ID);
        newUserSingInButton = (Button) findViewById(R.id.newUserSingIn_Button_ID);
        forgetPasswordTextView = (TextView) findViewById(R.id.forgetPassword_TextView_ID);

        newUserSingInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,singup.class));
            }
        });
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgetPassword.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaillogin = emailloginEditText.getText().toString().trim();
                String passwordlogin = passwordloginEditText.getText().toString().trim();
                if (emaillogin.isEmpty()){
                    emailloginEditText.setError("Please Enter Your Email");
                    emailloginEditText.requestFocus();
                    return;
                } else if (passwordlogin.isEmpty()) {
                    passwordloginEditText.setError("Enter Password");
                    passwordloginEditText.requestFocus();
                    return;
                }else {
                    firebaseAuth.signInWithEmailAndPassword(emaillogin,passwordlogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                chackEmailVerification();
                            }else {
                                Toast.makeText(MainActivity.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }
    private void chackEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser.isEmailVerified()==true){
            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this,NoteActivity.class));
        }else {
            Toast.makeText(MainActivity.this, "Verify your email fast", Toast.LENGTH_SHORT).show();
        }

    }
}
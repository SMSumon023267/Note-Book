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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetPassword extends AppCompatActivity {
    private EditText emailForgetPasswordEditText;
    private Button passwordRecoverButton;
    private TextView goBackToLoginActivityTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        goBackToLoginActivityTextView = (TextView) findViewById(R.id.go_BackToLoginActivity_TextView_ID);
        passwordRecoverButton = (Button) findViewById(R.id.passwordRecover_Button_ID);
        emailForgetPasswordEditText = (EditText) findViewById(R.id.email_ForgetPassword_EditText_ID);

        goBackToLoginActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this,MainActivity.class));
            }
        });

        passwordRecoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailForgetPasswordEditText.getText().toString().trim();

                if (email.isEmpty()){
                    Toast.makeText(ForgetPassword.this,"Pleace Enter your Email",Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgetPassword.this,"mail send,you can recover your password using mail",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgetPassword.this,MainActivity.class));
                            }else {
                                Toast.makeText(ForgetPassword.this,"Email is Wrong or Account Not Exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
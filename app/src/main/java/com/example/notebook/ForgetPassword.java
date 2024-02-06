package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {
    private EditText emailForgetPasswordEditText;
    private Button passwordRecoverButton;
    private TextView goBackToLoginActivityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();

        goBackToLoginActivityTextView = (TextView) findViewById(R.id.go_BackToLoginActivity_TextView_ID);

        goBackToLoginActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, Manifest.class));
            }
        });

        passwordRecoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailForgetPasswordEditText.getText().toString().trim();

                if (email.isEmpty()){
                    Toast.makeText(ForgetPassword.this,"Pleace Enter your Email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
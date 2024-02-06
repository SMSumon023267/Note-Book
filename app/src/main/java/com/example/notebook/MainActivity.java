package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText emailloginEditText,passwordloginEditText;
    private Button loginButton,newUserSingInButton;

    private TextView forgetPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
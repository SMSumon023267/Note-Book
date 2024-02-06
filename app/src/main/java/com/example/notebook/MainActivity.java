package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText emailloginEditText,passwordloginEditText;
    private Button loginButton,singUpButton;

    private TextView forgetPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailloginEditText = (EditText) findViewById(R.id.emailloginMailActivity_EditText_ID);
        passwordloginEditText = (EditText) findViewById(R.id.passwordloginMailActivity_EditText_ID);
        loginButton = (Button) findViewById(R.id.loginButton_ID);
        singUpButton = (Button) findViewById(R.id.singUpButton_ID);
        forgetPasswordTextView = (TextView) findViewById(R.id.forgetPassword_TextView_ID);
    }
}
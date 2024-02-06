package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class singup extends AppCompatActivity {
    private EditText emailSingUpEditText,passwordSingUpEditText;
    private Button singiUpButton;
    private TextView goLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

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

    }
}
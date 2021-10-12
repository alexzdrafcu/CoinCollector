package com.example.coincollector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton;
        registerButton = (Button) findViewById(R.id.login_register_button);
        registerButton.setOnClickListener(this);

        Button loginButton;
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        emailEditText = (EditText) findViewById(R.id.login_email_editText);
        passwordEditText = (EditText) findViewById(R.id.login_password_editText);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register_button: {
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            }
            case R.id.login_button: {
                userLogin();
                break;
            }
        }
    }

    private void userLogin(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Email required!");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Invalid email!");
            emailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordEditText.setError("Password required!");
            passwordEditText.requestFocus();
            return;
        }
        if(password.length() < 6){
            passwordEditText.setError("Password must contain at least 6 characters!");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Login error!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

package com.example.coincollector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText editTextNume, editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Button loginButton;
        loginButton = (Button) findViewById(R.id.register_login_button);
        loginButton.setOnClickListener(this);
        Button registerButton;
        registerButton= (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);

        editTextNume = (EditText) findViewById(R.id.register_name_editText);
        editTextEmail = (EditText) findViewById(R.id.register_email_editText);
        editTextPassword = (EditText) findViewById(R.id.register_password_editText);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_login_button: {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            }
            case R.id.register_button: {
                if(registerUser()){
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
                }
            }
        }
    }

    private boolean registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String nume = editTextNume.getText().toString().trim();

        if (nume.isEmpty()){
            editTextNume.setError("Name is required!");
            editTextNume.requestFocus();
            return false;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Invalid format!");
            editTextEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return false;
        }

        if(password.length() < 6 ){
            editTextPassword.setError("Password must contain at least 6 characters");
            editTextPassword.requestFocus();
            return false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(nume, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_LONG).show();
                                    }

                                    else{
                                        Toast.makeText(RegisterActivity.this, "Registration error!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration error!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return true;
    }

}
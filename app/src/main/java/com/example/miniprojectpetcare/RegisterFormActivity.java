package com.example.miniprojectpetcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterFormActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);
        firebaseAuth = FirebaseAuth.getInstance();
        final EditText petNameEditText = findViewById(R.id.editTextPetName);
        final EditText breedEditText = findViewById(R.id.editTextBreed);
        final EditText ageEditText = findViewById(R.id.editTextAge);
        final EditText emailEditText = findViewById(R.id.editTextEmail);
        final EditText passwordEditText = findViewById(R.id.editTextPassword);
        Button signUpButton = findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petName = petNameEditText.getText().toString();
                String breed = breedEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterFormActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration success
                            Toast.makeText(RegisterFormActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterFormActivity.this, LoginFormActivity.class);
                            startActivity(intent);
                        } else {
                            // Registration failed
                            Toast.makeText(RegisterFormActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

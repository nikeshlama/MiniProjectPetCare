package com.example.miniprojectpetcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class LoginFormActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewNewUser, textViewForgotPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginform);

        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewNewUser = findViewById(R.id.textViewNewUser);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);

        // Set a click listener for the Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        // Set a click listener for the "New User" link (Register Here)
        textViewNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the RegisterFormActivity when "New User" is clicked
                Intent intent = new Intent(LoginFormActivity.this, RegisterFormActivity.class);
                startActivity(intent);
            }
        });

        // Set a click listener for the "Forgot Password" link
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle "Forgot Password" logic (e.g., navigate to a password reset activity)
                forgotPassword();
            }
        });
    }

    // A method to handle login using Firebase Authentication
    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginFormActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sign in with email and password
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(LoginFormActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Navigate to the next activity after login (replace with your intended activity)
                        Intent intent = new Intent(LoginFormActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginFormActivity.this, "Login failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // A method to handle "Forgot Password" functionality
    private void forgotPassword() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(LoginFormActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send a password reset email
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Password reset email sent successfully
                        Toast.makeText(LoginFormActivity.this, "Password reset email sent to " + email,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // If sending fails, display a message to the user.
                        Toast.makeText(LoginFormActivity.this, "Failed to send reset email: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

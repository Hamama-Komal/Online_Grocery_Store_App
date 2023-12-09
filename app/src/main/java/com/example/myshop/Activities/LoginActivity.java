package com.example.myshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myshop.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        binding.progressBar.setVisibility(View.GONE);

        binding.loginBtn.setOnClickListener(view -> {
            loginUser();
            binding.progressBar.setVisibility(View.VISIBLE);
        });


        binding.signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            finish();


        });



    }

    private void loginUser() {

        String userEmail = binding.emailLogin.getText().toString();
        String userPassword = binding.passwordLogin.getText().toString();

        if( (!(userEmail.contains("@")) || userEmail.equals(""))){
            binding.emailLogin.setError("Invalid Email");
            return;
        }

        if( userPassword.length() < 6 || userPassword.equals("")){
            binding.passwordLogin.setError("Password length must be 7");
            return;
        }

        // Login The User
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Welcome Back..!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
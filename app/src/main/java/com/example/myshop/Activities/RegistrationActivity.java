package com.example.myshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myshop.Models.UserModel;
import com.example.myshop.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        binding.progressBar.setVisibility(View.GONE);

        binding.signIn.setOnClickListener(view -> {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        });

        binding.registerBtn.setOnClickListener(view -> {

            createUser();
            binding.progressBar.setVisibility(View.VISIBLE);

        });
    }

    private void createUser() {

        String userName = binding.nameReg.getText().toString();
        String userEmail = binding.emailReg.getText().toString();
        String userPassword = binding.passwordReg.getText().toString();



        if( userName.length() < 3 || userName.equals("")){
            binding.nameReg.setError("Invalid Name");
            return;
        }
        if( (!(userEmail.contains("@")) || userEmail.equals(""))){
            binding.emailReg.setError("Invalid Email");
            return;
        }

        if( userPassword.length() < 6 || userPassword.equals("")){
            binding.passwordReg.setError("Password length must be 7");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            UserModel userModel = new UserModel(userName, userEmail, userPassword);
                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("Users").child(id).setValue(userModel);
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, userName+" Welcome! Happy Shopping", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegistrationActivity.this, HomeActivity.class) );
                            finish();

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegistrationActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
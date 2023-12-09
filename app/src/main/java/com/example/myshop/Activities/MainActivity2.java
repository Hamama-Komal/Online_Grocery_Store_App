package com.example.myshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myshop.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();


      binding.start.setOnClickListener(view -> {


          if(firebaseAuth.getCurrentUser() != null){
              startActivity(new Intent(MainActivity2.this, HomeActivity.class));
          }

          else {
              startActivity(new Intent(MainActivity2.this, LoginActivity.class));
          }
        });


    }



}
package com.example.installmenttrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.installmenttrackingapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth Object
        mAuth = FirebaseAuth.getInstance();
        Objects.requireNonNull(getSupportActionBar()).hide();


        login();

    }

    private void login() {
        Toast.makeText(LoginActivity.this, "called", Toast.LENGTH_SHORT).show();

        binding.login.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                binding.progressBar.setVisibility(View.VISIBLE);

                String email = Objects.requireNonNull(binding.email.getText()).toString();
                String password = Objects.requireNonNull(binding.password.getText()).toString();

                if (email.isEmpty() || password.isEmpty()) {
               Toast.makeText(getApplicationContext(),"Enter email and password",Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.GONE);
                }
else{
                Log.d("msg", email + " " + password);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            binding.progressBar.setVisibility(View.GONE);
                            Log.d("msg1", "no");
                            Toast.makeText(getApplicationContext(), "May be internet issue or wrong credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            }
        });

    }

}
package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.assignment_3.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();


        binding.bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etUserName.getText().toString();
                String password = binding.etPassWord.getText().toString();


                if(TextUtils.isEmpty(email)) {
                    binding.etUserName.setError("You need to enter your email address");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    binding.etUserName.setError("You need to enter a password");
                    return;
                }

                binding.progressBar2.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
                    if (t.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(getApplicationContext(), SignUp.class));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "The username or password is incorrect", Toast.LENGTH_LONG).show();
                    }

                });

            }
        });


    }
    public void signUp(View view){
        Intent intent = new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);
    }

}
package com.example.assignment_3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_3.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();
        binding.bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etNewUserName.getText().toString();
                String password = binding.etNewPassword.getText().toString();

                String confirmPassword = binding.etConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    binding.etNewUserName.setError("You need to enter your email address");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    binding.etNewPassword.setError("You need to enter a password");
                    return;
                }

                if(TextUtils.isEmpty(confirmPassword)) {
                    binding.etNewPassword.setError("You need to re-enter the password again");
                    return;
                }

                if(!password.equals(confirmPassword)){
                    binding.etConfirmPassword.setError("The two passwords entered are not the same");
                    return;
                }
                binding.progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
                    if (t.isSuccessful()){
                        Toast.makeText(SignUp.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(SignUp.this, "An error occurred", Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);
                    }

                });

            }
        });
    }


}
package com.rektstudios.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {

    Button SignupButton;
    TextInputEditText SignupEmail, SignupPass, SignupConfirmPass;;
    TextInputLayout SignupEmailInput, SignupPassInput, SignupConfirmPassInput;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_signup, container, false);
        SignupButton=root.findViewById(R.id.signupButton);
        SignupEmail=root.findViewById(R.id.signupEmailText);
        SignupPass=root.findViewById(R.id.signupPassText);
        SignupConfirmPass=root.findViewById(R.id.signupConfirmPassText);
        SignupEmailInput=root.findViewById(R.id.signupEmailLayout);
        SignupPassInput=root.findViewById(R.id.signupPassLayout);
        SignupConfirmPassInput=root.findViewById(R.id.signupConfirmPassLayout);
        mAuth=FirebaseAuth.getInstance();

        SignupButton.setOnClickListener(v -> {
            String email = SignupEmail.getText().toString();
            String pass = SignupPass.getText().toString();
            String confirmPass = SignupConfirmPass.getText().toString();

            if(email.isEmpty()) {
                SignupEmailInput.setError("*Required");
                return;
            }
            SignupEmailInput.setErrorEnabled(false);
            if(pass.isEmpty()) {
                SignupPassInput.setError("*Required");
                return;
            }
            SignupPassInput.setErrorEnabled(false);
            if(confirmPass.isEmpty()) {
                SignupConfirmPassInput.setError("*Required");
                return;
            }
            if(!confirmPass.equals(pass)) {
                SignupConfirmPassInput.setError("*Passwords do not match");
                return;
            }
            SignupConfirmPassInput.setErrorEnabled(false);
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(task1 -> {
                        if(task1.isSuccessful())
                            Toast.makeText(getActivity(), "Registered successfully. Please check your email for verification.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                    });
                }
            });
        });
        return root;
    }
}
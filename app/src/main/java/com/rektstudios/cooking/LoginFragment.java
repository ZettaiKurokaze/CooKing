package com.rektstudios.cooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.Objects;

public class LoginFragment extends Fragment{
    Button LoginButton;
    TextInputEditText loginEmail, loginPass;
    TextInputLayout loginEmailInput, loginPassInput;
    TextView ForgotPass;
    private FirebaseAuth mAuth;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        LoginButton=root.findViewById(R.id.loginButton);
        loginEmail=root.findViewById(R.id.loginEmailText);
        loginPass=root.findViewById(R.id.loginPassText);
        loginEmailInput=root.findViewById(R.id.loginEmailLayout);
        loginPassInput=root.findViewById(R.id.loginPassLayout);
        ForgotPass=root.findViewById(R.id.forgotPassText);
        mAuth=FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(v -> {
            String email = loginEmail.getText().toString();
            String pass = loginPass.getText().toString();
            if(email.isEmpty()) {
                loginEmailInput.setError("*Email Required");
                return;
            }
            loginEmailInput.setErrorEnabled(false);
            if(pass.isEmpty()) {
                loginPassInput.setError("*Password Required");
                return;
            }
            loginPassInput.setErrorEnabled(false);
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()) {
                        Intent intent=new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        loginEmailInput.setErrorEnabled(false);
                    }
                    else {
                        loginEmailInput.setError("*Please verify your email address.");
                    }
                }
                else {
                    loginEmailInput.setError("*Invalid Credentials");
                }
            });
        });

        loginEmail.setTranslationX(800);
        loginPass.setTranslationX(800);
        ForgotPass.setTranslationX(800);
        LoginButton.setTranslationX(800);

        loginEmail.setAlpha(v);
        loginPass.setAlpha(v);
        ForgotPass.setAlpha(v);
        LoginButton.setAlpha(v);

        loginEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        loginPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ForgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        LoginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }

}
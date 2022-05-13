package com.rekstudios.cooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    Button LoginButton;
    TextInputLayout loginEmail, loginPass;
    TextView ForgotPass;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        LoginButton=root.findViewById(R.id.loginButton);
        loginEmail=root.findViewById(R.id.loginEmailLayout);
        loginPass=root.findViewById(R.id.loginPassLayout);
        ForgotPass=root.findViewById(R.id.forgotPassText);

        LoginButton.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
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
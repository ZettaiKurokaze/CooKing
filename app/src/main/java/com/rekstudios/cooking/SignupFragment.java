package com.rekstudios.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {

    Button SignupButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_signup, container, false);
        SignupButton=root.findViewById(R.id.signupButton);

        SignupButton.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        });
        return root;
    }
}
package com.rektstudios.cooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    TabLayout LoginTabLayout;
    ViewPager2 LoginViewPager;
    FloatingActionButton MetaFAB, GoogleFAB, TwitterFAB;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    float v=0;
    private LoginAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);

        LoginTabLayout=findViewById(R.id.loginTabLayout);
        LoginViewPager=findViewById(R.id.loginViewPager);
        MetaFAB=findViewById(R.id.fabMeta);
        GoogleFAB=findViewById(R.id.fabGoogle);
        TwitterFAB=findViewById(R.id.fabTwitter);

        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new LoginAdapter(fragmentManager,getLifecycle());
        LoginViewPager.setAdapter(adapter);

        LoginTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LoginViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        LoginViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                LoginTabLayout.selectTab(LoginTabLayout.getTabAt(position));
            }
        });

        MetaFAB.setTranslationY(300);
        GoogleFAB.setTranslationY(300);
        TwitterFAB.setTranslationY(300);

        MetaFAB.setAlpha(v);
        GoogleFAB.setAlpha(v);
        TwitterFAB.setAlpha(v);

        MetaFAB.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        GoogleFAB.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        TwitterFAB.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        }

//        GoogleFAB.setOnClickListener(v -> signIn());



    }


//    private SignInClient oneTapClient;
//    private BeginSignInRequest signInRequest;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState,
//                         @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                        .setSupported(true)
//                        .build())
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                // Automatically sign in when exactly one credential is retrieved.
//                .setAutoSelectEnabled(true)
//                .build();
//        // ...
//        oneTapClient.beginSignIn(signUpRequest)
//                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
//                    @Override
//                    public void onSuccess(BeginSignInResult result) {
//                        try {
//                            startIntentSenderForResult(
//                                    result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
//                                    null, 0, 0, 0);
//                        } catch (IntentSender.SendIntentException e) {
//                            Log.e("MyTag", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
//                        }
//                    }
//                })
//                .addOnFailureListener(this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // No saved credentials found. Launch the One Tap sign-up flow, or
//                        // do nothing and continue presenting the signed-out UI.
//                        Log.d("MyTag", e.getLocalizedMessage());
//                    }
//                });
//    }






}
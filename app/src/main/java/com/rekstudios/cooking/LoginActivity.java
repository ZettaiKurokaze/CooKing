package com.rekstudios.cooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout LoginTabLayout;
    ViewPager2 LoginViewPager;
    FloatingActionButton MetaFAB, GoogleFAB, TwitterFAB;
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



    }
}
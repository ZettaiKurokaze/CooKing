package com.rekstudios.cooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rekstudios.cooking.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BottomNavigationView bottomNavigation;

    @SuppressLint("NonConstantResourceId")
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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigation = findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_choose, R.id.navigation_search, R.id.navigation_add, R.id.navigation_favorite, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
//        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityLayout, new ChooseFragment()).commit();
//        navigationView.setSelectedItemId(R.id.nav_choose);
//
//        navigationView.setOnItemSelectedListener(item -> {
//            Fragment fragment = null;
//            switch (item.getItemId()){
//
//                case R.id.nav_choose:
//                    fragment = new ChooseFragment();
//                    break;
//
//                case R.id.nav_search:
//                    fragment = new SearchFragment();
////                    getSupportFragmentManager().beginTransaction()
////                            .setCustomAnimations(
////                                    R.anim.slide_in_from_right,  // enter
////                                    R.anim.slide_out_to_left,  // exit
////                                    R.anim.slide_in_from_left,   // popEnter
////                                    R.anim.slide_out_to_right  // popExit
////                            )
////                            .replace(R.id.mainActivityLayout,fragment)
////                            .addToBackStack(null)
////                            .commit();
//                    break;
//
//                case R.id.nav_add:
//                    fragment = new AddFragment();
//                    break;
//
//                case R.id.nav_favorite:
//                    fragment = new FavoriteFragment();
//                    break;
//
//                case R.id.nav_profile:
//                    fragment = new ProfileFragment();
//                    break;
//            }
//            if(fragment!=null)
//                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityLayout, fragment).commit();
//
//            return true;
//        });
    }
}
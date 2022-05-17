package com.rektstudios.cooking;

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
import com.google.firebase.auth.FirebaseAuth;
import com.rektstudios.cooking.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    BottomNavigationView bottomNavigation;
    private LinkedList<IngredientModel> mIngredientModels;
    private ArrayList<RecipeModel> recipeModels;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitIngredients();
        InitRecipes();

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
    }

    @Override
    protected void onDestroy() {
//        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }

    public LinkedList<IngredientModel> getIngredientsList() {
        return mIngredientModels;
    }

    public ArrayList<RecipeModel> getRecipeList() {
        return recipeModels;
    }

    public void InitIngredients(){
        mIngredientModels= new LinkedList<>();
        mIngredientModels.add(new IngredientModel(R.drawable.ic_rice,"Rice"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_onion,"Onion"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Cardamom"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Cinnamon"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Curry Paste"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_pineapple,"Pineapple"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Raisin"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Coriander"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Almond"));
        Collections.sort(mIngredientModels, new Comparator<IngredientModel>() {
            @Override
            public int compare(IngredientModel o1, IngredientModel o2) {
                return o1.getIngredientName().compareTo(o2.getIngredientName());
            }
        });
    }

    public void InitRecipes() {
        recipeModels=new ArrayList<>();
        recipeModels.add(new RecipeModel("Biriyani","120 min", (float) 4.3, "120 KCal", R.drawable.biriyani));
        recipeModels.add(new RecipeModel("Ramen02","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen03","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen04","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen05","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen06","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen07","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen08","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen09","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen10","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen11","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen12","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));
        recipeModels.add(new RecipeModel("Ramen13","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen));

    }

}
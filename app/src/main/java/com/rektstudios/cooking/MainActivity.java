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
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Bay Leaf"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Turmeric"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Chicken Stock"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Banana"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Milk"));
        mIngredientModels.add(new IngredientModel(R.drawable.ic_spice,"Apple"));
        Collections.sort(mIngredientModels, new Comparator<IngredientModel>() {
            @Override
            public int compare(IngredientModel o1, IngredientModel o2) {
                return o1.getIngredientName().compareTo(o2.getIngredientName());
            }
        });
    }

    public void InitRecipes() {
        ArrayList<IngredientModel> ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_rice,"Rice","g",300));
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter","g",25));
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken","pc(s)",4));
        ingredients.add(new IngredientModel(R.drawable.ic_onion,"Onion","pc(s)",1));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Bay Leaf","",1));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Cardamom","pod(s)",3));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Cinnamon","stick",1));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Turmeric","tsp",1));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Curry Paste","tbsp",4));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Raisin","g",85));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Chicken Stock","ml",850));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Coriander","g",30));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Almond","tbsp",2));
        Collections.sort(ingredients, new Comparator<IngredientModel>() {
            @Override
            public int compare(IngredientModel o1, IngredientModel o2) {
                return o1.getIngredientName().compareTo(o2.getIngredientName());
            }
        });

        ArrayList<StepsModel> steps = new ArrayList<>();
        steps.add(new StepsModel("Soak 300g basmati rice in warm water, then wash in cold until the water runs clear."));
        steps.add(new StepsModel("Heat 25g butter in a saucepan and cook 1 finely sliced large onion with 1 bay leaf, 3 cardamom pods and 1 small cinnamon stick for 10 mins.", 6000));
        steps.add(new StepsModel("Sprinkle in 1 tsp turmeric, then add 4 chicken breasts, cut into large chunks, and 4 tbsp curry paste. Cook until aromatic."));
        steps.add(new StepsModel("Stir the rice into the pan with 85g raisins, then pour over 850ml chicken stock."));
        steps.add(new StepsModel("Place a tight-fitting lid on the pan and bring to a hard boil, then lower the heat to a minimum and cook the rice for another 5 mins.", 3000));
        steps.add(new StepsModel("Turn off the heat and leave for 10 mins. Stir well, mixing through 15g ", 6000));


        recipeModels=new ArrayList<>();
        recipeModels.add(new RecipeModel("Biriyani","120 min", (float) 4.2, "120 KCal", R.drawable.biriyani, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        ingredients.add(new IngredientModel(R.drawable.ic_onion,"Onion"));
        steps = new ArrayList<>();
        recipeModels.add(new RecipeModel("Ramen","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        recipeModels.add(new RecipeModel("Burger","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Apple"));
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        recipeModels.add(new RecipeModel("Apple Pie","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Banana"));
        ingredients.add(new IngredientModel(R.drawable.ic_spice,"Milk"));
        recipeModels.add(new RecipeModel("Banana Shake","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        recipeModels.add(new RecipeModel("Lasagna","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        recipeModels.add(new RecipeModel("Pasta","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        recipeModels.add(new RecipeModel("Burrito","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        ingredients.add(new IngredientModel(R.drawable.ic_butter,"Butter"));
        recipeModels.add(new RecipeModel("Pizza","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        recipeModels.add(new RecipeModel("Chow Mein","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        recipeModels.add(new RecipeModel("Sandwich","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        recipeModels.add(new RecipeModel("Meatball","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));
        ingredients = new ArrayList<>();
        ingredients.add(new IngredientModel(R.drawable.ic_chicken,"Chicken"));
        ingredients.add(new IngredientModel(R.drawable.ic_rice,"Rice"));
        recipeModels.add(new RecipeModel("Omurice","120 min", (float) 4.3, "120 KCal", R.drawable.ic_ramen, 7, ingredients, steps, 65, 70, 98));

    }

}
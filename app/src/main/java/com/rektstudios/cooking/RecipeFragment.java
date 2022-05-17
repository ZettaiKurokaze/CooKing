package com.rektstudios.cooking;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private AppCompatImageButton BackButton, FavoritesButton;
    private String name;
    private AppCompatTextView RecipeName, RecipeRating, RecipeTime, ProteinText, FatText, CarbText, CalText;
    private RecyclerView RecipeIngredientRecyclerView;
    private RecipeModel recipe;
    private AppCompatImageView RecipeImage;
    private ArrayList<RecipeModel> recipes;
    private RecipeIngredientsAdapter adapter;
    private ArrayList<IngredientModel> ingredients;
    private RatingBar RecipeRatingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_recipe, container, false);
        name=getArguments().getString("RecipeName");
        recipes=((MainActivity) requireActivity()).getRecipeList();
        for(RecipeModel item : recipes) {
            if(item.getRecipeName().equals(name)) {
                recipe = item;
                break;
            }
        }
        RecipeRatingBar=root.findViewById(R.id.recipeRatingBar);
        RecipeImage=root.findViewById(R.id.recipeImageView);
        RecipeIngredientRecyclerView=root.findViewById(R.id.recipeIngredientsRecyclerView);
        RecipeName=root.findViewById(R.id.recipeNameText);
        RecipeRating=root.findViewById(R.id.ratingText);
        RecipeTime=root.findViewById(R.id.timeText);
        ProteinText=root.findViewById(R.id.proteinText);
        FatText=root.findViewById(R.id.fatText);
        CarbText=root.findViewById(R.id.carbText);
        CalText=root.findViewById(R.id.calText);
        BackButton=root.findViewById(R.id.backButton);
        FavoritesButton=root.findViewById(R.id.recipeFavButton);
        initRecipe();
        BackButton.setOnClickListener(v -> getFragmentManager().popBackStack());
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener((v, keyCode, event) -> {
            if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                getFragmentManager().popBackStack();
                return true;
            }
            return false;
        });
        return root;
    }

    public void initRecipe(){
        RecipeName.setText(recipe.getRecipeName());
        RecipeRating.setText(recipe.getRecipeRating());
        RecipeTime.setText(recipe.getRecipeTime());
        ProteinText.setText(recipe.getProtein());
        FatText.setText(recipe.getFat());
        CarbText.setText(recipe.getCarbohydrate());
        CalText.setText(recipe.getRecipeCal());
        RecipeImage.setImageResource(recipe.getRecipeImage());
        if(recipe.isFavoriteFlag())
            FavoritesButton.setImageResource(R.drawable.ic_heart_filled);
        else
            FavoritesButton.setImageResource(R.drawable.ic_heart_outline);
        FavoritesButton.setOnClickListener(v -> {
            if(recipe.isFavoriteFlag()) {
                FavoritesButton.setImageResource(R.drawable.ic_heart_outline);
                recipe.setFavoriteFlag(false);
            }
            else{
                FavoritesButton.setImageResource(R.drawable.ic_heart_filled);
                recipe.setFavoriteFlag(true);
            }
        });
        RecipeRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                recipe.updateRating(rating);
                RecipeRating.setText(recipe.getRecipeRating());
            }
        });
        ingredients=recipe.getIngredients();
        RecipeIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecipeIngredientRecyclerView.setHasFixedSize(true);
        adapter= new RecipeIngredientsAdapter(ingredients);
        RecipeIngredientRecyclerView.setAdapter(adapter);
    }
}
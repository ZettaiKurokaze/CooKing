package com.rektstudios.cooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.RecipeIngredientsViewHolder> {

    ArrayList<IngredientModel> arrayList;

    public RecipeIngredientsAdapter(ArrayList<IngredientModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecipeIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_ingredient_card, parent, false);
        return new RecipeIngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsViewHolder holder, int position) {
        IngredientModel data = arrayList.get(position);
        holder.IngredientIcon.setImageResource(data.getImageRes());
        holder.IngredientName.setText(data.getIngredientName());
        holder.IngredientAmount.setText(data.getAmountText());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecipeIngredientsViewHolder extends RecyclerView.ViewHolder {

        ImageView IngredientIcon;
        TextView IngredientName;
        TextView IngredientAmount;

        public RecipeIngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            IngredientIcon = itemView.findViewById(R.id.ingredientIcon);
            IngredientName = itemView.findViewById(R.id.ingredientName);
            IngredientAmount = itemView.findViewById(R.id.recipeIngredientAmount);

        }

    }

}

package com.rektstudios.cooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    LinkedList<RecipeModel> linkedList, backupList;

    public FavoritesAdapter(LinkedList<RecipeModel> linkedList) {
        this.linkedList = linkedList;
        this.backupList = linkedList;
    }

    public void setFilteredList(LinkedList<RecipeModel> filteredList) {
        this.linkedList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_grid, parent, false);
        return new FavoritesAdapter.FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesViewHolder holder, int position) {
        RecipeModel data = linkedList.get(position);
        holder.RecipeImage.setImageResource(data.getRecipeImage());
        holder.RecipeName.setText(data.getRecipeName());
        holder.RecipeTime.setText(data.getRecipeTime());
        holder.RecipeRating.setText(data.getRecipeRating());
        holder.RecipeCal.setText(data.getRecipeCal());
        if (data.isFavoriteFlag()) {
            holder.FavoriteButton.setImageResource(R.drawable.ic_heart_filled);
        } else {
            holder.FavoriteButton.setImageResource(R.drawable.ic_heart_outline);
        }
        holder.FavoriteButton.setOnClickListener(v -> {
            if (data.isFavoriteFlag()) {
                holder.FavoriteButton.setImageResource(R.drawable.ic_heart_outline);
                data.setFavoriteFlag(false);
                int i=linkedList.indexOf(data);
                linkedList.remove(i);
                notifyItemRemoved(i);
                i=backupList.indexOf(data);
                backupList.remove(i);
            } else {
                holder.FavoriteButton.setImageResource(R.drawable.ic_heart_filled);
                data.setFavoriteFlag(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView RecipeImage;
        TextView RecipeName, RecipeTime, RecipeRating, RecipeCal;
        ImageButton FavoriteButton;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            RecipeImage = itemView.findViewById(R.id.recipeImage);
            RecipeName = itemView.findViewById(R.id.recipeName);
            RecipeTime = itemView.findViewById(R.id.recipeTime);
            RecipeRating = itemView.findViewById(R.id.recipeRating);
            RecipeCal = itemView.findViewById(R.id.recipeCal);
            FavoriteButton = itemView.findViewById(R.id.recipeFavButton);
        }
    }

}

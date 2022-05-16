package com.rekstudios.cooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>{

    LinkedList<IngredientModel> linkedList, backupList;

    public IngredientsAdapter(LinkedList<IngredientModel> linkedList) {
        this.linkedList = linkedList;
        this.backupList = linkedList;
    }

    public void setFilteredList(LinkedList<IngredientModel> filteredList){
        this.linkedList=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_card,parent,false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        IngredientModel data=linkedList.get(position);
        holder.IngredientIcon.setImageResource(data.getImageRes());
        holder.IngredientName.setText(data.getIngredientName());
        if(!data.isButtonFlag()) {
            holder.IngredientButton.setBackgroundResource(R.drawable.ingredient_button_default);
            holder.IngredientButton.setRotation(0);
        }
        else{
            holder.IngredientButton.setBackgroundResource(R.drawable.ingredient_button_selected);
            holder.IngredientButton.setRotation(45);
        }
        holder.IngredientButton.setOnClickListener(v -> {
            if(data.isButtonFlag()) {
                holder.IngredientButton.setBackgroundResource(R.drawable.ingredient_button_default);
                holder.IngredientButton.animate().rotation(0).setDuration(300).start();
                data.setButtonFlag(false);
                moveItemBack(data);
            }
            else{
                holder.IngredientButton.setBackgroundResource(R.drawable.ingredient_button_selected);
                holder.IngredientButton.animate().rotation(-45).setDuration(300).start();
                data.setButtonFlag(true);
                if(position!=0)
                moveItemToTop(data);
            }
        });
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recycler_anim));
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class IngredientsViewHolder extends  RecyclerView.ViewHolder{

        ImageView IngredientIcon;
        TextView IngredientName;
        AppCompatButton IngredientButton;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            IngredientIcon=itemView.findViewById(R.id.ingredientIcon);
            IngredientName=itemView.findViewById(R.id.ingredientName);
            IngredientButton=itemView.findViewById(R.id.ingredientButton);

        }

    }

    public void moveItemToTop(IngredientModel ingredient) {
        String name=ingredient.getIngredientName();
        int i;
        for (i=0; i<linkedList.size(); i++) {
            if(name.compareTo(linkedList.get(i).getIngredientName())==0){
                linkedList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
        ingredient.setButtonFlag(true);
        linkedList.offerFirst(ingredient);
        notifyItemInserted(0);
        for (i=0; i<backupList.size(); i++) {
            if(name.compareTo(backupList.get(i).getIngredientName())==0){
                backupList.remove(i);
                backupList.offerFirst(ingredient);
                break;
            }
        }

    }

    public void moveItemBack(IngredientModel ingredient) {
        String name=ingredient.getIngredientName();
        int i;
        for (i=0; i<linkedList.size(); i++) {
            if(name.compareTo(linkedList.get(i).getIngredientName())==0){
                linkedList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
        ingredient.setButtonFlag(false);
        for (i = linkedList.size()-1; i>-1; i--) {
           if(name.compareTo(linkedList.get(i).getIngredientName())>0){
                linkedList.add(i+1,ingredient);
                notifyItemInserted(i+1);
                break;
           }
        }
        for (i=0; i<backupList.size(); i++) {
            if(name.compareTo(backupList.get(i).getIngredientName())==0){
                backupList.remove(i);
                break;
            }
        }
        for (i = backupList.size()-1; i>-1; i--) {
            if(name.compareTo(backupList.get(i).getIngredientName())>0){
                backupList.add(i+1,ingredient);
                break;
            }
        }

    }

}

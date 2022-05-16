package com.rektstudios.cooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    ArrayList<CategoryModel> arrayList;

    public CategoryAdapter(ArrayList<CategoryModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card,parent,false);
        return new CategoryViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        CategoryModel data=arrayList.get(position);
        holder.CategoryName.setText(data.getCategoryName());
        Context context=holder.CategoryCard.getContext();
        int white=ContextCompat.getColor(context,R.color.white), black = ContextCompat.getColor(context,R.color.black_2);
        if (data.isTouchFlag()) {
            holder.CategoryName.setTextColor(white);
            holder.CategoryCard.setCardBackgroundColor(black);
        } else {
            holder.CategoryName.setTextColor(black);
            holder.CategoryCard.setCardBackgroundColor(white);
        }
        holder.CategoryCard.setOnClickListener(v -> {
            if (data.isTouchFlag()) {
                holder.CategoryName.setTextColor(black);
                holder.CategoryCard.setCardBackgroundColor(white);
                data.setTouchFlag(false);
            } else {
                holder.CategoryName.setTextColor(white);
                holder.CategoryCard.setCardBackgroundColor(black);
                data.setTouchFlag(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CategoryViewHolder  extends  RecyclerView.ViewHolder{

        TextView CategoryName;
        CardView CategoryCard;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryName=itemView.findViewById(R.id.categoryName);
            CategoryCard=itemView.findViewById(R.id.categoryCard);
        }
    }
}

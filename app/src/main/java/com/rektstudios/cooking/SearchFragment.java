package com.rektstudios.cooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.rektstudios.cooking.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchView RecipeSearch;
    private ArrayList<RecipeModel> recipeModels;
    private ArrayList<CategoryModel> categoryModels;
    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;
    private RecyclerView categoryRecycler;
    private RecyclerView recipeRecycler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecipeSearch=root.findViewById(R.id.recipeSearchBar);
        RecipeSearch.clearFocus();
        RecipeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });


        categoryRecycler=root.findViewById(R.id.categoryRecyclerView);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        categoryRecycler.setHasFixedSize(true);

        categoryModels=new ArrayList<>();
        categoryModels.add(new CategoryModel("All"));
        categoryModels.add(new CategoryModel("Breakfast"));
        categoryModels.add(new CategoryModel("Lunch"));
        categoryModels.add(new CategoryModel("Dinner"));
        categoryModels.add(new CategoryModel("Snack"));

        categoryAdapter= new CategoryAdapter(categoryModels);
        categoryRecycler.setAdapter(categoryAdapter);

        recipeRecycler=root.findViewById(R.id.recipeRecyclerView);
        recipeRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        recipeRecycler.setHasFixedSize(true);

        recipeModels=((MainActivity) requireActivity()).getRecipeList();

        recipeAdapter= new RecipeAdapter(recipeModels);
        recipeRecycler.setAdapter(recipeAdapter);

        return root;
    }

    private void filterList(String text) {
        ArrayList<RecipeModel> filteredList= new ArrayList<>();
        for(RecipeModel item : recipeModels){
            if(item.getRecipeName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            recipeAdapter.setFilteredList(new ArrayList<>());
        }
        else{
            recipeAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
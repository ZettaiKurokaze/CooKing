package com.rektstudios.cooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rektstudios.cooking.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.LinkedList;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchView RecipeSearch;
    private LinkedList<IngredientModel> ingredients;
    private ArrayList<RecipeModel> recipeModels, mainModels;
    private ArrayList<CategoryModel> categoryModels;
    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;
    private RecyclerView categoryRecycler;
    private RecyclerView recipeRecycler;
    private RecipeAdapter.RecipeClickListener listener;


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

        mainModels=((MainActivity) requireActivity()).getRecipeList();
        filterByIngredients();
        listener= (v, position) -> {
            Fragment fragment = new RecipeFragment();Bundle args = new Bundle();
            args.putString("RecipeName", recipeModels.get(position).getRecipeName());
            fragment.setArguments(args);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragmentContainerView, fragment);
            transaction.addToBackStack(null).commit();
            BottomNavigationView bottomNavigation = root.findViewById(R.id.bottom_navigation);
        };
        recipeAdapter= new RecipeAdapter(recipeModels,listener);
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

    public void filterByIngredients(){
        ingredients=((MainActivity) requireActivity()).getIngredientsList();
        ArrayList<IngredientModel> list = new ArrayList<>();
        for(IngredientModel item : ingredients){
            if(item.isButtonFlag()){
                list.add(item);
            }
        }
        if(list.size()==0){
            recipeModels=mainModels;
            return;
        }
        recipeModels=new ArrayList<>();
        for(RecipeModel item : mainModels){
            ArrayList<IngredientModel> recipeList = item.getIngredients();
            if(recipeList.size()==0)
                break;
            Boolean flag=false;
            for (IngredientModel ing : list) {
                for (IngredientModel ing2 : recipeList) {
                    if(ing.getIngredientName().equals(ing2.getIngredientName())) {
                        recipeModels.add(item);
                        flag=true;
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.rekstudios.cooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.rekstudios.cooking.databinding.FragmentChooseBinding;

import java.util.LinkedList;

public class ChooseFragment extends Fragment {

    private RecyclerView IngredientRecyclerView;
    private FragmentChooseBinding binding;
    private SearchView ingredientSearch;
    private LinkedList<IngredientModel> ingredientModels;
    private IngredientsAdapter ingredientAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChooseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ingredientSearch=root.findViewById(R.id.ingredientSearch);
        ingredientSearch.clearFocus();
        ingredientSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        ingredientModels=((MainActivity) getActivity()).getIngredientsList();

        IngredientRecyclerView=root.findViewById(R.id.ingredientRecyclerView);
        IngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        IngredientRecyclerView.setHasFixedSize(true);



        ingredientAdapter= new IngredientsAdapter(ingredientModels);
        IngredientRecyclerView.setAdapter(ingredientAdapter);

        return root;
    }

    private void filterList(String text) {
        LinkedList<IngredientModel> filteredList= new LinkedList<>();
        for(IngredientModel item : ingredientModels){
            if(item.getIngredientName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            ingredientAdapter.setFilteredList(new LinkedList<>());
        }
        else{
            ingredientAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
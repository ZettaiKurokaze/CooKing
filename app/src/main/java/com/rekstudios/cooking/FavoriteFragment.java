package com.rekstudios.cooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rekstudios.cooking.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private SearchView favoriteSearch;
    private LinkedList<RecipeModel> favoriteModels;
    private FavoritesAdapter favoritesAdapter;
    private RecyclerView favoriteRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favoriteSearch=root.findViewById(R.id.favSearch);
        favoriteSearch.clearFocus();
        favoriteSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


        favoriteRecycler=root.findViewById(R.id.favRecyclerView);
        favoriteRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        favoriteRecycler.setHasFixedSize(true);

        favoriteModels=new LinkedList<>();
        InitFavorites();
        favoritesAdapter= new FavoritesAdapter(favoriteModels);
        favoriteRecycler.setAdapter(favoritesAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void InitFavorites(){
        ArrayList<RecipeModel> arrayList=((MainActivity) requireActivity()).getRecipeList();
        for(RecipeModel item : arrayList){
            if(item.isFavoriteFlag()){
                favoriteModels.add(item);
            }
        }
    }

    private void filterList(String text) {
        LinkedList<RecipeModel> filteredList= new LinkedList<>();
        for(RecipeModel item : favoriteModels){
            if(item.getRecipeName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            favoritesAdapter.setFilteredList(new LinkedList<>());
        }
        else{
            favoritesAdapter.setFilteredList(filteredList);
        }
    }
}
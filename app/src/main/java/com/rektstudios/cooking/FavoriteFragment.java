package com.rektstudios.cooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rektstudios.cooking.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;
import java.util.LinkedList;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private SearchView favoriteSearch;
    private LinkedList<RecipeModel> favoriteModels;
    private FavoritesAdapter favoritesAdapter;
    private RecyclerView favoriteRecycler;
    private FavoritesAdapter.RecipeClickListener listener;

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
        listener= (v, position) -> {
            Fragment fragment = new RecipeFragment();Bundle args = new Bundle();
            args.putString("RecipeName", favoriteModels.get(position).getRecipeName());
            fragment.setArguments(args);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragmentContainerView, fragment);
            transaction.addToBackStack(null).commit();
            BottomNavigationView bottomNavigation = root.findViewById(R.id.bottom_navigation);
        };
        favoritesAdapter= new FavoritesAdapter(favoriteModels, listener);
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
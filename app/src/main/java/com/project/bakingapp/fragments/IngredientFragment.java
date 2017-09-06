package com.project.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.bakingapp.ui.IngredientListAdapter;
import com.project.bakingapp.R;
import com.project.bakingapp.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientFragment extends Fragment {

    private Recipe mRecipe;

    @BindView(R.id.rv_ingredients)
    private RecyclerView mIngredientsRecyclerView;

    private IngredientListAdapter ingredientListAdapter;

    public IngredientFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_ingredient, container, false);
        ButterKnife.bind(this, rootView);
        initIngredientsRecyclerView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String RECIPE = "Recipe";
            mRecipe = bundle.getParcelable(RECIPE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mRecipe != null) {
            ingredientListAdapter.setmIngredients(mRecipe.getIngredients());
        }
    }

    private void initIngredientsRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(linearLayoutManager);
        mIngredientsRecyclerView.setHasFixedSize(true);
        ingredientListAdapter = new IngredientListAdapter();
        mIngredientsRecyclerView.setAdapter(ingredientListAdapter);
    }
}

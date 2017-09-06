package com.project.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.bakingapp.interfaces.OnItemClickListener;
import com.project.bakingapp.R;
import com.project.bakingapp.model.Recipe;
import com.project.bakingapp.network.StepListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepFragment extends Fragment implements OnItemClickListener {

    private final String LOG_TAG = StepFragment.class.getSimpleName();

    private Recipe mRecipe;

    @BindView(R.id.rv_steps)
    private RecyclerView mStepsRecyclerView;

    private StepListAdapter stepListAdapter;
    private OnRecipeStepClickListener mCallback;

    public StepFragment() {

    }

    public interface OnRecipeStepClickListener {
        void onRecipeStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnRecipeStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnRecipeStepClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_step, container, false);
        ButterKnife.bind(this, rootView);
        initStepsRecyclerView();
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
        if(mRecipe != null)
            stepListAdapter.setmRecipeSteps(mRecipe.getSteps());
    }

    private void initStepsRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mStepsRecyclerView.setLayoutManager(linearLayoutManager);
        mStepsRecyclerView.setHasFixedSize(true);
        stepListAdapter = new StepListAdapter(this);
        mStepsRecyclerView.setAdapter(stepListAdapter);
    }


    @Override
    public void onItemClicked(int position) {
        Log.d(LOG_TAG, mRecipe.getSteps().get(position).getShortDescription());
        mCallback.onRecipeStepSelected(position);
    }
}

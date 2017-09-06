package com.project.bakingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.bakingapp.R;
import com.project.bakingapp.fragments.ParentFragment;
import com.project.bakingapp.fragments.StepDetailFragment;
import com.project.bakingapp.fragments.StepFragment;
import com.project.bakingapp.model.Recipe;
import com.project.bakingapp.model.Step;

public class StepActivity extends AppCompatActivity implements StepFragment.OnRecipeStepClickListener {

    private static final String LOG_TAG = StepActivity.class.getSimpleName();
    private final String RECIPE = "Recipe";

    private Recipe mRecipe;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(findViewById(R.id.step_detail_linear_layout) != null) {
            mTwoPane = true;
            if(savedInstanceState == null) {
                if (getIntent() != null && getIntent().hasExtra(getString(R.string.recipe))) {
                    mRecipe = getIntent().getParcelableExtra(getString(R.string.recipe));

                }
            }
            else {
                mRecipe = savedInstanceState.getParcelable(RECIPE);
            }

            ParentFragment parentFragment = new ParentFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Recipe", mRecipe);
            parentFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, parentFragment)
                    .commit();

            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            Bundle stepBundle = new Bundle();
            stepBundle.putParcelable("Step", mRecipe.getSteps().get(0));
            stepDetailFragment.setArguments(stepBundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, stepDetailFragment)
                    .commit();

        } else {
            mTwoPane = false;

            if(savedInstanceState == null) {
                if (getIntent() != null && getIntent().hasExtra(getString(R.string.recipe))) {
                    mRecipe = getIntent().getParcelableExtra(getString(R.string.recipe));

                }
            }
            else {
                mRecipe = savedInstanceState.getParcelable(RECIPE);
            }
            ParentFragment parentFragment = new ParentFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Recipe", mRecipe);
            parentFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, parentFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RECIPE, mRecipe);
        //super.onSaveInstanceState(outState);
    }

    @Override
    public void onRecipeStepSelected(int position) {
        Log.d(LOG_TAG, "mTwoPane is " + mTwoPane);
        if(mRecipe != null) {
            Step step = mRecipe.getSteps().get(position);
            Log.d(LOG_TAG, step.getShortDescription());
            if(step != null) {
                if(!mTwoPane) {
                    Intent intent = new Intent(this, StepDetailActivity.class);
                    intent.putExtra(getString(R.string.step), step);
                    startActivity(intent);
                } else {
                    Bundle stepBundle = new Bundle();
                    stepBundle.putParcelable("Step", step);
                    StepDetailFragment stepDetailFragment = new StepDetailFragment();
                    stepDetailFragment.setArguments(stepBundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_detail_container, stepDetailFragment)
                            .commit();
                }
            }
        } else
            Log.d(LOG_TAG, "Recipe is null");
    }
}

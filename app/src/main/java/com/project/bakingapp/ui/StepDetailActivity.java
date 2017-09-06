package com.project.bakingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.bakingapp.R;
import com.project.bakingapp.fragments.StepDetailFragment;
import com.project.bakingapp.model.Step;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if(savedInstanceState == null) {
            if(getIntent().hasExtra(getString(R.string.step))) {
                Step step = getIntent().getParcelableExtra(getString(R.string.step));
                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Step", step);
                stepDetailFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_detail_container, stepDetailFragment)
                        .commit();
            }
        }
    }
}

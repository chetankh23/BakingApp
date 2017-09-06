package com.project.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentFragment extends Fragment {

    @BindView(android.R.id.tabhost)
    private FragmentTabHost mTabHost;

    public ParentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        if(bundle != null) {
            mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
            String INGREDIENTS_TAG = "Ingredients";
            mTabHost.addTab(mTabHost.newTabSpec("ingredients").setIndicator(INGREDIENTS_TAG), IngredientFragment.class, bundle);
            String STEPS_TAG = "Steps";
            mTabHost.addTab(mTabHost.newTabSpec("steps").setIndicator(STEPS_TAG), StepFragment.class, bundle);
        }
        return rootView;
    }
}

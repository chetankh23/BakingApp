package com.project.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.project.bakingapp.R;
import com.project.bakingapp.model.Step;


import butterknife.BindView;
import butterknife.ButterKnife;


public class StepDetailFragment extends Fragment {

    private final String LOG_TAG = StepDetailFragment.class.getSimpleName();

    @BindView(R.id.player_view)
    private SimpleExoPlayerView mPlayerView;

    @BindView(R.id.tv_stepdetail_description)
    private
    TextView mDescriptionTextView;

    private SimpleExoPlayer mExoPlayer;
    private Step mStep;

    public StepDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_step_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String STEP = "Step";
            mStep = bundle.getParcelable(STEP);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mStep != null) {
            Log.d(LOG_TAG, mStep.getVideoURL());
            Uri uri = Uri.parse(mStep.getVideoURL());
            initializePlayer(uri);
            mDescriptionTextView.setText(mStep.getDescription());
        }
    }

    private void initializePlayer(Uri mediaUri) {
        if(mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
/*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 270, getResources().getDisplayMetrics());
            mPlayerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
            //mDescriptionTextView.setVisibility(View.VISIBLE);
        } else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //mDescriptionTextView.setVisibility(View.GONE);
            mPlayerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }
*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}

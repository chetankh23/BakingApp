package com.project.bakingapp.network;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.bakingapp.interfaces.OnItemClickListener;
import com.project.bakingapp.R;
import com.project.bakingapp.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepItemViewHolder> {

    private List<Step> mRecipeSteps;
    private OnItemClickListener mOnItemClickListener;

    public StepListAdapter(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public StepItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new StepItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepItemViewHolder holder, int position) {
        holder.bind(mRecipeSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if(mRecipeSteps == null)
            return 0;

        return mRecipeSteps.size();
    }

    public class StepItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_step_description)
        TextView mStepDescriptionTextView;

        @BindView(R.id.iv_step_thumbnail)
        ImageView mStepThumbnailImageView;


        public StepItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(String stepDescription) {
            mStepDescriptionTextView.setText(stepDescription);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClicked(getAdapterPosition());
        }
    }

    public void setmRecipeSteps(List<Step> mRecipeSteps) {
        this.mRecipeSteps = mRecipeSteps;
        notifyDataSetChanged();
    }
}

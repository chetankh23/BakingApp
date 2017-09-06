package com.project.bakingapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.bakingapp.R;
import com.project.bakingapp.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientItemHolder> {

    private List<Ingredient> mIngredients;

    @Override
    public IngredientItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientItemHolder(rootView);
    }

    @Override
    public void onBindViewHolder(IngredientItemHolder holder, int position) {
        holder.bind(mIngredients.get(position));
    }

    @Override
    public int getItemCount() {
        if(mIngredients == null)
            return 0;

        return mIngredients.size();
    }

    class IngredientItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient_name)
        TextView mIngredientNameTextView;

        @BindView(R.id.tv_ingredient_quantity)
        TextView mIngredientQuantityTextView;

        @BindView(R.id.tv_ingredient_measure)
        TextView mIngredientMeasureTextView;

        public IngredientItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Ingredient ingredient) {
            mIngredientNameTextView.setText(ingredient.getIngredient());
            mIngredientQuantityTextView.setText(ingredient.getQuantity()+"");
            mIngredientMeasureTextView.setText(ingredient.getMeasure());
        }
    }

    public void setmIngredients(List<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
        notifyDataSetChanged();
    }
}

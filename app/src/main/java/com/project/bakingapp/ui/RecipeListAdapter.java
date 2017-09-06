package com.project.bakingapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.bakingapp.R;
import com.project.bakingapp.interfaces.OnItemClickListener;
import com.project.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>{

    private Context mContext;
    private List<Recipe> mRecipeList;
    private OnItemClickListener mOnItemClickListener;


    public RecipeListAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mRecipeList = new ArrayList<Recipe>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mRecipeList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mRecipeList == null)
             return 0;

        return mRecipeList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final ImageView mRecipeThumbnailImageView;
        final TextView mRecipeNameTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mRecipeThumbnailImageView = itemView.findViewById(R.id.iv_recipe_image);
            mRecipeNameTextView = itemView.findViewById(R.id.tv_recipe_name);
            itemView.setOnClickListener(this);
        }

        public void bind(Recipe recipe) {
            mRecipeNameTextView.setText(recipe.getName());
            String imageURL = recipe.getImage();
            if(imageURL != null && !TextUtils.isEmpty(imageURL))
                Picasso.with(mContext).load(imageURL).placeholder(R.drawable.recipe_placeholder).error(R.drawable.recipe_placeholder).into(mRecipeThumbnailImageView);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClicked(getAdapterPosition());
        }
    }

    public void setmRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}

package com.project.bakingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.project.bakingapp.R;
import com.project.bakingapp.interfaces.APIInterface;
import com.project.bakingapp.interfaces.OnItemClickListener;
import com.project.bakingapp.model.Recipe;
import com.project.bakingapp.network.APIClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_recipe_list)
    private RecyclerView mRecipeListRecyclerView;

    private RecipeListAdapter recipeListAdapter;
    private List<Recipe> mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        loadRecipes();
    }

    private void initUI() {
        recipeListAdapter = new RecipeListAdapter(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecipeListRecyclerView.setLayoutManager(layoutManager);
        mRecipeListRecyclerView.setHasFixedSize(true);
        mRecipeListRecyclerView.setAdapter(recipeListAdapter);
    }


    private void loadRecipes() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call call = apiInterface.getRecipeList();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if(response.code() == 200) {
                    mRecipeList = (List<Recipe>) response.body();
                    recipeListAdapter.setmRecipeList(mRecipeList);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(LOG_TAG, getString(R.string.recipe_list_error_message));
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Recipe recipe = mRecipeList.get(position);
        Log.d(LOG_TAG, recipe.getName());
        Log.d(LOG_TAG, recipe.getSteps().size() + "");
        Intent stepActivityIntent = new Intent(this, StepActivity.class);
        stepActivityIntent.putExtra(getString(R.string.recipe), recipe);
        startActivity(stepActivityIntent);
    }
}

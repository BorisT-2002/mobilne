package com.example.kolokvijum1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        notifyItemInserted(recipes.size() - 1);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.tvName.setText(recipe.getName());
        holder.tvPrepTime.setText(recipe.getPrepTime() + " min");
        holder.tvFavorite.setVisibility(recipe.isFavorite() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPrepTime;
        TextView tvFavorite;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_recipe_name);
            tvPrepTime = itemView.findViewById(R.id.tv_prep_time);
            tvFavorite = itemView.findViewById(R.id.tv_favorite);
        }
    }
}

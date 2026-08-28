package com.example.kolokvijum1;

public class Recipe {
    private final String name;
    private final int prepTime;
    private final boolean favorite;

    public Recipe(String name, int prepTime, boolean favorite) {
        this.name = name;
        this.prepTime = prepTime;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public boolean isFavorite() {
        return favorite;
    }
}

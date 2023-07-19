package com.example.recipefinder

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("hits") val hits: List<RecipeHit>
)

data class RecipeHit(
    @SerializedName("recipe") val recipe: Recipe
)

data class Recipe(
    @SerializedName("label") val title: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("ingredientLines") val ingredients: List<String>,
    @SerializedName("url") val instructions: String
)


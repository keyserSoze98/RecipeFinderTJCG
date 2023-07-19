package com.example.recipefinder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeSearchViewModel : ViewModel() {
    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> get() = _searchResults

    fun searchRecipes(query: String) {
        val appId = "bc3a3116"
        val appKey = "fa3d18988414465463554ee8083cab36"

        val call = RetrofitClient.edamamApiService.searchRecipes(query, appId, appKey)

        call.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    _searchResults.postValue(recipeResponse?.hits?.map { it.recipe })
                } else {
                    val errorMessage = response.message()
                    Log.e("API_ERROR", "Error: ${response.code()}, Message: $errorMessage")
                }
            }


            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Log.e("API_ERROR", "API Call Failed: ${t.message}")
            }
        })
    }
}


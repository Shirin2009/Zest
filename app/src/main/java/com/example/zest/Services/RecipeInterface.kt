package com.example.zest.Services

import com.example.zest.Models.RecipeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeInterface {
    @GET("api/")
    fun getSearchedRecipes(
        @Query("q") dish: String
    ): Deferred<RecipeResponse>
}
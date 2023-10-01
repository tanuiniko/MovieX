package com.applications.moviex.network

import com.applications.moviex.datamodel.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ) : Call<MovieResponse>
}
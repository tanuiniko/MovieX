package com.applications.moviex.datamodel

data class MovieResponse(val results : List<Movie>)

data class Movie(
    val title : String,
    val poster_path : String,
    val release_date : String
)

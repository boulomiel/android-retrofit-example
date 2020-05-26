package com.rubenmimoun.retrofitmoviedb

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("/json/movies.json")
    fun getMovieList() : Call<List<Movie>>
}
package com.rubenmimoun.retrofitmoviedb

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetriever{

    private var movieService : MovieService?  = null

    companion object{
        const val base_url ="https://api.androidhive.info/"
    }


    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            movieService = retrofit.create(MovieService::class.java)
    }


    fun getMovies(callback : Callback<List<Movie>>){
        val call = movieService!!.getMovieList()
        call.enqueue(callback)
    }
}
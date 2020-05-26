package com.rubenmimoun.retrofitmoviedb


data class Movie (
    val title: String,
    val image : String,
    val rating: Double,
    val releaseYear : Int,
    val genre : List<String>
) : Comparable<Movie>{


    override fun compareTo(other: Movie): Int {
        return this.releaseYear - other.releaseYear
    }
}




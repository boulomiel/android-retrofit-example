package com.rubenmimoun.retrofitmoviedb


// IF YOU USE THIS  => IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $ 

//data class MovieList (
//    val list : List<Movie>
//)

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




package com.rubenmimoun

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rubenmimoun.retrofitexamples.R
import com.rubenmimoun.retrofitmoviedb.Movie
import java.lang.StringBuilder

class AdapterMovie(private val context: Context, private val list :  List<Movie>) : RecyclerView.Adapter<AdapterMovie.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie =  list[position]
        val genreString =  genreString(movie.genre)

        holder.bind(movie.title, movie.releaseYear.toString(),genreString, movie.image)


    }



    private fun genreString( genresList: List<String>) : String{
        val stringBuilder  = StringBuilder()
        for (value in genresList){
            stringBuilder.append(value).append(" ")
        }
        return stringBuilder.toString()
    }


    inner class ViewHolder(itemView : View) :  RecyclerView.ViewHolder(itemView){

       private var titleView : TextView? = null
        private var releaseView : TextView? = null
        private var genresView : TextView? = null
        private  var imageView : ImageView? = null

        init {
            titleView = itemView.findViewById(R.id.title_item)
            releaseView = itemView.findViewById(R.id.release_date_item)
            genresView = itemView.findViewById(R.id.genres_item)
            imageView = itemView.findViewById(R.id.image)
        }


        fun bind(title :  String, release : String, genres : String, image: String){

            titleView!!.text = title
            releaseView!!.text = release
            genresView!!.text = genres

            Glide.with(context).load(image).into(imageView!!)

        }

    }
}
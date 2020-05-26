package com.rubenmimoun

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubenmimoun.retrofitexamples.R
import com.rubenmimoun.retrofitmoviedb.Movie
import com.rubenmimoun.retrofitmoviedb.MovieRetriever
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var adapter : AdapterMovie? = null
    private var movieList = arrayListOf<Movie>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (isNetworkConnected()) {
            repoRetriever.getMovies(callback)


        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    override fun onStart() {
        super.onStart()
        recycler.layoutManager = LinearLayoutManager(this)
    }

    // 1
    private val repoRetriever =
        MovieRetriever()

    //2
    private val callback = object : Callback<List<Movie>> {
        override fun onFailure(call: Call<List<Movie>>?, t:Throwable?) {
            Log.e("MainActivity", "Problem calling  API {${t?.message}}")
        }

        override fun onResponse(call: Call<List<Movie>>?, response: Response<List<Movie>>?) {
            response?.isSuccessful.let {

                 movieList = response!!.body() as ArrayList<Movie>
                movieList.sort()
                adapter = AdapterMovie(applicationContext, movieList)
                recycler.adapter = adapter


                Log.i("movie", movieList.toString())

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected(): Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}

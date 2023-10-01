package com.applications.moviex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applications.moviex.datamodel.MovieResponse
import com.applications.moviex.network.RetrofitClient
import com.applications.moviex.network.TmdbApi
import com.applications.moviex.recyclerview.MovieAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val API_KEY = "05f1bdd746834e678ee51f6abf41bf3a"
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.init(this, GlideBuilder()
            .setDefaultRequestOptions(RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            )
        )

        recyclerView=findViewById(R.id.recyclerViewMovie)
        recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

        val apiService =RetrofitClient.getRetrofitInstance().create(TmdbApi::class.java)
        Log.d("sunnn","inside oncreate")

        val call = apiService.getPopularMovies(API_KEY,1)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    val movies = response.body()?.results
                    adapter= MovieAdapter(this@MainActivity,movies!!)
                    recyclerView.adapter = adapter
                    Log.d("sunnn","movies from api = ${movies?.size}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("sunnn","failed")
                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_LONG).show()
            }

        })
    }
}

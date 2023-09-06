package com.example.moviewatchlist.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.MovieAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.moviewatchlist.R
import com.example.moviewatchlist.detail.DetailMovieActivity
import com.example.moviewatchlist.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            title = getString(R.string.favorite_movies)
            setDisplayHomeAsUpEnabled(true)
        }

        showFavoriteMovies()
    }

    private fun showFavoriteMovies() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedMovie ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedMovie)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this) { dataMovie ->
            movieAdapter.setData(dataMovie)
            binding.viewEmpty.root.visibility =
                if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}
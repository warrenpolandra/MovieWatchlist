package com.example.moviewatchlist.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.moviewatchlist.R
import com.example.moviewatchlist.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            binding.apply {
                // TextView and ImageView
                tvTitle.text = detailMovie.title
                tvReleaseDate.text = detailMovie.date
                tvRating.text = detailMovie.rating.toString()
                tvOverview.text = detailMovie.overview
                val imagePath = "https://image.tmdb.org/t/p/original${detailMovie.image}"
                Glide.with(this@DetailMovieActivity)
                    .load(imagePath)
                    .into(ivImage)

                // Button
                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)
                binding.btnFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

                btnShare.setOnClickListener {
                    val intent = Intent()
                    intent.apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Check out this amazing movie:\n$imagePath")
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(intent, "Share to:"))
                }
            }
        }
    }

    private fun setStatusFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite, 0, 0, 0)
        } else {
            binding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
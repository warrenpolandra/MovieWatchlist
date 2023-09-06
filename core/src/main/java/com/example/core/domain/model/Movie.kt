package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val image: String,
    val rating: Double,
    val date: String,
    val isFavorite: Boolean
) : Parcelable
package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers

// TODO correct service
interface ApiService {
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkM2Y4ZTY0MjA1NjU3NzNlYTZhMzllNmQwNjMzNTU5MSIsInN1YiI6IjY0ZDdhOThmYjZjMjY0MTE1NzUzYTZlMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DZxUJg40Y1xP0it-oh9BOEA1qifThEQArwHBPq3FpdE"
    )
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getList(): ListMovieResponse
}
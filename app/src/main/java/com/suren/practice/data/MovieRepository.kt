package com.suren.practice.data

import jakarta.inject.Inject

class MovieRepository @Inject constructor(val apiService: ApiService) {
    suspend fun getMovies() = apiService.getPosts()
}

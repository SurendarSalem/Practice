package com.suren.practice.data

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getPosts(): Response<List<Post>>
}
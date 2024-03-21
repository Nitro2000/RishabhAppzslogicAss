package com.bitspan.rishabhappzslogicass.data.service

import com.bitspan.rishabhappzslogicass.data.modal.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {

    @Headers("Content-Type: application/json")
    @GET("popular")
    suspend fun getMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): Response<MoviesResponse>

}
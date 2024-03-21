package com.bitspan.rishabhappzslogicass.data.repository

import com.bitspan.rishabhappzslogicass.data.modal.response.MoviesResponse
import com.bitspan.rishabhappzslogicass.data.service.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {


    suspend fun getMovies(lang: String, page: Int, authToken: String): Response<MoviesResponse> {
        return movieService.getMovies(lang, page, authToken)
    }

}
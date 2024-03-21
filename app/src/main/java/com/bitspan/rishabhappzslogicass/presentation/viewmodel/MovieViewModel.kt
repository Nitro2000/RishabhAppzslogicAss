package com.bitspan.rishabhappzslogicass.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitspan.rishabhappzslogicass.data.modal.response.MoviesResponse
import com.bitspan.rishabhappzslogicass.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    suspend fun getMovies(lang: String, page: Int, authToken: String): Response<MoviesResponse> {
        return movieRepository.getMovies(lang, page, authToken)
    }

}
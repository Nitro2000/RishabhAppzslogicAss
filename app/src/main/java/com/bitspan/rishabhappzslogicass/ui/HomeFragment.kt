package com.bitspan.rishabhappzslogicass.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bitspan.rishabhappzslogicass.DialogHelper
import com.bitspan.rishabhappzslogicass.R
import com.bitspan.rishabhappzslogicass.data.Constant
import com.bitspan.rishabhappzslogicass.data.modal.response.MovieData
import com.bitspan.rishabhappzslogicass.databinding.FragmentHomeBinding
import com.bitspan.rishabhappzslogicass.presentation.adapter.MovieImageAdapter
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.CommonViewModel
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context

    private val movieVM: MovieViewModel by viewModels()
    private lateinit var movieList: List<MovieData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        mContext = requireContext()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()

        binding.topBar.filterImg.setOnClickListener {
            DialogHelper.showFilterDialog(mContext) {
                Log.d("Rishabh", "it $it")
                if (it == 1) {
                    movieList = sortMoviesByTitle(movieList)
                    Log.d("Rishabh", " first title after: ${movieList[0].title}")
                    binding.movieRecView.adapter = MovieImageAdapter(movieList, mContext)
                } else {
                    movieList = sortMoviesByDate(movieList)
                    binding.movieRecView.adapter = MovieImageAdapter(movieList, mContext)
                }
            }
        }

    }

    private fun sortMoviesByTitle(movies: List<MovieData>): List<MovieData> {
        return movies.sortedBy { it.title }
    }



    fun sortMoviesByDate(movies: List<MovieData>): List<MovieData> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return movies.sortedBy {
            val movieDate = Calendar.getInstance().apply {
            time = formatter.parse(it.releaseDate ?: "2023-01-01") ?: Date()
        }
            movieDate.timeInMillis
        }
    }


    private fun getMovies() {
        startShimmer()
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                movieVM.getMovies(lang = Constant.language, page = Constant.page, authToken = "Bearer ${Constant.token}").let {
                    if (it.isSuccessful && it.body() != null) {
                        stopShimmer()
                        movieList = it.body()!!.results ?: listOf()
                        Log.d("Rishabh", " first title before: ${movieList[0].title}")
                        binding.movieRecView.adapter = MovieImageAdapter(movieList, mContext)
                    } else {
                        stopShimmer()
                        binding.notFoundTxt.visibility = View.VISIBLE
                        binding.movieNotFound.visibility = View.VISIBLE
                    }
                }

            } catch (e: Exception) {
                stopShimmer()
                binding.notFoundTxt.visibility = View.VISIBLE
                binding.movieNotFound.visibility = View.VISIBLE

            }
        }
    }

    private fun startShimmer() {
        binding.apply {
            shimmer.startShimmer()
            shimmer.visibility = View.VISIBLE
            movieRecView.visibility = View.GONE
        }
    }

    private fun stopShimmer() {
        binding.apply {
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            movieRecView.visibility = View.VISIBLE
        }
    }

}
package com.bitspan.rishabhappzslogicass.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bitspan.rishabhappzslogicass.R
import com.bitspan.rishabhappzslogicass.data.Constant
import com.bitspan.rishabhappzslogicass.databinding.FragmentHomeBinding
import com.bitspan.rishabhappzslogicass.presentation.adapter.MovieImageAdapter
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.CommonViewModel
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context

    private val movieVM: MovieViewModel by viewModels()

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

    }


    private fun getMovies() {
        startShimmer()
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                movieVM.getMovies(lang = Constant.language, page = Constant.page, authToken = "Bearer ${Constant.token}").let {
                    if (it.isSuccessful && it.body() != null) {
                        stopShimmer()
                        binding.movieRecView.adapter = MovieImageAdapter(it.body()!!.results ?: listOf(), mContext)
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
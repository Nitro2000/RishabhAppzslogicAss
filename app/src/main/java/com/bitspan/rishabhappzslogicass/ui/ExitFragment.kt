package com.bitspan.rishabhappzslogicass.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bitspan.rishabhappzslogicass.R
import com.bitspan.rishabhappzslogicass.databinding.FragmentExitBinding
import com.bitspan.rishabhappzslogicass.databinding.FragmentHomeBinding
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExitFragment : Fragment() {


    private lateinit var binding: FragmentExitBinding
    private lateinit var mContext: Context


    private val viewModel: CommonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExitBinding.inflate(inflater, container, false)
        mContext = requireContext()


        return binding.root
    }

    private fun getCount() {
        viewModel.getCount().observe(viewLifecycleOwner) {
            viewModel.count = it
            binding.timesTxt.text = getString(R.string.d_n_n_times, viewModel.count)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.count == null) getCount()
        else {
            binding.timesTxt.text = getString(R.string.d_n_n_times, viewModel.count)
        }

        binding.closeBtn.setOnClickListener {
            requireActivity().finish()
        }

    }

}
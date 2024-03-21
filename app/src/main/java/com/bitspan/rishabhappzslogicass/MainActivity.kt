package com.bitspan.rishabhappzslogicass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bitspan.rishabhappzslogicass.databinding.ActivityMainBinding
import com.bitspan.rishabhappzslogicass.presentation.viewmodel.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CommonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navController) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)



        bindObserver()
        getCount()


    }


    private fun bindObserver() {
        viewModel.count.observe(this) {
            viewModel.saveCountToStorage(it + 1)
        }
    }

    private fun getCount() {
        viewModel.getCountFromStorage().observe(this) {
            if (!viewModel.openBool) {
                viewModel.openBool = true
                viewModel.updateCount(it)
            }
        }
    }
}
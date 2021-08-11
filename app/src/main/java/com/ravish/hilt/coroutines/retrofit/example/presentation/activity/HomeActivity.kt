package com.ravish.hilt.coroutines.retrofit.example.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ravish.hilt.coroutines.retrofit.example.R
import com.ravish.hilt.coroutines.retrofit.example.data.model.User
import com.ravish.hilt.coroutines.retrofit.example.databinding.ActivityHomeBinding
import com.ravish.hilt.coroutines.retrofit.example.presentation.model.UIState
import com.ravish.hilt.coroutines.retrofit.example.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel? by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeFlowData()
        homeViewModel?.loadUserData()
    }

    private fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            homeViewModel?.userFlow?.collect { state ->
                when (state) {
                    is UIState.Loading -> {
                        showProgress()
                    }
                    is UIState.Success -> {
                        hideProgress()
                        state.data?.let { updateUI(it) } ?: showError()
                    }
                    is UIState.Error -> {
                        hideProgress()
                        showError()
                    }
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        binding.errorMessage.text = getString(R.string.error_message)
        binding.errorMessage.visibility = View.VISIBLE
    }

    private fun updateUI(user: User) {
        with(binding) {
            name.text = user.name
            location.text = user.location
            followers.text = getString(R.string.followers, user.followers)
            Glide.with(this@HomeActivity).load(user.avatar_url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }
    }
}
package com.manishbhati.baseproject.activity

import android.os.Bundle
import android.view.View
import com.manishbhati.baseproject.R
import com.manishbhati.baseproject.databinding.ActivityMainBinding
import com.manishbhati.baseproject.helper.Helper
import com.manishbhati.baseproject.helper.NetworkHelper
import com.manishbhati.data.network.SessionManager
import com.manishbhati.domain.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), Helper {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calculateArea(5)
    }

    fun calculateArea(radius: Int): Double {
        return Math.PI * radius * radius
    }

    override fun toggleProgress(show: Boolean) {
        if (show) showProgress()
        else hideProgress()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showError(msg: String?) {
        showShortToast(msg ?: getString(R.string.error_msg_common))
    }

}

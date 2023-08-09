package com.example.technicaltask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.technicaltask.App
import com.example.technicaltask.R
import com.example.technicaltask.databinding.ActivityMainBinding
import com.example.technicaltask.utils.gone
import com.example.technicaltask.utils.visible

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.tvId.text = getString(R.string.id, App.prefs.getId(), App.prefs.getUuid())
        initListeners()
    }

    private fun initListeners() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                viewModel.handleRedirect(url ?: "")
                viewModel.hideProgressBar()
                binding.tvId.text = getString(R.string.id, App.prefs.getId(), App.prefs.getUuid())
                binding.webView.visible()
                binding.btnStart.gone()
            }
        }
        binding.webView.webChromeClient = WebChromeClient()

        binding.btnStart.setOnClickListener {
            viewModel.showProgressBar()
            viewModel.loadWebPage()
        }
        viewModel.webView.observe(this) {
            binding.webView.loadUrl(it)
        }

        viewModel.isLoading.observe(this) { loading ->
            binding.progress.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}
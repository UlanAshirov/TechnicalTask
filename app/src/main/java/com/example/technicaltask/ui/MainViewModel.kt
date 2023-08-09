package com.example.technicaltask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltask.App
import com.example.technicaltask.BuildConfig.WEB_URL
import java.net.URL

class MainViewModel : ViewModel() {

    val webView = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadWebPage() {
        isLoading.value = true
        webView.value = WEB_URL
    }


    fun handleRedirect(url: String) {
        val parsedUrl = URL(url)
        val query = parsedUrl.query
        val queryParams = query?.split("&") ?: emptyList()

        for (param in queryParams) {
            val keyValue = param.split("=")
            if (keyValue.size == 2) {
                val key = keyValue[0]
                val value = keyValue[1]
                when (key) {
                    "id" -> App.prefs.saveId(value)
                    "uuid" -> App.prefs.saveUuid(value)
                }
            }
        }
        hideProgressBar()
    }

    fun hideProgressBar() {
        isLoading.value = false
    }

    fun showProgressBar() {
        isLoading.value = true
    }
}
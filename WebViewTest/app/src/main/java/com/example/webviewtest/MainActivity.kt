package com.example.webviewtest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var currentWebsiteUrl: String
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyWebViewPreferences", Context.MODE_PRIVATE)
        webView = findViewById(R.id.webView)

        // Load the initial website or the last visited website
        currentWebsiteUrl = sharedPreferences.getString("lastVisitedWebsite", "https://www.amazon.com").toString()
        currentWebsiteUrl?.let { loadWebsite(it) }
    }

//    override fun onPause() {
//        super.onPause()
//
////        webView.apply {
//////            clearMatches()
//////            clearHistory()
//////            clearFormData()
//////            clearSslPreferences()
//////            clearCache(true)
////
//////            CookieManager.getInstance().removeAllCookies(null)
////////            WebStorage.getInstance().deleteAllData()
////        }
//    }

    private fun loadWebsite(url: String) {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Check if the user has saved credentials for the current website
        val savedUsername = sharedPreferences.getString(url + "_username", null)
        val savedPassword = sharedPreferences.getString(url + "_password", null)
        if (savedUsername != null && savedPassword != null) {
            // Automatically fill in the login form with saved credentials
            val javascript =
                "javascript:document.getElementById('username').value = '$savedUsername';" +
                        "document.getElementById('password').value = '$savedPassword';"
            webView.loadUrl(javascript)
        }
        webView.loadUrl(url)
    }

    private fun saveCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(currentWebsiteUrl + "_username", username)
        editor.putString(currentWebsiteUrl + "_password", password)
        editor.putString("lastVisitedWebsite", currentWebsiteUrl)
        editor.apply()
    }

    private fun onUserLoggedIn(username: String, password: String) {
        saveCredentials(username, password)
    }
}

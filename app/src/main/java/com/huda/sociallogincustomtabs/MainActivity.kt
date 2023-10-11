package com.huda.sociallogincustomtabs

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.huda.sociallogincustomtabs.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Button click to open the Custom Tab
        binding.loginButton.setOnClickListener {
            val urlToOpen = "https://webdev.visionplus.id/auth/login" // Replace with your desired URL

            openCustomTab(urlToOpen)
        }
    }

    private fun openCustomTab(url: String) {
        val packageName = "com.android.chrome" // Package name for Google Chrome
        val customTabsBuilder = CustomTabsIntent.Builder()
        customTabsBuilder.setShowTitle(true)

        // Check if Google Chrome is installed and supported on the device
        if (isPackageInstalled(packageName, this)) {
            customTabsBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.purple_200))

            // Create an intent to launch the custom tab with Google Chrome
            val customTabsIntent = CustomTabsIntent.Builder().build()

            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(this, Uri.parse(url))
        } else {
            // Handle the case where Google Chrome is not installed
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun isPackageInstalled(packageName: String, context: Context): Boolean {
        val packageManager = context.packageManager
        val installedPackages = packageManager.getInstalledPackages(0)
        for (packageInfo in installedPackages) {
            if (packageName == packageInfo.packageName) {
                return true
            }
        }
        return false
    }
}
package com.projectgoth.ui.activity

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.projectgoth.R
import com.projectgoth.app.ApplicationEx
import com.projectgoth.datastore.AlertsDatastore
import com.projectgoth.datastore.ChatDatastore
import com.projectgoth.datastore.PostsDatastore

/**
 * Show splash UI and initial essential modules here to avoid white page
 * Converted to Kotlin - Phase 1 Migration
 */
class SplashActivity : Activity() {

    private val mLazyInitiization = Runnable { lazyInitiize() }

    companion object {
        private const val SPLASH_DISPLAY_DELAY = 300 // 300ms
        private var sIsSplashDisplayed = false

        @JvmStatic
        fun resetSplashDisplay() {
            sIsSplashDisplayed = false
        }

        @JvmStatic
        fun isSplashDisplayed(): Boolean = sIsSplashDisplayed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler(Looper.getMainLooper())
        val imageBackground = findViewById<ImageView>(R.id.splash_background)
        // let splash screen sustains at least 300ms, otherwise it may only "splash"
        handler.postDelayed(mLazyInitiization, SPLASH_DISPLAY_DELAY.toLong())
        imageBackground.visibility = View.GONE
    }

    private fun lazyInitiize() {
        val receivedIntent = intent
        val action = receivedIntent.action
        val flags = receivedIntent.flags
        val isFromLauncher = receivedIntent.hasCategory(Intent.CATEGORY_LAUNCHER) &&
                action != null && action == Intent.ACTION_MAIN
        val isLaunchFromHistory = (flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0

        val launchMainDrawer: Intent = if (isFromLauncher || isLaunchFromHistory) {
            Intent(this, MainDrawerLayoutActivity::class.java)
        } else {
            Intent(receivedIntent).apply {
                component = ComponentName(this@SplashActivity, MainDrawerLayoutActivity::class.java)
            }
        }

        val app = ApplicationEx.getInstance()
        if (!app.isInitilized) {
            app.initialize()
        }

        // Trigger lazy initialization of notification handler
        app.notificationHandler
        AlertsDatastore.getInstance()
        PostsDatastore.getInstance()
        ChatDatastore.getInstance()
        startActivity(launchMainDrawer)
        sIsSplashDisplayed = true
        finish()
    }
}

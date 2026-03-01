package com.projectgoth.ui.activity

import android.test.AndroidTestCase

/**
 * Tests for SplashActivity (Kotlin)
 */
class SplashActivityTest : AndroidTestCase() {

    override fun setUp() {
        super.setUp()
        SplashActivity.resetSplashDisplay()
    }

    override fun tearDown() {
        super.tearDown()
        SplashActivity.resetSplashDisplay()
    }

    fun testSplashDisplayedFlagInitiallyFalse() {
        assertFalse(SplashActivity.isSplashDisplayed())
    }

    fun testResetSplashDisplaySetsToFalse() {
        SplashActivity.resetSplashDisplay()
        assertFalse(SplashActivity.isSplashDisplayed())
    }
}

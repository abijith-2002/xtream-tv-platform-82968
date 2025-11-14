package com.example.android_tv_frontend

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.view.KeyEvent
import com.example.android_tv_frontend.ui.home.HomeFragment

/**
 * Main Activity for Android TV
 * Extends FragmentActivity for Leanback/TV compatibility.
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commitNow()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Delegate to fragment when possible for contextual behavior.
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is HomeFragment && fragment.onHardwareKeyDown(keyCode, event)) {
            return true
        }

        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                // Let fragment handle back stack/focus management first
                if (fragment is HomeFragment && fragment.handleBackPressed()) {
                    return true
                }
                super.onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}

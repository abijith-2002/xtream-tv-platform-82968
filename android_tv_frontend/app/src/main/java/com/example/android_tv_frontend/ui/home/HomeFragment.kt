package com.example.android_tv_frontend.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.android_tv_frontend.ui.theme.XTreamTVTheme

/**
 * Fragment that hosts the Compose-based TV Home screen.
 * Uses a ComposeView to integrate Compose within a FragmentActivity.
 */
class HomeFragment : Fragment() {

    private var requestFocusSidebar: (() -> Unit)? = null
    private var isSidebarFocused: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                XTreamTVTheme {
                    val sidebarFocusState = remember { mutableStateOf(true) }
                    requestFocusSidebar = {
                        sidebarFocusState.value = true
                    }
                    isSidebarFocused = sidebarFocusState.value

                    HomeScreen(
                        sidebarFocusState = sidebarFocusState
                    )
                }
            }
        }
    }

    /**
     * Handle DPAD/back at the Activity level and route to Compose where needed.
     */
    fun onHardwareKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Could intercept specific keys if needed.
        return false
    }

    /**
     * When back is pressed, ensure focus returns to the sidebar first.
     */
    fun handleBackPressed(): Boolean {
        return if (!isSidebarFocused) {
            requestFocusSidebar?.invoke()
            true
        } else {
            false
        }
    }
}

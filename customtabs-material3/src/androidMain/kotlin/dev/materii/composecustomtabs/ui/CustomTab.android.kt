@file:SuppressLint("SetJavaScriptEnabled")

package dev.materii.composecustomtabs.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.ui.material3.CustomTab

@Composable
actual fun CustomTab(
    state: CustomTabState,
    modifier: Modifier
) {
    CustomTab(
        state = state,
        modifier = modifier,
        webviewSettings = { javaScriptEnabled = true }
    )
}
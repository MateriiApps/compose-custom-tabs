@file:SuppressLint("SetJavaScriptEnabled")

package dev.materii.composecustomtabs.ui

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
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
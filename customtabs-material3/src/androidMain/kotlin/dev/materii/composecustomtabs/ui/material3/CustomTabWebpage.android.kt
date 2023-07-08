@file:SuppressLint("SetJavaScriptEnabled")
package dev.materii.composecustomtabs.ui.material3

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
fun AndroidCustomTabWebpage(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    webviewSettings: WebSettings.() -> Unit = { javaScriptEnabled = true }
) {
    WebView(
        state = state.state,
        navigator = state.navigator,
        client = state.client,
        chromeClient = state.webChromeClient,
        modifier = Modifier.fillMaxSize()
    ) { ctx ->
        WebView(ctx).apply {
            settings.apply(webviewSettings)
        }
    }
}

@Composable
actual fun CustomTabWebpage(
    state: CustomTabState,
    modifier: Modifier
) {
    AndroidCustomTab(state, modifier)
}
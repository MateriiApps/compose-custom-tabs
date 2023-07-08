@file:SuppressLint("SetJavaScriptEnabled")

package dev.materii.composecustomtabs.ui

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState

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

@Composable
fun CustomTab(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    webviewSettings: WebSettings.() -> Unit = { javaScriptEnabled = true },
    titleBar: ComposableContent = { AndroidCustomTabTitleBar(state) },
    webpage: ComposableContent = {
        AndroidCustomTabWebpage(
            state,
            webviewSettings = webviewSettings
        )
    }
) {
    CustomTab(
        state = state,
        modifier = modifier,
        titleBar = titleBar,
        webpage = webpage
    )
}


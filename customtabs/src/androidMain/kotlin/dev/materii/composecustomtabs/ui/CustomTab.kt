@file:SuppressLint("SetJavaScriptEnabled")

package dev.materii.composecustomtabs.ui

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.web.WebView
import dev.materii.composecustomtabs.ext.android.uri
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.state.LoadState

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


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
    Scaffold(
        topBar = {
            Column {
                titleBar()

                (state.loadState as? LoadState.Loading)?.let {
                    LinearProgressIndicator(
                        progress = it.progress,
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.primarySurface,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize()) {
            webpage()
        }
    }
}

@Composable
fun AndroidCustomTabTitleBar(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    title: ComposableContent = { Text(state.title ?: "No Title", maxLines = 1, overflow = TextOverflow.Ellipsis) },
    subTitle: ComposableContent = { Text(state.uri.host?: "about:blank") },
    navigationButton: ComposableContent? = null,
    actions: RowContent = {}
) {
    TopAppBar(
        title = {
            Column(
            ) {
                title()
                ProvideTextStyle(
                    MaterialTheme.typography.subtitle2.copy(
                        color = LocalContentColor.current.copy(alpha = 0.6f)
                    )
                ) {
                    subTitle()
                }
            }
        },
        modifier = modifier,
        actions = actions,
        navigationIcon = navigationButton
    )
}

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
        android.webkit.WebView(ctx).apply {
            settings.apply(webviewSettings)
        }
    }
}
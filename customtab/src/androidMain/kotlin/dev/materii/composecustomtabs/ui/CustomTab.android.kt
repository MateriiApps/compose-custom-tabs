package dev.materii.composecustomtabs.ui

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView
import dev.materii.composecustomtabs.ext.android.uri
import dev.materii.composecustomtabs.state.AndroidCustomTabState
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
fun AndroidCustomTab(
    state: CustomTabState,
    titleBar: ComposableContent = { AndroidCustomTabTitleBar(state) },
    webpage: ComposableContent = { AndroidCustomTabWebpage(state) }
) {
    Scaffold(
        topBar = {
            Column {
                titleBar()

                (state.loadState as? CustomTabState.LoadState.Loading)?.let {
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
    @SuppressLint("SetJavaScriptEnabled") webviewSettings: WebSettings.() -> Unit = { javaScriptEnabled = true }
) {
    if(state !is AndroidCustomTabState) {
        Box(modifier = Modifier.fillMaxSize()) {

        }
    } else {
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
}

@Composable
actual fun CustomTab(
    state: CustomTabState
) {
    AndroidCustomTab(state)
}
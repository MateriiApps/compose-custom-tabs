@file:SuppressLint("SetJavaScriptEnabled")
package dev.materii.composecustomtabs.ui.material3

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.state.LoadState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTab(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    webviewSettings: WebSettings.() -> Unit = { javaScriptEnabled = true },
    titleBar: @Composable () -> Unit = { CustomTabTitleBar(state) },
    webpage: @Composable () -> Unit = {
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
                        trackColor = MaterialTheme.colorScheme.primaryContainer,
                        color = MaterialTheme.colorScheme.primary
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
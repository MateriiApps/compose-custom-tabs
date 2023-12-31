package dev.materii.composecustomtabs.ui.material3

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
fun BaseCustomTab(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    titleBar: @Composable () -> Unit = { BaseCustomTabTitleBar(state) },
    webpage: @Composable () -> Unit = { CustomTabWebpage(state) }
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
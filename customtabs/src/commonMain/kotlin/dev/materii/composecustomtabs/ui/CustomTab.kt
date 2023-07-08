package dev.materii.composecustomtabs.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.state.LoadState

@Composable
expect fun CustomTab(
    state: CustomTabState,
    modifier: Modifier
)

@Composable
fun CustomTab(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    titleBar: ComposableContent = { },
    webpage: ComposableContent = {}
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
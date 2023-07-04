package dev.materii.composecustomtabs.ui

import androidx.compose.runtime.Composable
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
expect fun CustomTab(
    state: CustomTabState
)
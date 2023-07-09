package dev.materii.composecustomtabs.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
expect fun CustomTab(
    state: CustomTabState,
    modifier: Modifier = Modifier
)
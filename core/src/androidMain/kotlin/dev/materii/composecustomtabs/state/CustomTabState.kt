package dev.materii.composecustomtabs.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberCustomTabState(url: String): CustomTabState = remember {
    AndroidCustomTabState(url)
}
package dev.materii.composecustomtabs.state

import androidx.compose.runtime.Composable

expect class CustomTabState {

    var title: String?
        internal set

    var url: String?
        internal set

    var loadState: LoadState
        internal set

    fun navigate(url: String)

}

sealed interface LoadState {
    object Loaded : LoadState
    data class Loading(val progress: Float = 0f) : LoadState
}

@Composable
expect fun rememberCustomTabState(url: String): CustomTabState
package dev.materii.composecustomtabs.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy

abstract class CustomTabState {

    open var title: String? by mutableStateOf(null)
        internal set

    open var url: String? by mutableStateOf(null)
        internal set

    open var loadState by mutableStateOf<LoadState>(LoadState.Loaded)
        internal set

    abstract fun navigate(url: String)

    sealed interface LoadState {
        object Loaded : LoadState
        data class Loading(val progress: Float = 0f): LoadState
    }

}

@Composable
expect fun rememberCustomTabState(url: String): CustomTabState
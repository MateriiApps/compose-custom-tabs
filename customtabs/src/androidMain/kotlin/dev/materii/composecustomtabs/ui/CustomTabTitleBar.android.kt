package dev.materii.composecustomtabs.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import dev.materii.composecustomtabs.ext.android.uri
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
fun AndroidCustomTabTitleBar(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    title: ComposableContent = { Text(state.title ?: "No Title", maxLines = 1, overflow = TextOverflow.Ellipsis) },
    subTitle: ComposableContent = { Text(state.uri.host?: "about:blank") },
    navigationButton: ComposableContent? = null,
    actions: RowContent = {}
) {
    CustomTabTitleBar(
        state = state,
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        navigationButton = navigationButton,
        actions = actions
    )
}
package dev.materii.composecustomtabs.ui.material3

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import dev.materii.composecustomtabs.ext.android.uri
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
fun CustomTabTitleBar(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { Text(state.title ?: "No Title", maxLines = 1, overflow = TextOverflow.Ellipsis) },
    subTitle: @Composable () -> Unit = { Text(state.uri.host?: "about:blank") },
    navigationButton: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    BaseCustomTabTitleBar(
        state = state,
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        navigationButton = navigationButton,
        actions = actions
    )
}
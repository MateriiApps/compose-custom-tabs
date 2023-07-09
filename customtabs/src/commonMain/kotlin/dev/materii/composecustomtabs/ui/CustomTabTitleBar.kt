package dev.materii.composecustomtabs.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
fun BaseCustomTabTitleBar(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    title: ComposableContent = { Text(state.title ?: "No Title", maxLines = 1, overflow = TextOverflow.Ellipsis) },
    subTitle: ComposableContent = { Text(state.url ?: "about:blank") },
    navigationButton: ComposableContent? = null,
    actions: RowContent = {}
) {
    TopAppBar(
        title = {
            Column(
            ) {
                title()
                ProvideTextStyle(
                    MaterialTheme.typography.subtitle2.copy(
                        color = LocalContentColor.current.copy(alpha = 0.6f)
                    )
                ) {
                    subTitle()
                }
            }
        },
        modifier = modifier,
        actions = actions,
        navigationIcon = navigationButton
    )
}
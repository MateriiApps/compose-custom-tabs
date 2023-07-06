package dev.materii.composecustomtabs.ui.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import dev.materii.composecustomtabs.ext.android.uri
import dev.materii.composecustomtabs.state.CustomTabState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTabTitleBar(
    state: CustomTabState,
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { Text(state.title ?: "No Title", maxLines = 1, overflow = TextOverflow.Ellipsis) },
    subTitle: @Composable () -> Unit = { Text(state.uri.host?: "about:blank") },
    navigationButton: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Column(
            ) {
                title()
                ProvideTextStyle(
                    MaterialTheme.typography.labelLarge.copy(
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
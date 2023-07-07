package dev.materii.composecustomtabs.ui.material3

import android.webkit.WebSettings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.materii.composecustomtabs.state.CustomTabState

@Composable
expect fun CustomTabWebpage(
    state: CustomTabState,
    modifier: Modifier = Modifier,
)
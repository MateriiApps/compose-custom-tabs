package dev.materii.composecustomtabs.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle

internal typealias ComposableContent = @Composable () -> Unit

internal typealias RowContent = @Composable RowScope.() -> Unit
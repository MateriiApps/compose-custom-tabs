package dev.materii.demo.composecustomtab.ui.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.materii.composecustomtabs.state.rememberCustomTabState
import dev.materii.composecustomtabs.ui.material3.BaseCustomTab
import dev.materii.composecustomtabs.ui.material3.BaseCustomTabTitleBar
import dev.materii.composecustomtabs.ui.material3.CustomTab
import dev.materii.composecustomtabs.ui.material3.CustomTabTitleBar
import dev.materii.demo.composecustomtab.ui.theme.Material3Theme

class Material3Demo(private val url: String): Screen {

    @Composable
    override fun Content() {
        val state = rememberCustomTabState(url)
        val nav = LocalNavigator.currentOrThrow

        Material3Theme {
            CustomTab(
                state,
                titleBar = {
                    CustomTabTitleBar(
                        state,
                        navigationButton = {
                            IconButton(onClick = { nav.pop() }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                            }
                        }
                    )
                }
            )
        }
    }

}
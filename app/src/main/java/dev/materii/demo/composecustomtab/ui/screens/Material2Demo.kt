package dev.materii.demo.composecustomtab.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.materii.composecustomtabs.state.rememberCustomTabState
import dev.materii.composecustomtabs.ui.AndroidCustomTab
import dev.materii.composecustomtabs.ui.AndroidCustomTabTitleBar
import dev.materii.demo.composecustomtab.ui.theme.Material2Theme

class Material2Demo(private val url: String): Screen {

    @Composable
    override fun Content() {
        val state = rememberCustomTabState(url)
        val nav = LocalNavigator.currentOrThrow

        Material2Theme {
            AndroidCustomTab(
                state,
                titleBar = {
                    AndroidCustomTabTitleBar(
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
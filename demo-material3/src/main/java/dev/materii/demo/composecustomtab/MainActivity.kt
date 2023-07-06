package dev.materii.demo.composecustomtab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import dev.materii.demo.composecustomtab.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(MainScreen())
        }
    }
}
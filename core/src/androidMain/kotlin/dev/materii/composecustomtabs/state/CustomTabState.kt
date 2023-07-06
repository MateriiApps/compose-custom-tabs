package dev.materii.composecustomtabs.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState
import dev.materii.composecustomtabs.webview.WebChromeClient
import dev.materii.composecustomtabs.webview.WebViewClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual class CustomTabState(url: String) {

    private val webViewScope = CoroutineScope(Dispatchers.IO)

    val state: WebViewState = WebViewState(WebContent.Url(url))
    val navigator: WebViewNavigator = WebViewNavigator(webViewScope)
    val client: WebViewClient = WebViewClient(this)
    val webChromeClient: WebChromeClient = WebChromeClient(this)

    actual var title by mutableStateOf<String?>(null, structuralEqualityPolicy())

    actual var url by mutableStateOf<String?>(null, structuralEqualityPolicy())

    actual var loadState: LoadState by mutableStateOf(LoadState.Loaded, structuralEqualityPolicy())

    actual fun navigate(url: String) {
        navigator.loadUrl(url)
    }

}

@Composable
actual fun rememberCustomTabState(url: String): CustomTabState = remember {
    CustomTabState(url)
}
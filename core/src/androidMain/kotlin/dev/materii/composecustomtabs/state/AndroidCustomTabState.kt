package dev.materii.composecustomtabs.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState
import dev.materii.composecustomtabs.webview.WebChromeClient
import dev.materii.composecustomtabs.webview.WebViewClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AndroidCustomTabState(url: String): CustomTabState() {

    private val webViewScope = CoroutineScope(Dispatchers.IO)

    val state: WebViewState = WebViewState(WebContent.Url(url))
    val navigator: WebViewNavigator = WebViewNavigator(webViewScope)
    val client: WebViewClient = WebViewClient(this)
    val webChromeClient: WebChromeClient = WebChromeClient(this)

    override var title by mutableStateOf<String?>(null, structuralEqualityPolicy())

    override var url by mutableStateOf<String?>(null, structuralEqualityPolicy())

    override fun navigate(url: String) {
        navigator.loadUrl(url)
    }

}
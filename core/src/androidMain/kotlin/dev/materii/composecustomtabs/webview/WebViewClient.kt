package dev.materii.composecustomtabs.webview

import android.graphics.Bitmap
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import dev.materii.composecustomtabs.state.AndroidCustomTabState
import dev.materii.composecustomtabs.state.CustomTabState

class WebViewClient(private val customTabState: AndroidCustomTabState): AccompanistWebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        customTabState.loadState = CustomTabState.LoadState.Loaded
        customTabState.url = url ?: ""
        customTabState.title = view?.title ?: ""
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        customTabState.loadState = CustomTabState.LoadState.Loading(0f)
    }

}
package dev.materii.composecustomtabs.webview

import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebChromeClient
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.state.LoadState

class WebChromeClient(private val customTabState: CustomTabState) : AccompanistWebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (customTabState.loadState is LoadState.Loaded) return
        customTabState.loadState = LoadState.Loading(newProgress / 100.0f)
    }
}
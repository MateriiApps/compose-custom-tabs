package dev.materii.composecustomtabs.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.LoadingState
import dev.materii.composecustomtabs.state.AndroidCustomTabState
import dev.materii.composecustomtabs.state.CustomTabState

class WebChromeClient(private val customTabState: AndroidCustomTabState): AccompanistWebChromeClient() {

    init {

    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (customTabState.loadState is CustomTabState.LoadState.Loaded) return
        customTabState.loadState = CustomTabState.LoadState.Loading(newProgress / 100.0f)
    }

}
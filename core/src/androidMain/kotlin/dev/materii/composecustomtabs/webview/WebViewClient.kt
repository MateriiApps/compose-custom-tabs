package dev.materii.composecustomtabs.webview

import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import dev.materii.composecustomtabs.state.CustomTabState
import dev.materii.composecustomtabs.state.LoadState

class WebViewClient(private val customTabState: CustomTabState) : AccompanistWebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        customTabState.loadState = LoadState.Loaded
        customTabState.url = url ?: ""
        customTabState.title = view?.title ?: ""
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        customTabState.loadState = LoadState.Loading(0f)
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        val supportedSchemes = listOf("http", "https", "ftp", "ftps", "about", "javascript", "file")
        val isSupported = supportedSchemes.contains(request?.url?.scheme)
        if(!isSupported) {
            request?.url?.let {
                Intent(Intent.ACTION_VIEW).apply {
                    data = request.url
                    view?.context?.startActivity(this)
                }
            }
        }

        return !isSupported
    }

}
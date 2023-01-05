package id.faizalempe.wangku.presentation.screen.webview

import android.annotation.SuppressLint
import android.webkit.*
import id.faizalempe.wangku.util.ext.showToast
import id.faizalempe.core.ext.safe
import id.faizalempe.data.util.toObject
import id.faizalempe.wangku.databinding.FragmentWebviewBinding
import id.faizalempe.wangku.presentation.screen.ContainerActivity
import id.faizalempe.wangku.presentation.base.BaseFragment
import id.faizalempe.wangku.util.ext.getViewBinding
import id.faizalempe.wangku.util.ext.goneIf
import id.faizalempe.wangku.util.ext.visible

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version WebviewFragment, v 0.1 14/12/22 13.09 by Faizal Muhammad Priyowibowo
 */
class WebviewFragment : BaseFragment<FragmentWebviewBinding>() {

    private val args by lazy {
        arguments?.getString(ContainerActivity.NAV_ARGUMENT).orEmpty().toObject<WebviewArgs>()
    }

    override fun inflateViewBinding(): FragmentWebviewBinding =
        getViewBinding(FragmentWebviewBinding::inflate)

    @SuppressLint("SetJavaScriptEnabled")
    override fun FragmentWebviewBinding.init() {
        safe(args) { webviewArgs ->
            toolbarWebview.apply {
                title = webviewArgs.title
                subtitle = webviewArgs.url
                setNavigationOnClickListener {
                    if (webview.canGoBack()) webview.goBack() else back()
                }
            }
            webview.run {
                settings.apply {
                    domStorageEnabled = true
                    javaScriptEnabled = true
                    setSupportZoom(true)
                    loadWithOverviewMode = true
                    isClickable = true
                }

                webViewClient = object : WebViewClient() {

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)
                        context.showToast("Error loading page")
                    }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progressWebview.run {
                            visible()
                            progress = newProgress
                            goneIf(newProgress == COMPLETED_PROGRESS)
                        }
                    }

                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        toolbarWebview.title = title.orEmpty()
                    }
                }

                loadUrl(webviewArgs.url)
            }
        }
    }

    override fun onDestroyView() {
        binding.webview.apply {
            removeAllViews()
            destroy()
        }
        super.onDestroyView()
    }

    companion object {
        private const val COMPLETED_PROGRESS = 100
    }
}
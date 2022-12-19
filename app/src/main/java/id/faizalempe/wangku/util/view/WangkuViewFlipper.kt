package id.faizalempe.wangku.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import id.faizalempe.wangku.util.ext.clickDebounce
import id.faizalempe.wangku.databinding.ViewErrorBinding
import id.faizalempe.wangku.databinding.ViewLoadingBinding

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version WangkuViewFlipper, v 0.1 09/12/22 17.33 by Faizal Muhammad Priyowivowo
 */
class WangkuViewFlipper constructor(
    context: Context,
    attrs: AttributeSet
) : ViewFlipper(context, attrs) {

    private val loadingBinding by lazy {
        ViewLoadingBinding.inflate(LayoutInflater.from(context))
    }

    private val errorBinding by lazy {
        ViewErrorBinding.inflate(LayoutInflater.from(context))
    }

    init {
        addView(loadingBinding.root)
        addView(errorBinding.root)
    }

    fun showLoading() {
        displayedChild = LOADING
    }

    fun showError() {
        displayedChild = ERROR
    }

    fun showContent() {
        displayedChild = CONTENT
    }

    fun clickRefresh(action: () -> Unit) {
        errorBinding.btnError.clickDebounce { action() }
    }


    companion object {
        const val LOADING = 0
        const val ERROR = 1
        const val CONTENT = 2
    }
}
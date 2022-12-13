package id.faizalempe.wangku.util.ext

import android.view.View
import id.faizalempe.core.constant.WangkuConstant

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version ViewExt, v 0.1 08/12/22 14.37 by Faizal Muhammad Priyowibowo
 */

fun View?.clickDebounce(delay: Long = WangkuConstant.Time.CLICK_DELAY, action: () -> Unit) {
    this?.let { view ->
        var lastClickTime = 0L
        view.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= delay) {
                lastClickTime = currentTime
                action.invoke()
            }
        }
    }
}

fun View?.goneIf(condition: Boolean) {
    this?.let { view -> if (condition) view.gone() }
}

fun View?.invisibleIf(condition: Boolean) {
    this?.let { view -> if (condition) view.invisible() }
}

fun View?.visible() {
    this?.let { view -> view.visibility = View.VISIBLE}
}

fun View?.gone() {
    this?.let { view -> view.visibility = View.GONE}
}

fun View?.invisible() {
    this?.let { view -> view.visibility = View.INVISIBLE}
}
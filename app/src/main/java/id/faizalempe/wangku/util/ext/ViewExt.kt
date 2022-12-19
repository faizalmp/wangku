package id.faizalempe.wangku.util.ext

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.ext.safe
import id.faizalempe.wangku.R

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version ViewExt, v 0.1 08/12/22 14.37 by Faizal Muhammad Priyowibowo
 */

fun View?.clickDebounce(delay: Long = WangkuConstant.Time.CLICK_DELAY, action: () -> Unit) {
    safe(this) { view ->
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
    safe(this) { view -> if (condition) view.gone() }
}

fun View?.invisibleIf(condition: Boolean) {
    safe(this) { view -> if (condition) view.invisible() }
}

fun View?.visible() {
    safe(this) { view -> view.visibility = View.VISIBLE}
}

fun View?.gone() {
    safe(this) { view -> view.visibility = View.GONE}
}

fun View?.invisible() {
    safe(this) { view -> view.visibility = View.INVISIBLE}
}

fun ImageView?.loadImage(
    imageUrl: String,
    @DrawableRes placeholderRes: Int = R.drawable.bg_general_placeholder
) {
    safe(this) { imageView ->
        Glide
            .with(imageView.context)
            .load(imageUrl)
            .placeholder(placeholderRes)
            .into(imageView)
    }
}
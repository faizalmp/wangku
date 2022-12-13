package id.faizalempe.core.constant.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.NavigationRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import id.faizalempe.wangku.presentation.screen.ContainerActivity

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version ContextExt, v 0.1 08/12/22 17.13 by Faizal Muhammad Priyowibowo
 */

fun Context?.startNewContainerActivity(
    @NavigationRes navGraphResId: Int,
    isFinishCurrent: Boolean = false
) {
    this?.let { context ->
        val intent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(ContainerActivity.EXTRA_NAV_GRAPH, navGraphResId)
        }
        context.startActivity(intent)
        val isActivityContext = context is FragmentActivity || context is Activity
        if (isFinishCurrent && isActivityContext) (context as? Activity)?.finish()
    }
}

fun Context?.getColorCompat(@ColorRes colorResId: Int): Int {
    return this?.let { context ->
       ContextCompat.getColor(context, colorResId)
    } ?: android.R.color.transparent
}

fun Context?.getDimen(@DimenRes dimenResId: Int): Int {
    return this?.resources?.getDimensionPixelSize(dimenResId) ?: 0
}
package id.faizalempe.wangku.util.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import id.faizalempe.core.ext.safe
import id.faizalempe.data.util.toJsonString
import id.faizalempe.wangku.navigation.WangkuScreen
import id.faizalempe.wangku.presentation.screen.ContainerActivity

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version ContextExt, v 0.1 08/12/22 17.13 by Faizal Muhammad Priyowibowo
 */

fun Context?.startNewContainerActivity(
    screen: WangkuScreen,
    isFinishCurrent: Boolean = false,
    param: Any = ""
) {
    safe(this) { context ->
        val intent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(ContainerActivity.EXTRA_NAV_GRAPH, screen.navGraphRes)
            putExtra(ContainerActivity.EXTRA_PARAM, param.toJsonString())
        }
        context.startActivity(intent)
        val isActivityContext = context is FragmentActivity || context is Activity
        if (isFinishCurrent && isActivityContext) (context as? Activity)?.finish()
    }
}

fun Context?.getColorCompat(@ColorRes colorResId: Int): Int {
    return safe(this) { context ->
       ContextCompat.getColor(context, colorResId)
    } ?: android.R.color.transparent
}

fun Context?.getDimen(@DimenRes dimenResId: Int): Int {
    return this?.resources?.getDimensionPixelSize(dimenResId) ?: 0
}

fun Context?.showToast(message: String, toastLength: Int = Toast.LENGTH_SHORT) {
    safe(this) { context ->
        Toast.makeText(context, message, toastLength).show()
    }
}
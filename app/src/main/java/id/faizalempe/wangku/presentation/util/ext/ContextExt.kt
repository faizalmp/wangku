package id.faizalempe.wangku.presentation.util.ext

import android.content.Context
import android.content.Intent
import androidx.annotation.NavigationRes
import id.faizalempe.wangku.presentation.screen.ContainerActivity

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version ContextExt, v 0.1 08/12/22 17.13 by Faizal Muhammad Priyowibowo
 */

fun Context?.startNewContainerActivity(@NavigationRes navGraphResId: Int) {
    this?.let { context ->
        val intent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(ContainerActivity.EXTRA_NAV_GRAPH, navGraphResId)
        }
        context.startActivity(intent)
    }
}
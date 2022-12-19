package id.faizalempe.wangku.presentation.screen.webview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version WebviewArgs, v 0.1 15/12/22 09.28 by Faizal Muhammad Priyowibowo
 */
@Parcelize
data class WebviewArgs(
    val url: String = "",
    val title: String= ""
) : Parcelable

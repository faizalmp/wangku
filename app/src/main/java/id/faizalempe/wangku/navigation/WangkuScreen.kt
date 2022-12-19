package id.faizalempe.wangku.navigation

import androidx.annotation.NavigationRes
import id.faizalempe.wangku.R

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version WangkuScreen, v 0.1 14/12/22 17.25 by Faizal Muhammad Priyowibowo
 */
enum class WangkuScreen(@NavigationRes val navGraphRes: Int) {
    Splash(R.navigation.navigation_splash),
    Main(R.navigation.navigation_main),
    Webview(R.navigation.navigation_webview)
}
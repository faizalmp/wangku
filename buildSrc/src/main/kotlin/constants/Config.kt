package constants

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version Config, v 0.1 08/12/22 10.49 by Faizal Muhammad Priyowibowo
 */
object Config {

    const val minSdk = 21
    const val targetSdk = 32
    const val appId = "id.faizalempe.wangku"
    const val jvmTarget = "1.8"

    object AppVersion {
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object BuildTypes {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }
}
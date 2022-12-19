package id.faizalempe.core.ext

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GeneralExt, v 0.1 18/12/22 23.07 by Faizal Muhammad Priyowibowo
 */

inline fun <reified T : Any> tag(): String = T::class.java.simpleName

inline fun <T : Any, R : Any> safe(p1: T?, block: (T) -> R?): R? {
    return if (p1 != null) block(p1) else null
}

inline fun <T1 : Any, T2 : Any, R : Any> safe(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
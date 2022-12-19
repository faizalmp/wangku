package id.faizalempe.core.ext

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version NumbExt, v 0.1 08/12/22 17.23 by Faizal Muhammad Priyowibowo
 */

fun Int?.orZero() : Int = this ?: 0
fun Int.isZero() : Boolean = this == 0
fun Int.half() : Int = this / 2

fun Float?.orZero() : Float = this ?: 0f
fun Float.isZero() : Boolean = this == 0f
fun Float.half() : Float = this / 2

fun Long?.orZero() : Long = this ?: 0L
fun Long.isZero() : Boolean = this == 0L
fun Long.half() : Long = this / 2

fun Double?.orZero() : Double = this ?: 0.toDouble()
fun Double.isZero() : Boolean = this == 0.toDouble()
fun Double.half() : Double = this / 2
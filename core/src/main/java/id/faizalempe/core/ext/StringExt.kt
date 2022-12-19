package id.faizalempe.core.ext

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version StringExt, v 0.1 09/12/22 16.28 by Faizal Muhammad Priyowivowo
 */
fun String.deleteSubstring(sub: String): String = this.replace(sub, "")

fun String.appendSubstring(sub: String): String = sub.plus(this)

fun String.appendSubstringIf(sub: String, condition: Boolean) = if (condition) sub.plus(this) else this
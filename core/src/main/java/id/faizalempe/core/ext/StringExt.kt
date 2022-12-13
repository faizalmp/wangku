package id.faizalempe.core.ext

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version StringExt, v 0.1 09/12/22 16.28 by Faizal Muhammad Priyowivowo
 */
fun String.deleteSubstring(sub: String): String = this.replace(sub, "")
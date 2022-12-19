package id.faizalempe.core.constant

import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt
import kotlin.random.Random.Default.nextLong

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version RandomMatchers, v 0.1 19/12/22 18.28 by Faizal Muhammad Priyowibowo
 */
object RandomMatchers {

    private val alphanumeric = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private const val DEFAULT_LENGTH = 10

    fun getRandomString() = (1..DEFAULT_LENGTH)
        .map { nextInt(0, alphanumeric.size).let { alphanumeric[it] } }
        .joinToString("")

    fun getRandomInt() = nextInt(DEFAULT_LENGTH)

    fun getRandomBoolean() = nextBoolean()

    fun getRandomLong() = nextLong()
}
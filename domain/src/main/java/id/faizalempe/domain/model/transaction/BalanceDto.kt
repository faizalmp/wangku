package id.faizalempe.domain.model.transaction

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BalanceDto, v 0.1 15/12/22 14.54 by Faizal Muhammad Priyowibowo
 */
data class BalanceDto(
    val total: Long = 0,
    val cashIn: Long = 0,
    val cashOut: Long = 0
)
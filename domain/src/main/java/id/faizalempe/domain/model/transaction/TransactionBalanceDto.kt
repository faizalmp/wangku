package id.faizalempe.domain.model.transaction

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionBalanceDto, v 0.1 15/12/22 14.54 by Faizal Muhammad Priyowibowo
 */
data class TransactionBalanceDto(
    val balance: BalanceDto = BalanceDto(),
    val transactions: List<TransactionDto> = emptyList()
)
package id.faizalempe.data.local.model

import id.faizalempe.data.local.model.entity.TransactionEntity

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionBalanceData, v 0.1 16/12/22 13.48 by Faizal Muhammad Priyowibowo
 */
data class TransactionBalanceData(
    val balance: BalanceData = BalanceData(),
    val transactions: List<TransactionEntity> = emptyList()
)
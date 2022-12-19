package id.faizalempe.data.mapper

import id.faizalempe.data.local.model.entity.TransactionEntity
import id.faizalempe.data.local.model.BalanceData
import id.faizalempe.data.local.model.TransactionBalanceData
import id.faizalempe.domain.model.transaction.BalanceDto
import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.model.transaction.TransactionDto

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version TransactionMapper, v 0.1 13/12/22 14.58 by Faizal Muhammad Priyowivowo
 */
object TransactionMapper {

    fun mapTransactionBalanceDataToDto(data: TransactionBalanceData): TransactionBalanceDto {
        return TransactionBalanceDto(
            balance = mapBalanceDataToDto(data.balance),
            transactions = mapTransactionsEntityToDto(data.transactions)
        )
    }

    private fun mapBalanceDataToDto(data: BalanceData): BalanceDto {
        return BalanceDto(
            total = data.total,
            cashIn = data.cashIn,
            cashOut = data.cashOut
        )
    }

    private fun mapTransactionsEntityToDto(transactions: List<TransactionEntity>): List<TransactionDto> {
        return transactions.map { transaction ->
            TransactionDto(
                id = transaction.id,
                category = transaction.category.orEmpty(),
                desc = transaction.desc.orEmpty(),
                amount = transaction.amount,
                datetime = transaction.datetime
            )
        }
    }

    fun mapTransactionDtoToEntity(transaction: TransactionDto): TransactionEntity =
        TransactionEntity(
            id = transaction.id,
            category = transaction.category,
            desc = transaction.desc,
            amount = transaction.amount,
            datetime = transaction.datetime
        )
}
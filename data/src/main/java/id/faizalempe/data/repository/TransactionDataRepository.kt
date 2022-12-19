package id.faizalempe.data.repository

import id.faizalempe.data.local.datasource.TransactionLocalDataSource
import id.faizalempe.data.mapper.TransactionMapper
import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionDataRepository, v 0.1 15/12/22 16.06 by Faizal Muhammad Priyowibowo
 */
class TransactionDataRepository @Inject constructor(
    private val localDataSource: TransactionLocalDataSource
) : TransactionRepository {

    override fun getAllTransactionBalance(): Observable<TransactionBalanceDto> =
        localDataSource.getTransactionBalance().map { data ->
            TransactionMapper.mapTransactionBalanceDataToDto(data)
        }

    override fun createTransaction(transaction: TransactionDto): Observable<Unit> {
        val transactionEntity = TransactionMapper.mapTransactionDtoToEntity(transaction)
        return localDataSource.createTransaction(transactionEntity)
    }

    override fun updateTransaction(transaction: TransactionDto): Observable<Unit> {
        val transactionEntity = TransactionMapper.mapTransactionDtoToEntity(transaction)
        return localDataSource.updateTransaction(transactionEntity)
    }

    override fun deleteTransaction(id: Int): Observable<Unit> {
        return localDataSource.deleteTransaction(id)
    }
}
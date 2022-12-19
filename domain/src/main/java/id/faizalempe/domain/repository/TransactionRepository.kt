package id.faizalempe.domain.repository

import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.model.transaction.TransactionDto
import io.reactivex.Observable

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsRepository, v 0.1 12/12/22 17.38 by Faizal Muhammad Priyowivowo
 */
interface TransactionRepository {

    fun getAllTransactionBalance(): Observable<TransactionBalanceDto>

    fun createTransaction(transaction: TransactionDto): Observable<Unit>

    fun updateTransaction(transaction: TransactionDto): Observable<Unit>

    fun deleteTransaction(id: Int): Observable<Unit>

}
package id.faizalempe.data.base.transaction

import id.faizalempe.data.local.model.TransactionBalanceData
import id.faizalempe.data.local.model.entity.TransactionEntity
import io.reactivex.Observable

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version TransactionDataSource, v 0.1 12/12/22 15.47 by Faizal Muhammad Priyowivowo
 */
interface TransactionDataSource {

    fun getTransactionBalance(): Observable<TransactionBalanceData>

    fun createTransaction(transaction: TransactionEntity): Observable<Unit>

    fun updateTransaction(transaction: TransactionEntity): Observable<Unit>

    fun deleteTransaction(id: Int): Observable<Unit>
}
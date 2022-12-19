package id.faizalempe.data.local.datasource

import id.faizalempe.data.base.transaction.TransactionDataSource
import id.faizalempe.data.local.dao.TransactionDao
import id.faizalempe.data.local.model.TransactionBalanceData
import id.faizalempe.data.local.model.entity.TransactionEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionLocalDataSource, v 0.1 15/12/22 15.42 by Faizal Muhammad Priyowibowo
 */
class TransactionLocalDataSource @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionDataSource {

    override fun getTransactionBalance(): Observable<TransactionBalanceData> =
        Observable.zip(
            transactionDao.getBalance(),
            transactionDao.getAllTransactions()
        ) { balance, transactions -> TransactionBalanceData(balance, transactions) }

    override fun createTransaction(transaction: TransactionEntity): Observable<Unit> =
        Observable.fromCallable { transactionDao.insertTransaction(transaction) }

    override fun updateTransaction(transaction: TransactionEntity): Observable<Unit> =
        Observable.fromCallable { transactionDao.updateTransaction(transaction) }

    override fun deleteTransaction(id: Int): Observable<Unit> =
        Observable.fromCallable { transactionDao.deleteTransaction(id) }
}
package id.faizalempe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.faizalempe.data.local.model.BalanceData
import id.faizalempe.data.local.model.entity.TransactionEntity
import io.reactivex.Single

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionDao, v 0.1 15/12/22 14.13 by Faizal Muhammad Priyowibowo
 */
@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY datetime DESC")
    fun getAllTransactions(): Single<List<TransactionEntity>>

    @Query("SELECT " +
        "(SELECT SUM(amount)) AS total, " +
        "(SELECT SUM(amount) FROM transactions WHERE amount > 0) AS cashIn, " +
        "(SELECT SUM(amount) FROM transactions WHERE amount < 0) AS cashOut" +
        " FROM transactions")
    fun getBalance(): Single<BalanceData>

    @Update
    fun updateTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteTransaction(id: Int)

    @Query("DELETE FROM transactions")
    fun deleteAllTransactions()

}
package id.faizalempe.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionEntity, v 0.1 15/12/22 14.14 by Faizal Muhammad Priyowibowo
 */
@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int,
    @ColumnInfo(name = "category")
    @Json(name = "category")
    val category: String? = null,
    @ColumnInfo(name = "desc")
    @Json(name = "desc")
    val desc: String? = null,
    @ColumnInfo(name = "amount")
    @Json(name = "amount")
    val amount: Long,
    @ColumnInfo(name = "datetime")
    @Json(name = "datetime")
    val datetime: String
)
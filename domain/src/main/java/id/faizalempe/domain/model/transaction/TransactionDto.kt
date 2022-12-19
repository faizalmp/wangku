package id.faizalempe.domain.model.transaction

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionDto, v 0.1 15/12/22 14.14 by Faizal Muhammad Priyowibowo
 */
@Parcelize
data class TransactionDto(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "category")
    val category: String = "",
    @Json(name = "desc")
    val desc: String = "",
    @Json(name = "amount")
    val amount: Long = 0,
    @Json(name = "datetime")
    val datetime: String = ""
) : Parcelable
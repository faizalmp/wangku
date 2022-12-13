package id.faizalempe.data.remote.response

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ArticleSourceResponse, v 0.1 13/12/22 15.01 by Faizal Muhammad Priyowivowo
 */
data class ArticleSourceResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)

package id.faizalempe.data.remote.response

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsResponse, v 0.1 13/12/22 10.25 by Faizal Muhammad Priyowivowo
 */
data class NewsResponse(
    @Json(name = "totalResults")
    val totalResults: Int? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "articles")
    val articles: List<ArticleResponse>? = null
)

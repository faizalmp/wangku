package id.faizalempe.domain.model.news

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsDto, v 0.1 13/12/22 10.25 by Faizal Muhammad Priyowivowo
 */
data class NewsDto(
    @Json(name = "totalResults")
    val totalResults: Int = 0,
    @Json(name = "status")
    val status: String = "",
    @Json(name = "articles")
    val articles: List<ArticleDto> = emptyList()
)

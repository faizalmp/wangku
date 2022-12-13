package id.faizalempe.domain.model.news

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ArticleDto, v 0.1 13/12/22 15.01 by Faizal Muhammad Priyowivowo
 */
data class ArticleDto(
    @Json(name = "source")
    val source: ArticleSourceDto = ArticleSourceDto(),
    @Json(name = "author")
    val author: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "url")
    val url: String = "",
    @Json(name = "urlToImage")
    val urlToImage: String = "",
    @Json(name = "publishedAt")
    val publishedAt: String = "",
    @Json(name = "content")
    val content: String = ""
)

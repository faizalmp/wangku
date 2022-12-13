package id.faizalempe.data.remote.response

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ArticleResponse, v 0.1 13/12/22 15.01 by Faizal Muhammad Priyowivowo
 */
data class ArticleResponse(
    @Json(name = "source")
    val source: ArticleSourceResponse? = null,
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "content")
    val content: String? = null
)

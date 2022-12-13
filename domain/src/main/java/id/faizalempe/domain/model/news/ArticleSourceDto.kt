package id.faizalempe.domain.model.news

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ArticleSourceResponse, v 0.1 13/12/22 15.01 by Faizal Muhammad Priyowivowo
 */
data class ArticleSourceDto(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = ""
)

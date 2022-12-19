package id.faizalempe.data.mapper

import id.faizalempe.core.ext.orZero
import id.faizalempe.data.remote.response.ArticleResponse
import id.faizalempe.data.remote.response.ArticleSourceResponse
import id.faizalempe.data.remote.response.NewsResponse
import id.faizalempe.domain.model.news.ArticleDto
import id.faizalempe.domain.model.news.ArticleSourceDto
import id.faizalempe.domain.model.news.NewsDto

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsMapper, v 0.1 13/12/22 14.58 by Faizal Muhammad Priyowivowo
 */
object NewsMapper {

    fun mapNewsResponseToDto(response: NewsResponse): NewsDto {
        return NewsDto(
            totalResults = response.totalResults.orZero(),
            status = response.status.orEmpty(),
            articles = mapArticlesResponseToDto(response.articles.orEmpty())
        )
    }

    private fun mapArticlesResponseToDto(articles: List<ArticleResponse>): List<ArticleDto> {
        return articles.map { article ->
            ArticleDto(
                source = mapArticleSourceResponseToDto(article.source),
                author = article.author.orEmpty(),
                title = article.title.orEmpty(),
                description = article.description.orEmpty(),
                url = article.url.orEmpty(),
                urlToImage = article.urlToImage.orEmpty(),
                publishedAt = article.publishedAt.orEmpty(),
                content = article.content.orEmpty()
            )
        }
    }

    fun mapArticleSourceResponseToDto(source: ArticleSourceResponse?): ArticleSourceDto {
        return ArticleSourceDto(
            id = source?.id.orEmpty(),
            name = source?.name.orEmpty()
        )
    }
}
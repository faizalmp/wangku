package id.faizalempe.data

import id.faizalempe.core.constant.RandomMatchers
import id.faizalempe.data.mapper.NewsMapper
import id.faizalempe.data.remote.response.ArticleResponse
import id.faizalempe.data.remote.response.ArticleSourceResponse
import id.faizalempe.data.remote.response.NewsResponse
import id.faizalempe.domain.model.news.ArticleDto
import org.junit.Test
import org.junit.Assert.assertEquals

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version NewsMapperTest, v 0.1 19/12/22 18.14 by Faizal Muhammad Priyowibowo
 */
class NewsMapperTest {

    @Test
    fun map_news_response_to_dto_return_result() {
        // given
        val newsResponse = generateNewsResponse()

        // when
        val newsDto = NewsMapper.mapNewsResponseToDto(newsResponse)

        // then
        assertEquals(newsResponse.totalResults, newsDto.totalResults)
        assertEquals(newsResponse.status, newsDto.status)
        assertEquals(newsResponse.articles?.size , newsDto.articles.size)
    }

    @Test
    fun map_news_response_to_dto_return_default() {
        // given
        val newsResponse = NewsResponse()

        // when
        val newsDto = NewsMapper.mapNewsResponseToDto(newsResponse)

        // then
        assertEquals(newsResponse.totalResults, null)
        assertEquals(newsResponse.status, null)
        assertEquals(newsResponse.articles?.get(0)?.url , null)
        assertEquals(newsDto.totalResults, 0)
        assertEquals(newsDto.status, "")
        assertEquals(newsDto.articles , emptyList<ArticleDto>())
    }

    private fun generateNewsResponse(): NewsResponse = NewsResponse(
        totalResults = RandomMatchers.getRandomInt(),
        status = RandomMatchers.getRandomString(),
        articles = listOf(generateArticleResponse(), generateArticleResponse())
    )

    private fun generateArticleResponse(): ArticleResponse = ArticleResponse(
        source = generateArticleSourceResponse(),
        author = RandomMatchers.getRandomString(),
        title = RandomMatchers.getRandomString(),
        description = RandomMatchers.getRandomString(),
        url = RandomMatchers.getRandomString(),
        urlToImage = RandomMatchers.getRandomString(),
        publishedAt = RandomMatchers.getRandomString(),
        content = RandomMatchers.getRandomString()
    )

    private fun generateArticleSourceResponse(): ArticleSourceResponse = ArticleSourceResponse(
        id = RandomMatchers.getRandomString(),
        name = RandomMatchers.getRandomString()
    )
}
package id.faizalempe.domain.usecase.news

import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.domain.util.BaseUseCase
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GetRemoteNews, v 0.1 19/12/22 23.43 by Faizal Muhammad Priyowibowo
 */
class GetRemoteNews @Inject constructor(
    private val repository: NewsRepository
) : BaseUseCase<NewsDto, GetRemoteNews.Params>() {

    override fun build(params: Params): Observable<NewsDto> {
        return repository.getRemoteNews(params.page)
    }

    data class Params(
        val page: Int = WangkuConstant.Data.News.DEFAULT_PAGE
    )
}
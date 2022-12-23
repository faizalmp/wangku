package id.faizalempe.data.remote.datasource

import id.faizalempe.data.base.news.NewsDataSource
import id.faizalempe.data.remote.api.NewsApi
import id.faizalempe.data.remote.response.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsRemoteDataSource, v 0.1 12/12/22 15.20 by Faizal Muhammad Priyowivowo
 */
@Singleton
class NewsRemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
) : NewsDataSource {

    override fun getNews(page: Int): Observable<NewsResponse> =
        newsApi.getNews(page = page).toObservable()
}
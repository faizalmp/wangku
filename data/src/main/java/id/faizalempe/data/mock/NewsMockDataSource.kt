package id.faizalempe.data.mock

import id.faizalempe.data.base.news.NewsDataSource
import id.faizalempe.data.remote.response.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsMockDataSource, v 0.1 12/12/22 15.20 by Faizal Muhammad Priyowivowo
 */
@Singleton
class NewsMockDataSource @Inject constructor() : NewsDataSource {

    override fun getNews(page: Int): Observable<NewsResponse> = Observable.just(NewsResponse())
}
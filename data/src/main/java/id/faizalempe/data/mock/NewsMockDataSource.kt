package id.faizalempe.data.mock

import id.faizalempe.data.base.news.NewsBaseDataSource
import id.faizalempe.data.remote.response.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsMockDataSource, v 0.1 12/12/22 15.20 by Faizal Muhammad Priyowivowo
 */
@Singleton
class NewsMockDataSource @Inject constructor() : NewsBaseDataSource {

    override fun getNews(): Observable<NewsResponse> = Observable.just(NewsResponse())
}
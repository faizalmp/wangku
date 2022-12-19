package id.faizalempe.data.base.news

import id.faizalempe.data.remote.response.NewsResponse
import io.reactivex.Observable

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsDataSource, v 0.1 12/12/22 15.47 by Faizal Muhammad Priyowivowo
 */
interface NewsDataSource {
    fun getNews(page: Int): Observable<NewsResponse>
}
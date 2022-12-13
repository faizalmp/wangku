package id.faizalempe.domain.repository

import id.faizalempe.domain.model.news.NewsDto
import io.reactivex.Observable

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsRepository, v 0.1 12/12/22 17.38 by Faizal Muhammad Priyowivowo
 */
interface NewsRepository {

    fun getRemoteNews(): Observable<NewsDto>

}
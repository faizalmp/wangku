package id.faizalempe.domain.interactor

import id.faizalempe.domain.model.news.NewsDto
import io.reactivex.Observable

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsInteractor, v 0.1 12/12/22 17.33 by Faizal Muhammad Priyowivowo
 */
interface NewsInteractor {
    fun getRemoteNews(): Observable<NewsDto>
}
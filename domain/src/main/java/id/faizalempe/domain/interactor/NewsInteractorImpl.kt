package id.faizalempe.domain.interactor

import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsInteractorImpl, v 0.1 12/12/22 17.33 by Faizal Muhammad Priyowivowo
 */
class NewsInteractorImpl @Inject constructor(
    private val repository: NewsRepository
) : NewsInteractor {

    override fun getRemoteNews(): Observable<NewsDto> {
        return repository.getRemoteNews()
    }
}
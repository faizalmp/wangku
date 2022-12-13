package id.faizalempe.wangku.presentation.screen.home.news

import id.faizalempe.core.ext.observe
import id.faizalempe.domain.interactor.NewsInteractor
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsPresenter, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
class NewsPresenter @Inject constructor(
    private val view: NewsContract.View,
    private val newsInteractor: NewsInteractor
) : NewsContract.Presenter {

    override fun getNews() {
        with(view) {
            showLoading()
            newsInteractor.getRemoteNews().observe(
                onSuccess = { showContent(it) },
                onError = { e -> e.message?.let { showError(it) } }
            )
        }
    }
}
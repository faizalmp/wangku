package id.faizalempe.wangku.presentation.screen.home.main

import id.faizalempe.core.ext.observe
import id.faizalempe.domain.interactor.NewsInteractor
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MainPresenter, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val newsInteractor: NewsInteractor
) : MainContract.Presenter {

    override fun getModularMain() {
        with(view) {
            showLoading()
            newsInteractor.getRemoteNews().observe(
                onSuccess = { showContent(it) },
                onError = { e -> e.message?.let { showError(it) } }
            )
        }
    }
}
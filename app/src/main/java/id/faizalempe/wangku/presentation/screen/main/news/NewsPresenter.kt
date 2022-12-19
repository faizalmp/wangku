package id.faizalempe.wangku.presentation.screen.main.news

import androidx.lifecycle.DefaultLifecycleObserver
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.ext.safe
import id.faizalempe.domain.usecase.news.GetRemoteNews
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsPresenter, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
class NewsPresenter @Inject constructor(
    private val view: NewsContract.View,
    private val getRemoteNews: GetRemoteNews
) : NewsContract.Presenter, DefaultLifecycleObserver {

    private var page: Int = WangkuConstant.Data.News.DEFAULT_PAGE

    override fun getNews(isFirstTimeLoad: Boolean) {
        view.showLoading()
        if (isFirstTimeLoad) page = WangkuConstant.Data.News.DEFAULT_PAGE
        getRemoteNews.observe(
            params = GetRemoteNews.Params(page = page),
            onSuccess = {
                view.showContent(it)
                page++
            },
            onError = { e -> safe(e.message) { view.showError(it) } }
        )
    }

    override fun onDestroy() {
        getRemoteNews.dispose()
    }
}
package id.faizalempe.wangku.presentation.screen.main.news

import androidx.lifecycle.LifecycleOwner
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.ext.safe
import id.faizalempe.domain.usecase.news.GetRemoteNews
import id.faizalempe.wangku.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsPresenter, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
class NewsPresenter @Inject constructor(
    private val view: NewsContract.View,
    private val getRemoteNews: GetRemoteNews
) : BasePresenter(), NewsContract.Presenter {

    private var currentPage = WangkuConstant.Data.News.DEFAULT_PAGE

    override fun getNews(isFirstTimeLoad: Boolean) {
        if (isFirstTimeLoad) {
            view.showLoading()
            currentPage = WangkuConstant.Data.News.DEFAULT_PAGE
        } else {
            view.showPaginationLoading()
        }
        getRemoteNews.observe(
            params = GetRemoteNews.Params(page = currentPage),
            onSuccess = {
                view.showContent(it, isFirstTimeLoad)
                currentPage++
                        },
            onError = { e -> safe(e.message) { view.showError(it) } }
        )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        getRemoteNews.dispose()
        super.onDestroy(owner)
    }
}
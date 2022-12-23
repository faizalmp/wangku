package id.faizalempe.wangku.presentation.screen.main.news

import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.presentation.base.AbstractContract

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsContract, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
interface NewsContract {

    interface View : AbstractContract.AbstractView {
        fun showContent(news: NewsDto, isFirstTimeLoad: Boolean)
        fun showPaginationLoading()
    }

    interface Presenter : AbstractContract.AbstractPresenter {
        fun getNews(isFirstTimeLoad: Boolean)
    }
}
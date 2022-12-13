package id.faizalempe.wangku.presentation.screen.home.news

import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.util.base.AbstractContract

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsContract, v 0.1 12/12/22 12.54 by Faizal Muhammad Priyowivowo
 */
interface NewsContract {

    interface View : AbstractContract.AbstractView {
        fun showContent(news: NewsDto)
    }

    interface Presenter : AbstractContract.AbstractPresenter {
        fun getNews()
    }
}
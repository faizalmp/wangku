package id.faizalempe.wangku.presentation.di.home.news

import dagger.Module
import dagger.Provides
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.home.news.NewsContract
import id.faizalempe.wangku.presentation.screen.home.news.NewsPresenter

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsModule, v 0.1 12/12/22 18.52 by Faizal Muhammad Priyowivowo
 */
@Module
class NewsModule(private val view: NewsContract.View) {

    @Provides
    @PerActivity
    fun provideNewsContractView(): NewsContract.View = view

    @Provides
    @PerActivity
    fun provideMainContractPresenter(
        newsPresenter: NewsPresenter
    ): NewsContract.Presenter = newsPresenter

}
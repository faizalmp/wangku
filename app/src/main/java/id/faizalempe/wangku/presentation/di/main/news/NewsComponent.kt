package id.faizalempe.wangku.presentation.di.main.news

import dagger.Subcomponent
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.main.news.NewsFragment

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsComponent, v 0.1 12/12/22 18.52 by Faizal Muhammad Priyowivowo
 */
@PerActivity
@Subcomponent(modules = [NewsModule::class])
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}
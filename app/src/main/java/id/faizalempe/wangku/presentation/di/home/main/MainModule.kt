package id.faizalempe.wangku.presentation.di.home.main

import dagger.Module
import dagger.Provides
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.home.main.MainContract
import id.faizalempe.wangku.presentation.screen.home.main.MainPresenter

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MainModule, v 0.1 12/12/22 18.52 by Faizal Muhammad Priyowivowo
 */
@Module
class MainModule(private val view: MainContract.View) {

    @Provides
    @PerActivity
    fun provideMainContractView(): MainContract.View = view

    @Provides
    @PerActivity
    fun provideMainContractPresenter(
        mainPresenter: MainPresenter
    ): MainContract.Presenter = mainPresenter

}
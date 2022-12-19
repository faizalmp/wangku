package id.faizalempe.wangku.presentation.di.main.transaction.detail

import dagger.Module
import dagger.Provides
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionContract
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionPresenter

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionModule, v 0.1 18/12/22 20.23 by Faizal Muhammad Priyowibowo
 */
@Module
class DetailTransactionModule(private val view: DetailTransactionContract.View) {

    @Provides
    @PerActivity
    fun provideDetailTransactionContractView(): DetailTransactionContract.View = view

    @Provides
    @PerActivity
    fun provideDetailTransactionContractPresenter(
        detailTransactionPresenter: DetailTransactionPresenter
    ): DetailTransactionContract.Presenter = detailTransactionPresenter
}
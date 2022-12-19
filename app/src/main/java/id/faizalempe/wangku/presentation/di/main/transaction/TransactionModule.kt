package id.faizalempe.wangku.presentation.di.main.transaction

import dagger.Module
import dagger.Provides
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.main.transaction.TransactionContract
import id.faizalempe.wangku.presentation.screen.main.transaction.TransactionPresenter

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionModule, v 0.1 16/12/22 12.26 by Faizal Muhammad Priyowibowo
 */
@Module
class TransactionModule(private val view: TransactionContract.View) {

    @Provides
    @PerActivity
    fun provideTransactionContractView(): TransactionContract.View = view

    @Provides
    @PerActivity
    fun proviewTransactionContractPresenter(
        transactionPresenter: TransactionPresenter
    ): TransactionContract.Presenter = transactionPresenter
}
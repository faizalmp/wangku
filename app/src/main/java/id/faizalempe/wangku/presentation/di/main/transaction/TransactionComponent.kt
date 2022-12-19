package id.faizalempe.wangku.presentation.di.main.transaction

import dagger.Subcomponent
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.main.transaction.TransactionFragment

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionComponent, v 0.1 16/12/22 12.26 by Faizal Muhammad Priyowibowo
 */
@PerActivity
@Subcomponent(modules = [TransactionModule::class])
interface TransactionComponent {

    fun inject(transactionFragment: TransactionFragment)

}
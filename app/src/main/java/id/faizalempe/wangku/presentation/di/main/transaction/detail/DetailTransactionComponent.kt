package id.faizalempe.wangku.presentation.di.main.transaction.detail

import dagger.Subcomponent
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionFragment

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionComponent, v 0.1 18/12/22 20.23 by Faizal Muhammad Priyowibowo
 */
@PerActivity
@Subcomponent(modules = [DetailTransactionModule::class])
interface DetailTransactionComponent {

    fun inject(detailTransactionFragment: DetailTransactionFragment)

}
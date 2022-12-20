package id.faizalempe.wangku.presentation.screen.main.transaction

import androidx.lifecycle.LifecycleOwner
import id.faizalempe.core.ext.safe
import id.faizalempe.domain.usecase.transaction.GetAllTransactionBalance
import id.faizalempe.wangku.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionPresenter, v 0.1 16/12/22 12.28 by Faizal Muhammad Priyowibowo
 */
class TransactionPresenter @Inject constructor(
    private val view: TransactionContract.View,
    private val getAllTransactionBalance: GetAllTransactionBalance
) : BasePresenter(), TransactionContract.Presenter {

    override fun getTransactionBalance(
        startDate: String,
        endDate: String
    ) {
        with(view) {
            showLoading()
            getAllTransactionBalance.observe(
                params = GetAllTransactionBalance.Params(),
                onSuccess = { transactionBalance ->  view.showContent(transactionBalance) },
                onError = { e -> safe(e.message) { showError(it) } }
            )
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        getAllTransactionBalance.dispose()
        super.onDestroy(owner)
    }
}
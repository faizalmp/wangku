package id.faizalempe.wangku.presentation.screen.main.transaction.detail

import androidx.lifecycle.LifecycleOwner
import id.faizalempe.core.ext.safe
import id.faizalempe.domain.usecase.transaction.CreateTransaction
import id.faizalempe.domain.usecase.transaction.DeleteTransaction
import id.faizalempe.domain.usecase.transaction.UpdateTransaction
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.wangku.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionPresenter, v 0.1 18/12/22 12.51 by Faizal Muhammad Priyowibowo
 */
class DetailTransactionPresenter @Inject constructor(
    private val view: DetailTransactionContract.View,
    private val createTransaction: CreateTransaction,
    private val updateTransaction: UpdateTransaction,
    private val deleteTransaction: DeleteTransaction
) : BasePresenter(), DetailTransactionContract.Presenter {

    override fun createTransaction(transaction: TransactionDto) {
        with(view) {
            showLoading()
            createTransaction.observe(
                params = CreateTransaction.Params(transaction),
                onSuccess = { onSubmitSuccess() },
                onError = { e -> safe(e.message) { showError(it) } }
            )
        }
    }

    override fun updateTransaction(transaction: TransactionDto) {
        with(view) {
            showLoading()
            updateTransaction.observe(
                params = UpdateTransaction.Params(transaction),
                onSuccess = { onSubmitSuccess() },
                onError = { e -> safe(e.message) { showError(it) } }
            )
        }
    }

    override fun deleteTransaction(id: Int) {
        with(view) {
            showLoading()
            deleteTransaction.observe(
                params = DeleteTransaction.Params(id),
                onSuccess = { onSubmitSuccess() },
                onError = { e -> safe(e.message) { showError(it) } }
            )
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        createTransaction.dispose()
        updateTransaction.dispose()
        deleteTransaction.dispose()
        super.onDestroy(owner)
    }
}
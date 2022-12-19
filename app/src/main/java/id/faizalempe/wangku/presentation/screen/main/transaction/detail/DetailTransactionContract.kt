package id.faizalempe.wangku.presentation.screen.main.transaction.detail

import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.wangku.presentation.base.AbstractContract

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionContract, v 0.1 18/12/22 12.50 by Faizal Muhammad Priyowibowo
 */
interface DetailTransactionContract {

    interface View : AbstractContract.AbstractView {
        fun onSubmitSuccess()
    }

    interface Presenter : AbstractContract.AbstractPresenter {

        fun createTransaction(transaction: TransactionDto)

        fun updateTransaction(transaction: TransactionDto)

        fun deleteTransaction(id: Int)

    }
}
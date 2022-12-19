package id.faizalempe.wangku.presentation.screen.main.transaction

import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.wangku.presentation.base.AbstractContract

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionContract, v 0.1 16/12/22 12.28 by Faizal Muhammad Priyowibowo
 */
interface TransactionContract {

    interface View : AbstractContract.AbstractView {
        fun showContent(transactionBalance: TransactionBalanceDto)
    }

    interface Presenter : AbstractContract.AbstractPresenter {
        fun getTransactionBalance(startDate: String = "", endDate: String = "")
    }
}
package id.faizalempe.wangku.presentation.base

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version AbstractContract, v 0.1 12/12/22 12.38 by Faizal Muhammad Priyowivowo
 */
interface AbstractContract {
    interface AbstractView {

        fun showLoading()

        fun showError(message: String)

    }

    interface AbstractPresenter {
        fun onDestroy()
    }
}
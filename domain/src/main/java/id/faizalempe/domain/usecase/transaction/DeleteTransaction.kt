package id.faizalempe.domain.usecase.transaction

import id.faizalempe.domain.util.BaseUseCase
import id.faizalempe.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DeleteTransaction, v 0.1 20/12/22 00.33 by Faizal Muhammad Priyowibowo
 */
class DeleteTransaction @Inject constructor(
    private val repository: TransactionRepository
): BaseUseCase<Unit, DeleteTransaction.Params>() {

    override fun build(params: Params): Observable<Unit> =
        repository.deleteTransaction(params.id)

    data class Params(
        val id: Int
    )
}
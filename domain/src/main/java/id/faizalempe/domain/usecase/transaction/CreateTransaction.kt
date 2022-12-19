package id.faizalempe.domain.usecase.transaction

import id.faizalempe.domain.util.BaseUseCase
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version CreateTransaction, v 0.1 20/12/22 00.33 by Faizal Muhammad Priyowibowo
 */
class CreateTransaction @Inject constructor(
    private val repository: TransactionRepository
): BaseUseCase<Unit, CreateTransaction.Params>() {

    override fun build(params: Params): Observable<Unit> =
        repository.createTransaction(params.transaction)

    data class Params(
        val transaction: TransactionDto
    )
}
package id.faizalempe.domain.usecase.transaction

import id.faizalempe.domain.util.BaseUseCase
import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GetAllTransactionBalance, v 0.1 20/12/22 00.33 by Faizal Muhammad Priyowibowo
 */
class GetAllTransactionBalance @Inject constructor(
    private val repository: TransactionRepository
): BaseUseCase<TransactionBalanceDto, GetAllTransactionBalance.Params>() {

    override fun build(params: Params): Observable<TransactionBalanceDto> =
        repository.getAllTransactionBalance()

    data class Params(
        val data: Any = ""
    )
}
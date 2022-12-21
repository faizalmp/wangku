package id.faizalempe.domain

import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.repository.TransactionRepository
import id.faizalempe.domain.usecase.transaction.GetAllTransactionBalance
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GetAllTransactionBalanceTest, v 0.1 21/12/22 13.16 by Faizal Muhammad Priyowibowo
 */
class GetAllTransactionBalanceTest {

    private val scheduler by lazy { Schedulers.trampoline() }

    private val transactionRepository: TransactionRepository = mockk(relaxed = true)

    lateinit var getAllTransactionBalance: GetAllTransactionBalance

    private val transactionBalanceDto by lazy { TransactionBalanceDto() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getAllTransactionBalance = GetAllTransactionBalance(transactionRepository)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `get all transaction balance return result`() {
        // given
        val params = GetAllTransactionBalance.Params()
        every { transactionRepository.getAllTransactionBalance() } returns Observable.just(transactionBalanceDto)

        // when
        getAllTransactionBalance.build(params)

        // then
        verify { transactionRepository.getAllTransactionBalance() }
    }
}
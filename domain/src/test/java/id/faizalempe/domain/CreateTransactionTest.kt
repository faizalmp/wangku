package id.faizalempe.domain

import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.domain.repository.TransactionRepository
import id.faizalempe.domain.usecase.transaction.CreateTransaction
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
 * @version CreateTransactionTest, v 0.1 21/12/22 13.16 by Faizal Muhammad Priyowibowo
 */
class CreateTransactionTest {

    private val scheduler by lazy { Schedulers.trampoline() }

    private val transactionRepository: TransactionRepository = mockk(relaxed = true)

    lateinit var createTransaction: CreateTransaction

    private val transactionDto by lazy { TransactionDto() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        createTransaction = CreateTransaction(transactionRepository)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `create transaction return result`() {
        // given
        val params = CreateTransaction.Params(transactionDto)
        every { transactionRepository.createTransaction(transactionDto) } returns Observable.just(Unit)

        // when
        createTransaction.build(params)

        // then
        verify { transactionRepository.createTransaction(transactionDto) }
    }
}
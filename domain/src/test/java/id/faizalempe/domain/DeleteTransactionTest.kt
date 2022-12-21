package id.faizalempe.domain

import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.domain.repository.TransactionRepository
import id.faizalempe.domain.usecase.transaction.DeleteTransaction
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
 * @version DeleteTransactionTest, v 0.1 21/12/22 13.16 by Faizal Muhammad Priyowibowo
 */
class DeleteTransactionTest {

    private val scheduler by lazy { Schedulers.trampoline() }

    private val transactionRepository: TransactionRepository = mockk(relaxed = true)

    lateinit var deleteTransaction: DeleteTransaction

    private val transactionDto by lazy { TransactionDto() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        deleteTransaction = DeleteTransaction(transactionRepository)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `delete transaction return result`() {
        // given
        val id = 1
        val params = DeleteTransaction.Params(id)
        every { transactionRepository.deleteTransaction(id) } returns Observable.just(Unit)

        // when
        deleteTransaction.build(params)

        // then
        verify { transactionRepository.deleteTransaction(id) }
    }
}
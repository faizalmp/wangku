package id.faizalempe.wangku

import id.faizalempe.core.ext.OnSuccessCallback
import id.faizalempe.domain.usecase.transaction.GetAllTransactionBalance
import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.wangku.presentation.screen.main.transaction.TransactionContract
import id.faizalempe.wangku.presentation.screen.main.transaction.TransactionPresenter
import io.mockk.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version TransactionPresenterTest, v 0.1 19/12/22 19.19 by Faizal Muhammad Priyowibowo
 */
class TransactionPresenterTest {

    private val view: TransactionContract.View = mockk(relaxed = true)

    private val getAllTransactionBalance: GetAllTransactionBalance = mockk(relaxed = true)

    lateinit var presenter: TransactionPresenter

    private val transactionBalanceDto by lazy { TransactionBalanceDto() }

    private val scheduler by lazy { Schedulers.trampoline() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = TransactionPresenter(view, getAllTransactionBalance)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `test get balance transaction return result`() {
        // given
        every { getAllTransactionBalance.observe(any(), any(), any()) } answers {
            secondArg<OnSuccessCallback<TransactionBalanceDto>>().invoke(transactionBalanceDto)
        }

        // when
        presenter.getTransactionBalance()

        // then
        verify {
            view.showLoading()
            view.showContent(any())
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}
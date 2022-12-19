package id.faizalempe.wangku

import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.util.CalendarUtil
import id.faizalempe.domain.usecase.transaction.CreateTransaction
import id.faizalempe.domain.usecase.transaction.DeleteTransaction
import id.faizalempe.domain.usecase.transaction.UpdateTransaction
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionContract
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionPresenter
import io.mockk.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionPresenterTest, v 0.1 19/12/22 19.19 by Faizal Muhammad Priyowibowo
 */
class DetailTransactionPresenterTest {

    private val view: DetailTransactionContract.View = mockk(relaxed = true)

    private val createTransaction: CreateTransaction = mockk(relaxed = true)
    private val updateTransaction: UpdateTransaction = mockk(relaxed = true)
    private val deleteTransaction: DeleteTransaction = mockk(relaxed = true)

    lateinit var presenter: DetailTransactionPresenter

    private val scheduler by lazy { Schedulers.trampoline() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter =
            DetailTransactionPresenter(view, createTransaction, updateTransaction, deleteTransaction)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `test create transaction return result`() {
        // given
        every { createTransaction.observe(any(), any(), any()) } answers {
            secondArg<(Unit) -> Unit>().invoke(Unit)
        }

        // when
        presenter.createTransaction(generateTransactionDto())

        // then
        verify {
            view.showLoading()
            view.onSubmitSuccess()
        }
    }

    @Test
    fun `test update transaction return result`() {
        // given
        every { updateTransaction.observe(any(), any(), any()) } answers {
            secondArg<(Unit) -> Unit>().invoke(Unit)
        }

        // when
        presenter.updateTransaction(generateTransactionDto())

        // then
        verify {
            view.showLoading()
            view.onSubmitSuccess()
        }
    }

    @Test
    fun `test delete transaction return result`() {
        // given
        val params = DeleteTransaction.Params(99)
        every { deleteTransaction.observe(params, any(), any()) } answers {
            secondArg<(Unit) -> Unit>().invoke(Unit)
        }

        // when
        presenter.deleteTransaction(99)

        // then
        verify {
            view.showLoading()
            view.onSubmitSuccess()
        }
    }

    private fun generateTransactionDto(): TransactionDto = TransactionDto(
        id = 99,
        category = WangkuConstant.TransactionCategory.EXPENSE,
        desc = "Pengeluaran",
        amount = 1000,
        datetime = CalendarUtil.getCurrentDate()
    )

    @After
    fun tearDown() {
        unmockkAll()
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }


}
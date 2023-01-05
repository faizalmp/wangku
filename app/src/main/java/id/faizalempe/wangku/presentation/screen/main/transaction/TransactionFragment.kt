package id.faizalempe.wangku.presentation.screen.main.transaction

import id.faizalempe.core.ext.tag
import id.faizalempe.core.util.CalendarUtil
import id.faizalempe.core.util.CalendarUtil.toCalendar
import id.faizalempe.core.util.CalendarUtil.toDateFormat
import id.faizalempe.wangku.util.ext.getDimen
import id.faizalempe.domain.model.transaction.TransactionBalanceDto
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.databinding.FragmentTransactionBinding
import id.faizalempe.wangku.databinding.ItemTransactionBinding
import id.faizalempe.wangku.presentation.di.main.transaction.TransactionModule
import id.faizalempe.wangku.presentation.base.BaseFragment
import id.faizalempe.wangku.presentation.base.GenericRecyclerViewAdapter
import id.faizalempe.wangku.presentation.screen.main.transaction.detail.DetailTransactionFragment
import id.faizalempe.wangku.util.ext.clickDebounce
import id.faizalempe.wangku.util.ext.getItemViewBinding
import id.faizalempe.wangku.util.ext.getViewBinding
import id.faizalempe.wangku.util.view.LinearItemMarginDecoration
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version TransactionFragment, v 0.1 09/12/22 13.04 by Faizal Muhammad Priyowivowo
 */
class TransactionFragment : BaseFragment<FragmentTransactionBinding>(), TransactionContract.View {

    @Inject
    lateinit var presenter: TransactionPresenter

    private val transactionAdapter by lazy {
        GenericRecyclerViewAdapter<TransactionDto, ItemTransactionBinding>(
            areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
            itemViewBinding = { getItemViewBinding(ItemTransactionBinding::inflate) },
            onBindItem = { itemData, _ ->
                val itemCal = itemData.datetime.toCalendar()
                tvTransactionDesc.text = itemData.desc
                tvTransactionCategory.text = itemData.category
                tvTransactionAmount.text = "${itemData.amount}"
                tvTransactionDate.text = "${itemCal.get(Calendar.DAY_OF_MONTH)}"
                tvTransactionMonth.text = itemCal.toDateFormat(CalendarUtil.Format.MONTH_FORMAT)
                tvTransactionYear.text = "${itemCal.get(Calendar.YEAR)}"
            },
            onItemClick = { itemData, _ ->
                DetailTransactionFragment.newInstance(
                    transactionDto = itemData,
                    onDismiss = { presenter.getTransactionBalance() }
                ).show(childFragmentManager, tag<DetailTransactionFragment>())
            }
        )
    }

    override fun inflateViewBinding(): FragmentTransactionBinding =
        getViewBinding(FragmentTransactionBinding::inflate)

    override fun FragmentTransactionBinding.init() {
        inject()
        presenter.attach(lifecycle)
        initView()
        initListener()
        presenter.getTransactionBalance()
    }
    
    private fun FragmentTransactionBinding.initView() {
        rvTransaction.apply {
            addItemDecoration(
                LinearItemMarginDecoration(context.getDimen(com.intuit.sdp.R.dimen._8sdp))
            )
            adapter = transactionAdapter
        }
    }

    private fun FragmentTransactionBinding.initListener() {
        flipperTransaction.clickRefresh { presenter.getTransactionBalance() }
        fabTransactionCreate.clickDebounce {
            DetailTransactionFragment.newInstance(
                onDismiss = { presenter.getTransactionBalance() }
            ).show(childFragmentManager, tag<DetailTransactionFragment>())
        }
    }

    override fun showLoading() = binding.flipperTransaction.showLoading()

    override fun showError(message: String) = binding.flipperTransaction.showError()

    override fun showContent(transactionBalance: TransactionBalanceDto) {
        with(binding) {
            flipperTransaction.showContent()
            transactionAdapter.setData(transactionBalance.transactions)
            tvTransactionTotal.text = "${transactionBalance.balance.total}"
            tvTransactionIncome.text = "${transactionBalance.balance.cashIn}"
            tvTransactionExpense.text = "${transactionBalance.balance.cashOut}"
        }
    }

    private fun inject() {
        WangkuApp.getAppComponent(requireContext())
            .plus(TransactionModule(this))
            .inject(this)
    }
}
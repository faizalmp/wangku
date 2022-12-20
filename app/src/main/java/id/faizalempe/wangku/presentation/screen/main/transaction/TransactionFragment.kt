package id.faizalempe.wangku.presentation.screen.main.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
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
import id.faizalempe.wangku.util.ext.getViewBind
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
        object : GenericRecyclerViewAdapter<TransactionDto, ItemTransactionBinding>(
            areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id }
        ) {
            override fun inflateViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): ItemTransactionBinding =
                ItemTransactionBinding.inflate(inflater, parent, false)

            override fun onBindItem(itemData: TransactionDto, position: Int) {
                with(itemBinding) {
                    val itemCal = itemData.datetime.toCalendar()
                    tvTransactionDesc.text = itemData.desc
                    tvTransactionCategory.text = itemData.category
                    tvTransactionAmount.text = itemData.amount.toString()
                    tvTransactionDate.text = itemCal.get(Calendar.DAY_OF_MONTH).toString()
                    tvTransactionMonth.text = itemCal.toDateFormat(CalendarUtil.Format.MONTH_FORMAT)
                    tvTransactionYear.text = itemCal.get(Calendar.YEAR).toString()
                }
            }

            override fun onItemClick(itemData: TransactionDto, position: Int) {
                DetailTransactionFragment.newInstance(
                    transactionDto = itemData,
                    onDismiss = { presenter.getTransactionBalance() }
                ).show(childFragmentManager, tag<DetailTransactionFragment>())
            }
        }
    }

    override fun inflateViewBinding(): FragmentTransactionBinding =
        getViewBind(FragmentTransactionBinding::inflate)

    override fun FragmentTransactionBinding.init() {
        inject()
        presenter.attachView(lifecycle)
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

    override fun showLoading() {
        binding.flipperTransaction.showLoading()
    }

    override fun showError(message: String) {
        binding.flipperTransaction.showError()
    }

    override fun showContent(transactionBalance: TransactionBalanceDto) {
        with(binding) {
            flipperTransaction.showContent()
            transactionAdapter.setData(transactionBalance.transactions)
            tvTransactionTotal.text = transactionBalance.balance.total.toString()
            tvTransactionIncome.text = transactionBalance.balance.cashIn.toString()
            tvTransactionExpense.text = transactionBalance.balance.cashOut.toString()
        }
    }

    private fun inject() {
        WangkuApp.getAppComponent(requireContext())
            .plus(TransactionModule(this))
            .inject(this)
    }
}
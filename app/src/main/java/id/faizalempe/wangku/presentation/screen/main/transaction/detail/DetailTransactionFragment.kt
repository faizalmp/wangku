package id.faizalempe.wangku.presentation.screen.main.transaction.detail

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.ext.*
import id.faizalempe.core.util.CalendarUtil.getCalendarDate
import id.faizalempe.core.util.CalendarUtil.getCurrentDate
import id.faizalempe.core.util.CalendarUtil.toCalendar
import id.faizalempe.core.util.CalendarUtil.toDateFormat
import id.faizalempe.domain.model.transaction.TransactionDto
import id.faizalempe.wangku.R
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.databinding.FragmentTransactionDetailBinding
import id.faizalempe.wangku.presentation.base.BaseBottomSheetFragment
import id.faizalempe.wangku.presentation.di.main.transaction.detail.DetailTransactionModule
import id.faizalempe.wangku.util.ext.clickDebounce
import id.faizalempe.wangku.util.ext.getViewBind
import id.faizalempe.wangku.util.ext.goneIf
import id.faizalempe.wangku.util.ext.showToast
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DetailTransactionFragment, v 0.1 16/12/22 15.03 by Faizal Muhammad Priyowibowo
 */
class DetailTransactionFragment :
    BaseBottomSheetFragment<FragmentTransactionDetailBinding>(),
    DetailTransactionContract.View {

    private val transaction: TransactionDto? by lazy {
        arguments?.getParcelable(TRANSACTION_ARGS) as? TransactionDto
    }

    private var onDismiss: () -> Unit = {}

    @Inject
    lateinit var presenter: DetailTransactionPresenter

    override fun inflateViewBinding(): FragmentTransactionDetailBinding =
        getViewBind(FragmentTransactionDetailBinding::inflate)

    override fun showLoading() = binding.flipperTransactiondetail.showLoading()

    override fun showError(message: String) = binding.flipperTransactiondetail.showContent()

    override fun onSubmitSuccess() {
        binding.flipperTransactiondetail.showContent()
        dismissAllowingStateLoss()
    }

    override fun FragmentTransactionDetailBinding.init() {
        inject()
        presenter.attachView(lifecycle)
        initView()
        initListener()
    }

    private fun FragmentTransactionDetailBinding.initView() {
        flipperTransactiondetail.showContent()
        btnTransactiondetailDelete.goneIf(transaction == null)
        safe(transaction) { data ->
            tvTransactiondetailTitle.text = getString(R.string.transactiondetail_edit_title)
            btnTransactiondetail.text = getString(R.string.transactiondetail_edit_action)
            etTransactiondetailDate.setText(data.datetime)
            etTransactiondetailAmount.setText(data.amount.toString())
            etTransactiondetailDesc.setText(data.desc)
            rgTransactiondetail.check(
                if (data.category == WangkuConstant.TransactionCategory.INCOME) {
                    R.id.rb_income
                } else {
                    R.id.rb_expense
                }
            )
        } ?: run {
            tvTransactiondetailTitle.text = getString(R.string.transactiondetail_create_title)
            btnTransactiondetail.text = getString(R.string.transactiondetail_create_action)
            etTransactiondetailDate.setText(getCurrentDate())
            etTransactiondetailAmount.setText(DEFAULT_AMOUNT)
            rgTransactiondetail.check(R.id.rb_income)
        }
    }

    private fun FragmentTransactionDetailBinding.initListener() {
        etTransactiondetailDate.clickDebounce {
            showDatePicker { date -> etTransactiondetailDate.setText(date) }
        }
        btnTransactiondetail.clickDebounce {
            submitCheck { submit() }
        }

        btnTransactiondetailDelete.clickDebounce {
            safe(transaction) { presenter.deleteTransaction(it.id) }
        }
    }

    private fun FragmentTransactionDetailBinding.submitCheck(action: () -> Unit = {}) {
        val amount = etTransactiondetailAmount.text.toString()
        when {
            rgTransactiondetail.checkedRadioButtonId == -1 -> {
                context.showToast("Please choose transaction type")
                return
            }
            amount.isEmpty() || amount == DEFAULT_AMOUNT -> {
                context.showToast("Please enter amount")
                return
            }
            else -> action.invoke()
        }
    }

    private fun FragmentTransactionDetailBinding.submit() {
        val category = if (rbIncome.isChecked) {
            WangkuConstant.TransactionCategory.INCOME
        } else {
            WangkuConstant.TransactionCategory.EXPENSE
        }
        val amount = if (rbIncome.isChecked) {
            etTransactiondetailAmount.text.toString().deleteSubstring(NEGATIVE).toLong().orZero()
        } else {
            etTransactiondetailAmount.text.toString()
                .appendSubstringIf(
                    NEGATIVE,
                    !etTransactiondetailAmount.text.toString().contains(NEGATIVE)
                )
                .toLong()
                .orZero()
        }
        safe(transaction) { trx ->
            presenter.updateTransaction(
                trx.copy(
                    amount = amount,
                    desc = etTransactiondetailDesc.text.toString(),
                    datetime = etTransactiondetailDate.text.toString(),
                    category = category
                )
            )
        } ?: run {
            presenter.createTransaction(
                TransactionDto(
                    amount = amount,
                    desc = etTransactiondetailDesc.text.toString(),
                    datetime = etTransactiondetailDate.text.toString(),
                    category = category
                )
            )
        }
    }

    private fun inject() {
        WangkuApp.getAppComponent(requireContext())
            .plus(DetailTransactionModule(this))
            .inject(this)
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        safe(context) { ctx ->
            val dateCalendar = transaction?.datetime?.toCalendar()
                ?: getCurrentDate().toCalendar()
            DatePickerDialog(
                ctx,
                { _, year, month, dayOfMonth ->
                    onDateSelected.invoke(getCalendarDate(year, month, dayOfMonth).toDateFormat())
                },
                dateCalendar.get(Calendar.YEAR),
                dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded) {
            try { super.show(manager, tag) } catch (_: IllegalStateException) {}
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismiss.invoke()
        super.onDismiss(dialog)
    }

    companion object {

        private const val TRANSACTION_ARGS = "TRANSACTION_ARGS"
        private const val DEFAULT_AMOUNT = "0"
        private const val NEGATIVE = "-"

        @JvmStatic
        fun newInstance(
            transactionDto: TransactionDto? = null,
            onDismiss: () -> Unit = {}
        ) = DetailTransactionFragment().apply {
            arguments = bundleOf(TRANSACTION_ARGS to transactionDto)
            this.onDismiss = onDismiss
        }
    }
}
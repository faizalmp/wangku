package id.faizalempe.wangku.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.core.constant.ext.getColorCompat
import id.faizalempe.wangku.util.ext.viewBinding

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseFragment, v 0.1 08/12/22 14.16 by Faizal Muhammad Priyowibowo
 */
abstract class BaseFragment<VB : ViewBinding>(
    @ColorRes private val statusBarColorResId: Int = WangkuConstant.NO_RESOURCES
) : Fragment() {

    protected val binding: VB by viewBinding { inflateViewBinding() }

    protected val navController by lazy { findNavController() }

    abstract fun inflateViewBinding(): VB

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun back() {
        navController.previousBackStackEntry?.let { navController.navigateUp() } ?: finish()
    }

    override fun onStart() {
        super.onStart()
        if (statusBarColorResId != WangkuConstant.NO_RESOURCES) {
            activity?.window?.statusBarColor = context.getColorCompat(statusBarColorResId)
        }
    }

    protected fun finish() {
        activity?.finish()
    }
}
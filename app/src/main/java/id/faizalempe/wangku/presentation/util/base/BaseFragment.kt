package id.faizalempe.wangku.presentation.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseActivity, v 0.1 08/12/22 14.16 by Faizal Muhammad Priyowibowo
 */
abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

    protected val navController by lazy { findNavController() }

    abstract fun inflateViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    protected fun back() {
        navController.previousBackStackEntry?.let { navController.navigateUp() } ?: finish()
    }

    protected fun finish() {
        activity?.finish()
    }
}
package id.faizalempe.wangku.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.faizalempe.wangku.util.ext.viewBinding

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseBottomSheetFragment, v 0.1 08/12/22 14.16 by Faizal Muhammad Priyowibowo
 */
abstract class BaseBottomSheetFragment<VB : ViewBinding>(
) : BottomSheetDialogFragment() {

    protected val binding: VB by viewBinding { inflateViewBinding() }

    abstract fun inflateViewBinding(): VB

    abstract fun VB.init()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.init()
    }
}
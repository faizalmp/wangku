package id.faizalempe.wangku.presentation.screen.home.menu

import android.os.Bundle
import android.view.View
import id.faizalempe.wangku.databinding.FragmentMenuBinding
import id.faizalempe.wangku.util.base.BaseFragment
import id.faizalempe.wangku.util.ext.getViewBind

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MenuFragment, v 0.1 09/12/22 13.04 by Faizal Muhammad Priyowivowo
 */
class MenuFragment : BaseFragment<FragmentMenuBinding>() {

    override fun inflateViewBinding(): FragmentMenuBinding =
        getViewBind(FragmentMenuBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {

    }
}
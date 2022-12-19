package id.faizalempe.wangku.presentation.screen.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import id.faizalempe.core.ext.safe
import id.faizalempe.wangku.R
import id.faizalempe.wangku.databinding.FragmentMainBinding
import id.faizalempe.wangku.presentation.base.BaseFragment
import id.faizalempe.wangku.util.ext.getViewBind

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version MainFragment, v 0.1 08/12/22 17.51 by Faizal Muhammad Priyowibowo
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val navChildController by lazy {
        (childFragmentManager.findFragmentById(R.id.host_main) as? NavHostFragment)
            ?.findNavController()
    }

    override fun inflateViewBinding(): FragmentMainBinding =
        getViewBind(FragmentMainBinding::inflate)

    override fun FragmentMainBinding.init() {
        safe(navChildController) { bottomnavMain.setupWithNavController(it) }
    }
}
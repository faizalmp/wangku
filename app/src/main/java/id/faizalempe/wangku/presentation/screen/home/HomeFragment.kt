package id.faizalempe.wangku.presentation.screen.home

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import id.faizalempe.wangku.R
import id.faizalempe.wangku.databinding.FragmentHomeBinding
import id.faizalempe.wangku.util.base.BaseFragment
import id.faizalempe.wangku.util.ext.getViewBind

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version HomeFragment, v 0.1 08/12/22 17.51 by Faizal Muhammad Priyowibowo
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val navChildController by lazy {
        (childFragmentManager.findFragmentById(R.id.host_home) as? NavHostFragment)
            ?.findNavController()
    }

    override fun inflateViewBinding(): FragmentHomeBinding =
        getViewBind(FragmentHomeBinding::inflate)

    override fun initView() {
        with(binding) {
            navChildController?.let { bottomNavHome.setupWithNavController(it) }
        }
    }
}
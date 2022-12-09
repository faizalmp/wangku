package id.faizalempe.wangku.presentation.screen.home

import android.os.Bundle
import android.view.View
import id.faizalempe.wangku.databinding.FragmentHomeBinding
import id.faizalempe.wangku.presentation.util.base.BaseFragment

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version HomeFragment, v 0.1 08/12/22 17.51 by Faizal Muhammad Priyowibowo
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun inflateViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
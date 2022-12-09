package id.faizalempe.wangku.presentation.screen.splash

import android.os.Bundle
import android.view.View
import id.faizalempe.wangku.R
import id.faizalempe.wangku.databinding.FragmentSplashBinding
import id.faizalempe.wangku.presentation.util.base.BaseFragment
import id.faizalempe.wangku.presentation.util.constant.WangkuConstant
import id.faizalempe.wangku.presentation.util.ext.RxUtil
import id.faizalempe.wangku.presentation.util.ext.startNewContainerActivity

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version SplashFragment, v 0.1 08/12/22 17.27 by Faizal Muhammad Priyowibowo
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun inflateViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxUtil.doDelayed(WangkuConstant.Time.SPLASH_DELAY) {
            context.startNewContainerActivity(R.navigation.navigation_main)
        }
    }
}
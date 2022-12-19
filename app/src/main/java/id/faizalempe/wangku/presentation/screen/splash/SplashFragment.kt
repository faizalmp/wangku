package id.faizalempe.wangku.presentation.screen.splash

import id.faizalempe.wangku.databinding.FragmentSplashBinding
import id.faizalempe.wangku.presentation.base.BaseFragment
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.wangku.util.RxUtil
import id.faizalempe.wangku.util.ext.startNewContainerActivity
import id.faizalempe.wangku.navigation.WangkuScreen

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version SplashFragment, v 0.1 08/12/22 17.27 by Faizal Muhammad Priyowibowo
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun inflateViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun FragmentSplashBinding.init() {
        RxUtil.doDelayed(WangkuConstant.Time.SPLASH_DELAY) {
            context.startNewContainerActivity(WangkuScreen.Main, true)
        }
    }
}
package id.faizalempe.wangku.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.faizalempe.wangku.R
import id.faizalempe.wangku.databinding.ActivityContainerBinding
import id.faizalempe.wangku.navigation.WangkuScreen
import id.faizalempe.wangku.presentation.base.BaseActivity
import id.faizalempe.wangku.util.ext.getViewBind

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {

    private val navGraphId by lazy {
        intent.getIntExtra(EXTRA_NAV_GRAPH, WangkuScreen.Splash.navGraphRes)
    }

    private val param by lazy {
        intent.getStringExtra(EXTRA_PARAM).orEmpty()
    }

    override fun inflateViewBinding(): ActivityContainerBinding =
        getViewBind(ActivityContainerBinding::inflate)

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container_host) as? NavHostFragment)
            ?.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController?.setGraph(navGraphId, bundleOf(NAV_ARGUMENT to param))
    }

    companion object {
        const val EXTRA_NAV_GRAPH = "EXTRA_NAV_GRAPH"
        const val EXTRA_PARAM = "EXTRA_PARAM"

        const val NAV_ARGUMENT = "NAV_ARGUMENT"
    }
}
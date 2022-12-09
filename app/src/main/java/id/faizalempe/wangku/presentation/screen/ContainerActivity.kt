package id.faizalempe.wangku.presentation.screen

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.faizalempe.wangku.R
import id.faizalempe.wangku.databinding.ActivityContainerBinding
import id.faizalempe.wangku.presentation.util.base.BaseActivity

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {

    private val navGraphId by lazy {
        intent.getIntExtra(EXTRA_NAV_GRAPH, R.navigation.navigation_splash)
    }

    override fun inflateViewBinding(): ActivityContainerBinding =
        ActivityContainerBinding.inflate(layoutInflater)

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container_host) as? NavHostFragment)
            ?.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController?.setGraph(navGraphId)
    }

    companion object {
        const val EXTRA_NAV_GRAPH = "EXTRA_NAV_GRAPH"
    }
}
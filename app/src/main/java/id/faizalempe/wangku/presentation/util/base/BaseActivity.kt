package id.faizalempe.wangku.presentation.util.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseActivity, v 0.1 08/12/22 14.16 by Faizal Muhammad Priyowibowo
 */
abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
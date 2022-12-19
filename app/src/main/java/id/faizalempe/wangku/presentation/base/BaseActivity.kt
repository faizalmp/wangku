package id.faizalempe.wangku.presentation.base

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import id.faizalempe.core.constant.WangkuConstant
import id.faizalempe.wangku.util.ext.getColorCompat
import id.faizalempe.wangku.R
import id.faizalempe.wangku.util.ext.viewBinding

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseActivity, v 0.1 08/12/22 14.16 by Faizal Muhammad Priyowibowo
 */
abstract class BaseActivity<VB : ViewBinding>(
    @ColorRes private val statusBarColorResId: Int = R.color.color_primary
) : AppCompatActivity() {

    protected val binding: VB by viewBinding { inflateViewBinding() }

    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        if (statusBarColorResId != WangkuConstant.Presentation.NO_RESOURCES) {
            window.statusBarColor = getColorCompat(statusBarColorResId)
        }
    }
}
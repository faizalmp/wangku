package id.faizalempe.wangku.presentation.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * @author Yoko Ahadazaro (yoko.ahadazaro@dana.id)
 * @version BasePresenter, v 0.1 20/12/22 10.32 by Yoko Ahadazaro
 */
open class BasePresenter : DefaultLifecycleObserver {

    private var lifecycle: Lifecycle? = null

    fun attachView(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle

        lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycle = null
        super.onDestroy(owner)
    }
}
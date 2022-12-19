package id.faizalempe.domain.util

import id.faizalempe.core.ext.OnErrorCallback
import id.faizalempe.core.ext.OnSuccessCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version BaseUseCase, v 0.1 19/12/22 22.30 by Faizal Muhammad Priyowibowo
 */
abstract class BaseUseCase<T : Any, P> {

    private val disposables by lazy { CompositeDisposable() }

    abstract fun build(params: P) : Observable<T>

    fun observe(
        params: P,
        onSuccess: OnSuccessCallback<T>,
        onError: OnErrorCallback,
        onComplete: () -> Unit = {},
        onDispose: () -> Unit = {}
    ) {
        build(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnDispose(onDispose)
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
                dispose()
            }, {
                onComplete()
                dispose()
            }).let { disposables.add(it) }
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}
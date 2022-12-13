package id.faizalempe.core.ext

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version RxExt, v 0.1 12/12/22 17.45 by Faizal Muhammad Priyowivowo
 */

inline fun <reified T : Any> Observable<T>.observe(
    noinline onSuccess: (T) -> Unit,
    noinline onError: (Throwable) -> Unit,
    noinline onComplete: () -> Unit = {},
) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableObserver<T>() {
            override fun onNext(t: T) {
                onSuccess(t)
                dispose()
            }

            override fun onError(e: Throwable) {
                onError(e)
                e.message?.let { Log.e("http error", it) }
                dispose()
            }

            override fun onComplete() {
                onComplete()
            }
        })
}
package id.faizalempe.wangku.util

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version RxExt, v 0.1 09/12/22 08.56 by Faizal Muhammad Priyowibowo
 */

object RxUtil {

    private lateinit var disposable: Disposable

    fun doDelayed(delay: Long, action: () -> Unit) {
        if (RxUtil::disposable.isInitialized) disposable.dispose()
        disposable = Observable.timer(delay, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe { action.invoke() }
    }

}
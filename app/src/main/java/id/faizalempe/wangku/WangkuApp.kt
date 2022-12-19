package id.faizalempe.wangku

import android.app.Application
import android.content.Context
import id.faizalempe.data.di.ApiModule
import id.faizalempe.data.di.NetworkModule
import id.faizalempe.wangku.di.component.ApplicationComponent
import id.faizalempe.wangku.di.component.DaggerApplicationComponent
import id.faizalempe.wangku.di.module.ApplicationModule
import id.faizalempe.wangku.di.module.RepositoryModule

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version WangkuApp, v 0.1 08/12/22 13.32 by Faizal Muhammad Priyowibowo
 */
class WangkuApp : Application() {

    private val appComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .apiModule(ApiModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun getAppComponent(context: Context): ApplicationComponent =
            (context.applicationContext as WangkuApp).appComponent
    }
}
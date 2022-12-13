package id.faizalempe.wangku.di.component

import android.app.Application
import android.content.Context
import dagger.Component
import id.faizalempe.data.di.ApiModule
import id.faizalempe.data.di.NetworkModule
import id.faizalempe.domain.interactor.NewsInteractor
import id.faizalempe.domain.repository.NewsRepository
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.di.module.ApplicationModule
import id.faizalempe.wangku.di.module.InteractorModule
import id.faizalempe.wangku.di.module.RepositoryModule
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ApplicationComponent, v 0.1 12/12/22 11.45 by Faizal Muhammad Priyowivowo
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class,
        InteractorModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: WangkuApp)

    // App
    fun application(): Application
    fun context(): Context

    // Repository
    fun newsRepository(): NewsRepository

    // Interactor
    fun newsInteractor(): NewsInteractor

}
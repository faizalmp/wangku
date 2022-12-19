package id.faizalempe.wangku.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id.faizalempe.wangku.WangkuApp
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ApplicationModule, v 0.1 12/12/22 11.52 by Faizal Muhammad Priyowivowo
 */
@Module
class ApplicationModule(private val app: WangkuApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}
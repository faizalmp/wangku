package id.faizalempe.wangku.di.component

import android.app.Application
import android.content.Context
import dagger.Component
import id.faizalempe.data.di.ApiModule
import id.faizalempe.data.di.DatabaseModule
import id.faizalempe.data.di.NetworkModule
import id.faizalempe.data.local.dao.TransactionDao
import id.faizalempe.domain.repository.NewsRepository
import id.faizalempe.domain.repository.TransactionRepository
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.di.module.ApplicationModule
import id.faizalempe.wangku.di.module.RepositoryModule
import id.faizalempe.wangku.presentation.di.main.news.NewsComponent
import id.faizalempe.wangku.presentation.di.main.news.NewsModule
import id.faizalempe.wangku.presentation.di.main.transaction.TransactionComponent
import id.faizalempe.wangku.presentation.di.main.transaction.TransactionModule
import id.faizalempe.wangku.presentation.di.main.transaction.detail.DetailTransactionComponent
import id.faizalempe.wangku.presentation.di.main.transaction.detail.DetailTransactionModule
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
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: WangkuApp)

    // Subcomponent
    fun plus(module: NewsModule): NewsComponent
    fun plus(module: TransactionModule): TransactionComponent
    fun plus(module: DetailTransactionModule): DetailTransactionComponent

    // App
    fun application(): Application
    fun context(): Context

    // Dao
    fun transactionDao(): TransactionDao

    // Repository
    fun newsRepository(): NewsRepository
    fun transactionRepository(): TransactionRepository

}
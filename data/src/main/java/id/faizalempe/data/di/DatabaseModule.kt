package id.faizalempe.data.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.faizalempe.data.local.WangkuDatabase
import id.faizalempe.data.local.dao.TransactionDao
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DatabaseModule, v 0.1 15/12/22 15.48 by Faizal Muhammad Priyowibowo
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWangkuDatabase(application: Application): WangkuDatabase =
        WangkuDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideTransactionDao(db: WangkuDatabase): TransactionDao = db.transactionDao()
}
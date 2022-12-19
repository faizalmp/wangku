package id.faizalempe.wangku.di.module

import dagger.Module
import dagger.Provides
import id.faizalempe.data.repository.NewsDataRepository
import id.faizalempe.data.repository.TransactionDataRepository
import id.faizalempe.domain.repository.NewsRepository
import id.faizalempe.domain.repository.TransactionRepository
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version RepositoryModule, v 0.1 13/12/22 01.05 by Faizal Muhammad Priyowivowo
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDataRepository: NewsDataRepository
    ): NewsRepository = newsDataRepository

    @Provides
    @Singleton
    fun provideTransactionRepository(
        transactionDataRepository: TransactionDataRepository
    ): TransactionRepository = transactionDataRepository
}
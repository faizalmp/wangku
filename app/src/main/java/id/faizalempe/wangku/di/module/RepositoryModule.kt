package id.faizalempe.wangku.di.module

import dagger.Module
import dagger.Provides
import id.faizalempe.data.repository.NewsDataRepository
import id.faizalempe.domain.repository.NewsRepository
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version RepositoryModule, v 0.1 13/12/22 01.05 by Faizal Muhammad Priyowivowo
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsDataRepository(
        newsDataRepository: NewsDataRepository
    ): NewsRepository = newsDataRepository
}
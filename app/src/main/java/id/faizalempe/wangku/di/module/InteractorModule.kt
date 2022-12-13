package id.faizalempe.wangku.di.module

import dagger.Module
import dagger.Provides
import id.faizalempe.domain.interactor.NewsInteractor
import id.faizalempe.domain.interactor.NewsInteractorImpl

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version InteractorModule, v 0.1 13/12/22 01.05 by Faizal Muhammad Priyowivowo
 */
@Module
class InteractorModule {

    @Provides
    fun provideNewsInteractor(
        newsInteractorImpl: NewsInteractorImpl
    ): NewsInteractor = newsInteractorImpl
}
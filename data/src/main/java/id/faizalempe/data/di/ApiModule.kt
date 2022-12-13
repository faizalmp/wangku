package id.faizalempe.data.di

import dagger.Module
import dagger.Provides
import id.faizalempe.data.BuildConfig
import id.faizalempe.data.remote.api.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ApiModule, v 0.1 12/12/22 14.10 by Faizal Muhammad Priyowivowo
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideNewsApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
    ): NewsApi = retrofitBuilder
        .baseUrl(BuildConfig.NEWS_API_HOST)
        .client(okHttpClientBuilder.build())
        .build()
        .create(NewsApi::class.java)

}
package id.faizalempe.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NetworkModule, v 0.1 12/12/22 14.10 by Faizal Muhammad Priyowivowo
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkhttpClientBuilder(
        okhttpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder = OkHttpClient
        .Builder()
        .addInterceptor(okhttpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

}
package id.faizalempe.data.remote.api

import id.faizalempe.data.BuildConfig
import id.faizalempe.data.remote.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsApi, v 0.1 12/12/22 14.57 by Faizal Muhammad Priyowivowo
 */
interface NewsApi {

    @GET("top-headlines")
    fun getNews(
        @Query("apiKey") region: String = BuildConfig.NEWS_API_KEY,
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 5
    ): Single<NewsResponse>
}
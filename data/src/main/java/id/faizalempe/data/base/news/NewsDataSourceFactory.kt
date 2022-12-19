package id.faizalempe.data.base.news

import id.faizalempe.data.base.AbstractDataSource
import id.faizalempe.data.base.DataSourceType
import id.faizalempe.data.mock.NewsMockDataSource
import id.faizalempe.data.remote.datasource.NewsRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsDataSourceFactory, v 0.1 12/12/22 15.48 by Faizal Muhammad Priyowivowo
 */
@Singleton
class NewsDataSourceFactory @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val mockDataSource: NewsMockDataSource
) : AbstractDataSource<NewsDataSource>() {

    override fun createData(source: DataSourceType): NewsDataSource = when (source) {
        DataSourceType.REMOTE -> remoteDataSource
        DataSourceType.MOCK -> mockDataSource
        else -> remoteDataSource
    }

}
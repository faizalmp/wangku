package id.faizalempe.data.repository

import id.faizalempe.data.base.DataSourceType
import id.faizalempe.data.base.news.NewsDataSourceFactory
import id.faizalempe.data.mapper.NewsMapper
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsDataRepository, v 0.1 12/12/22 17.27 by Faizal Muhammad Priyowivowo
 */
@Singleton
class NewsDataRepository @Inject constructor(
    private val dataSourceFactory: NewsDataSourceFactory
) : NewsRepository {

    override fun getRemoteNews(): Observable<NewsDto> =
        dataSourceFactory.createData(DataSourceType.REMOTE).getNews().map { response ->
            NewsMapper.mapNewsResponseToDto(response)
        }

}
package id.faizalempe.data.base.transaction

import id.faizalempe.data.base.AbstractDataSource
import id.faizalempe.data.base.DataSourceType
import id.faizalempe.data.local.datasource.TransactionLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version TransactionDataSourceFactory, v 0.1 12/12/22 15.48 by Faizal Muhammad Priyowivowo
 */
@Singleton
class TransactionDataSourceFactory @Inject constructor(
    private val localDataSource: TransactionLocalDataSource
) : AbstractDataSource<TransactionDataSource>() {

    override fun createData(source: DataSourceType): TransactionDataSource = localDataSource

}
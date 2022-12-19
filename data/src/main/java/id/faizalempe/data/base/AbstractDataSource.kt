package id.faizalempe.data.base

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version AbstractDataSource, v 0.1 12/12/22 15.50 by Faizal Muhammad Priyowivowo
 */
abstract class AbstractDataSource<T> {

    abstract fun createData(source: DataSourceType): T

}
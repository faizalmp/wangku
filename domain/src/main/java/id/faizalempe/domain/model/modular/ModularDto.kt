package id.faizalempe.domain.model.modular

import com.squareup.moshi.Json

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ModularDto, v 0.1 09/12/22 16.14 by Faizal Muhammad Priyowivowo
 */
data class ModularDto(
    @Json(name = "type")
    private val _type: ModularType?,
    @Json(name = "data")
    private val _data: String?
) {
    val type: ModularType get() = _type ?: ModularType.Unknown
    val data: String get() = _data.orEmpty()
}
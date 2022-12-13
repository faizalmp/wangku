package id.faizalempe.domain.model.modular

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ModularType, v 0.1 09/12/22 16.16 by Faizal Muhammad Priyowivowo
 */
enum class ModularType(type: String) {
    Profile("PROFILE"),
    Balance("BALANCE"),
    Shortcut("SHORTCUT"),
    News("NEWS"),
    Unknown("UNKNOWN")
}

object ModularViewType {
    const val PROFILE = 0
    const val BALANCE = 1
    const val SHORTCUT = 2
    const val NEWS = 3
    const val UNKNOWN = 99
}
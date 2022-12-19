package utils

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import constants.Dependencies.Command
import java.util.*

private fun DependencyHandler.addModule(pair: Pair<String, String>) {
    add(pair.first.replace("module", "").toLowerCase(Locale.ROOT), project(pair.second))
}
fun DependencyHandler.implementLibs(libs: List<Pair<String, String>>) {
    libs.forEach { pair ->
        when (pair.first) {
            Command.moduleImplementation,
            Command.moduleApi -> addModule(pair)
            else -> add(pair.first, pair.second)
        }
    }
}
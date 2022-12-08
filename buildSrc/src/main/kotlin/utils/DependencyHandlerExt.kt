package utils

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import constants.Dependencies.Command

fun DependencyHandler.implementModule(modulePath: String) {
    add(Command.implementation, project(modulePath))
}
fun DependencyHandler.implementLibs(libs: List<Pair<String, String>>) {
    libs.forEach { pair ->
        when (pair.first) {
            Command.moduleImplementation -> implementModule(pair.second)
            else -> add(pair.first, pair.second)
        }
    }
}
package constants

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version Dependencies, v 0.1 08/12/22 10.49 by Faizal Muhammad Priyowibowo
 */
object Dependencies {

    object Command {
        const val implementation = "implementation"
        const val testImplementation = "testImplementation"
        const val androidTestImplementation = "androidTestImplementation"
        const val api = "api"

        const val moduleImplementation = "moduleImplementation"
        const val moduleApi = "moduleApi"
    }

    private object Versions {
        // Common Lib Version
        const val kotlinVersion = "1.7.0"
        const val appCompatVersion = "1.5.1"
        const val rxJavaVersion = "2.2.21"

        // Test Lib Version
        const val jUnitVersion = "4.13.2"
        const val androidJUnitVersion = "1.1.4"
        const val espressoVersion = "3.5.0"

        // UI Lib Version
        const val materialVersion = "1.7.0"
        const val constraintLayoutVersion = "2.1.4"
        const val navVersion = "2.5.3"
    }

    private object Libs {
        // Common Libs
        const val kotlin = "androidx.core:core-ktx:${Versions.kotlinVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        val commonLibs = listOf(
            Command.implementation to kotlin,
            Command.implementation to appCompat,
            Command.implementation to rxJava
        )

        // Test Libs
        const val jUnit = "junit:junit:${Versions.jUnitVersion}"
        const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnitVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        val unitTestLibs = listOf(
            Command.testImplementation to jUnit
        )
        val instrumentedTestLibs = listOf(
            Command.androidTestImplementation to androidJUnit,
            Command.androidTestImplementation to espresso
        )

        // UI Libs
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        val uiLibs = listOf(
            Command.implementation to material,
            Command.implementation to constraintLayout,
            Command.implementation to navFragment,
            Command.implementation to navUi
        )
    }

    object Module {
        private const val appModulePath = ":app"
        private const val dataModulePath = ":data"
        private const val domainModulePath = ":domain"
        val appModule = Command.moduleImplementation to appModulePath
        val dataModule = Command.moduleImplementation to dataModulePath
        val domainModule = Command.moduleImplementation to domainModulePath
    }

    val appDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.uiLibs)
        addAll(Libs.unitTestLibs)
        addAll(Libs.instrumentedTestLibs)
        add(Module.domainModule)
    }

    val dataDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.unitTestLibs)
    }

    val domainDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.unitTestLibs)
        add(Module.dataModule)
    }
}
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
        const val kapt = "kapt"

        const val moduleImplementation = "moduleImplementation"
        const val moduleApi = "moduleApi"
    }

    private object Versions {
        // Common Lib Version
        const val kotlinVersion = "1.7.0"
        const val appCompatVersion = "1.5.1"
        const val rxJavaVersion = "2.2.21"
        const val rxAndroidVersion = "2.1.1"
        const val moshiVersion = "1.14.0"
        const val daggerVersion = "2.44.2"

        // Test Lib Version
        const val jUnitVersion = "4.13.2"
        const val androidJUnitVersion = "1.1.4"
        const val espressoVersion = "3.5.0"

        // UI Lib Version
        const val materialVersion = "1.7.0"
        const val constraintLayoutVersion = "2.1.4"
        const val navVersion = "2.5.3"
        const val intuitVersion = "1.1.0"

        // Network Lib Version
        const val retrofitVersion = "2.9.0"
        const val okhttpVersion = "4.10.0"
    }

    private object Libs {
        // Common Libs
        const val kotlin = "androidx.core:core-ktx:${Versions.kotlinVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
        val commonLibs = listOf(
            Command.implementation to kotlin,
            Command.implementation to appCompat,
            Command.implementation to rxJava,
            Command.implementation to rxAndroid,
            Command.implementation to moshi,
            Command.implementation to dagger,
            Command.kapt to daggerCompiler
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
        const val intuitSdp = "com.intuit.sdp:sdp-android:${Versions.intuitVersion}"
        const val intuitSsp = "com.intuit.ssp:ssp-android:${Versions.intuitVersion}"
        val uiLibs = listOf(
            Command.implementation to material,
            Command.implementation to constraintLayout,
            Command.implementation to navFragment,
            Command.implementation to navUi,
            Command.implementation to intuitSdp,
            Command.implementation to intuitSsp
        )

        // Network Libs
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
        val networkLibs = listOf(
            Command.implementation to retrofit,
            Command.implementation to retrofitRxJava,
            Command.implementation to retrofitMoshiConverter,
            Command.implementation to okhttp,
            Command.implementation to okhttpLogging,
        )
    }

    object Module {
        private const val appModulePath = ":app"
        private const val dataModulePath = ":data"
        private const val domainModulePath = ":domain"
        private const val coreModulePath = ":core"
        val appModule = Command.moduleImplementation to appModulePath
        val dataModule = Command.moduleImplementation to dataModulePath
        val domainModule = Command.moduleImplementation to domainModulePath
        val coreModule = Command.moduleImplementation to coreModulePath
    }

    val appDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.uiLibs)
        addAll(Libs.unitTestLibs)
        addAll(Libs.instrumentedTestLibs)
        addAll(Libs.networkLibs)
        add(Module.coreModule)
        add(Module.domainModule)
        add(Module.dataModule)
    }

    val dataDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.unitTestLibs)
        addAll(Libs.networkLibs)
        add(Module.coreModule)
        add(Module.domainModule)
    }

    val domainDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.unitTestLibs)
        add(Module.coreModule)
    }

    val coreDependencies = mutableListOf<Pair<String, String>>().apply {
        addAll(Libs.commonLibs)
        addAll(Libs.unitTestLibs)
    }
}
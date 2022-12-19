import constants.Config
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.parcelize")
}

fun getPropertiesFile(path: String) = Properties().apply {
    load(FileInputStream(file(path)))
}

android {
    compileSdk = Config.targetSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(Config.BuildTypes.RELEASE) {
            isMinifyEnabled = true
            getPropertiesFile("../config.properties").forEach { key, value ->
                buildConfigField(type = "String", name = key.toString(), value = value.toString())
            }
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(Config.BuildTypes.DEBUG) {
            isMinifyEnabled = false
            getPropertiesFile("../config.properties").forEach { key, value ->
                buildConfigField(type = "String", name = key.toString(), value = value.toString())
            }
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
import constants.Config
import constants.Dependencies
import utils.implementLibs
import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

fun getPropertiesFile(path: String) = Properties().apply {
    load(FileInputStream(file(path)))
}

android {
    compileSdk = Config.targetSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.AppVersion.versionCode
        versionName = Config.AppVersion.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(Config.BuildTypes.RELEASE) {
            isMinifyEnabled = true
            isShrinkResources = true
            getPropertiesFile("../config.properties").forEach { key, value ->
                buildConfigField(type = "String", name = key.toString(), value = value.toString())
            }
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(Config.BuildTypes.DEBUG) {
            isMinifyEnabled = false
            isShrinkResources = false
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
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies { implementLibs(Dependencies.appDependencies) }
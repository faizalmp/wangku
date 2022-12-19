import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(plugin(id = "com.android.library", version = "7.3.1"))
    implementation(plugin(id = "org.jetbrains.kotlin.android", version = "1.7.20"))
    implementation(plugin(id = "org.jetbrains.kotlin.kapt", version = "1.7.20"))
}

fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
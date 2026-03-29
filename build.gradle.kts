// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.4" apply false

    //add safeargs
    id("androidx.navigation.safeargs.kotlin") version "2.9.0" apply false

    //for RoomDataBase
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false

}

// Hilt plugin for classpath (Gradle Kotlin DSL)
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}

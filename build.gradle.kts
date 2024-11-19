// Top-level build file where you can add configuration options common to all sub-projects/modules.
// tambahkan ini untuk penggunaan navigation pelemparan data
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // navigation
        //noinspection UseTomlInstead
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.4") // versi terbaru

    }
}

//noinspection GradleDependency
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
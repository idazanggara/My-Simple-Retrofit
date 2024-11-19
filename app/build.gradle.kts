plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id ("kotlin-parcelize")

}

android {
    namespace = "com.enigma.mysimpleretrofit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.enigma.mysimpleretrofit"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val baseUrl: String = project.property("BASE_URL") as String
        buildConfigField("String", "BASE_URL", "\"${baseUrl}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Tambah disini
    buildFeatures{
        viewBinding = true
        buildConfig = true // Aktifkan BuildConfig
    }
    // ---

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

//noinspection UseTomlInstead
dependencies {
    // lottie
    implementation ("com.airbnb.android:lottie:4.1.0")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.15.0") // Moshi Kotlin support
    // pastekan juga OkHttp untuk debugging request dan responsenya
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.0")
    implementation ("com.squareup.okhttp3:okhttp:4.2.1")

    // room
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.4")

    // viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    // livedata opsional
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    //Image
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    implementation ("com.squareup.picasso:picasso:2.71828")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    // material
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
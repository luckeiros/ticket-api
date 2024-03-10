plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.luckeiros.ticketandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luckeiros.ticketandroid"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    /* Android */
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)

    /* Kotlin */
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines)

    /* Google */
    implementation(libs.google.material)
    implementation(libs.google.material)

    /* Retrofit */
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)

    /* Koin */
    implementation(libs.koin.android)
    implementation(libs.koin.compiler)
    implementation(libs.koin.core.coroutines)

    /* Glide */
    implementation(libs.glide)
    implementation(libs.glide.compiler)

    /* Testing */
    implementation(libs.junit)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.arch.core.testing)
    implementation(libs.mockk.android)
    implementation(libs.mockk.core)
    implementation(libs.koin.test)
}
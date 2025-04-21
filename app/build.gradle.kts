plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.services)
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

        buildConfigField("String", "BASE_URL", "\"https://app.ticketmaster.com/discovery/v2/\"")
        buildConfigField("long", "CONNECT_TIMEOUT", "10L")
        buildConfigField("long", "READ_TIMEOUT", "30L")
        buildConfigField("long", "WRITE_TIMEOUT", "15L")
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"DW0E98NrxUIfDDtNN7ijruVSm60ryFLX\"")
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

    packaging {
        resources.excludes.add("META-INF/*")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
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

    /* Room */
    implementation(libs.room)
    kapt(libs.room.compiler)

    /* Testing */
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.core)
    testImplementation(libs.koin.test)
    testImplementation(libs.coroutines.test)
}
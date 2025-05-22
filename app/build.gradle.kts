import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

// define version
val curSdk = 36
val minimumSdk = 24
val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val isRc = true

val versionNameGradle: String
    get() {
        val versionString = "${versionMajor}.${versionMinor}.${versionPatch}"

        if (isRc) {
            return "${versionString}-RC"
        }
        return versionString
    }

val versionCodeGradle: Int
    get(){
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }
android {
    namespace = "com.nusantarian.moviecatalogue"
    compileSdk = curSdk

    defaultConfig {
        applicationId = "com.nusantarian.moviecatalogue"
        minSdk = minimumSdk
        targetSdk = curSdk
        versionCode = versionCodeGradle
        versionName = versionNameGradle

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "TMDB_TOKEN_ID", properties.getProperty("TMDB_TOKEN_ID"))
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dynamicFeatures += setOf(":dynamicfeature")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.core.splashscreen)

    // lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.activity)
    ksp(libs.androidx.lifecycle.compiler)

    // koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation(libs.logging.interceptor)

    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)

    // image loader
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)

    // image carousel
    implementation(libs.android.image.slider)

    // paging
    implementation(libs.androidx.paging.runtime.ktx)

    // test unit
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

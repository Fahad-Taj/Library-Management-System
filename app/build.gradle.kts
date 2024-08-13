plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.librarymanagementsystem"
    compileSdk = 34

    configurations.all{
        resolutionStrategy {
            force("androidx.compose.material3:material3:1.2.1")
        }
    }

    defaultConfig {
        applicationId = "com.example.librarymanagementsystem"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/androidx.compose.material3_material3.version"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")

// OkHttp Logging Interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

// Flow
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

// Navigation in Compose
    implementation ("androidx.navigation:navigation-compose:2.4.0-alpha10")

// ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-alpha07")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

// Compose UI
    implementation ("androidx.compose.ui:ui:1.0.1")
    implementation ("androidx.compose.material:material:1.0.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.0.1")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.0.1")

// Paging
    implementation ("androidx.paging:paging-runtime:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha14")

    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:5.0.0")

    // Core splashscreen
    implementation ("androidx.core:core-splashscreen:1.0.1")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))


    // DataStore preferences
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

//    implementation("androidx.compose.material3:material3:1.2.1") {
//        force = true
//    }

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.gms.services) // <-- CAMBIA ESTA LÍNEA
}

android {
    namespace = "com.example.myautoo"
    compileSdk = 36 

    defaultConfig {
        applicationId = "com.example.myautoo"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    implementation("androidx.compose.foundation:foundation:1.8.3")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    implementation("androidx.navigation:navigation-compose:2.9.3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    implementation("androidx.compose.foundation:foundation:1.8.3")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    implementation("androidx.navigation:navigation-compose:2.9.3")
}
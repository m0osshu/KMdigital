// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Ruta: C:/Users/dasal/OneDrive/Desktop/miautoconcarrito/build.gradle.kts

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}

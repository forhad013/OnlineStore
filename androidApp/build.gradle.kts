 plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")

     kotlin("plugin.serialization")
     id("kotlin-parcelize")
     id( "kotlin-kapt")
 }

android {
    namespace = "com.greenrobotdev.onlinestore.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.greenrobotdev.onlinestore.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(arrayOf("--release", "17"))
    }
}

dependencies {
    implementation(project(":shared"))

    with(compose){
        implementation(ui)
        implementation(uiTooling)
        implementation(preview)
        implementation(foundation)
        implementation(material3)
        implementation(runtime)
    }
    implementation(libs.compose.activity)
    implementation(libs.swipe.refresh)
    implementation(libs.koin.android)
    implementation(libs.decompose.router)
    implementation(libs.arkivanov.decompose)
}
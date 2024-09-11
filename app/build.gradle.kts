plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.room")
    id("com.mikepenz.aboutlibraries.plugin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.book"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.book"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(libs.androidx.material3.window.size)
    implementation("androidx.compose.material:material:1.6.8")

    implementation("androidx.compose.material:material-icons-extended:1.7.0")
    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation(libs.androidx.emoji2.emojipicker)


    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.52")

    implementation("com.google.dagger:hilt-compiler:2.52")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    implementation("io.coil-kt:coil-compose:2.2.2")
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // About open source libraries
    implementation("com.mikepenz:aboutlibraries-core:11.2.2")
    implementation("com.mikepenz:aboutlibraries-compose-m3:11.2.2")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Permission Handling
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

    // Pdf parser
    implementation("com.tom-roush:pdfbox-android:2.0.27.0")

    // Epub parser
    implementation("org.jsoup:jsoup:1.18.1")

    // Fb2 parser
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.1")

    // App Compat for Language Switcher
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.appcompat:appcompat-resources:1.7.0")

    // Coil for loading bitmaps
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Drag & Drop
    implementation("sh.calvin.reorderable:reorderable:2.3.0")
    implementation(kotlin("script-runtime"))
    
    

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
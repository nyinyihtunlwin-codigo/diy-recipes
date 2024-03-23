import projects.nyinyihtunlwin.buildsrc.DiyRecipesProject

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    namespace = "projects.nyinyihtunlwin.network"
    compileSdk = DiyRecipesProject.compileSdk

    defaultConfig {
        minSdk = DiyRecipesProject.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("stage")
    productFlavors {
        create("staging") {
            dimension = flavorDimensions[0]
            buildConfigField("String", "BASE_MEAL_URL", "\"https://www.themealdb.com/\"")
            buildConfigField("String", "BASE_COCKTAILS_URL", "\"https://asia-southeast1-extra-space-express-staging.cloudfunctions.net/\"")
        }
        create("prod") {
            dimension = flavorDimensions[0]
            buildConfigField("String", "BASE_MEAL_URL", "\"https://www.themealdb.com/\"")
            buildConfigField("String", "BASE_COCKTAILS_URL", "\"https://asia-southeast1-extra-space-express-staging.cloudfunctions.net/\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    kapt(libs.hilt.android.compiler)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.arrow)
    implementation(libs.timber)
    implementation(libs.curl)
    implementation(libs.bundles.ktor.android)
    implementation(libs.ktor.kotlinx.serialization)
    implementation(libs.ktor.auth)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.arrow)

    implementation(libs.hilt.android.asProvider())
    kapt(libs.hilt.compiler)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)

    implementation("com.github.mrmike:ok2curl:0.7.0")
}
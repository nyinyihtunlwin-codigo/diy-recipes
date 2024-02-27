import projects.nyinyihtunlwin.buildsrc.DiyRecipesProject

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.com.google.gms.google.service)
    alias(libs.plugins.com.google.firebase.crashlytics)
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "projects.nyinyihtunlwin.diyrecipes"
    compileSdk = DiyRecipesProject.compileSdk

    defaultConfig {
        applicationId = "projects.nyinyihtunlwin.diyrecipes"
        minSdk = DiyRecipesProject.minSdk
        targetSdk = DiyRecipesProject.targetSdk
        versionCode = DiyRecipesProject.versionCode
        versionName = DiyRecipesProject.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore/release.keystore")
            storePassword = properties.getValue("RELEASE_KEY_STORE_PASSWORD") as String
            keyAlias = properties.getValue("RELEASE_KEY_ALIAS") as String
            keyPassword = properties.getValue("RELEASE_KEY_PASSWORD") as String
        }
        getByName("debug") {
            storeFile = file("../keystore/debug.keystore")
            storePassword = properties.getValue("DEBUG_KEY_STORE_PASSWORD") as String
            keyAlias = properties.getValue("DEBUG_KEY_ALIAS") as String
            keyPassword = properties.getValue("DEBUG_KEY_PASSWORD") as String
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
            versionNameSuffix = "-staging"
            applicationIdSuffix = ".staging"
            resValue("string", "app_name", "(STG)DIY Recipes")
        }
        create("prod") {
            dimension = flavorDimensions[0]
            resValue("string", "app_name", "DIY Recipes")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = DiyRecipesProject.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:meals"))
    implementation(project(":feature:cocktails"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.asProvider())
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.google.ads.identifier)

    implementation(libs.hilt.android.asProvider())
    kapt(libs.hilt.android.compiler)
    implementation(libs.arrow)

    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling.asProvider())
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}
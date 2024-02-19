plugins {
    `kotlin-dsl`
}

group = "projects.nyinyihtunlwin.buildsrc"
version = "1.0"

repositories {
    mavenCentral()
    google()
    maven(url = "https://jitpack.io")
    gradlePluginPortal()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
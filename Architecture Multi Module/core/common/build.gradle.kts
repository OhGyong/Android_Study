plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.architecturemultimodule.hiltCore)
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
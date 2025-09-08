plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.architecturemultimodule.android.kotlinAndroid)
    alias(libs.plugins.architecturemultimodule.hiltAndroid)
}

android {
    namespace = "com.study.architecturemultimodule.core.di"
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
}
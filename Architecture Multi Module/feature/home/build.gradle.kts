plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.architecturemultimodule.android.kotlinAndroid)
    alias(libs.plugins.architecturemultimodule.hiltAndroid)
}

android {
    namespace = "com.study.architecturemultimodule.feature.home"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.domain)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.hilt.navigation.compose)
}
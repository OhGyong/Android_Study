plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.architecturemultimodule.android.kotlinAndroid)
}

android {
    namespace = "com.study.architecturemultimodule.feature.home"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
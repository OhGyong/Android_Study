plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.architecturemultimodule.android.kotlinAndroid)
}

android {
    namespace = "com.study.architecturemultimodule"

    defaultConfig {
        applicationId = "com.study.architecturemultimodule"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(projects.feature.home)
}
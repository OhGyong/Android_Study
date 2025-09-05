plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

gradlePlugin {
    plugins {
        register("kotlinAndroid") {
            id = "architecturemultimodule.android.kotlinAndroid"
            implementationClass = "com.study.architecturemultimodule.build_logic.KotlinAndroidPlugin"
        }
    }
}
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
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

gradlePlugin {
    plugins {
        register("kotlinAndroid") {
            id = "architecturemultimodule.android.kotlinAndroid"
            implementationClass = "com.study.architecturemultimodule.build_logic.KotlinAndroidPlugin"
        }

        register("hiltAndroid") {
            id = "architecturemultimodule.hiltAndroid"
            implementationClass = "com.study.architecturemultimodule.build_logic.HiltAndroidPlugin"
        }

        register("hiltCore") {
            id = "architecturemultimodule.hiltCore"
            implementationClass = "com.study.architecturemultimodule.build_logic.HiltCorePlugin"
        }
    }
}
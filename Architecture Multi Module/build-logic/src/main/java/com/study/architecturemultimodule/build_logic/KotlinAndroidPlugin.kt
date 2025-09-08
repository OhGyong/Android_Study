package com.study.architecturemultimodule.build_logic

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal class KotlinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.libs

            pluginManager.apply("org.jetbrains.kotlin.android") // pluginId 접근 1
            pluginManager.apply(libs.findPlugin("kotlin-compose").get().get().pluginId) // pluginId 접근 2

            androidExtension.apply {
                compileSdk = 36

                defaultConfig {
                    minSdk = 26
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-core-ktx").get())
                add("implementation", libs.findLibrary("androidx-activity-compose").get())
                add("implementation", libs.findLibrary("androidx-ui").get())
                add("implementation", libs.findLibrary("androidx-ui-graphics").get())
                add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
                add("implementation", libs.findLibrary("androidx-material3").get())
                add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
            }
        }
    }
}
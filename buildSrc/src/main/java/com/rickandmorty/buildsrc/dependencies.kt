@file:Suppress("unused")

package com.rickandmorty.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:8.0.2"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val turbine = "app.cash.turbine:turbine:1.0.0"

    object Kotlin {
        private const val version = "1.8.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.7.3"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.10.1"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.3.0"
        const val material = "com.google.android.material:material:1.9.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Activity {
            private const val version = "1.7.2"
            const val ktx = "androidx.activity:activity-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.6.2"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.5.3"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val compose = "androidx.navigation:navigation-compose:2.7.4"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Compose {
            private const val version = "2023.03.00"
            const val bom = "androidx.compose:compose-bom:$version"
            const val ui = "androidx.compose.ui:ui"
            const val uiGraphics = "androidx.compose.ui:ui-graphics"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val material3 = "androidx.compose.material3:material3"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:1.5.1"
        }

        object Room {
            private const val version = "2.5.1"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Test {
            private const val version = "1.5.0"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.5"
                const val junit = "androidx.test.ext:junit:$version"
            }
            object Espresso{
                private const val version="3.5.1"
                const val core = "androidx.test.espresso:espresso-core:$version"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
            }
        }
    }

    object Coil {
        private const val version = "1.4.0"
        const val coilCompose = "io.coil-kt:coil-compose:$version"
    }

    object OkHttp3 {
        private const val version = "4.11.0"
        const val okhttp3 = "com.squareup.okhttp3:okhttp:$version"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Arrow {
        private const val version = "1.1.5"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object Hilt {
        private const val version = "2.46.1"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:5.1.0"
        const val inline = "org.mockito:mockito-inline:5.2.0"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }
}
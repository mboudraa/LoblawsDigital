object Android {
    const val compileSdkVersion = 29
    const val minSdkVersion = 23
    const val targetSdkVersion = 29

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val androidx_appcompat = "1.1.0"
    const val androidx_core = "1.2.0"
    const val androidx_constraint_layout = "2.0.0-beta4"
    const val androidx_fragment = "1.2.2"
    const val androidx_lifecycle = "2.2.0"
    const val androidx_navigation = "2.2.1"
    const val coil = "0.9.5"
    const val dagger = "2.21"
    const val java = "1.8"
    const val junit = "4.12"
    const val jsoup = "1.13.1"
    const val kotlin = "1.3.70"
    const val kotlinx_coroutines = "1.3.3"
    const val material = "1.2.0-alpha05"
    const val mockk = "1.9.3"
    const val moshi = "1.8.0"
    const val okhttp = "4.4.0"
    const val gradle_android_build_tool = "4.0.0-beta01"
    const val retrofit = "2.7.2"
    const val timber = "4.7.1"
    const val truth = "0.42"
}

object Classpath {
    const val android_gradle_build_tools = "com.android.tools.build:gradle:${Versions.gradle_android_build_tool}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidx_navigation_safeargs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"
}

object Plugins {
    const val android_application = "com.android.application"
    const val androidx_navigation_safeargs = "androidx.navigation.safeargs.kotlin"
    const val kotlin_android = "kotlin-android"
    const val kotlin_android_extensions = "kotlin-android-extensions"
    const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
    const val kotlin_kapt = "kotlin-kapt"
}

object Libs {
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout}"
    const val androidx_lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle}"
    const val androidx_fragment = "androidx.fragment:fragment-ktx:${Versions.androidx_fragment}"
    const val androidx_navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    const val androidx_navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val junit = "junit:junit:${Versions.junit}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines}"
    const val kotlinx_coroutines_ui = "org.jetbrains.kotlinx:kotlinx-coroutines-ui:${Versions.kotlinx_coroutines}"
    const val kotlinx_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutines}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshi_kotlin_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp_mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}

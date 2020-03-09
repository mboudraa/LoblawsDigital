plugins {
    id(Plugins.android_application)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_android_extensions)
    id(Plugins.kotlin_kapt)
    id(Plugins.androidx_navigation_safeargs)
}

android {

    compileSdkVersion(Android.compileSdkVersion)

    defaultConfig {
        applicationId = "com.mbo.league"
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = Android.testInstrumentationRunner

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://www.reddit.com/\""
        )
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        setSourceCompatibility(Versions.java)
        setTargetCompatibility(Versions.java)
    }

    kotlinOptions {
        jvmTarget = Versions.java
        freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {
    implementation(project(":redux"))
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.androidx_appcompat)
    implementation(Libs.androidx_constraint_layout)
    implementation(Libs.androidx_core)
    implementation(Libs.androidx_lifecycle_runtime)
    implementation(Libs.androidx_navigation_fragment)
    implementation(Libs.androidx_navigation_ui)
    implementation(Libs.coil)
    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)
    implementation(Libs.material)
    implementation(Libs.jsoup)
    implementation(Libs.moshi)
    kapt(Libs.moshi_kotlin_codegen)
    implementation(Libs.okhttp)
    implementation(Libs.okhttp_logging_interceptor)
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_moshi_converter)
    implementation(Libs.timber)

    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.okhttp_mockwebserver)
    testImplementation(Libs.kotlinx_coroutines_test)
    testImplementation(Libs.mockk)
}

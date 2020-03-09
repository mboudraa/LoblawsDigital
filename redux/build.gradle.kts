plugins {
    id(Plugins.kotlin_jvm)
}

tasks {
    compileKotlin {
        kotlinOptions{
            jvmTarget = Versions.java
            freeCompilerArgs += listOf(
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
            )
        }
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.java
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib)
    api(Libs.kotlinx_coroutines_core)

    testImplementation(Libs.kotlinx_coroutines_test)
    testImplementation(Libs.junit)
}

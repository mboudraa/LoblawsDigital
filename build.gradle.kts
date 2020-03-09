buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(Classpath.android_gradle_build_tools)
        classpath(Classpath.kotlin_gradle_plugin)
        classpath(Classpath.androidx_navigation_safeargs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    configurations.all {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

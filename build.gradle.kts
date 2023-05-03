plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.multiplatfrom).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.cocoapods).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
//    id("com.android.application").version("8.0.0").apply(false)
//    id("com.android.library").version("8.0.0").apply(false)
//    kotlin("android").version("1.8.10").apply(false)
//    kotlin("multiplatform").version("1.8.10").apply(false)
//    id("org.jetbrains.compose") version "1.4.0" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}

buildscript {
    dependencies {
        classpath(libs.sql.delight.gradle)
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
//        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//    }
//}

//tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.PodGenTask>().configureEach {
//    doLast {
//
//        val xcodeprojFiles = listOf(
//            "Pods/Pods.xcodeproj",
//            "synthetic.xcodeproj",
//        )
//
//        for (xcodeprojFile in xcodeprojFiles) {
//            val file = project.buildDir.resolve("cocoapods/synthetic/${family.name}/$xcodeprojFile/project.pbxproj")
//            setIosDeploymentTarget(file)
//        }
//    }
//}
//
//fun Project.setIosDeploymentTarget(
//    xcodeprojFile: File,
//    source: String = "11.0",
//    target: String = "14.1",
//) {
//    val lines = xcodeprojFile.readLines()
//    val out = xcodeprojFile.bufferedWriter()
//    out.use {
//        for (line in lines) {
//            out.write(line.replace("IPHONEOS_DEPLOYMENT_TARGET = $source;", "IPHONEOS_DEPLOYMENT_TARGET = $target;"))
//            out.write(("\n"))
//        }
//    }
//}



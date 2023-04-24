import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id ("kotlin-kapt")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

//    val xcf = XCFramework()
//    val iosTargets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())
//
//    iosTargets.forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//            xcf.add(this)
//            binaryOption("bundleId", "com.greenrobotdev.onlinestore.shared")
//        }
//    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        val commonMain by getting{
         dependencies{
             with(compose){
                     implementation(ui)
                     implementation(foundation)
                     implementation(material3)
                     implementation(runtime)
             }
         }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

}



//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile::class.java).all {
//    if (name == "linkDebugFrameworkIos") {
//
//        doFirst {
//            val configuration = System.getenv("CONFIGURATION")
//            val sdkName = System.getenv("SDK_NAME")
//            val targetBuild = System.getenv("TARGET_BUILD_DIR")
//            val archs = System.getenv("ARCHS")
//            val framework = System.getenv("FRAMEWORKS_FOLDER_PATH")
//
//            copy {
//                from("${project.rootDir}/shared/src/commonMain/resources/ios")
//                into("${project.buildDir}/xcode-frameworks/$configuration/$sdkName/shared.framework")
//            }
//        }
//    }
//}

//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile::class.java).all {
//    if (name.contains("linkPodDebugFrameworkIosArm64")) {
//        print("mugamba $name")
//        kotlinOptions {
//            allWarningsAsErrors = false
//            enabled = false
//
//        }
//        doFirst {
//            val configuration = System.getenv("CONFIGURATION")
//            val sdkName = System.getenv("SDK_NAME")
//            val targetBuild = System.getenv("TARGET_BUILD_DIR")
//            val archs = System.getenv("ARCHS")
//            val framework = System.getenv("FRAMEWORKS_FOLDER_PATH")
//
//            copy {
//                from("${project.rootDir}/shared/src/commonMain/resources/ios")
//                into("${project.buildDir}/xcode-frameworks/$configuration/$sdkName/shared.framework")
//            }
//        }
//    }
//}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon::class.java).all {

    if (name == "compileCommonMainKotlinMetadata") {

        kotlinOptions {
            allWarningsAsErrors = false
            enabled = false
        }
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile::class.java).all {

    if (name == "compileIosMainKotlinMetadata") {
        print(" mugamba $name")

        kotlinOptions{
            enabled = false
            allWarningsAsErrors = false

        }
    }
}


android {
    namespace = "com.greenrobotdev.onlinestore"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
    kapt {
        correctErrorTypes = true
    }
}
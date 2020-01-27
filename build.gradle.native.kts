import org.jetbrains.kotlin.config.KotlinCompilerVersion

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
        google()
        maven(
                "http://oss.sonatype.org/content/repositories/snapshots"
        )
        maven(
                "https://plugins.gradle.org/m2/"
        )
        maven(
                "https://dl.bintray.com/kotlin/kotlin-eap"
        )
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.61"))
        classpath(kotlin("frontend-plugin", version = "0.0.26"))
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
}
repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    google()
    maven(
            "http://dl.bintray.com/kotlin/kotlin-eap-1.2"
    )
    maven(
            "https://kotlin.bintray.com/kotlinx"
    )
}
//https://github.com/JetBrains/kotlin-native/blob/master/GRADLE_PLUGIN.md
kotlin {
    project.buildDir = file("buildNative")
    linuxX64("linuxX64") {
        binaries {
            //-fPIC for debug required
		executable()
        }
    }
    sourceSets {
        commonMain {
            kotlin.srcDir("src/nativeMain/kotlin")
            kotlin.srcDir("src/commonMain/kotlin")
            kotlin.srcDir("src/commonS01HeapMain/kotlin")
            kotlin.srcDir("src/commonS01BufferMainmemoryMain/kotlin")
            kotlin.srcDir("src/commonS06DictionaryNoneMain/kotlin")
            kotlin.srcDir("src/commonS08HashMapMain/kotlin")
            dependencies {
                implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
                implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
//				implementation("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
            }
        }
        commonTest {
            kotlin.srcDir("src/commonTest/kotlin")
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
    sourceSets["linuxX64Main"].kotlin.srcDir("src/linuxX64Main/kotlin")
}

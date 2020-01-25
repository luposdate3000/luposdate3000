import org.jetbrains.kotlin.config.KotlinCompilerVersion

buildscript {
	val kotlin_version  = "1.3.61"

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
		classpath(kotlin("gradle-plugin", version = "$kotlin_version"))
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
}

kotlin {
	linuxX64() {
		binaries {
//			executable()
			sharedLib()
		}
	}
	sourceSets {
		commonMain {
			kotlin.srcDir("src/linuxX64Main")
			kotlin.srcDir("src/commonMain")
			kotlin.srcDir("src/commonBufferMainmemoryMain")
			kotlin.srcDir("src/commonResultDictionaryNoneMain")
			kotlin.srcDir("src/commonTripleStoreHashMapMain")
			dependencies {
				implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
				implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
				implementation("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
			}
		}
		commonTest {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}
	}
}

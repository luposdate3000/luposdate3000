import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetPreset

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
val mingwPath = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")
kotlin {
    project.buildDir = file("buildNative")
    val hostOs = System.getProperty("os.name")
    val hostTarget = when {
        hostOs == "Mac OS X" -> macosX64("macosX64")
        hostOs == "Linux" -> linuxX64("linuxX64")
        hostOs.startsWith("Windows") -> mingwX64("mingwX64")
        else -> throw GradleException("Host OS '$hostOs' is not supported in Kotlin/Native $project.")
    }
    hostTarget.apply {
        val main by compilations.getting
        val dirent by main.cinterops.creating
        val stdio by main.cinterops.creating
        val unistd by main.cinterops.creating
        binaries {
            executable()
        }
    }
    sourceSets {
        commonMain {
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
                implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
                implementation("com.benasher44:uuid:0.0.7")
                implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
                implementation ("io.ktor:ktor-client-core:1.3.1")
                implementation ("io.ktor:ktor-client-core-native:1.3.1")
                implementation ("io.ktor:ktor-client-cio:1.3.1")
                implementation ("io.ktor:ktor-client-logging:1.3.1")
                implementation ("io.ktor:ktor-client-logging-native:1.3.1")
                implementation ("org.slf4j:slf4j-nop:1.7.25")
            }
        }
    }
    when{
        hostOs=="Linux"->{
            sourceSets["linuxX64Main"].dependencies{
                implementation("com.soywiz.korlibs.klock:klock-linuxx64:1.8.7")
            }
            sourceSets["linuxX64Main"].kotlin.srcDir("src/linuxX64Main/kotlin")
        }
        hostOs=="Mac OS X"->{
            sourceSets["macosX64Main"].dependencies{
                implementation("com.soywiz.korlibs.klock:klock-macosx64:1.8.9")
            }
            sourceSets["macosX64Main"].kotlin.srcDir("src/macosX64Main/kotlin")
        }
        else -> throw GradleException("Host OS '$hostOs' is not supported in Luposdate3000 $project.")
    }
    sourceSets["commonMain"].kotlin.srcDir("src/nativeMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00ExecutionSequentialMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00TraceOffMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00ResultFlowExecuteTestsMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS01HeapMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS01BufferMainmemoryMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS03DictionaryIntArrayMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS05HashMapMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS12LocalMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS14NoneMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS15DistributedMain/kotlin")
}

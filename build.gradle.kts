buildscript {
    repositories {
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
        maven("http://oss.sonatype.org/content/repositories/snapshots")
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26")
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
kotlin {
    project.buildDir = file("buildJvm")
    linuxX64("linuxX64") {
        val main by compilations.getting
        val dirent by main.cinterops.creating
        val stdio by main.cinterops.creating
        val unistd by main.cinterops.creating
        binaries {
            executable()
        }
    }
    sourceSets["commonMain"].dependencies {
        implementation("com.benasher44:uuid:0.0.7")
        implementation("com.soywiz.korlibs.klock:klock-linuxx64:1.8.7")
        implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
        implementation("io.ktor:ktor-client-cio:1.3.1")
        implementation("io.ktor:ktor-client-core-native:1.3.1")
        implementation("io.ktor:ktor-client-core:1.3.1")
        implementation("io.ktor:ktor-client-logging-native:1.3.1")
        implementation("io.ktor:ktor-client-logging:1.3.1")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.3.70")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.70")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
        implementation("org.slf4j:slf4j-nop:1.7.25")
    }
    sourceSets["commonMain"].kotlin.srcDir("src/commonMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00ExecutionSequentialMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00ResultFlowExecuteTestsMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS00TraceOffMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS01BufferMainmemoryMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS01HeapMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS03DictionaryIntArrayMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS05HashMapMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS12LocalMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS14NoneMain/kotlin")
    sourceSets["commonMain"].kotlin.srcDir("src/commonS15LocalMain/kotlin")
    sourceSets["linuxX64Main"].kotlin.srcDir("src/linuxX64Main/kotlin")
    sourceSets["linuxX64Main"].kotlin.srcDir("src/nativeMain/kotlin")
}

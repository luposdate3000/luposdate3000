import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "14"
    //kotlinOptions.jvmTarget = "1.8"
    //see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
    //or kotlinc -X
    kotlinOptions.freeCompilerArgs += "-Xno-param-assertions"
    kotlinOptions.freeCompilerArgs += "-Xuse-ir"
    kotlinOptions.freeCompilerArgs += "-Xnew-inference"
    kotlinOptions.freeCompilerArgs += "-Xno-receiver-assertions"
    kotlinOptions.freeCompilerArgs += "-Xno-call-assertions"
}
buildscript {
    repositories {
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.255-SNAPSHOT")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26")
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.255-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}
application {
    mainClassName = "MainKt"
}
tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
project.buildDir = file("build/build_1.4.255-SNAPSHOT_jvm_SparqlTestSuite_On_Sequential_MultiMap_BPlusTreePartition_Korio_On_BTree_BTree_Empty_128_8_8_8_8_false_ECoverage.Disabled_Off_None_2000_Off_Off_true_EPOPDebugMode.DEBUG2")
dependencies {
    implementation("com.benasher44:uuid:0.0.7")
    implementation("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
    implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
    implementation("org.apache.jena:jena-arq:3.14.0")
    implementation("org.apache.jena:jena-core:3.14.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.255-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.255-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.255-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("org.slf4j:slf4j-simple:1.7.25")
}
sourceSets["main"].java.srcDir("src.generated/commonConfig/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS00LaunchSparqlTestSuiteMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS01HeapMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS03DictionaryMultiMapMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS16ServerCommunicationNoneMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmS00WrapperJenaOnMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmS16HttpEndpointKorioMain/kotlin")

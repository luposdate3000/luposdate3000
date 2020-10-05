import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
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
project.buildDir = file("build/build_1.4.255-SNAPSHOT_jvm_Endpoint_Off_Threads_Small_BPlusTreePartition_JavaNet_Off_BTree_BTree_XML_false_8196_1048576_512_512_1048576_true_ECoverage.Disabled_Off_None_-1_Off_On_true_EPOPDebugMode.NONE")
dependencies {
    implementation("com.benasher44:uuid:0.0.7")
    implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.255-SNAPSHOT")
}
sourceSets["main"].java.srcDir("src.generated/commonConfig/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS00LaunchEndpointMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS01HeapMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS03DictionarySmallMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/commonS16ServerCommunicationNoneMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmS00ParallelThreadsMain/kotlin")
sourceSets["main"].java.srcDir("src.generated/jvmS16HttpEndpointJavaNetMain/kotlin")

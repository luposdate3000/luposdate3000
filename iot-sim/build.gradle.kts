plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.javadocmd:simplelatlng:1.3.1")
    implementation(project(":des-core"))
    implementation(project(":iot-db-interface"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {9
        events("passed", "skipped", "failed")
    }
}

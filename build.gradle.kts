if (File("src/build.gradle.kts").exists()) {
    dependencies {
        project(":src")
    }
} else {
    task<Exec>("build") {
        commandLine("./launcher.main.kts", "--help")
    }
}

if (File("build.config").exists()) {
    dependencies {
        project(":src")
    }
} else {
    task<Exec>("build") {
        commandLine("./launcher.main.kts", "--help")
    }
}

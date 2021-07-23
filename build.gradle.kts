if (File("build.config").exists()) {
    dependencies {
        project(":src")
    }
} else {
    tasks.register("build") {
        throw Exception("call './launcher.main.kts' first with the arguments as described by the documentation")
    }
}

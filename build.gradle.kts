dependencies {
    project(":src")
}
tasks.register("build") {
    if (!File("build.config").exists()) {
        throw Exception("call './launcher.main.kts' first with the arguments as described by the documentation")
    }
}

plugins {
    id("com.github.node-gradle.node") version "3.1.0"
}

evaluationDependsOn(":src:luposdate3000_endpoint")
task<Exec>("bowerInstall") {
    dependsOn("npmInstall")
    commandLine("./node_modules/.bin/bower", "install", "--allow-root")
}
task<Exec>("build") {
    dependsOn("bowerInstall")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir("../..")
    commandLine("./launcher.main.kts", "--copySPAClient")
}

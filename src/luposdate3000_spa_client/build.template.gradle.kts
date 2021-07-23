plugins {
    id("com.github.node-gradle.node") version "3.1.0"
}

evaluationDependsOn(":src:luposdate3000_endpoint")

task<Exec>("downloadInstruments") {
    commandLine("git", "clone", "--depth=1", "https://github.com/nbrosowsky/tonejs-instruments.git")
}
task("downloadInstrumentsIfNotExist") {
    println(File("x").absolutePath)
    if (!File("src/luposdate3000_spa_client/tonejs-instruments").exists()) {
        dependsOn("downloadInstruments")
    }
}
task<Exec>("bowerInstall") {
    dependsOn("npmInstall")
    commandLine("./node_modules/.bin/bower", "install", "--allow-root")
}
task<Exec>("build") {
    dependsOn("bowerInstall")
    dependsIn("downloadInstrumentsIfNotExist")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir("../..")
    commandLine("./launcher.main.kts", "--copySPAClient")
}

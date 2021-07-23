plugins {
    id("org.ysb33r.nodejs.base") version "0.10.0-alpha.2"
}

evaluationDependsOn(":src:luposdate3000_endpoint")
var executableDirectory = ""
nodejs {
    executableByVersion("12.21.0")
    executableDirectory = executable.get().toString()
    executableDirectory = executableDirectory.substring(0, executableDirectory.length - 4) // remove the trailing program name
}
task<Exec>("downloadInstruments") {
    commandLine("git", "clone", "--depth=1", "https://github.com/nbrosowsky/tonejs-instruments.git")
}
task("downloadInstrumentsIfNotExist") {
    if (!File("src/luposdate3000_spa_client/tonejs-instruments").exists()) {
        dependsOn("downloadInstruments")
    }
}
task<Exec>("npmInstall") {
    environment["PATH"] = executableDirectory + ":" + environment["PATH"]
    commandLine(executableDirectory + "npm", "install")
}
task<Exec>("bowerInstall") {
    dependsOn("npmInstall")
    environment["PATH"] = executableDirectory + ":" + environment["PATH"]
    commandLine("./node_modules/.bin/bower", "install", "--allow-root")
}
task<Exec>("build") {
    dependsOn("bowerInstall")
    dependsOn("downloadInstrumentsIfNotExist")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir("../..")
    environment["PATH"] = executableDirectory + ":" + environment["PATH"]
    commandLine("./launcher.main.kts", "--copySPAClient")
}

/*
plugins {
    id("org.ysb33r.nodejs.base") version "0.10.0-alpha.2"
    id("org.ysb33r.nodejs.npm") version "0.10.0-alpha.2"
}
//https://ysb33rorg.gitlab.io/nodejs-gradle-plugin/0.10.0-alpha.2/docs/product-documentation.html
task<org.ysb33r.gradle.nodejs.tasks.NpmPackageJsonInit>("x"){ 
}
*/

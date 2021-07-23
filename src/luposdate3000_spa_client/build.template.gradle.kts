plugins {
    id("org.ysb33r.nodejs.base") version "0.10.0-alpha.2"
}

evaluationDependsOn(":src:luposdate3000_endpoint")
var executableDirectory = ""
var isWindows = false
nodejs {
    executableByVersion("12.21.0")
    executableDirectory = executable.get().toString()
    isWindows = executableDirectory.endsWith("node.exe")
    if (isWindows) {
        executableDirectory = executableDirectory.substring(0, executableDirectory.length - 8) // remove the trailing program name
    } else {
        executableDirectory = executableDirectory.substring(0, executableDirectory.length - 4) // remove the trailing program name
    }
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
    if (isWindows) {
        commandLine(executableDirectory + "npm.cmd", "install","--scripts-prepend-node-path")
    } else {
        commandLine(executableDirectory + "npm", "install","--scripts-prepend-node-path")
    }
}
task<Exec>("bowerInstall") {
    dependsOn("npmInstall")
if (isWindows) {
    environment["PATH"] = File("./src/luposdate3000_spa_client/node_modules/.bin/").absolutePath+";"+executableDirectory + ";" + environment["PATH"]
    commandLine("bower", "install")
}else{
    environment["PATH"] = File("./src/luposdate3000_spa_client/node_modules/.bin/").absolutePath+":"+executableDirectory + ":" + environment["PATH"]
    commandLine("bower", "install", "--allow-root")
}
}
task<Exec>("build") {
    dependsOn("bowerInstall")
    dependsOn("downloadInstrumentsIfNotExist")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir("../..")
if(isWindows){
 environment["PATH"] = File("./src/luposdate3000_spa_client/node_modules/.bin/").absolutePath+";"+executableDirectory + ";" + environment["PATH"]
}else{
 environment["PATH"] = File("./src/luposdate3000_spa_client/node_modules/.bin/").absolutePath+":"+executableDirectory + ":" + environment["PATH"]
}
    commandLine("./launcher.main.kts", "--copySPAClient")
}

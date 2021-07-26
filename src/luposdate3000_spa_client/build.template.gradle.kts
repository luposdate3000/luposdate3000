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
        commandLine(executableDirectory + "npm.cmd", "install", "--scripts-prepend-node-path")
    } else {
        commandLine(executableDirectory + "npm", "install", "--scripts-prepend-node-path")
    }
}
task<Exec>("bowerInstall") {
    dependsOn("npmInstall")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine(executableDirectory + "node.exe", File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/bower/lib/bin/bower.js").absolutePath, "install")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine("./node_modules/.bin/bower", "install", "--allow-root")
    }
}
task<Exec>("launcherCopySpaClient") {
    dependsOn(":src:luposdate3000_endpoint:build")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir(rootProject.projectDir)
    if (isWindows) {
        commandLine("cmd","/c","kotlin","\""+ File(rootProject.projectDir.toString() + "/launcher.main.kts").absolutePath +"\"", "--copySPAClient")
    } else {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ":" + executableDirectory + ":" + environment["PATH"]
        commandLine("./launcher.main.kts", "--copySPAClient")
    }
}
task<Exec>("gulpBuild") {
    dependsOn("bowerInstall")
    dependsOn("downloadInstrumentsIfNotExist")
    dependsOn("launcherCopySpaClient")
    environment["DISABLE_NOTIFIER"] = "true"
    workingDir(rootProject.projectDir.toString()+ "/src/luposdate3000_spa_client")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine("cmd","/c", "\""+File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/gulp.cmd").absolutePath+"\"")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine("./node_modules/.bin/gulp")
    }
}
task<Exec>("gulpOnly") {
    environment["DISABLE_NOTIFIER"] = "true"
    workingDir(rootProject.projectDir.toString()+ "/src/luposdate3000_spa_client")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine("cmd","/c", "\""+File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/gulp.cmd").absolutePath+"\"")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine("./node_modules/.bin/gulp")
    }
}
task("build") {
    dependsOn("gulpBuild")
}

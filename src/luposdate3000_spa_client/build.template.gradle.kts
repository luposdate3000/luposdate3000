import java.io.FileOutputStream
import java.net.URL

evaluationDependsOn(":src:luposdate3000_endpoint")
var executableDirectoryBaseDir = gradle.gradleUserHomeDir.toString() + "/native-binaries/node/"
var isWindows = System.getProperty("os.name").contains("Win")
var executableDirectory = if (isWindows) {
    executableDirectoryBaseDir + "node-v16.5.0-win-x64/"
} else {
    executableDirectoryBaseDir + "node-v16.5.0-linux-x64/bin/"
}

fun myDownload(src: String, destDir: String, destName: String) {
    File(destDir).mkdirs()
    val u = URL(src)
    val dis = u.openStream()
    val buffer = ByteArray(1024)
    val fos = FileOutputStream(destDir + destName)
    while (true) {
        val length = dis.read(buffer)
        if (length <= 0) {
            break
        }
        fos.write(buffer, 0, length)
    }
    dis.close()
    fos.close()
    File(destDir + destName + ".download.ok").printWriter().use {}
}

task("downloadNode") {
    if (isWindows) {
        myDownload("https://nodejs.org/dist/v16.5.0/node-v16.5.0-win-x64.zip", executableDirectoryBaseDir, "node-v16.5.0-win-x64.zip")
    } else {
        myDownload("https://nodejs.org/dist/v16.5.0/node-v16.5.0-linux-x64.tar.xz", executableDirectoryBaseDir, "node-v16.5.0-linux-x64.tar.xz")
    }
}
task("downloadNodeIfNotExist") {
    if (!File(executableDirectoryBaseDir + ".download.ok").exists()) {
        dependsOn("downloadNode")
    }
}
task<Exec>("installNode") {
    dependsOn("downloadNodeIfNotExist")
    if (isWindows) {
        commandLine("unzip", executableDirectoryBaseDir + "node-v16.5.0-win-x64.zip")
    } else {
        commandLine("tar", "-xf", executableDirectoryBaseDir + "node-v16.5.0-linux-x64.tar.xz")
    }
    workingDir(executableDirectoryBaseDir)
    doLast {
        File(executableDirectoryBaseDir + "ok").printWriter().use {}
    }
}
task("installNodeIfNotExist") {
    if (!File(executableDirectoryBaseDir + "ok").exists()) {
        dependsOn("installNode")
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
    dependsOn("installNodeIfNotExist")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine(executableDirectory + "npm.cmd", "install", "--scripts-prepend-node-path")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine(executableDirectory + "npm", "install", "--scripts-prepend-node-path")
    }
}
task<Exec>("bowerInstall") {
    dependsOn("installNodeIfNotExist")
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
    dependsOn("installNodeIfNotExist")
    dependsOn(":src:luposdate3000_endpoint:build")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir(rootProject.projectDir)
    if (isWindows) {
        commandLine("cmd", "/c", "kotlin", "\"" + File(rootProject.projectDir.toString() + "/launcher.main.kts").absolutePath + "\"", "--copySPAClient")
    } else {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ":" + executableDirectory + ":" + environment["PATH"]
        commandLine("./launcher.main.kts", "--copySPAClient")
    }
}
task<Exec>("gulpBuild") {
    dependsOn("installNodeIfNotExist")
    dependsOn("bowerInstall")
    dependsOn("downloadInstrumentsIfNotExist")
    dependsOn("launcherCopySpaClient")
    environment["DISABLE_NOTIFIER"] = "true"
    workingDir(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine("cmd", "/c", "\"" + File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/gulp.cmd").absolutePath + "\"")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine("./node_modules/.bin/gulp")
    }
}
task<Exec>("gulpOnly") {
    dependsOn("installNodeIfNotExist")
    environment["DISABLE_NOTIFIER"] = "true"
    workingDir(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client")
    if (isWindows) {
        environment["PATH"] = File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/").absolutePath + ";" + executableDirectory + ";" + environment["PATH"]
        commandLine("cmd", "/c", "\"" + File(rootProject.projectDir.toString() + "/src/luposdate3000_spa_client/node_modules/.bin/gulp.cmd").absolutePath + "\"")
    } else {
        environment["PATH"] = executableDirectory + ":" + environment["PATH"]
        commandLine("./node_modules/.bin/gulp")
    }
}
task("build") {
    dependsOn("gulpBuild")
}

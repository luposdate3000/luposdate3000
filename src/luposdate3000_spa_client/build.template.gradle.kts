plugins {
    id("com.github.node-gradle.node") version "3.1.0"
}

evaluationDependsOn(":src:luposdate3000_endpoint")
task<Exec>("bowerInstall") {
dependsOn("npmInstall")
commandLine("./node_modules/.bin/bower","install", "--allow-root")
}
task<Exec>("build") {
    dependsOn("bowerInstall")
    mustRunAfter(":src:luposdate3000_endpoint:build")
    workingDir("../..")
    commandLine("./launcher.main.kts", "--copySPAClient")
}

/*
task npmInstall(type: Exec) {
    workingDir 'src/main/webapp'
    commandLine 'npm', 'install'
}
*/
//   https://github.com/srs/gradle-node-plugin/blob/master/docs/node.md
//   https://gist.github.com/spikeheap/8558786

// https://github.com/node-gradle/gradle-node-plugin/blob/master/examples/simple-node/npm/build.gradle

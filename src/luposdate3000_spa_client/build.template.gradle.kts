evaluationDependsOn(":src:luposdate3000_endpoint")
task<Exec>("build") {
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

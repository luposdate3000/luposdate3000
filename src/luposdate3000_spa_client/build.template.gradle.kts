fun myProcessBuilder(cmd: List<String>): ProcessBuilder {
    if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
        if (cmd[0].contains("/")) {
            val lastIdx = cmd[0].lastIndexOf("/")
            val path = cmd[0].substring(0, lastIdx)
            val proc = cmd[0].substring(lastIdx + 1)
            val realCmd = mutableListOf<String>("cmd.exe", "/C", proc)
            for (i in 1 until cmd.size) {
                realCmd.add(cmd[i])
            }
            println("realCmd:: " + realCmd)
            val pb = ProcessBuilder(realCmd)
            val env = pb.environment()
            println("oldpath:: " + env["PATH"])
            env["PATH"] = path + ";" + env["PATH"]
            return pb
        } else {
            return ProcessBuilder(cmd)
        }
    } else {
        return ProcessBuilder(cmd)
    }
}
evaluationDependsOn(":src:luposdate3000_endpoint")
tasks.register("build") {
    val proc = myProcessBuilder(listOf("../../launcher.main.kts", "--copySPAClient"))
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    proc.waitFor()
    if (proc.exitValue() != 0) {
        throw Exception("exit-code:: " + proc.exitValue())
    }
}

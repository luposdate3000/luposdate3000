
fun myProcessBuilder(cmd: List<String>): ProcessBuilder {
    if (System.getProperty("os.name").contains("Win")) {
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
    mustRunAfter(":src:luposdate3000_endpoint:build")
    doLast {
        val proc = myProcessBuilder(listOf("./launcher.main.kts", "--copySPAClient"))
            .start()
        val inputstream = proc!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputThread = Thread {
            var inputline = inputreader.readLine()
            while (inputline != null) {
                println(inputline)
                inputline = inputreader.readLine()
            }
        }
        val errorstream = proc!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                println(errorline)
                errorline = errorreader.readLine()
            }
        }
        proc.waitFor()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
        if (proc.exitValue() != 0) {
            throw Exception("exit-code:: " + proc.exitValue())
        }
    }
}

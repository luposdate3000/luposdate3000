internal fun exec(clazz: Class<*>, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    return exec(clazz.name,args,jvmArgs)
}
internal fun exec(className:String, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    val javaHome = System.getProperty("java.home")
    val javaBin = javaHome + "/bin/java"
    val classpath = System.getProperty("java.class.path")
    val command = ArrayList<String>()
    command.add(javaBin)
    command.addAll(jvmArgs)
    command.add("-cp")
    command.add(classpath)
    command.add(className)
    command.addAll(args)
    val builder = ProcessBuilder(command)
    val env = builder.environment()
    env.putAll(System.getenv())
    println(env)
    builder.redirectError(java.io.File(className+".err"))
    builder.redirectOutput(java.io.File(className+".log"))
    val process = builder.start()
    process.waitFor()
    return process.exitValue()
}
public fun main(){
        exec("lupos.launch_code_gen_test_00.COUNT3Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.SimpleDELETE3Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.RDFinferencetestKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Simple2Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.STRDTKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.CalculatewhichsetsaresubsetsofothersexcludeAsubsetOfAKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Synbad07rqKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq12asparql21Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq12bsparql1640Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq65sparql32978Kt", jvmArgs = listOf("-Xmx8g"))
}

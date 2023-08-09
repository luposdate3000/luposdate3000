import java.util.concurrent.TimeUnit
import java.lang.ProcessBuilder.Redirect
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
    builder.redirectError(Redirect.appendTo(java.io.File(className+".err")))
    builder.redirectOutput(Redirect.appendTo(java.io.File(className+".log")))
    val process = builder.start()
    process.waitFor(10, TimeUnit.SECONDS)
    process.destroyForcibly()
    process.waitFor()
    return process.exitValue()
}
public fun main(){
    val tests=listOf(
        "lupos.launch_code_gen_test_00.Bind01BINDfixeddataforOWLDLKt",
    )
    tests.parallelStream().forEach{it->
              println(it+" start")
              exec(it, jvmArgs = listOf("-Xmx8g"))
              println(it+" done")
    }
}

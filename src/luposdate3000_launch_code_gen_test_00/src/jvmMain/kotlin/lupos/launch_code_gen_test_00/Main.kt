import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit
internal fun exec(clazz: Class<*>, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    return exec(clazz.name, args, jvmArgs)
}
internal fun exec(className: String, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
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
    builder.redirectError(Redirect.appendTo(java.io.File(className + ".err")))
    builder.redirectOutput(Redirect.appendTo(java.io.File(className + ".log")))
    val process = builder.start()
    process.waitFor(10, TimeUnit.SECONDS)
    process.destroyForcibly()
    process.waitFor()
    return process.exitValue()
}
public fun main() {
    val tests = listOf(
        "lupos.launch_code_gen_test_00.Constructwhere03CONSTRUCTWHEREKt",
        "lupos.launch_code_gen_test_00.DEntailmenttesttoshowthatneitherliteralsinsubjectpositionnornewlyintroducedsurrogateblanknodesaretobereturnedinqueryanswersKt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery121853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery122210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery112553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery122553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery132553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery142553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery152553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery162553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery172553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery22553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery32553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery412553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery52553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery62553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery712553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery722553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery72553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery732553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery742553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery752553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery762553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery12553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery102553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery112553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery122553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery212553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery222553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery232553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery242553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery252553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery32553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery42553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery52553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery712553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery722553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery732553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery742553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery752553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery762553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery772553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbtc028sparql3778Kt",
    )
    tests.parallelStream().forEach { it ->
        println(it + " start")
        exec(it, jvmArgs = listOf("-Xmx16g"))
        println(it + " done")
    }
}

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
    )
    val tests2=listOf(
        "lupos.launch_code_gen_test_00.INSERTsamebnodetwiceKt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery111853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery112210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery112553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbtc020sparql80Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional11sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional16sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional16sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional18sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional23sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional23sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional28sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional34sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional37sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional39sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional39sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional41sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional45sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional47sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional47sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional52sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional56sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional5sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional60sparql4Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesoptional63sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent03sparql764Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent12sparql567Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent14sparql567Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent17sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent21sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesx1sparql5Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql973Kt",
        "lupos.launch_code_gen_test_00.SimpleDELETE4USINGKt",
        "lupos.launch_code_gen_test_00.SimpleDELETE7Kt",
        "lupos.launch_code_gen_test_00.Simpleinsertdatanamed1Kt",
        "lupos.launch_code_gen_test_00.Sq14limitbyresourceKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromafullyboundminuendKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromapartiallyboundminuendKt",
        "lupos.launch_code_gen_test_00.TZKt",
    )
    tests.parallelStream().forEach{it->
              println(it+" start")
              exec(it, jvmArgs = listOf("-Xmx8g"))
              println(it+" done")
    }
}

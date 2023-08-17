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
    )
    val tests2 = listOf(
        "lupos.launch_code_gen_test_00.CalculatepropersubsetKt",
        "lupos.launch_code_gen_test_00.CalculatewhichsetsaresubsetsofothersexcludeAsubsetOfAKt",
        "lupos.launch_code_gen_test_00.CalculatewhichsetsaresubsetsofothersincludeAsubsetOfAKt",
        "lupos.launch_code_gen_test_00.CalculatewhichsetshavethesameelementsKt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnodeKt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeKt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolutionKt",
        "lupos.launch_code_gen_test_00.INSERTsamebnodetwiceKt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery11853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery41853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery21853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery71853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery81853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbtc020sparql80Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent03sparql764Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent17sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql973Kt",
        "lupos.launch_code_gen_test_00.Simpleinsertdatanamed1Kt",
        "lupos.launch_code_gen_test_00.Sq14limitbyresourceKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromapartiallyboundminuendKt",
    )
    tests.parallelStream().forEach { it ->
        println(it + " start")
        exec(it, jvmArgs = listOf("-Xmx8g"))
        println(it + " done")
    }
}

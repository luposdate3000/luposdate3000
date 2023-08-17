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
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery51853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery52210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery52553sparql2553Kt",
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
        "lupos.launch_code_gen_test_00.Resourcesbtc035sparql61664Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent03sparql764Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent06sparql10467Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent17sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq10sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq111sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12a1sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12b1sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12b2sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12b3sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12b4sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12csparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq1sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq2sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq3asparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq3bsparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq3csparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq41sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq42sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq4sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq5asparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq5bsparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq61sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq62sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq63sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq66sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq67sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq68sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq6sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq71sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq72sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq7sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq8sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq91sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq92sparql32978Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq9sparql32978Kt",
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

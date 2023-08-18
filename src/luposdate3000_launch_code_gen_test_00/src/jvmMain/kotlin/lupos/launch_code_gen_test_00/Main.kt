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
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery111853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery112553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery11853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery121853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery122553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery131853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery132553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery31853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery32210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery411853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery412210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery412553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery41853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery211853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery212210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery212553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery21853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery711853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery712210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery712553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery71853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery721853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery722210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery722553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery731853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery732210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery732553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery741853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery742210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery742553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery751853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery752210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery752553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery771853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery772210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery772553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery81853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent03sparql764Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql21Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql247Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq12asparql973Kt",
        "lupos.launch_code_gen_test_00.Simpleinsertdatanamed1Kt",
        "lupos.launch_code_gen_test_00.Sparqldl06rqcycleofundistinguishedvariablesKt",
        "lupos.launch_code_gen_test_00.Sq14limitbyresourceKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromapartiallyboundminuendKt",
    )
    tests.parallelStream().forEach { it ->
        println(it + " start")
        exec(it, jvmArgs = listOf("-Xmx8g"))
        println(it + " done")
    }
}

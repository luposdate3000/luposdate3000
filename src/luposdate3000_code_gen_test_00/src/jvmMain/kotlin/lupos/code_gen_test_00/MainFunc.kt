import lupos.code_gen_test_00.GROUPCONCAT1
import lupos.code_gen_test_00.sparqldl07rqtwodistinguishedvariablesundist
import lupos.code_gen_test_00.STRLEN
import lupos.code_gen_test_00.Group3
import lupos.code_gen_test_00.sq13Subqueriesdontinjectbindings
import lupos.code_gen_test_00.resourcesmyqueriesubungent05sparql66
import lupos.code_gen_test_00.resourcesbsbmbiquery42210sparql2210
import lupos.code_gen_test_00.resourcesbsbmexplorequery82210sparql2210
import lupos.code_gen_test_00.resourcessp2bq12asparql247
import lupos.code_gen_test_00.resourcessp2bq67sparql700
internal fun exec(clazz: Class<*>, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    val javaHome = System.getProperty("java.home")
    val javaBin = javaHome + "/bin/java"
    val classpath = System.getProperty("java.class.path")
    val className = clazz.name
    val command = ArrayList<String>()
    command.add(javaBin)
    command.addAll(jvmArgs)
    command.add("-cp")
    command.add(classpath)
    command.add(className)
    command.addAll(args)
    val builder = ProcessBuilder(command)
    val process = builder.inheritIO().start()
    process.waitFor()
    return process.exitValue()
}
public fun main(){
        exec(GROUPCONCAT1::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(sparqldl07rqtwodistinguishedvariablesundist::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(STRLEN::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(Group3::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(sq13Subqueriesdontinjectbindings::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcesmyqueriesubungent05sparql66::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcesbsbmbiquery42210sparql2210::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcesbsbmexplorequery82210sparql2210::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq12asparql247::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq67sparql700::class.java, jvmArgs = listOf("-Xmx8g"))
}

import lupos.launch_code_gen_test_00.DELETEINSERT1
import lupos.launch_code_gen_test_00.SimpleDELETE2WITH
import lupos.launch_code_gen_test_00.RDFtestforblanknodecardinalities
import lupos.launch_code_gen_test_00.pp30Operatorprecedence1
import lupos.launch_code_gen_test_00.resourcesbsbmbiquery82210sparql2210
import lupos.launch_code_gen_test_00.resourcessp2bq7sparql21
import lupos.launch_code_gen_test_00.resourcessp2bq66sparql247
import lupos.launch_code_gen_test_00.resourcessp2bq12a1sparql973
import lupos.launch_code_gen_test_00.resourcessp2bq4sparql973
import lupos.launch_code_gen_test_00.resourcessp2bq5asparql1294
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
        exec(DELETEINSERT1::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(SimpleDELETE2WITH::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(RDFtestforblanknodecardinalities::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(pp30Operatorprecedence1::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcesbsbmbiquery82210sparql2210::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq7sparql21::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq66sparql247::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq12a1sparql973::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq4sparql973::class.java, jvmArgs = listOf("-Xmx8g"))
        exec(resourcessp2bq5asparql1294::class.java, jvmArgs = listOf("-Xmx8g"))
}

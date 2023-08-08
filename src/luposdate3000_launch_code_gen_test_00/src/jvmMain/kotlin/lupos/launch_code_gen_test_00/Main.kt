import lupos.launch_code_gen_test_00.DROPDEFAULT
import lupos.launch_code_gen_test_00.parentquerywithhasChildmin1restriction
import lupos.launch_code_gen_test_00.pp28aDiamondwithlooppp
import lupos.launch_code_gen_test_00.resourcessp2bq6sparql247
import lupos.launch_code_gen_test_00.resourcessp2bq12b4sparql700
import lupos.launch_code_gen_test_00.resourcessp2bq12asparql973
import lupos.launch_code_gen_test_00.resourcessp2bq12b4sparql1294
import lupos.launch_code_gen_test_00.resourcessp2bq42sparql1294
import lupos.launch_code_gen_test_00.resourcessp2bq6sparql32978
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
    builder.redirectErrorStream(true)
    builder.redirectOutput(java.io.File(className+".log"))
    val process = builder.start()
    process.waitFor()
    return process.exitValue()
}
public fun main(){
        exec("lupos.launch_code_gen_test_00.DROPDEFAULTKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.ParentquerywithhasChildmin1restrictionKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Pp28aDiamondwithloopppKt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq6sparql247Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq12b4sparql700Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq12asparql973Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq12b4sparql1294Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq42sparql1294Kt", jvmArgs = listOf("-Xmx8g"))
        exec("lupos.launch_code_gen_test_00.Resourcessp2bq6sparql32978Kt", jvmArgs = listOf("-Xmx8g"))
}

#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
var releaseMode = ""
var suspendMode = ""
var inlineMode = ""
var partitionMode = ""
var memoryMode = ""
var proguardMode = ""
var mainClass = ""
var jenaWrapper = ""
var endpointMode = ""
var dryMode = ""
var fastMode = ""
var intellijMode = ""
var cleanedArgs = mutableListOf<String>()
enum class ExecMode { LAUNCH, COMPILE, HELP }
var execMode = ExecMode.LAUNCH
enum class ParamClassMode { VALUES, NO_VALUE }
class ParamClass {
    val name: String
    val default: String
    val values: Map<String, () -> Unit>
    val action: () -> Unit
    val mode: ParamClassMode
    var additionalHelp: (String) -> Unit = {}
    constructor (name: String, default: String, values: Map<String, () -> Unit>) {
        this.name = name
        this.default = default
        this.values = values
        this.action = {}
        this.mode = ParamClassMode.VALUES
    }
    constructor(name: String, action: () -> Unit) {
        this.name = name
        this.default = ""
        this.values = mapOf()
        this.action = action
        this.mode = ParamClassMode.NO_VALUE
    }
    fun setAdditionalHelp(additionalHelp: (String) -> Unit): ParamClass {
        this.additionalHelp = additionalHelp
        return this
    }
    fun execDefault() {
        if (mode == ParamClassMode.VALUES) {
            values[default]!!()
        }
    }
    fun exec(arg: String) {
        when (mode) {
            ParamClassMode.VALUES -> {
                val value = arg.substring(name.length)
                if (!values.contains(value)) {
                    throw Exception("'$name' does not allow the value '$value'")
                }
                values[value]!!()
            }
            ParamClassMode.NO_VALUE -> {
                if (arg != name) {
                    throw Exception("'$name' does not allow Values")
                }
                action()
            }
        }
    }
    fun help(indention: String = "") {
        when (mode) {
            ParamClassMode.VALUES -> {
                println("$indention  $name=${values.keys.map{it}.toString().replace("[","<").replace("]",">").replace(", ","|")}  default=$default")
            }
            ParamClassMode.NO_VALUE -> {
                println("$indention  $name")
            }
        }
        additionalHelp("$indention  ")
    }
}
val compileParams = mutableListOf<ParamClass>(
    ParamClass(
        "--dryMode",
        "Disable",
        mapOf(
            "Enable" to { dryMode = "Enable" },
            "Disable" to { dryMode = "Disable" },
        )
    ),
    ParamClass(
        "--intellijMode",
        "Disable",
        mapOf(
            "Enable" to { intellijMode = "Enable" },
            "Disable" to { intellijMode = "Disable" },
        )
    ),
    ParamClass(
        "--fastMode",
        "JVM",
        mapOf(
            "JVM" to { fastMode = "JVM" },
            "JS" to { fastMode = "JS" },
            "Native" to { fastMode = "Native" },
            "Disable" to { fastMode = "Disable" },
        )
    ),
)
var enabledParams = mutableListOf<ParamClass>()
val defaultParams = mutableListOf(
    ParamClass(
        "--jenaWrapper",
        "Off",
        mapOf(
            "On" to { jenaWrapper = "On" },
            "Off" to { jenaWrapper = "Off" },
        )
    ),
    ParamClass(
        "--releaseMode",
        "Disable",
        mapOf(
            "Enable" to { releaseMode = "Enable" },
            "Disable" to { releaseMode = "Disable" },
        )
    ),
    ParamClass(
        "--suspendMode",
        "Disable",
        mapOf(
            "Enable" to { suspendMode = "Enable" },
            "Disable" to { suspendMode = "Disable" },
        )
    ),
    ParamClass(
        "--inlineMode",
        "Disable",
        mapOf(
            "Enable" to { inlineMode = "Enable" },
            "Disable" to { inlineMode = "Disable" },
        )
    ),
    ParamClass(
        "--partitionMode",
        "On",
        mapOf(
            "On" to { partitionMode = "" },
            "Off" to { partitionMode = "_NoPartitions" },
        )
    ),
    ParamClass(
        "--memoryMode",
        "inmemory",
        mapOf(
            "persistent" to { memoryMode = "_Persistent" },
            "inmemory" to { memoryMode = "_Inmemory" },
        )
    ),
    ParamClass(
        "--proguardMode",
        "Off",
        mapOf(
            "On" to { proguardMode = "-pro" },
            "Off" to { proguardMode = "" },
        )
    ),
    ParamClass(
        "--endpointMode",
        "None",
        mapOf(
            "None" to { endpointMode = "None" },
            "JavaSockets" to { endpointMode = "JavaSockets" },
        )
    ),
    ParamClass(
        "--mainClass",
        "Endpoint",
        mapOf(
            "Binary_Test_Suite" to { mainClass = "Binary_Test_Suite" },
            "Benchmark" to { mainClass = "Benchmark" },
            "Benchmark_Fig5" to { mainClass = "Benchmark_Fig5" },
            "Code_Gen_Example" to { mainClass = "Code_Gen_Example" },
            "Endpoint" to { mainClass = "Endpoint" },
            "Generate_Binary_Test_Suite" to { mainClass = "Generate_Binary_Test_Suite" },
            "Import" to { mainClass = "Import" },
            "Prepared_Statement" to { mainClass = "Prepared_Statement" },
            "Sparql_Test_Suite" to { mainClass = "Sparql_Test_Suite" },
        )
    ),
    ParamClass(
        "--help",
        {
            execMode = ExecMode.HELP
        }
    ),
    ParamClass(
        "--compileAll",
        {
enableParams(compileParams)
            execMode = ExecMode.COMPILE
        }
    ).setAdditionalHelp {
        for (param in compileParams) {
            param.help(it)
        }
    },
)
fun enableParams(params:List<ParamClass>){
for (param in params) {
    param.execDefault()
enabledParams.add(param)
}
}
enableParams(defaultParams)
loop@for (arg in args) {
    for (param in enabledParams) {
        if (arg.startsWith(param.name)) {
            param.exec(arg)
            continue@loop
        }
    }
    cleanedArgs.add(arg)
}
var appendix = ""
if (suspendMode == "Enable") {
    appendix += "_Coroutines"
} else {
    appendix += "_Threads"
}
if (inlineMode == "Enable") {
    appendix += "_Inline"
} else {
    appendix += "_NoInline"
}
if (releaseMode == "Enable") {
    appendix += "_Release"
} else {
    appendix += "_Debug"
}
when (execMode) {
    ExecMode.HELP -> {
 println("Usage ./exec-any.main.kts <options>")
            println("where possible options include:")
            for (param in enabledParams) {
                param.help()
            }
}
    ExecMode.COMPILE -> {
        var releaseMode2 = ReleaseMode.valueOf(releaseMode)
        var suspendMode2 = SuspendMode.valueOf(suspendMode)
        var inlineMode2 = InlineMode.valueOf(inlineMode)
        var dryMode2 = DryMode.valueOf(dryMode)
        var fastMode2 = FastMode.valueOf(fastMode)
        var intellijMode2 = IntellijMode.valueOf(intellijMode)
        createBuildFileForModule("Luposdate3000_Shared", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Jena_Wrapper_On", "Luposdate3000_Jena_Wrapper", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Jena_Wrapper_Off", "Luposdate3000_Jena_Wrapper", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Parser", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Buffer_Manager_Inmemory", "Luposdate3000_Buffer_Manager", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Buffer_Manager_Persistent", "Luposdate3000_Buffer_Manager", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Dictionary_Inmemory", "Luposdate3000_Dictionary", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Operators", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Result_Format", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Triple_Store_Id_Triple", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Triple_Store_All", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Triple_Store_All_NoPartitions", "Luposdate3000_Triple_Store_All", "src${Platform.getPathSeparator()}luposdate3000_triple_store_all", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2, arrayOf("--USE_PARTITIONS2=false"))
        createBuildFileForModule("Luposdate3000_Optimizer", "Luposdate3000_Optimizer", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Optimizer_NoPartitions", "Luposdate3000_Optimizer", "src${Platform.getPathSeparator()}luposdate3000_optimizer", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2, arrayOf("--USE_PARTITIONS=false"))
        createBuildFileForModule("Luposdate3000_Endpoint", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Test", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Endpoint_None", "Luposdate3000_Endpoint_Launcher", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Endpoint_Java_Sockets", "Luposdate3000_Endpoint_Launcher", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Benchmark", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Benchmark_fig5", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Prepared_Statement", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Code_Gen_Example", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
        createBuildFileForModule("Luposdate3000_Launch_Generate_Binary_Test_Suite", "Luposdate3000_Main", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
    }
    ExecMode.LAUNCH -> {
        File("log").mkdirs()
        val jars = mutableListOf(
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager$memoryMode-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Operators-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Parser-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Result_Format-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Shared-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Test-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All$partitionMode-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Optimizer$partitionMode-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint_$endpointMode-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_$jenaWrapper-jvm$proguardMode.jar",
            "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Launch_$mainClass-jvm$proguardMode.jar",
        )
        val userHome = Platform.getUserHome()
        for (f in Platform.findNamedFileInDirectory("${Platform.getGradleCache()}modules-2${Platform.getPathSeparator()}files-2.1${Platform.getPathSeparator()}com.soywiz.korlibs.krypto${Platform.getPathSeparator()}krypto-jvm${Platform.getPathSeparator()}1.9.1${Platform.getPathSeparator()}", "krypto-jvm-1.9.1.jar")) {
            jars.add(f)
        }
        for (f in Platform.findNamedFileInDirectory("${Platform.getMavenCache()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib${Platform.getPathSeparator()}1.4.255-SNAPSHOT", "kotlin-stdlib-1.4.255-SNAPSHOT.jar")) {
            jars.add(f)
        }
        var classpath = ""
        for (jar in jars) {
            if (classpath == "") {
                classpath = jar
            } else {
                if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
                    classpath = "$classpath;$jar"
                } else {
                    classpath = "$classpath:$jar"
                }
            }
        }
        val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt")
        cmd.addAll(cleanedArgs)
        println(cmd)
        throw Exception("finish")
        val p = ProcessBuilder(cmd)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
        p.waitFor()
        if (p.exitValue() != 0) {
            throw Exception("exit-code:: " + p.exitValue())
        }
    }
}

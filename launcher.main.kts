#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.util.jar.JarFile
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
var skipArgs = false
enum class ExecMode { LAUNCH, COMPILE, HELP, COMPILE_AND_LAUNCH, GENERATE_PARSER, GENERATE_ENUMS, SETUP_INTELLIJ_IDEA, SETUP_JS, ALL_TEST }
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
                val value = arg.substring(name.length + 1)
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
            skipArgs = true
        }
    ),
    ParamClass(
        "--allTest",
        {
            execMode = ExecMode.ALL_TEST
            skipArgs = true
        }
    ),
    ParamClass(
        "--generateParser",
        {
            execMode = ExecMode.GENERATE_PARSER
            skipArgs = true
        }
    ),
    ParamClass(
        "--generateEnums",
        {
            execMode = ExecMode.GENERATE_ENUMS
            skipArgs = true
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
    ParamClass(
        "--compileAndLaunch",
        {
            enableParams(compileParams)
            execMode = ExecMode.COMPILE_AND_LAUNCH
        }
    ).setAdditionalHelp {
        for (param in compileParams) {
            param.help(it)
        }
    },
    ParamClass(
        "--clearCaches",
        {
            if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
                File("${System.getenv("LOCALAPPDATA")}${Platform.getPathSeparator()}main.kts.compiled.cache").deleteRecursively()
            } else {
                File("${System.getProperty("user.home")}${Platform.getPathSeparator()}.cache${Platform.getPathSeparator()}main.kts.compiled.cache").deleteRecursively()
            }
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}modules-2").deleteRecursively()
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}jars-8").deleteRecursively()
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}jars-3").deleteRecursively()
            File(Platform.getMavenCache() + "${Platform.getPathSeparator()}luposdate3000").deleteRecursively()
            File("build-cache").deleteRecursively()
            skipArgs = true
        }
    ),
    ParamClass(
        "--setupJS",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_JS
            fastMode = "JS"
        }
    ),
    ParamClass(
        "--setupIntellijIdea",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_INTELLIJ_IDEA
            releaseMode = "Disable"
            suspendMode = "Disable"
            inlineMode = "Disable"
            dryMode = "Enable"
            fastMode = "JVM"
            intellijMode = "Enable"
        }
    ),
)
fun enableParams(params: List<ParamClass>) {
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
            if (skipArgs) {
                break@loop
            }
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
    ExecMode.HELP -> onHelp()
    ExecMode.COMPILE -> onCompile()
    ExecMode.LAUNCH -> onLaunch()
    ExecMode.GENERATE_PARSER -> onGenerateParser()
    ExecMode.GENERATE_ENUMS -> onGenerateEnums()
    ExecMode.SETUP_INTELLIJ_IDEA -> onSetupIntellijIdea()
    ExecMode.SETUP_JS -> onSetupJS()
    ExecMode.ALL_TEST -> onAllTest()
    ExecMode.COMPILE_AND_LAUNCH -> {
        onCompile()
        onLaunch()
    }
}
fun onHelp() {
    println("Usage ./exec-any.main.kts <options>")
    println("where possible options include:")
    for (param in defaultParams) {
        param.help()
    }
}
fun onCompile() {
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
fun onSetupIntellijIdea() {
    File(".idea").deleteRecursively()
    File("log").mkdirs()
    onCompile()
    var releaseMode2 = ReleaseMode.valueOf(releaseMode)
    var suspendMode2 = SuspendMode.valueOf(suspendMode)
    var inlineMode2 = InlineMode.valueOf(inlineMode)
    var dryMode2 = DryMode.valueOf(dryMode)
    var fastMode2 = FastMode.valueOf(fastMode)
    var intellijMode2 = IntellijMode.valueOf(intellijMode)
    createBuildFileForModule("Luposdate3000_Shared_Inline", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
    createBuildFileForModule("Luposdate3000_Scripting", releaseMode2, suspendMode2, inlineMode2, dryMode2, fastMode2, intellijMode2)
    File("build.gradle.kts").printWriter().use { outBuildGradle ->
        outBuildGradle.println("dependencies {")
        outBuildGradle.println("    project(\":src\")")
        outBuildGradle.println("}")
    }
    File("settings.gradle").printWriter().use { outSettingsGradle ->
        File("src${Platform.getPathSeparator()}build.gradle.kts").printWriter().use { outBuildGradle ->
            outSettingsGradle.println("pluginManagement {")
            outSettingsGradle.println("    repositories {")
            outSettingsGradle.println("        mavenLocal()")
            outSettingsGradle.println("        gradlePluginPortal()")
            outSettingsGradle.println("    }")
            outSettingsGradle.println("}")
            outSettingsGradle.println("rootProject.name = \"Luposdate3000\"")
            outSettingsGradle.println("include(\":src\")")
            outBuildGradle.println("dependencies {")
            Files.walk(Paths.get("src"), 1).forEach { it ->
                val name = it.toString()
                println(name)
                if (name.startsWith("src/lupos") || name.startsWith("src\\lupos")) {
                    if (!name.contains("shared_inline")) {
                        outSettingsGradle.println("include(\":src:${name.substring(4)}\")")
                        outBuildGradle.println("    project(\":src:${name.substring(4)}\")")
                    }
                }
            }
            outBuildGradle.println("}")
        }
    }
}
fun onLaunch() {
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
    for (f in Platform.findNamedFileInDirectory("${Platform.getMavenCache()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib${Platform.getPathSeparator()}$compilerVersion", "kotlin-stdlib-$compilerVersion.jar")) {
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
    val p = ProcessBuilder(cmd)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("exit-code:: " + p.exitValue())
    }
}
fun onGenerateParser() {
    val outFile = File("src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserGenerated.kt")
    outFile.printWriter().use { out ->
        out.println("package lupos.s02buildSyntaxTree.turtle")
        out.println("import lupos.s00misc.IMyInputStream")
        out.println("import lupos.s00misc.Luposdate3000Exception")
        out.println("import kotlin.jvm.JvmField")
        out.println("internal open class ParserException(msg: String) : Luposdate3000Exception(\"ParserContext\", msg)")
        out.println("internal class ParserExceptionEOF : ParserException(\"EOF\")")
        out.println("internal class ParserExceptionUnexpectedChar(context: ParserContext) : ParserException(\"unexpected char 0x\${context.c.toString(16)} at \${context.line}:\${context.column}\")")
    }
    val scriptFile = "src/luposdate3000_scripting/parsergenerator.main.kts"
    val generatingArgs = arrayOf(
        listOf(scriptFile, "PARSER_CONTEXT"),
        listOf(scriptFile, "parse_dot", "DOT"),
        listOf(scriptFile, "parse_ws", "SKIP_WS"),
        listOf(scriptFile, "parse_ws_forced", "SKIP_WS_FORCED"),
        listOf(scriptFile, "parse_statement", "BASE", "PREFIX", "BASE2", "PREFIX2", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL"),
        listOf(scriptFile, "parse_base", "IRIREF"),
        listOf(scriptFile, "parse_prefix", "PNAME_NS"),
        listOf(scriptFile, "parse_prefix2", "IRIREF"),
        listOf(scriptFile, "parse_predicate", "VERB1", "IRIREF", "PNAME_NS"),
        listOf(scriptFile, "parse_obj", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL", "STRING_LITERAL_QUOTE", "STRING_LITERAL_SINGLE_QUOTE", "STRING_LITERAL_LONG_SINGLE_QUOTE", "STRING_LITERAL_LONG_QUOTE", "INTEGER", "DECIMAL", "DOUBLE", "BOOLEAN"),
        listOf(scriptFile, "parse_triple_end", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT"),
        listOf(scriptFile, "parse_triple_end_or_object_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf(scriptFile, "parse_triple_end_or_object_string", "LANGTAG", "IRI1", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf(scriptFile, "parse_triple_end_or_object_string_typed", "IRIREF", "PNAME_NS"),
        listOf(scriptFile, "parse_triple_end_or_object_string_typed_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf(scriptFile, "parse_subject_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
        listOf(scriptFile, "parse_predicate_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
    )
    for (args in generatingArgs) {
        ProcessBuilder(args)
            .redirectOutput(Redirect.appendTo(outFile))
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    }
}
fun onGenerateEnumsHelper(enumName: String, packageName: String, modifier: String, fileName: String) {
    val mapping2 = mutableListOf<String>()
    var id = 0
    File(fileName + ".txt").forEachLine {
        mapping2.add(it)
        id++
    }
    val mapping = mapping2.sorted()
    File(fileName + ".kt").printWriter().use { out ->
        out.println("package $packageName")
        out.println("$modifier typealias $enumName = Int")
    }
    File(fileName + "Ext.kt").printWriter().use { out ->
        out.println("package $packageName")
        out.println("import kotlin.jvm.JvmField")
        out.println("$modifier object ${enumName}Ext {")
        for (i in 0 until mapping.size) {
            out.println("    $modifier const val ${mapping[i]}: $enumName = $i")
        }
        out.println("    $modifier const val values_size: Int = ${mapping.size}")
        out.println("    @JvmField $modifier val names: Array<String> = arrayOf(")
        for (i in 0 until mapping.size) {
            out.println("        \"${mapping[i]}\",")
        }
        out.println("    )")
        out.println("}")
    }
}
fun onGenerateEnums() {
    val generatingArgs = arrayOf(
        listOf("MyPrintWriterMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}MyPrintWriterMode"),
        listOf("BuiltInFunctions", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}sparql1_1${Platform.getPathSeparator()}BuiltInFunctions"),
        listOf("BinaryTestCaseOutputMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_test${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}BinaryTestCaseOutputMode"),
        listOf("Aggregation", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}sparql1_1${Platform.getPathSeparator()}Aggregation"),
        listOf("TripleStoreFeature", "lupos.s05tripleStore", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s05tripleStore${Platform.getPathSeparator()}TripleStoreFeature"),
        listOf("IteratorBundleMode", "lupos.s04logicalOperators.iterator", "internal", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s04logicalOperators${Platform.getPathSeparator()}iterator${Platform.getPathSeparator()}IteratorBundleMode"),
        listOf("ESortPriority", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ESortPriority"),
        listOf("ETripleIndexType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ETripleIndexType"),
        listOf("EGraphRefType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EGraphRefType"),
        listOf("EOperatorID", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EOperatorID"),
        listOf("EModifyType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EModifyType"),
        listOf("ETripleComponentType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ETripleComponentType"),
        listOf("EGraphOperationType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EGraphOperationType"),
        listOf("ESortType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ESortType"),
        listOf("EGroupMember", "", "lupos.s00misc", "public", "", "", "src${Platform.getPathSeparator()}luposdate3000_optimizer${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EGroupMember"),
        listOf("EQueryResultToStream", "lupos.s11outputResult", "public", "src${Platform.getPathSeparator()}luposdate3000_result_format${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s11outputResult${Platform.getPathSeparator()}EQueryResultToStream"),
        listOf("EPOPDebugMode", "lupos.s00misc", "public", "", "src${Platform.getPathSeparator()}luposdate3000_operators${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EPOPDebugMode"),
        listOf("Turtle2ParserState", "lupos.s02buildSyntaxTree.turtle", "internal", "src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserState"),
        listOf("EOptimizerID", "lupos.s00misc", "internal", "src${Platform.getPathSeparator()}luposdate3000_optimizer${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EOptimizerID"),
        listOf("EOperatingSystem", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EOperatingSystem"),
        listOf("EIndexPattern", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EIndexPattern"),
    )
    for (args in generatingArgs) {
        onGenerateEnumsHelper(args[0], args[1], args[2], args[3])
    }
}
fun copyFromJar(source: InputStream, dest: String) {
    val out = FileOutputStream(dest)
    while (source.available()> 0) {
        out.write(source.read())
    }
    out.close()
    source.close()
}
fun onSetupJS() {
    onCompile()
    val jsStdlib = JarFile(File("${Platform.getMavenCache()}${Platform.getPathSeparator()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib-js${Platform.getPathSeparator()}$compilerVersion${Platform.getPathSeparator()}kotlin-stdlib-js-$compilerVersion.jar"))
    copyFromJar(jsStdlib.getInputStream(jsStdlib.getEntry("kotlin.js")), "build-cache${Platform.getPathSeparator()}kotlin.js")
    copyFromJar(jsStdlib.getInputStream(jsStdlib.getEntry("kotlin.js.map")), "build-cache${Platform.getPathSeparator()}kotlin.js.map")
    val kryptoLib = JarFile(find("${Platform.getGradleCache()}${Platform.getPathSeparator()}modules-2${Platform.getPathSeparator()}files-2.1${Platform.getPathSeparator()}com.soywiz.korlibs.krypto${Platform.getPathSeparator()}krypto-js", "krypto-js-1.9.1.jar")!!)
    copyFromJar(kryptoLib.getInputStream(kryptoLib.getEntry("krypto-root-krypto.js")), "build-cache${Platform.getPathSeparator()}krypto-root-krypto.js")
    copyFromJar(kryptoLib.getInputStream(kryptoLib.getEntry("krypto-root-krypto.js.map")), "build-cache${Platform.getPathSeparator()}krypto-root-krypto.js.map")
    File("build-cache${Platform.getPathSeparator()}index.html").printWriter().use { out ->
        out.println("<!DOCTYPE html>")
        out.println("<html lang=\"en\">")
        out.println("<head>")
        out.println("    <meta charset=\"utf-8\">")
        out.println("    <title>Luposdate3000</title>")
        out.println("    <script src=\"kotlin.js\"></script>")
        out.println("    <script src=\"krypto-root-krypto.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Shared.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Buffer_Manager_Inmemory/Luposdate3000_Buffer_Manager.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Dictionary_Inmemory/Luposdate3000_Dictionary.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Jena_Wrapper_Off/Luposdate3000_Jena_Wrapper.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Operators.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Parser.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Result_Format.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Triple_Store_Id_Triple.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Triple_Store_All_NoPartitions/Luposdate3000_Triple_Store_All.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Optimizer_NoPartitions/Luposdate3000_Optimizer.js\"></script>")
        out.println("    <script src=\"bin$appendix/Luposdate3000_Endpoint.js\"></script>")
        out.println("</head>")
        out.println("<body>")
        out.println("<script>")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.initialize()")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b(\"INSERT DATA { <s> <p> <o> } \")")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b(\"SELECT (5 as ?x) ?s {?s ?p ?o .}\")")
        out.println("</script>")
        out.println("</body>")
        out.println("</html>")
    }
}
fun find(path: String, fName: String): File? {
    val f = File(path)
    if (fName == f.getName()) {
        return f
    }
    if (f.isDirectory()) {
        for (aChild in f.list()) {
            val ff = find(path + File.separator + aChild, fName)
            if (ff != null) {
                return ff
            }
        }
    }
    return null
}
fun onAllTest() {
    for (r in listOf("Enable", "Disable")) {
        for (i in listOf("Enable", "Disable")) {
            for (s in listOf("Disable")) {
                ProcessBuilder("./launcher.main.kts", "--compileAll", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--dryMode=Disable", "--fastMode=Disable", "--intellijMode=Disable")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s.compile-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=On", "--partitionMode=On", "--memoryMode=inmemory", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-Partitions.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                File("/tmp/luposdate3000/").deleteRecursively()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Off", "--partitionMode=On", "--memoryMode=inmemory", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Off", "--partitionMode=On", "--memoryMode=persistent", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=On", "--partitionMode=On", "--memoryMode=inmemory", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-Partitions-Proguard.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Off", "--partitionMode=On", "--memoryMode=inmemory", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Proguard.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                File("/tmp/luposdate3000/").deleteRecursively()
                ProcessBuilder("./launcher.main.kts", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Off", "--partitionMode=On", "--memoryMode=persistent", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent-Proguard.test-log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            }
        }
    }
}

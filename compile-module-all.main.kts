#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
var releaseMode = ReleaseMode.Disable
var suspendMode = SuspendMode.Disable
var inlineMode = InlineMode.Disable
var dryMode = DryMode.Disable
var fastMode = FastMode.JVM
var intellijMode = IntellijMode.Disable

for (arg in args) {
    if (arg.startsWith("--releaseMode=")) {
        releaseMode = ReleaseMode.valueOf(arg.substring("--releaseMode=".length))
    } else if (arg.startsWith("--suspendMode=")) {
        suspendMode = SuspendMode.valueOf(arg.substring("--suspendMode=".length))
    } else if (arg.startsWith("--inlineMode=")) {
        inlineMode = InlineMode.valueOf(arg.substring("--inlineMode=".length))
    } else if (arg.startsWith("--dryMode=")) {
        dryMode = DryMode.valueOf(arg.substring("--dryMode=".length))
    } else if (arg.startsWith("--fastMode=")) {
        fastMode = FastMode.valueOf(arg.substring("--fastMode=".length))
    } else if (arg.startsWith("--intellijMode=")) {
        intellijMode = IntellijMode.valueOf(arg.substring("--intellijMode=".length))
    }
}

createBuildFileForModule("Luposdate3000_Shared", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_On", "Luposdate3000_Jena_Wrapper", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_Off", "Luposdate3000_Jena_Wrapper", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Parser", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Inmemory", "Luposdate3000_Buffer_Manager", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Persistent", "Luposdate3000_Buffer_Manager", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Dictionary_Inmemory", "Luposdate3000_Dictionary", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Operators", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Result_Format", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Triple_Store_Id_Triple", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Triple_Store_All", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Triple_Store_All_NoPartitions", "Luposdate3000_Triple_Store_All", "src${Platform.getPathSeparator()}luposdate3000_triple_store_all", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode, arrayOf("--USE_PARTITIONS2=false"))
createBuildFileForModule("Luposdate3000_Optimizer", "Luposdate3000_Optimizer", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Optimizer_NoPartitions", "Luposdate3000_Optimizer", "src${Platform.getPathSeparator()}luposdate3000_optimizer", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode, arrayOf("--USE_PARTITIONS=false"))
createBuildFileForModule("Luposdate3000_Endpoint", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Test", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Endpoint_None", "Luposdate3000_Endpoint_Launcher", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Endpoint_Java_Sockets", "Luposdate3000_Endpoint_Launcher", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_fig5", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Prepared_Statement", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Code_Gen_Example", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Generate_Binary_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)

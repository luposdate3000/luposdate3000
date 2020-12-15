#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
 
import lupos.s00misc.Platform

createBuildFileForModule("Luposdate3000_Shared", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_On", "Luposdate3000_Jena_Wrapper", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_Off", "Luposdate3000_Jena_Wrapper", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Parser", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Dictionary_Inmemory", "Luposdate3000_Dictionary", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Operators", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Result_Format", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Inmemory", "Luposdate3000_Buffer_Manager", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Persistent", "Luposdate3000_Buffer_Manager", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Triple_Store_Id_Triple", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Triple_Store_All", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Optimizer", "Luposdate3000_Optimizer", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Optimizer_NoPartitions", "Luposdate3000_Optimizer", "src${Platform.getPathSeparator()}luposdate3000_optimizer", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Test", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint_None", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint_Java_Sockets", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_Jena", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_1", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_2", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_3", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_4", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Prepared_Statement", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.Disable, IntellijMode.Disable)





















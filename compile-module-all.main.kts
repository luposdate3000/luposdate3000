#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform

createBuildFileForModule("Luposdate3000_Shared", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_On", "Luposdate3000_Jena_Wrapper", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_Off", "Luposdate3000_Jena_Wrapper", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Parser", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Dictionary_Inmemory", "Luposdate3000_Dictionary", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Operators", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Result_Format", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Inmemory", "Luposdate3000_Buffer_Manager", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Triple_Store_Id_Triple", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Triple_Store_All", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Optimizer", "Luposdate3000_Optimizer", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Optimizer_NoPartitions", "Luposdate3000_Optimizer", "src${Platform.getPathSeparator()}luposdate3000_optimizer", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Test", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint_None", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Endpoint_Java_Sockets", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_Jena", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)
createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", ReleaseMode.Enable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Disable)


 

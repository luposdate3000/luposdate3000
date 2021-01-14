#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

File(".idea").deleteRecursively()
File("log").mkdirs()

var releaseMode = ReleaseMode.Disable
var suspendMode = SuspendMode.Disable
var inlineMode = InlineMode.Disable
var dryMode = DryMode.Disable
var fastMode = FastMode.JVM
var intellijMode = IntellijMode.Enable

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
createBuildFileForModule("Luposdate3000_Launch_Benchmark_Jena", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_1", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_2", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_3", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_4", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Prepared_Statement", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Code_Gen_Example", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)
createBuildFileForModule("Luposdate3000_Launch_Generate_Binary_Test_Suite", "Luposdate3000_Main", releaseMode, suspendMode, inlineMode, dryMode, fastMode, intellijMode)

// IDE only fake modules
createBuildFileForModule("Luposdate3000_Shared_Inline", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Disable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Scripting", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Disable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)

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

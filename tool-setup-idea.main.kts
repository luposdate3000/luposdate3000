#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

File(".idea").deleteRecursively()
createBuildFileForModule("Luposdate3000_Shared", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_On", "Luposdate3000_Jena_Wrapper", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Jena_Wrapper_Off", "Luposdate3000_Jena_Wrapper", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Parser", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Dictionary_Inmemory", "Luposdate3000_Dictionary", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Operators", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Result_Format", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Buffer_Manager_Inmemory", "Luposdate3000_Buffer_Manager", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Triple_Store_Id_Triple", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Triple_Store_All", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Optimizer", "Luposdate3000_Optimizer", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Optimizer_NoPartitions", "Luposdate3000_Optimizer", "src${Platform.getPathSeparator()}luposdate3000_optimizer", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Endpoint", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Test", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Endpoint_None", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Endpoint_Java_Sockets", "Luposdate3000_Endpoint_Launcher", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark_Jena", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Benchmark", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Binary_Test_Suite", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Endpoint", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Import", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Launch_Sparql_Test_Suite", "Luposdate3000_Main", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)

//IDE only fake modules
createBuildFileForModule("Luposdate3000_Shared_Inline", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)
createBuildFileForModule("Luposdate3000_Scripting", ReleaseMode.Disable, SuspendMode.Disable, InlineMode.Enable, DryMode.Disable, FastMode.JVM, IntellijMode.Enable)


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
            if (name.startsWith("src${Platform.getPathSeparator()}lupos")) {
                outSettingsGradle.println("include(\":src:${name.substring(4)}\")")
                outBuildGradle.println("    project(\":src:${name.substring(4)}\")")
            }
        }
        outBuildGradle.println("}")
    }
}

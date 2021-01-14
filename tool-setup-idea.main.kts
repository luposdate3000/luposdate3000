#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
File(".idea").deleteRecursively()
File("log").mkdirs()
val p = ProcessBuilder("./compile-module-all.main.kts", "--releaseMode=Disable", "--suspendMode=Disable", "--inlineMode=Disable", "--dryMode=Enable", "--fastMode=JVM", "--intellijMode=Enable")
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
    .waitFor()
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

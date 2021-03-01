/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

enum class ReleaseMode {
    Enable, Disable
}

enum class DryMode {
    Enable, Disable
}

enum class TargetMode {
    JVM, JS, Native, All
}

enum class IntellijMode {
    Enable, Disable
}

var compilerVersion = "1.5.255-SNAPSHOT"

// var compilerVersion = "1.4.255-SNAPSHOT"
// var compilerVersion = "1.4.0"
val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")
private fun printDependencies(dependencies: Set<String>, buildForIDE: Boolean, appendix: String, out: PrintWriter) {
    for (d in dependencies) {
        if (d.startsWith("luposdate3000")) {
            if (buildForIDE) {
                var t = d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()
                if (t.contains("#")) {
                    t = t.substring(0, t.indexOf("#"))
                }
                out.println("                implementation(project(\":src:${t}\"))")
            } else {
                out.println("                compileOnly(\"${d.replace("#", "")}\")")
            }
        } else if (d.startsWith("npm(")) {
            out.println("                implementation($d)")
        } else {
            out.println("                implementation(\"$d\")")
        }
    }
}

private fun copyFileWithReplacement(src: File, dest: File, replacement: Map<String, String>) {
    dest.printWriter().use { out ->
        src.forEachLine { it ->
            var s = it
            for ((k, v) in replacement) {
                s = s.replace(k, v)
                if (k.startsWith(" ")) {
                    if (s.startsWith(k.substring(1))) {
                        s = v + s.substring(k.length - 1)
                    }
                }
            }
            out.println(s)
        }
    }
}

private fun copyFilesWithReplacement(src: String, dest: String, replacement: Map<String, String>, pathSeparator: String) {
    for (it in File(src).walk()) {
        val tmp = it.toString()
        val t = tmp.substring(src.length)
        if (File(tmp).isFile()) {
            copyFileWithReplacement(File(src + pathSeparator + t), File(dest + pathSeparator + t), replacement)
        } else {
            File(dest + pathSeparator + t).mkdirs()
        }
    }
}

class CreateModuleArgs() {
    var enabledFunc: () -> Boolean = { true }
    var enabledRunFunc: () -> Boolean = { true }
    var moduleName: String = ""
    var moduleFolder: String = ""
    var modulePrefix: String = ""
    var platform: String = "linuxX64"
    var releaseMode: ReleaseMode = ReleaseMode.Disable
    var suspendMode: SuspendMode = SuspendMode.Disable
    var inlineMode: InlineMode = InlineMode.Disable
    var dryMode: DryMode = DryMode.Disable
    var target: TargetMode = TargetMode.JVM
    var ideaBuildfile: IntellijMode = IntellijMode.Disable
    var codegenKAPT: Boolean = false
    var codegenKSP: Boolean = false
    var args: MutableMap<String, String> = mutableMapOf()

    init {
        if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
            platform = "mingw64"
        } else {
            platform = "linuxX64"
        }
    }

    fun clone(): CreateModuleArgs {
        var res = CreateModuleArgs()
        res.enabledFunc = enabledFunc
        res.enabledRunFunc = enabledRunFunc
        res.moduleName = moduleName
        res.moduleFolder = moduleFolder
        res.modulePrefix = modulePrefix
        res.platform = platform
        res.releaseMode = releaseMode
        res.suspendMode = suspendMode
        res.inlineMode = inlineMode
        res.dryMode = dryMode
        res.target = target
        res.ideaBuildfile = ideaBuildfile
        res.codegenKAPT = codegenKAPT
        res.codegenKSP = codegenKSP
        res.args = args
        return res
    }

    fun ssetEnabledFunc(enabledFunc: () -> Boolean): CreateModuleArgs {
        val res = clone()
        res.enabledFunc = enabledFunc
        return res
    }

    fun ssetEnabledRunFunc(enabledRunFunc: () -> Boolean): CreateModuleArgs {
        val res = clone()
        res.enabledRunFunc = enabledRunFunc
        return res
    }

    fun ssetArgs(args: MutableMap<String, String>): CreateModuleArgs {
        val res = clone()
        res.args = mutableMapOf()
        res.args.putAll(this.args)
        res.args.putAll(args)
        return res
    }

    fun ssetArgs2(args: MutableMap<String, MutableMap<String, String>>): CreateModuleArgs {
        val arg = args[moduleName]
        if (arg != null) {
            val res = clone()
            res.args = mutableMapOf()
            res.args.putAll(this.args)
            res.args.putAll(arg)
            args.remove(moduleName)
            return res
        } else {
            return this
        }
    }

    fun ssetModuleName(moduleName: String): CreateModuleArgs {
        val res = clone()
        val onWindows = System.getProperty("os.name").contains("Windows")
        val pathSeparator: String
        if (onWindows) {
            pathSeparator = "\\\\"
        } else {
            pathSeparator = "/"
        }
        res.moduleName = moduleName
        res.modulePrefix = moduleName
        res.moduleFolder = "src${pathSeparator}${moduleName.toLowerCase()}"
        return res
    }

    fun ssetModuleName(moduleName: String, modulePrefix: String): CreateModuleArgs {
        val res = clone()
        val onWindows = System.getProperty("os.name").contains("Windows")
        val pathSeparator: String
        if (onWindows) {
            pathSeparator = "\\\\"
        } else {
            pathSeparator = "/"
        }
        res.moduleName = moduleName
        res.modulePrefix = modulePrefix
        res.moduleFolder = "src${pathSeparator}${moduleName.toLowerCase()}"
        return res
    }

    fun ssetModuleName(moduleName: String, modulePrefix: String, moduleFolder: String): CreateModuleArgs {
        val res = clone()
        res.moduleName = moduleName
        res.moduleFolder = moduleFolder
        res.modulePrefix = modulePrefix
        return res
    }

    fun ssetReleaseMode(releaseMode: ReleaseMode): CreateModuleArgs {
        val res = clone()
        res.releaseMode = releaseMode
        return res
    }

    fun ssetSuspendMode(suspendMode: SuspendMode): CreateModuleArgs {
        val res = clone()
        res.suspendMode = suspendMode
        return res
    }

    fun ssetInlineMode(inlineMode: InlineMode): CreateModuleArgs {
        val res = clone()
        res.inlineMode = inlineMode
        return res
    }

    fun ssetDryMode(dryMode: DryMode): CreateModuleArgs {
        val res = clone()
        res.dryMode = dryMode
        return res
    }

    fun ssetTarget(target: TargetMode): CreateModuleArgs {
        val res = clone()
        res.target = target
        return res
    }

    fun ssetIdeaBuildfile(ideaBuildfile: IntellijMode): CreateModuleArgs {
        val res = clone()
        res.ideaBuildfile = ideaBuildfile
        return res
    }

    fun ssetCodegenKSP(codegenKSP: Boolean): CreateModuleArgs {
        val res = clone()
        res.codegenKSP = codegenKSP
        return res
    }

    fun ssetCodegenKAPT(codegenKAPT: Boolean): CreateModuleArgs {
        val res = clone()
        res.codegenKAPT = codegenKAPT
        return res
    }

    fun getPossibleOptions(): List<String> {
        val res = mutableListOf<String>()
        if (File(File(moduleFolder), "configOptions").exists()) {
            File(File(moduleFolder), "configOptions").forEachLine {
                val opt = it.split(",")
                if (opt.size == 4) {
                    res.add(opt[0])
                }
            }
        }
        return res
    }
}

public fun createBuildFileForModule(moduleArgs: CreateModuleArgs) {
    val copySelevtively = false
    try {
        if (moduleArgs.dryMode == DryMode.Enable || moduleArgs.ideaBuildfile == IntellijMode.Enable) {
            moduleArgs.dryMode = DryMode.Enable
        } else {
            moduleArgs.dryMode = DryMode.Disable
        }
        var appendix = ""
        if (moduleArgs.suspendMode == SuspendMode.Enable) {
            appendix += "_Coroutines"
        } else {
            appendix += "_Threads"
        }
        if (moduleArgs.inlineMode == InlineMode.Enable) {
            appendix += "_Inline"
        } else {
            appendix += "_NoInline"
        }
        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
            appendix += "_Release"
        } else {
            appendix += "_Debug"
        }
        val onWindows = System.getProperty("os.name").contains("Windows")
        val onLinux = !onWindows // TODO this is not correct ...
        val pathSeparator: String
        val pathSeparatorEscaped: String
        if (onWindows) {
            pathSeparator = "\\"
            pathSeparatorEscaped = "\\\\"
        } else {
            pathSeparator = "/"
            pathSeparatorEscaped = "/"
        }
        var enableJVM = moduleArgs.target == TargetMode.All || moduleArgs.target == TargetMode.JVM
        var enableJS = moduleArgs.target == TargetMode.All || moduleArgs.target == TargetMode.JS
        var enableNative = moduleArgs.target == TargetMode.All || moduleArgs.target == TargetMode.Native
        if (File("${moduleArgs.moduleFolder}/disableTarget").exists()) {
            File("${moduleArgs.moduleFolder}/disableTarget").forEachLine {
                when (it) {
                    "jvm" -> enableJVM = false
                    "js" -> {
                        enableJS = false
                    }
                    moduleArgs.platform, "native" -> enableNative = false
                }
            }
        }
        if (!(enableJVM || enableJS || enableNative)) {
            return
        }
        val buildLibrary = moduleArgs.modulePrefix != "Luposdate3000_Main"
        println("generating buildfile for ${moduleArgs.moduleName}")
        if (!buildLibrary && moduleArgs.codegenKSP) {
            if (compilerVersion != "1.4.0" || copySelevtively == false) {
                return
            }
        }
        var shortFolder = ".$pathSeparator${moduleArgs.moduleName}"
        shortFolder = shortFolder.substring(shortFolder.lastIndexOf(pathSeparator) + 1)
        File("src.generated").deleteRecursively()
        val buildFolder = "build-cache${pathSeparator}build_$shortFolder$appendix"
        println("buildFolder :: $buildFolder")
        val srcFolder = "build-cache${pathSeparator}src_$shortFolder$appendix"
        if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
            File("src.generated").mkdirs()
            val p = Paths.get("${moduleArgs.moduleFolder}${pathSeparator}src")
            println("basepath=$p")
            Files.walk(p, 1).forEach { it ->
                val tmp = it.toString()
                if (tmp.length > p.toString().length) {
                    val idx = tmp.lastIndexOf(pathSeparator)
                    val f: String
                    if (idx >= 0) {
                        f = tmp.substring(idx + 1)
                    } else {
                        f = tmp
                    }
                    if (f.startsWith("common")) {
                        copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("common.*Main", "commonMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public "), pathSeparator)
                    } else if (f.startsWith("jvm")) {
                        if (enableJVM) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("jvm.*Main", "jvmMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public "), pathSeparator)
                        }
                    } else if (f.startsWith("js")) {
                        if (enableJS) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("js.*Main", "jsMain"), mapOf(), pathSeparator)
                        }
                    } else if (f.startsWith("native")) {
                        if (enableNative) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("native.*Main", "nativeMain"), mapOf(), pathSeparator)
                        }
                    } else if (f.startsWith(moduleArgs.platform)) {
                        if (enableNative) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("${moduleArgs.platform}.*Main", "${moduleArgs.platform}Main"), mapOf(), pathSeparator)
                        }
                    }
                }
            }
            File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos").mkdirs()
            File("src.generated${pathSeparator}settings.gradle.kts").printWriter().use { out ->
                out.println("pluginManagement {")
                out.println("    resolutionStrategy {")
                out.println("        eachPlugin {")
                out.println("            when (requested.id.id) {")
                out.println("                \"kotlin-ksp\",")
                out.println("                \"org.jetbrains.kotlin.kotlin-ksp\",")
                out.println("                \"org.jetbrains.kotlin.ksp\" -> useModule(\"org.jetbrains.kotlin:kotlin-ksp:\${requested.version}\")")
                out.println("            }")
                out.println("        }")
                out.println("    }")
                out.println("    repositories {")
                out.println("        mavenLocal()")
                out.println("        maven(\"https://dl.bintray.com/kotlin/kotlin-eap\")")
                out.println("        google()")
                out.println("        gradlePluginPortal()")
                out.println("    }")
                out.println("}")
                out.println("rootProject.name = \"${moduleArgs.moduleName}\"") // maven-artifactID
            }
        }
        val commonDependencies = mutableSetOf<String>()
        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared")) {
            commonDependencies.add("luposdate3000:Luposdate3000_Shared:0.0.1")
        }
        if (File("${moduleArgs.moduleFolder}${pathSeparator}commonDependencies").exists()) {
            File("${moduleArgs.moduleFolder}${pathSeparator}commonDependencies").forEachLine {
                if (it.length > 0) {
                    commonDependencies.add(it)
                }
            }
        }
        val jvmDependencies = mutableSetOf<String>()
        if (File("${moduleArgs.moduleFolder}${pathSeparator}jvmDependencies").exists()) {
            File("${moduleArgs.moduleFolder}${pathSeparator}jvmDependencies").forEachLine {
                if (it.length > 0) {
                    jvmDependencies.add(it)
                }
            }
        }
        val jsDependencies = mutableSetOf<String>()
        if (File("${moduleArgs.moduleFolder}${pathSeparator}jsDependencies").exists()) {
            File("${moduleArgs.moduleFolder}${pathSeparator}jsDependencies").forEachLine {
                if (it.length > 0) {
                    jsDependencies.add(it)
                }
            }
        }
        val nativeDependencies = mutableSetOf<String>()
        if (File("${moduleArgs.moduleFolder}${pathSeparator}nativeDependencies").exists()) {
            File("${moduleArgs.moduleFolder}${pathSeparator}nativeDependencies").forEachLine {
                if (it.length > 0) {
                    nativeDependencies.add(it)
                }
            }
        }
        for (filename in listOf("src.generated${pathSeparator}build.gradle.kts", "${moduleArgs.moduleFolder}${pathSeparator}build.gradle.kts")) {
            var buildForIDE = filename != "src.generated${pathSeparator}build.gradle.kts"
            if (moduleArgs.ideaBuildfile == IntellijMode.Enable && !buildForIDE) {
                continue
            }
            if (moduleArgs.ideaBuildfile == IntellijMode.Disable && buildForIDE) {
                continue
            }
            File(filename).printWriter().use { out ->
                out.println("import org.jetbrains.kotlin.gradle.tasks.KotlinCompile")
                out.println("buildscript {")
                out.println("    repositories {")
                out.println("        mavenLocal()")
                out.println("        jcenter()")
                out.println("        google()")
                out.println("        mavenCentral()")
                out.println("        maven(\"https://plugins.gradle.org/m2/\")")
                out.println("        maven(\"https://dl.bintray.com/kotlin/kotlin-eap\")")
                out.println("    }")
                out.println("    dependencies {")
                out.println("        classpath(\"org.jetbrains.kotlin:kotlin-gradle-plugin:${compilerVersion}\")")
                if (enableJVM) {
                    out.println("        classpath(\"com.guardsquare:proguard-gradle:7.0.1\")")
                }
                out.println("    }")
                out.println("}")
                if (buildForIDE) {
                    for (d in commonDependencies) {
                        if (d.startsWith("luposdate3000")) {
                            var t = d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()
                            if (t.contains("#")) {
                                t = t.substring(0, t.indexOf("#"))
                            }
                            out.println("    evaluationDependsOn(\":src:${t}\")")
                        }
                    }
                }
                out.println("plugins {")
                out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"${compilerVersion}\"")
                if (!buildLibrary && moduleArgs.codegenKAPT) {
                    out.println("    id(\"org.jetbrains.kotlin.kapt\") version \"${compilerVersion}\"")
                }
                if (!buildLibrary && moduleArgs.codegenKSP) {
                    out.println("    id(\"kotlin-ksp\") version \"1.4.0-dev-experimental-20200914\"")
                }
                if (buildForIDE && !buildLibrary) {
                    out.println("    application")
                }
                out.println("}")
                out.println("repositories {")
                out.println("    mavenLocal()")
                out.println("    jcenter()")
                out.println("    google()")
                out.println("    mavenCentral()")
                out.println("    maven(\"https://plugins.gradle.org/m2/\")")
                out.println("    maven(\"https://dl.bintray.com/kotlin/kotlin-eap\")")
                out.println("}")
                out.println("group = \"luposdate3000\"") // maven-groupID
                out.println("version = \"0.0.1\"") // maven-version
                out.println("apply(plugin = \"maven-publish\")")
                // see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
                // or kotlinc -X
                out.println("kotlin {")
                out.println("    explicitApi()") // https://zsmb.co/mastering-api-visibility-in-kotlin/
                out.println("    metadata {")
                out.println("        compilations.forEach{")
                out.println("            it.kotlinOptions {")
                out.println("                freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                out.println("            }")
                out.println("        }")
                out.println("    }")
                if (enableJVM) {
                    out.println("    jvm {")
                    out.println("        compilations.forEach{")
                    out.println("            it.kotlinOptions {")
                    out.println("                jvmTarget= \"1.8\"")
                    out.println("                useIR = true")
                    out.println("                freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                    out.println("                freeCompilerArgs += \"-Xno-param-assertions\"")
                    out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("                freeCompilerArgs += \"-Xno-receiver-assertions\"")
                    out.println("                freeCompilerArgs += \"-Xno-call-assertions\"")
                    out.println("            }")
                    out.println("        }")
                    out.println("    }")
                }
                if (enableJS) {
                    out.println("    js {")
                    out.println("        moduleName = \"${moduleArgs.modulePrefix}\"")
                    out.println("        browser {")
                    out.println("            compilations.forEach{")
                    out.println("                it.kotlinOptions {")
                    out.println("                    freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                    out.println("                    freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("                }")
                    out.println("            }")
                    out.println("            dceTask {")
                    out.println("                dceOptions.devMode = true")
                    out.println("            }")
                    out.println("            testTask {")
                    out.println("                enabled = false")
                    out.println("            }")
                    out.println("        }")
                    out.println("        nodejs {")
                    out.println("            compilations.forEach{")
                    out.println("                it.kotlinOptions {")
                    out.println("                    freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                    out.println("                    freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("                }")
                    out.println("            }")
                    out.println("            testTask {")
                    out.println("                enabled = false")
                    out.println("            }")
                    out.println("        }")
                    out.println("        binaries.executable()")
                    out.println("    }")
                }
                if (enableNative) {
                    out.println("    ${moduleArgs.platform}(\"${moduleArgs.platform}\") {")
                    out.println("        compilations.forEach{")
                    out.println("            it.kotlinOptions {")
                    out.println("                freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                    out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("            }")
                    out.println("        }")
                    out.println("        binaries {")
                    if (buildLibrary) {
                        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                            out.println("            sharedLib (listOf(RELEASE)){")
                            out.println("                baseName = \"${moduleArgs.modulePrefix}\"")
                            out.println("            }")
                        } else {
                            out.println("            sharedLib (listOf(DEBUG)){")
                            out.println("                baseName = \"${moduleArgs.modulePrefix}\"")
                            out.println("            }")
                        }
                    } else {
                        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                            out.println("            executable(listOf(RELEASE)) {")
                            out.println("            }")
                        } else {
                            out.println("            executable(listOf(DEBUG)) {")
                            out.println("            }")
                        }
                    }
                    out.println("        }")
                    out.println("    }")
                }
                out.println("    sourceSets {")
                out.println("        val commonMain by getting {")
                out.println("            dependencies {")
                printDependencies(commonDependencies, buildForIDE, appendix, out)
                out.println("            }")
                out.println("        }")
                if (enableJVM) {
                    out.println("        val jvmMain by getting {")
                    out.println("            dependencies {")
                    printDependencies(jvmDependencies, buildForIDE, appendix, out)
                    if (!buildLibrary && moduleArgs.codegenKSP) {
                        out.println("dependencies {")
                        if (buildForIDE) {
                            out.println("    implementation(project(\":src:luposdate3000_code_generator_ksp\"))")
                            out.println("    configurations[\"ksp\"].dependencies.add(project.dependencies.create(project(\":src:luposdate3000_code_generator_ksp\")))")
                        } else {
                            out.println("    implementation(\"luposdate3000:Luposdate3000_Code_Generator_KSP:0.0.1\")")
                            out.println("    configurations[\"ksp\"].dependencies.add(project.dependencies.create(\"luposdate3000:Luposdate3000_Code_Generator_KSP:0.0.1\"))")
                        }
                        out.println("}")
                    }
                    out.println("            }")
                    out.println("        }")
                }
                if (enableJS) {
                    out.println("        val jsMain by getting {")
                    out.println("            dependencies {")
                    printDependencies(jsDependencies, buildForIDE, appendix, out)
                    out.println("            }")
                    out.println("        }")
                }
                if (enableNative) {
                    out.println("        val ${moduleArgs.platform}Main by getting {")
                    out.println("            dependencies {")
                    printDependencies(nativeDependencies, buildForIDE, appendix, out)
                    out.println("            }")
                    out.println("        }")
                }
                out.println("    }")
                if (buildForIDE) {
                    if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"commonMain\"].resources.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}resources\")")
                    }
                    if (enableJVM) {
                        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
                            out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}kotlin\")")
                            out.println("    sourceSets[\"jvmMain\"].resources.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}resources\")")
                        }
                    }
                    if (enableJS) {
                        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
                            out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}kotlin\")")
                            out.println("    sourceSets[\"jsMain\"].resources.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}resources\")")
                        }
                    }
                    if (enableNative) {
                        out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}${moduleArgs.platform}Main${pathSeparatorEscaped}resources\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleArgs.moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}${moduleArgs.platform}Main${pathSeparatorEscaped}resources\")")
                        }
                    }
                } else {
                    out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"commonMain${pathSeparatorEscaped}kotlin\")")
                    out.println("    sourceSets[\"commonMain\"].resources.srcDir(\"commonMain${pathSeparatorEscaped}resources\")")
                    if (enableJVM) {
                        out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"jvmMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"jvmMain\"].resources.srcDir(\"jvmMain${pathSeparatorEscaped}resources\")")
                    }
                    if (enableJS) {
                        out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"jsMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"jsMain\"].resources.srcDir(\"jsMain${pathSeparatorEscaped}resources\")")
                    }
                    if (enableNative) {
                        out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"${moduleArgs.platform}Main${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"nativeMain${pathSeparatorEscaped}resources\")")
                        out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"${moduleArgs.platform}Main${pathSeparatorEscaped}resources\")")
                    }
                }
                out.println("}")
                if (!buildLibrary && moduleArgs.codegenKAPT) {
                    out.println("dependencies {")
                    if (buildForIDE) {
                        out.println("    \"kapt\"(project(\":src:luposdate3000_code_generator_kapt\")) // attention to the '\"' around kapt - otherwise it resolves to another function")
                    } else {
                        out.println("    \"kapt\"(\"luposdate3000:Luposdate3000_Code_Generator_KAPT:0.0.1\") // attention to the '\"' around kapt - otherwise it resolves to another function")
                    }
                    out.println("}")
                }
                if (buildForIDE && !buildLibrary) {
                    out.println("application{")
                    out.println("    mainClass.set(\"MainKt\")")
                    out.println("}")
                }
                if (enableJVM) {
                    out.println("tasks.register<proguard.gradle.ProGuardTask>(\"proguard\") {")
                    out.println("    dependsOn(\"build\")")
                    out.println("    injars(\"build/libs/${moduleArgs.moduleName}-jvm-0.0.1.jar\")")
                    out.println("    outjars(\"build/libs/${moduleArgs.moduleName}-jvm-0.0.1-pro.jar\")")
                    out.println("    val javaHome = System.getProperty(\"java.home\")")
                    out.println("    if (System.getProperty(\"java.version\").startsWith(\"1.\")) {")
                    out.println("        libraryjars(\"\$javaHome/lib/rt.jar\")")
                    out.println("    } else {")
                    out.println("        libraryjars(")
                    out.println("            mapOf(")
                    out.println("                \"jarfilter\" to \"!**.jar\",")
                    out.println("                \"filter\" to \"!module-info.class\"")
                    out.println("            ),")
                    out.println("            \"\$javaHome/jmods/java.base.jmod\"")
                    out.println("        )")
                    out.println("    }")
                    out.println("    File(\"external_jar_dependencies\").printWriter().use { out ->")
                    out.println("        for (f in configurations.getByName(\"jvmRuntimeClasspath\").resolve()) {")
                    out.println("            if (!\"\$f\".contains(\"luposdate3000\")) {")
                    out.println("                out.println(\"\$f\")")
                    out.println("            }")
                    out.println("        }")
                    out.println("    }")
                    out.println("    for (f in configurations.getByName(\"jvmCompileClasspath\").resolve()) {")
                    out.println("        libraryjars(files(\"\$f\"))")
                    out.println("    }")
                    out.println("    printusage()")
                    out.println("    forceprocessing()")
                    out.println("    optimizationpasses(5)")
                    out.println("    allowaccessmodification()")
                    out.println("    dontobfuscate()")
                    out.println("    printconfiguration(\"config.pro.generated\")")
                    out.println("    printmapping(\"build/mapping.txt\")")
                    out.println("    keep(\"@lupos.ProguardKeepAnnotation public class *\")")
                    out.println("    keepclassmembers(\"class * { @lupos.ProguardKeepAnnotation public *; }\")")
                    out.println("    keepclassmembers(\"class * { public <fields>; }\")")
                    out.println("    keep(\"public class MainKt { public static void main(java.lang.String[]); }\")")
                    out.println("}")
                }
            }
        }
        if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
            File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc$pathSeparator").mkdirs()
        }
        val typeAliasAll = mutableMapOf<String, Pair<String, String>>()
        val typeAliasUsed = mutableMapOf<String, Pair<String, String>>()
        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.${moduleArgs.moduleName}.SanityCheckOff")
        } else {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.${moduleArgs.moduleName}.SanityCheckOn")
        }
        if (moduleArgs.suspendMode == SuspendMode.Enable) {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.${moduleArgs.moduleName}.ParallelCoroutine")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.${moduleArgs.moduleName}.ParallelCoroutineJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.${moduleArgs.moduleName}.ParallelCoroutineCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.${moduleArgs.moduleName}.ParallelCoroutineQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.${moduleArgs.moduleName}.MyCoroutineLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.${moduleArgs.moduleName}.MyCoroutineReadWriteLock")
        } else {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.${moduleArgs.moduleName}.ParallelThread")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.s00misc.ParallelThreadJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.${moduleArgs.moduleName}.ParallelThreadCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.${moduleArgs.moduleName}.ParallelThreadQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.s00misc.MyThreadLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.${moduleArgs.moduleName}.MyThreadReadWriteLock")
        }
// selectively copy classes which are inlined from the inline internal module ->
        val classNamesRegex = Regex("\\s*([a-zA-Z0-9_]*)")
        val classNamesFound = mutableMapOf<String, MutableSet<String>>()
        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
            for (f in File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src").walk()) {
                if (f.isFile()) {
                    try {
                        f.forEachLine { it ->
                            var tmp = ""
                            var idxClass = it.indexOf("class")
                            if (idxClass >= 0) {
                                tmp = it.substring(idxClass + 5)
                            } else {
                                var idxObject = it.indexOf("object")
                                if (idxObject >= 0) {
                                    tmp = it.substring(idxObject + 6)
                                } else {
                                    var idxInterface = it.indexOf("interface")
                                    if (idxInterface >= 0) {
                                        tmp = it.substring(idxInterface + 9)
                                    }
                                }
                            }
                            if (tmp.length > 0) {
                                var tmp2 = classNamesRegex.find(tmp)!!.groupValues[1]
                                if (tmp2.startsWith("_")) {
                                    tmp2 = tmp2.substring(1)
                                }
                                if (tmp2.length > 0) {
                                    val tmp3 = classNamesFound[tmp2]
                                    if (tmp3 == null) {
                                        classNamesFound[tmp2] = mutableSetOf(f.toString())
                                    } else {
                                        tmp3.add(f.toString())
                                    }
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                } else {
                    if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
                        val f2 = File(f.toString().replace("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src", "src.generated"))
                        f2.mkdirs()
                    }
                }
            }
            if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
                if (copySelevtively) {
                    var changed = true
                    while (changed) {
                        changed = false
                        val classNamesUsed = mutableMapOf<String, MutableSet<String>>()
                        for (f in File("src.generated").walk()) {
                            if (f.isFile()) {
                                try {
                                    f.forEachLine { line ->
                                        val tmpSet = mutableListOf(line)
                                        val tmpAlias = mutableSetOf<String>()
                                        for ((k, v) in typeAliasAll) {
                                            if (line.indexOf(k) >= 0) {
                                                tmpSet.add(v.second)
                                                typeAliasUsed[k] = v
                                                tmpAlias.add(k)
                                            }
                                        }
                                        for (a in tmpAlias) {
                                            typeAliasAll.remove(a)
                                        }
                                        for (it in tmpSet) {
                                            val tmp = mutableSetOf<String>()
                                            for ((k, v) in classNamesFound) {
                                                if (it.indexOf(k) >= 0) {
                                                    classNamesUsed[k] = v
                                                    tmp.add(k)
                                                    changed = true
                                                }
                                            }
                                            for (k in tmp) {
                                                classNamesFound.remove(k)
                                            }
                                        }
                                    }
                                } catch (e: Throwable) {
                                    e.printStackTrace()
                                }
                            }
                        }
                        for ((k, v) in classNamesUsed) {
                            for (fname in v) {
                                val src = File(fname)
                                val dest = File(fname.replace("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src", "src.generated"))
                                copyFileWithReplacement(src, dest, mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"))
                                try {
                                    val src2 = File(fname.replace(".kt", "Alias.kt"))
                                    val dest2 = File(fname.replace("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src", "src.generated").replace(".kt", "Alias.kt"))
                                    copyFileWithReplacement(src2, dest2, mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"))
                                } catch (e: Throwable) {
                                }
                            }
                        }
                        println(classNamesUsed.keys)
                    }
                } else {
                    typeAliasUsed.putAll(typeAliasAll)
                    copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src"), "src.generated", mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"), pathSeparator)
                }
            } else {
                var configPathBase = "src${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleArgs.moduleFolder}${pathSeparator}src"
                var configPath = "${configPathBase}${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc"
                File(configPath).mkdirs()
                typeAliasUsed.putAll(typeAliasAll)
                try {
                    copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}commonMain"), ("${configPathBase}${pathSeparator}commonMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"), pathSeparator)
                } catch (e: Throwable) {
                }
                try {
                    copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jvmMain"), ("${configPathBase}${pathSeparator}jvmMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"), pathSeparator)
                } catch (e: Throwable) {
                }
                try {
                    copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jsMain"), ("${configPathBase}${pathSeparator}jsMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"), pathSeparator)
                } catch (e: Throwable) {
                }
                try {
                    copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}nativeMain"), ("${configPathBase}${pathSeparator}nativeMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.${moduleArgs.moduleName}"), pathSeparator)
                } catch (e: Throwable) {
                }
            }
        }
        var configFile: String
        if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
            configFile = "src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}Config-${moduleArgs.moduleName}.kt"
        } else {
            var configPathBase = "src${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleArgs.moduleFolder}${pathSeparator}src"
            var configPath = "${configPathBase}${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc"
            configFile = "${configPath}${pathSeparator}Config-${moduleArgs.moduleName}.kt"
        }
        println(typeAliasUsed.keys)
        println()
        // selectively copy classes which are inlined from the inline internal module <-
        val remainingArgs = mutableMapOf<String, String>()
        remainingArgs.putAll(moduleArgs.args)
        File(configFile).printWriter().use { out ->
            out.println("package lupos.s00misc")
            for ((k, v) in typeAliasUsed) {
                out.println("internal typealias ${v.first} = ${v.second}")
            }
            if (File("${moduleArgs.moduleFolder}${pathSeparator}configOptions").exists()) {
                File("${moduleArgs.moduleFolder}${pathSeparator}configOptions").forEachLine {
                    val opt = it.split(",")
                    if (opt.size == 4) {
                        var value = opt[3]
                        val v = remainingArgs[opt[0]]
                        if (v != null) {
                            value = v
                            remainingArgs.remove(opt[0])
                        }
                        if (opt[1] == "typealias") {
                            out.println("public ${opt[1]} ${opt[0]} = $value")
                        } else {
                            out.println("${opt[1]} ${opt[0]}: ${opt[2]} = $value")
                        }
                    }
                }
            }
        }
        if (remainingArgs.size > 0) {
            for ((k, v) in remainingArgs) {
                println("unknown argument '$k' = '$v'")
            }
            throw Exception("unknown arguments")
        }
        if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
            if (moduleArgs.inlineMode == InlineMode.Enable) {
                applyInlineEnable()
            } else {
                applyInlineDisable()
            }
            if (moduleArgs.suspendMode == SuspendMode.Enable) {
                applySuspendEnable()
            } else {
                applySuspendDisable()
            }
        }
        File("gradle.properties").copyTo(File("src.generated${pathSeparator}gradle.properties"))
        if (moduleArgs.dryMode == DryMode.Disable) {
            if (onWindows) {
                var path = System.getProperty("user.dir")
                File("$path${pathSeparator}gradle").copyRecursively(File("$path${pathSeparator}src.generated${pathSeparator}gradle"))
                File("gradlew.bat").copyTo(File("src.generated${pathSeparator}gradlew.bat"))
                if (enableJVM) {
                    runCommand(listOf("gradlew.bat", "proguard"), File("$path${pathSeparator}src.generated"))
                } else {
                    runCommand(listOf("gradlew.bat", "build"), File("$path${pathSeparator}src.generated"))
                }
                runCommand(listOf("gradlew.bat", "publishToMavenLocal"), File("$path${pathSeparator}src.generated"))
            } else if (onLinux) {
                if (enableJVM) {
                    runCommand(listOf("../gradlew", "proguard"), File("src.generated"))
                } else {
                    runCommand(listOf("../gradlew", "build"), File("src.generated"))
                }
                runCommand(listOf("../gradlew", "publishToMavenLocal"), File("src.generated"))
            }
        }
        try {
            File("build-cache${pathSeparator}bin$appendix").mkdirs()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        try {
            File(srcFolder).deleteRecursively()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        try {
            File(buildFolder).deleteRecursively()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        if (moduleArgs.dryMode == DryMode.Disable) {
            try {
                Files.move(Paths.get("src.generated${pathSeparator}build"), Paths.get(buildFolder))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (moduleArgs.ideaBuildfile == IntellijMode.Disable) {
            try {
                Files.move(Paths.get("src.generated"), Paths.get(srcFolder))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (moduleArgs.dryMode == DryMode.Disable) {
            if (enableJVM) {
                try {
                    Files.copy(Paths.get(buildFolder + "${pathSeparator}libs${pathSeparator}${moduleArgs.moduleName}-jvm-0.0.1.jar"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}-jvm.jar"), StandardCopyOption.REPLACE_EXISTING)
                    Files.copy(Paths.get(buildFolder + "${pathSeparator}libs${pathSeparator}${moduleArgs.moduleName}-jvm-0.0.1-pro.jar"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}-jvm-pro.jar"), StandardCopyOption.REPLACE_EXISTING)
                    Files.copy(Paths.get("$srcFolder${pathSeparator}external_jar_dependencies"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}-jvm.classpath"), StandardCopyOption.REPLACE_EXISTING)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (enableJS) {
                if (moduleArgs.modulePrefix == moduleArgs.moduleName) {
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleArgs.modulePrefix}${pathSeparator}kotlin${pathSeparator}${moduleArgs.modulePrefix}.js"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.modulePrefix}.js"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleArgs.modulePrefix}${pathSeparator}kotlin${pathSeparator}${moduleArgs.modulePrefix}.js.map"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.modulePrefix}.js.map"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                } else {
                    File("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}").mkdirs()
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleArgs.modulePrefix}${pathSeparator}kotlin${pathSeparator}${moduleArgs.modulePrefix}.js"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}${pathSeparator}${moduleArgs.modulePrefix}.js"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleArgs.modulePrefix}${pathSeparator}kotlin${pathSeparator}${moduleArgs.modulePrefix}.js.map"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}${moduleArgs.moduleName}${pathSeparator}${moduleArgs.modulePrefix}.js.map"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
            if (enableNative) {
                if (moduleArgs.platform == "linuxX64") {
                    try {
                        if (buildLibrary) {
                            if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${moduleArgs.modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${moduleArgs.modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                            } else {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${moduleArgs.modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${moduleArgs.modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                            }
                        } else {
                            if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseExecutable${pathSeparator}${moduleArgs.moduleName}.kexe"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
                            } else {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugExecutable${pathSeparator}${moduleArgs.moduleName}.kexe"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib${moduleArgs.moduleName}-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
                            }
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
        }
        for (f in listOf(File(File(srcFolder), "gradle.log"), File(File(srcFolder), "gradle.err"))) {
            if (f.exists()) {
                f.forEachLine {
                    println(it)
                }
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        for (f in listOf(File(File("src.generated"), "gradle.log"), File(File("src.generated"), "gradle.err"))) {
            if (f.exists()) {
                f.forEachLine {
                    println(it)
                }
            }
        }
        throw e
    }
}

public fun runCommand(command: List<String>, workingDir: File) {
    val p = ProcessBuilder(command)
        .directory(workingDir)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        //        .redirectOutput(Redirect.to(File(File("src.generated"), "gradle.log"))) //this disables the warnings?!?
        //        .redirectError(Redirect.to(File(File("src.generated"), "gradle.err"))) //this disables the warnings?!?
        .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("executing '$command' failed")
    }
}

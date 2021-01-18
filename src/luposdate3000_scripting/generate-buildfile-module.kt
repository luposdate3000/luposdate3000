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
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
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
val compilerVersion = "1.5.255-SNAPSHOT"
val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")
private fun printDependencies(dependencies: Set<String>, buildForIDE: Boolean, appendix: String, out: PrintWriter) {
    for (d in dependencies) {
        if (d.startsWith("luposdate3000")) {
            if (buildForIDE) {
                out.println("                implementation(project(\":src:${d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()}\"))")
            } else {
                out.println("                compileOnly(\"$d\")")
            }
        } else if(d.startsWith("npm(")){
            out.println("                implementation($d)")
}else{
            out.println("                implementation(\"$d\")")
}
    }
}
private fun copyFileWithReplacement(src: File, dest: File, replacement: Map<String, String>) {
    dest.printWriter().use { out ->
        src.forEachLine { it ->
            var s = it
            for ((k, v)in replacement) {
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
public fun createBuildFileForModule(args: Array<String>) {
    val onWindows = System.getProperty("os.name").contains("Windows")
    val pathSeparator: String
    if (onWindows) {
        pathSeparator = "\\"
    } else {
        pathSeparator = "/"
    }
    var moduleName = ""
    var moduleFolder = ""
    var modulePrefix = ""
    var platform = "linuxX64"
    var inlineMode = InlineMode.Enable
    var suspendMode = SuspendMode.Enable
    var releaseMode = ReleaseMode.Enable
    var target = TargetMode.All
    var dryMode = DryMode.Disable
    var ideaBuildfile = IntellijMode.Disable
    for (arg in args) {
        when {
            arg == "--inline" -> inlineMode = InlineMode.Enable
            arg == "--noinline" -> inlineMode = InlineMode.Disable
            arg == "--suspend" -> suspendMode = SuspendMode.Enable
            arg == "--nosuspend" -> suspendMode = SuspendMode.Disable
            arg == "--release" -> releaseMode = ReleaseMode.Enable
            arg == "--debug" -> releaseMode = ReleaseMode.Disable
            arg == "--fastJVM" -> target = TargetMode.JVM
            arg == "--fastJS" -> target = TargetMode.JS
            arg == "--fastNative" -> target = TargetMode.Native
            arg == "--dry" -> dryMode = DryMode.Enable
            arg == "--idea" -> {
                dryMode = DryMode.Enable
                ideaBuildfile = IntellijMode.Enable
            }
            arg.startsWith("--module=") -> moduleName = arg.substring("--module=".length)
            arg.startsWith("--src=") -> moduleFolder = arg.substring("--src=".length).replace("/", pathSeparator).replace("\\", pathSeparator)
            arg.startsWith("--platform=") -> platform = arg.substring("--platform=".length)
            arg.startsWith("--prefix=") -> modulePrefix = arg.substring("--prefix=".length).replace("/", pathSeparator).replace("\\", pathSeparator)
        }
    }
    if (moduleName == "") {
        throw Exception("you must specify a moduleName '--module=xyz'")
    }
    if (moduleFolder == "") {
        moduleFolder = "src${pathSeparator}${moduleName.toLowerCase()}"
    }
    if (modulePrefix == "") {
        modulePrefix = moduleName
    }
    if (!validPlatforms.contains(platform)) {
        throw Exception("unsupported platform $platform")
    }
    if (moduleFolder.startsWith("/")) { // TODO same for Windows
        throw Exception("only relative paths allowed")
    }
    createBuildFileForModule(moduleName, moduleFolder, modulePrefix, platform, releaseMode, suspendMode, inlineMode, dryMode, target, ideaBuildfile, args)
}
public fun createBuildFileForModule(moduleName: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, target: TargetMode, ideaBuildfile: IntellijMode, args: Array<String> = arrayOf<String>()) {
    createBuildFileForModule(moduleName, moduleName, releaseMode, suspendMode, inlineMode, dryMode, target, ideaBuildfile, args)
}
public fun createBuildFileForModule(moduleName: String, modulePrefix: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, target: TargetMode, ideaBuildfile: IntellijMode, args: Array<String> = arrayOf<String>()) {
    val onWindows = System.getProperty("os.name").contains("Windows")
    val pathSeparator: String
    if (onWindows) {
        pathSeparator = "\\"
    } else {
        pathSeparator = "/"
    }
    createBuildFileForModule(moduleName, modulePrefix, "src${pathSeparator}${moduleName.toLowerCase()}", releaseMode, suspendMode, inlineMode, dryMode, target, ideaBuildfile, args)
}
public fun createBuildFileForModule(moduleName: String, modulePrefix: String, moduleFolder: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, target: TargetMode, ideaBuildfile: IntellijMode, args: Array<String> = arrayOf<String>()) {
    createBuildFileForModule(moduleName, moduleFolder, modulePrefix, "linuxX64", releaseMode, suspendMode, inlineMode, dryMode, target, ideaBuildfile, args)
}
public fun createBuildFileForModule(moduleName_: String, moduleFolder: String, modulePrefix: String, platform: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode2: DryMode, target: TargetMode, ideaBuildfile: IntellijMode, args: Array<String> = arrayOf<String>()) {
    try {
        var dryMode: DryMode
        if (dryMode2 == DryMode.Enable || ideaBuildfile == IntellijMode.Enable) {
            dryMode = DryMode.Enable
        } else {
            dryMode = DryMode.Disable
        }
        var appendix = ""
        if (suspendMode == SuspendMode.Enable) {
            appendix += "_Coroutines"
        } else {
            appendix += "_Threads"
        }
        if (inlineMode == InlineMode.Enable) {
            appendix += "_Inline"
        } else {
            appendix += "_NoInline"
        }
        if (releaseMode == ReleaseMode.Enable) {
            appendix += "_Release"
        } else {
            appendix += "_Debug"
        }
        val moduleName = moduleName_
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
        var enableJVM = target == TargetMode.All || target == TargetMode.JVM
        var enableJS = target == TargetMode.All || target == TargetMode.JS
        var enableNative = target == TargetMode.All || target == TargetMode.Native
        if (File("$moduleFolder/disableTarget").exists()) {
            File("$moduleFolder/disableTarget").forEachLine {
                when (it) {
                    "jvm" -> enableJVM = false
                    "js" -> {
                        enableJS = false
                    }
                    platform, "native" -> enableNative = false
                }
            }
        }
        if (!(enableJVM || enableJS || enableNative)) {
            return
        }
        val buildLibrary = modulePrefix != "Luposdate3000_Main"
        println("generating buildfile for $moduleName")
        var shortFolder = ".$pathSeparator$moduleName"
        shortFolder = shortFolder.substring(shortFolder.lastIndexOf(pathSeparator) + 1)
        File("src.generated").deleteRecursively()
        val buildFolder = "build-cache${pathSeparator}build_$shortFolder$appendix"
        println("buildFolder :: $buildFolder")
        val srcFolder = "build-cache${pathSeparator}src_$shortFolder$appendix"
        if (ideaBuildfile == IntellijMode.Disable) {
            File("src.generated").mkdirs()
            val p = Paths.get("$moduleFolder${pathSeparator}src")
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
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("js.*Main", "jsMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public "), pathSeparator)
                        }
                    } else if (f.startsWith("native")) {
                        if (enableNative) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("native.*Main", "nativeMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public "), pathSeparator)
                        }
                    } else if (f.startsWith(platform)) {
                        if (enableNative) {
                            copyFilesWithReplacement(tmp, "src.generated$pathSeparator" + f.replace("$platform.*Main", "${platform}Main"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public "), pathSeparator)
                        }
                    }
                }
            }
            File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos").mkdirs()
            File("src.generated${pathSeparator}settings.gradle").printWriter().use { out ->
                out.println("pluginManagement {")
                out.println("    repositories {")
                out.println("        mavenLocal()")
                out.println("        gradlePluginPortal()")
                out.println("    }")
                out.println("}")
                out.println("rootProject.name = \"$moduleName\"") // maven-artifactID
            }
        }
        val commonDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-common:$compilerVersion")
        if (!moduleName.startsWith("Luposdate3000_Shared")) {
            commonDependencies.add("luposdate3000:Luposdate3000_Shared:0.0.1")
        }
        if (File("${moduleFolder}${pathSeparator}commonDependencies").exists()) {
            File("${moduleFolder}${pathSeparator}commonDependencies").forEachLine {
                if (it.length > 0) {
                    commonDependencies.add(it)
                }
            }
        }
        val jvmDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$compilerVersion")
        if (File("${moduleFolder}${pathSeparator}jvmDependencies").exists()) {
            File("${moduleFolder}${pathSeparator}jvmDependencies").forEachLine {
                if (it.length > 0) {
                    jvmDependencies.add(it)
                }
            }
        }
        val jsDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-js:$compilerVersion")
        if (File("${moduleFolder}${pathSeparator}jsDependencies").exists()) {
            File("${moduleFolder}${pathSeparator}jsDependencies").forEachLine {
                if (it.length > 0) {
                    jsDependencies.add(it)
                }
            }
        }
        val nativeDependencies = mutableSetOf<String>()
        if (File("${moduleFolder}${pathSeparator}nativeDependencies").exists()) {
            File("${moduleFolder}${pathSeparator}nativeDependencies").forEachLine {
                if (it.length > 0) {
                    nativeDependencies.add(it)
                }
            }
        }
        for (filename in listOf("src.generated${pathSeparator}build.gradle.kts", "${moduleFolder}${pathSeparator}build.gradle.kts")) {
            var buildForIDE = filename != "src.generated${pathSeparator}build.gradle.kts"
            if (ideaBuildfile == IntellijMode.Enable && !buildForIDE) {
                continue
            }
            if (ideaBuildfile == IntellijMode.Disable && buildForIDE) {
                continue
            }
            File(filename).printWriter().use { out ->
                out.println("import org.jetbrains.kotlin.gradle.tasks.KotlinCompile")
                out.println("buildscript {")
                out.println("    repositories {")
                out.println("        jcenter()")
                out.println("        google()")
                out.println("        mavenLocal()")
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
                            out.println("    evaluationDependsOn(\":src:${d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()}\")")
                        }
                    }
                }
                out.println("plugins {")
                out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"${compilerVersion}\"")
                if (buildForIDE && !buildLibrary) {
                    out.println("    application")
                }
                out.println("}")
                out.println("repositories {")
                out.println("    jcenter()")
                out.println("    google()")
                out.println("    mavenLocal()")
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
                    out.println("        moduleName = \"${modulePrefix}\"")
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
                    out.println("    $platform(\"$platform\") {")
                    out.println("        compilations.forEach{")
                    out.println("            it.kotlinOptions {")
                    out.println("                freeCompilerArgs += \"-Xopt-in=kotlin.RequiresOptIn\"")
                    out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("            }")
                    out.println("        }")
                    out.println("        binaries {")
                    if (buildLibrary) {
                        if (releaseMode == ReleaseMode.Enable) {
                            out.println("            sharedLib (listOf(RELEASE)){")
                            out.println("                baseName = \"${modulePrefix}\"")
                            out.println("            }")
                        } else {
                            out.println("            sharedLib (listOf(DEBUG)){")
                            out.println("                baseName = \"${modulePrefix}\"")
                            out.println("            }")
                        }
                    } else {
                        if (releaseMode == ReleaseMode.Enable) {
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
                    out.println("        val ${platform}Main by getting {")
                    out.println("            dependencies {")
                    printDependencies(nativeDependencies, buildForIDE, appendix, out)
                    out.println("            }")
                    out.println("        }")
                }
                out.println("    }")
                if (buildForIDE) {
                    if (!moduleName.startsWith("Luposdate3000_Shared_Inline")) {
                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}kotlin\")")
                    }
                    if (enableJVM) {
                        if (!moduleName.startsWith("Luposdate3000_Shared_Inline")) {
                            out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}kotlin\")")
                        }
                    }
                    if (enableJS) {
                        if (!moduleName.startsWith("Luposdate3000_Shared_Inline")) {
                            out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}kotlin\")")
                        }
                    }
                    if (enableNative) {
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                        if (!moduleName.startsWith("Luposdate3000_Shared_Inline")) {
                            out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                            out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleFolder}${pathSeparatorEscaped}src${pathSeparatorEscaped}${platform}Main${pathSeparatorEscaped}kotlin\")")
                        }
                    }
                } else {
                    out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"commonMain${pathSeparatorEscaped}kotlin\")")
                    if (enableJVM) {
                        out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"jvmMain${pathSeparatorEscaped}kotlin\")")
                    }
                    if (enableJS) {
                        out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"jsMain${pathSeparatorEscaped}kotlin\")")
                    }
                    if (enableNative) {
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"${platform}Main${pathSeparatorEscaped}kotlin\")")
                    }
                }
                out.println("}")
                if (buildForIDE && !buildLibrary) {
                    out.println("application{")
                    out.println("    mainClass.set(\"MainKt\")")
                    out.println("}")
                }
                if (enableJVM) {
                    out.println("tasks.register<proguard.gradle.ProGuardTask>(\"proguard\") {")
                    out.println("    dependsOn(\"build\")")
                    out.println("    injars(\"build/libs/$moduleName-jvm-0.0.1.jar\")")
                    out.println("    outjars(\"build/libs/$moduleName-jvm-0.0.1-pro.jar\")")
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
                    out.println("    for(f in configurations.getByName(\"jvmCompileClasspath\").resolve()){")
                    out.println("        libraryjars(files(\"\$f\"))")
                    out.println("    }")
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
        if (ideaBuildfile == IntellijMode.Disable) {
            File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc$pathSeparator").mkdirs()
        }
        val typeAliasAll = mutableMapOf<String, Pair<String, String>>()
        val typeAliasUsed = mutableMapOf<String, Pair<String, String>>()
        if (releaseMode == ReleaseMode.Enable) {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.$moduleName.SanityCheckOff")
        } else {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.$moduleName.SanityCheckOn")
        }
        if (suspendMode == SuspendMode.Enable) {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.$moduleName.ParallelCoroutine")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.$moduleName.ParallelCoroutineJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.$moduleName.ParallelCoroutineCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.$moduleName.ParallelCoroutineQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.$moduleName.MyCoroutineLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.$moduleName.MyCoroutineReadWriteLock")
        } else {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.$moduleName.ParallelThread")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.$moduleName.ParallelThreadJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.$moduleName.ParallelThreadCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.$moduleName.ParallelThreadQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.$moduleName.MyThreadLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.$moduleName.MyThreadReadWriteLock")
        }
// selectively copy classes which are inlined from the inline internal module ->
        val classNamesRegex = Regex("\\s*([a-zA-Z0-9_]*)")
        val classNamesFound = mutableMapOf<String, MutableSet<String>>()
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
                if (ideaBuildfile == IntellijMode.Disable) {
                    val f2 = File(f.toString().replace("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src", "src.generated"))
                    f2.mkdirs()
                }
            }
        }
        var configFile: String
        if (ideaBuildfile == IntellijMode.Disable) {
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
                        copyFileWithReplacement(src, dest, mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"))
                        try {
                            val src2 = File(fname.replace(".kt", "Alias.kt"))
                            val dest2 = File(fname.replace("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src", "src.generated").replace(".kt", "Alias.kt"))
                            copyFileWithReplacement(src2, dest2, mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"))
                        } catch (e: Throwable) {
                        }
                    }
                }
                println(classNamesUsed.keys)
            }
            configFile = "src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}Config-$moduleName.kt"
        } else {
            var configPathBase = "src${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleFolder}${pathSeparator}src"
            var configPath = "${configPathBase}${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc"
            File(configPath).mkdirs()
            typeAliasUsed.putAll(typeAliasAll)
            try {
                copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}commonMain"), ("${configPathBase}${pathSeparator}commonMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"), pathSeparator)
            } catch (e: Throwable) {
            }
            try {
                copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jvmMain"), ("${configPathBase}${pathSeparator}jvmMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"), pathSeparator)
            } catch (e: Throwable) {
            }
            try {
                copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jsMain"), ("${configPathBase}${pathSeparator}jsMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"), pathSeparator)
            } catch (e: Throwable) {
            }
            try {
                copyFilesWithReplacement(("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}nativeMain"), ("${configPathBase}${pathSeparator}nativeMain"), mapOf(" public " to " @lupos.ProguardKeepAnnotation public ", "lupos.modulename" to "lupos.$moduleName"), pathSeparator)
            } catch (e: Throwable) {
            }
            configFile = "${configPath}${pathSeparator}Config-$moduleName.kt"
        }
        println(typeAliasUsed.keys)
        println()
// selectively copy classes which are inlined from the inline internal module <-
        File(configFile).printWriter().use { out ->
            out.println("package lupos.s00misc")
            for ((k, v) in typeAliasUsed) {
                out.println("internal typealias ${v.first} = ${v.second}")
            }
            if (File("${moduleFolder}${pathSeparator}configOptions").exists()) {
                File("${moduleFolder}${pathSeparator}configOptions").forEachLine {
                    val opt = it.split(",")
                    if (opt.size == 4) {
                        var value = opt[3]
                        for (a in args) {
                            if (a.startsWith("--${opt[0]}") && a.contains("=")) {
                                value = a.substring(a.indexOf("=") + 1)
                            }
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
        if (ideaBuildfile == IntellijMode.Disable) {
            if (inlineMode == InlineMode.Enable) {
                applyInlineEnable()
            } else {
                applyInlineDisable()
            }
            if (suspendMode == SuspendMode.Enable) {
                applySuspendEnable()
            } else {
                applySuspendDisable()
            }
        }
        File("gradle.properties").copyTo(File("src.generated${pathSeparator}gradle.properties"))
        if (dryMode == DryMode.Disable) {
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
        if (dryMode == DryMode.Disable) {
            try {
                Files.move(Paths.get("src.generated${pathSeparator}build"), Paths.get(buildFolder))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (ideaBuildfile == IntellijMode.Disable) {
            try {
                Files.move(Paths.get("src.generated"), Paths.get(srcFolder))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (dryMode == DryMode.Disable) {
            if (enableJVM) {
                try {
                    Files.copy(Paths.get(buildFolder + "${pathSeparator}libs${pathSeparator}$moduleName-jvm-0.0.1.jar"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$moduleName-jvm.jar"), StandardCopyOption.REPLACE_EXISTING)
                    Files.copy(Paths.get(buildFolder + "${pathSeparator}libs${pathSeparator}$moduleName-jvm-0.0.1-pro.jar"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$moduleName-jvm-pro.jar"), StandardCopyOption.REPLACE_EXISTING)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (enableJS) {
                if (modulePrefix == moduleName) {
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${modulePrefix}${pathSeparator}kotlin${pathSeparator}$modulePrefix.js"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$modulePrefix.js"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${modulePrefix}${pathSeparator}kotlin${pathSeparator}$modulePrefix.js.map"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$modulePrefix.js.map"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                } else {
                    File("build-cache${pathSeparator}bin$appendix${pathSeparator}$moduleName").mkdirs()
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${modulePrefix}${pathSeparator}kotlin${pathSeparator}$modulePrefix.js"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$moduleName${pathSeparator}$modulePrefix.js"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                    try {
                        Files.copy(Paths.get(buildFolder + "${pathSeparator}js${pathSeparator}packages${pathSeparator}${modulePrefix}${pathSeparator}kotlin${pathSeparator}$modulePrefix.js.map"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}$moduleName${pathSeparator}$modulePrefix.js.map"), StandardCopyOption.REPLACE_EXISTING)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
            if (enableNative) {
                if (platform == "linuxX64") {
                    try {
                        if (buildLibrary) {
                            if (releaseMode == ReleaseMode.Enable) {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib$modulePrefix.so"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                            } else {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib$modulePrefix.so"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                            }
                        } else {
                            if (releaseMode == ReleaseMode.Enable) {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseExecutable${pathSeparator}$moduleName.kexe"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
                            } else {
                                Files.copy(Paths.get(buildFolder + "${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugExecutable${pathSeparator}$moduleName.kexe"), Paths.get("build-cache${pathSeparator}bin$appendix${pathSeparator}lib$moduleName-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
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

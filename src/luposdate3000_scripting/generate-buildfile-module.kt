import java.io.File
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

enum class FastMode {
    JVM, JS, NATIVE, Disable
}

enum class IntellijMode {
    Enable, Disable
}
    val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")

fun createBuildFileForModule(args: Array<String>) {
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
    var fastMode = FastMode.Disable
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
            arg == "--fastJVM" -> fastMode = FastMode.JVM
            arg == "--fastJS" -> fastMode = FastMode.JS
            arg == "--fastNATIVE" -> fastMode = FastMode.NATIVE
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
    if (moduleFolder.startsWith("/")) {//TODO same for Windows
        throw Exception("only relative paths allowed")
    }
    createBuildFileForModule(moduleName, moduleFolder, modulePrefix, platform, releaseMode, suspendMode, inlineMode, dryMode, fastMode, ideaBuildfile, args)
}

fun createBuildFileForModule(moduleName: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, fastMode: FastMode, ideaBuildfile: IntellijMode) {
    createBuildFileForModule(moduleName, moduleName, releaseMode, suspendMode, inlineMode, dryMode, fastMode, ideaBuildfile)
}

fun createBuildFileForModule(moduleName: String, modulePrefix: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, fastMode: FastMode, ideaBuildfile: IntellijMode) {
    val onWindows = System.getProperty("os.name").contains("Windows")
    val pathSeparator: String
    if (onWindows) {
        pathSeparator = "\\"
    } else {
        pathSeparator = "/"
    }
    createBuildFileForModule(moduleName, modulePrefix, "src${pathSeparator}${moduleName.toLowerCase()}", releaseMode, suspendMode, inlineMode, dryMode, fastMode, ideaBuildfile)
}

fun createBuildFileForModule(moduleName: String, modulePrefix: String, moduleFolder: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode: DryMode, fastMode: FastMode, ideaBuildfile: IntellijMode) {
    createBuildFileForModule(moduleName, moduleFolder, modulePrefix, "linuxX64", releaseMode, suspendMode, inlineMode, dryMode, fastMode, ideaBuildfile, arrayOf<String>())
}

fun createBuildFileForModule(moduleName: String, moduleFolder: String, modulePrefix: String, platform: String, releaseMode: ReleaseMode, suspendMode: SuspendMode, inlineMode: InlineMode, dryMode2: DryMode, fastMode: FastMode, ideaBuildfile: IntellijMode, args: Array<String>) {
    var dryMode: DryMode
    if (dryMode2 == DryMode.Enable || ideaBuildfile == IntellijMode.Enable) {
        dryMode = DryMode.Enable
    } else {
        dryMode = DryMode.Disable
    }
    val onWindows = System.getProperty("os.name").contains("Windows")
    val onLinux = !onWindows //TODO this is not correct ...
    val pathSeparator: String
    val pathSeparatorEscaped: String
    if (onWindows) {
        pathSeparator = "\\"
        pathSeparatorEscaped = "\\\\"
    } else {
        pathSeparator = "/"
        pathSeparatorEscaped = "/"
    }

var enableJVM=fastMode == FastMode.Disable || fastMode == FastMode.JVM
var enableJS=fastMode == FastMode.Disable || fastMode == FastMode.JS
var enableNATIVE=fastMode == FastMode.Disable || fastMode == FastMode.NATIVE

if(File("$moduleFolder/disableTarget").exists()){
File("$moduleFolder/disableTarget").forEachLine{
when(it){
"jvm"->enableJVM=false
"js"->enableJS=false
platform,"native"->enableNATIVE=false
}
}
}
if(!(enableJVM||enableJS||enableNATIVE)){
return
}
    val buildLibrary = modulePrefix != "Luposdate3000_Main"
    println("generating buildfile for $moduleName")
    var shortFolder = "./${moduleFolder}"//TODO does this work as intended on windows
    shortFolder = shortFolder.substring(shortFolder.lastIndexOf(pathSeparator) + 1)
    File("src.generated").deleteRecursively()
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
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("common.*Main", "commonMain")))
                } else if (f.startsWith("jvm")) {
     if (enableJVM) {
               File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("jvm.*Main", "jvmMain")))
}
                } else if (f.startsWith("js")) {
if (enableJS) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("js.*Main", "jsMain")))
}
                } else if (f.startsWith("native")) {
if (enableNATIVE) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("native.*Main", "${platform}Main")))
}
                } else if (f.startsWith(platform)) {
if (enableNATIVE) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("${platform}.*Main", "${platform}Main")))
}
                }
            }
        }
    }
    File("settings.gradle").printWriter().use { out ->
        out.println("pluginManagement {")
        out.println("    repositories {")
        out.println("        mavenLocal()")
        out.println("        gradlePluginPortal()")
        out.println("    }")
        out.println("}")
        out.println("rootProject.name = \"$moduleName\"")//maven-artifactID
    }
    val commonDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT")
    if (moduleName != "Luposdate3000_Shared" && moduleName != "Luposdate3000_Shared_Inline") {
        commonDependencies.add("luposdate3000:Luposdate3000_Shared:0.0.1")
    }
    if (File("${moduleFolder}${pathSeparator}commonDependencies").exists()) {
        File("${moduleFolder}${pathSeparator}commonDependencies").forEachLine {
            if (it.length > 0) {
                commonDependencies.add(it)
            }
        }
    }
    val jvmDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.255-SNAPSHOT")
    if (File("${moduleFolder}${pathSeparator}jvmDependencies").exists()) {
        File("${moduleFolder}${pathSeparator}jvmDependencies").forEachLine {
            if (it.length > 0) {
                jvmDependencies.add(it)
            }
        }
    }
    val jsDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-js:1.4.255-SNAPSHOT")
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
    for (filename in listOf("build.gradle.kts", "${moduleFolder}${pathSeparator}build.gradle.kts")) {
        var buildForIDE = filename != "build.gradle.kts"
        if (ideaBuildfile == IntellijMode.Enable && !buildForIDE) {
            continue
        }
        if (ideaBuildfile == IntellijMode.Disable && buildForIDE) {
            continue
        }
        File(filename).printWriter().use { out ->
            out.println("import org.jetbrains.kotlin.gradle.tasks.KotlinCompile")
            out.println("tasks.withType<KotlinCompile>().all {")
            out.println("    kotlinOptions.jvmTarget = \"1.8\"")//kotlinOptions.jvmTarget = \"14\"
            //see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
            //or kotlinc -X
            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-param-assertions\"")
            out.println("    kotlinOptions.freeCompilerArgs += \"-Xuse-ir\"")
            out.println("    kotlinOptions.freeCompilerArgs += \"-Xnew-inference\"")
            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-receiver-assertions\"")
            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-call-assertions\"")
            out.println("}")
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
            out.println("        classpath(\"org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.255-SNAPSHOT\")")
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
            out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"1.4.255-SNAPSHOT\"")
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
            out.println("group = \"luposdate3000\"")//maven-groupID
            out.println("version = \"0.0.1\"")//maven-version
            out.println("apply(plugin = \"maven-publish\")")
            out.println("kotlin {")
            if (enableJVM) {
                out.println("    jvm()")
            }
            if (enableJS) {
                out.println("    js {")
                out.println("        moduleName = \"${moduleName}\"")
                out.println("        browser {")
                out.println("        }")
                out.println("        nodejs {")
                out.println("        }")
                out.println("    }")
            }
            if (enableNATIVE) {
                out.println("    $platform(\"$platform\") {")
                out.println("        binaries {")
                if (buildLibrary) {
                    if (releaseMode == ReleaseMode.Enable) {
                        out.println("            sharedLib (listOf(RELEASE)){")
                    } else {
                        out.println("            sharedLib (listOf(DEBUG)){")
                    }
                    out.println("                baseName = \"${modulePrefix}\"")
                    out.println("            }")
                } else {
                    if (releaseMode == ReleaseMode.Enable) {
                        out.println("            executable(listOf(RELEASE)) {")
                    } else {
                        out.println("            executable(listOf(DEBUG)) {")
                    }
                    out.println("            }")
                }
                out.println("        }")
                out.println("    }")
            }
            out.println("    sourceSets {")
            out.println("        val commonMain by getting {")
            out.println("            dependencies {")
            for (d in commonDependencies) {
                if (d.startsWith("luposdate3000")) {
                    if (buildForIDE) {
                        out.println("                implementation(project(\":src:${d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()}\"))")
                    } else {
                        out.println("                compileOnly(\"$d\")")
                    }
                } else {
                    out.println("                implementation(\"$d\")")
                }
            }
            out.println("            }")
            out.println("        }")
            if (enableJVM) {
                out.println("        val jvmMain by getting {")
                out.println("            dependencies {")
                for (d in jvmDependencies) {
                    if (d.startsWith("luposdate3000")) {
                        out.println("                compileOnly(\"$d\")")
                    } else {
                        out.println("                implementation(\"$d\")")
                    }
                }
                out.println("            }")
                out.println("        }")
            }
            if (enableJS) {
                out.println("        val jsMain by getting {")
                out.println("            dependencies {")
                for (d in jsDependencies) {
                    if (d.startsWith("luposdate3000")) {
                        out.println("                compileOnly(\"$d\")")
                    } else {
                        out.println("                implementation(\"$d\")")
                    }
                }
                out.println("            }")
                out.println("        }")
            }
            if (enableNATIVE) {
                out.println("        val ${platform}Main by getting {")
                out.println("            dependencies {")
                for (d in nativeDependencies) {
                    if (d.startsWith("luposdate3000")) {
                        out.println("                compileOnly(\"$d\")")
                    } else {
                        out.println("                implementation(\"$d\")")
                    }
                }
                out.println("            }")
                out.println("        }")
            }
            out.println("    }")
            if (buildForIDE) {
                if (moduleName != "Luposdate3000_Shared_Inline") {
                    out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleName}${pathSeparatorEscaped}src${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}kotlin\")")
                }
                if (enableJVM) {
                    if (moduleName != "Luposdate3000_Shared_Inline") {
                        out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleName}${pathSeparatorEscaped}src${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}kotlin\")")
                    }
                }
                if (enableJS) {
                    if (moduleName != "Luposdate3000_Shared_Inline") {
                        out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleName}${pathSeparatorEscaped}src${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}kotlin\")")
                    }
                }
                if (enableNATIVE) {
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                    if (moduleName != "Luposdate3000_Shared_Inline") {
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleName}${pathSeparatorEscaped}src${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparatorEscaped}xxx_generated_xxx${pathSeparatorEscaped}${moduleName}${pathSeparatorEscaped}src${pathSeparatorEscaped}${platform}Main${pathSeparatorEscaped}kotlin\")")
                    }
                }
            } else {
                out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}kotlin\")")
                if (enableJVM) {
                    out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}kotlin\")")
                }
                if (enableJS) {
                    out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}kotlin\")")
                }
                if (enableNATIVE) {
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}${platform}Main${pathSeparatorEscaped}kotlin\")")
                }
            }
            out.println("}")
            if (buildForIDE && !buildLibrary) {
                out.println("application{")
                out.println("    mainClass.set(\"MainKt\")")
                out.println("}")
            }
/*            if (!buildLibrary) {
//https://play.kotlinlang.org/hands-on/Introduction%20to%20Kotlin%20Multiplatform/03_multiplatform_jvm
                out.println("val run by tasks.creating(JavaExec::class) {")
                out.println("    group = \"application\"")
                out.println("    main = \"MainKt\"")
                out.println("    workingDir = project.rootDir")
                out.println("    kotlin {")
                out.println("        val main = targets[\"jvm\"].compilations[\"main\"]")
                out.println("        dependsOn(main.compileAllTaskName)")
                out.println("        classpath({ main.output.allOutputs.files},{ configurations[\"jvmRuntimeClasspath\"]})")
                out.println("    }")
                out.println("    File(\"\${project.rootDir}${pathSeparatorEscaped}log\").mkdirs()")
                out.println("}")
            }
*/
        }
    }
    if (ideaBuildfile == IntellijMode.Disable) {
        File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}").mkdirs()
    }
    val typeAliasAll = mutableMapOf<String, Pair<String, String>>()
    val typeAliasUsed = mutableMapOf<String, Pair<String, String>>()
    if (releaseMode == ReleaseMode.Enable) {
        typeAliasAll["SanityCheck"] = Pair("SanityCheck", "SanityCheckOff")
    } else {
        typeAliasAll["SanityCheck"] = Pair("SanityCheck", "SanityCheckOn")
    }
    if (suspendMode == SuspendMode.Enable) {
        typeAliasAll["Parallel"] = Pair("Parallel", "ParallelCoroutine")
        typeAliasAll["ParallelJob"] = Pair("ParallelJob", "ParallelCoroutineJob")
        typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "ParallelCoroutineCondition")
        typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "ParallelCoroutineQueue<T>")
        typeAliasAll["MyLock"] = Pair("MyLock", "MyCoroutineLock")
        typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "MyCoroutineReadWriteLock")
    } else {
        typeAliasAll["Parallel"] = Pair("Parallel", "ParallelThread")
        typeAliasAll["ParallelJob"] = Pair("ParallelJob", "ParallelThreadJob")
        typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "ParallelThreadCondition")
        typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "ParallelThreadQueue<T>")
        typeAliasAll["MyLock"] = Pair("MyLock", "MyThreadLock")
        typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "MyThreadReadWriteLock")
    }
//selectively copy classes which are inlined from the inline module ->
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
                        val tmp2 = classNamesRegex.find(tmp)!!.groupValues[1]
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
                    src.copyTo(dest)
                }
            }
            println(classNamesUsed.keys)
        }
    }
    println(typeAliasUsed.keys)
    println()
    var configFile: String
    if (ideaBuildfile == IntellijMode.Enable) {
        var configPathBase = "src${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}src"
        var configPath = "${configPathBase}${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc"
        File(configPath).mkdirs()
        typeAliasUsed.putAll(typeAliasAll)
        try {
            File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}commonMain").copyRecursively(File("${configPathBase}${pathSeparator}commonMain"))
        } catch (e: Throwable) {
        }
        try {
            File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jvmMain").copyRecursively(File("${configPathBase}${pathSeparator}jvmMain"))
        } catch (e: Throwable) {
        }
        try {
            File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}jsMain").copyRecursively(File("${configPathBase}${pathSeparator}jsMain"))
        } catch (e: Throwable) {
        }
        try {
            File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}src${pathSeparator}nativeMain").copyRecursively(File("${configPathBase}${pathSeparator}nativeMain"))
        } catch (e: Throwable) {
        }
        configFile = "${configPath}${pathSeparator}Config-${moduleName}.kt"
    } else {
        configFile = "src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}Config-${moduleName}.kt"
    }
//selectively copy classes which are inlined from the inline module <-
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
                        out.println("${opt[1]} ${opt[0]} = $value")
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
    if (dryMode == DryMode.Disable) {
        if (onWindows) {
            var path = System.getProperty("user.dir")
            runCommand(listOf("./gradlew.bat", "build"), File(path))
            runCommand(listOf("./gradlew.bat", "publishToMavenLocal"), File(path))
        } else if (onLinux) {
            runCommand(listOf("./gradlew", "build"), File("."))
            runCommand(listOf("./gradlew", "publishToMavenLocal"), File("."))
        }
    }
    try {
        File(".gradle").deleteRecursively()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    try {
        File("build-cache").mkdirs()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    try {
        File("build-cache${pathSeparator}bin").mkdirs()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    try {
        File("build-cache${pathSeparator}src-${shortFolder}").deleteRecursively()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    try {
        File("build-cache${pathSeparator}build-${shortFolder}").deleteRecursively()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    if (dryMode == DryMode.Disable) {
        try {
            Files.move(Paths.get("build"), Paths.get("build-cache${pathSeparator}build-${shortFolder}"))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    if (ideaBuildfile == IntellijMode.Disable) {
        try {
            Files.move(Paths.get("src.generated"), Paths.get("build-cache${pathSeparator}src-${shortFolder}"))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    if (dryMode == DryMode.Disable) {
        if (enableJVM) {
            try {
                Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}libs${pathSeparator}${moduleName}-jvm-0.0.1.jar"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}${moduleName}-jvm.jar"), StandardCopyOption.REPLACE_EXISTING)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (enableJS) {
            try {
                Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleName}${pathSeparator}kotlin${pathSeparator}${moduleName}.js"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}${moduleName}.js"), StandardCopyOption.REPLACE_EXISTING)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            try {
                Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}js${pathSeparator}packages${pathSeparator}${moduleName}${pathSeparator}kotlin${pathSeparator}${moduleName}.js.map"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}${moduleName}.js.map"), StandardCopyOption.REPLACE_EXISTING)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (enableNATIVE) {
            if (platform == "linuxX64") {
                try {
                    if (buildLibrary) {
                        if (releaseMode == ReleaseMode.Enable) {
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                        } else {
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                        }
                    } else {
                        if (releaseMode == ReleaseMode.Enable) {
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseExecutable${pathSeparator}${moduleName}.kexe"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
                        } else {
                            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugExecutable${pathSeparator}${moduleName}.kexe"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.kexe"), StandardCopyOption.REPLACE_EXISTING)
                        }
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
    }
}

fun runCommand(command: List<String>, workingDir: File) {
    val p = ProcessBuilder(command)
            .directory(workingDir)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("executing '$command' failed")
    }
}

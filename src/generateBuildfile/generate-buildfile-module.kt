import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

fun createBuildFileForModule(args: Array<String>) {
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

    var moduleName = ""
    var moduleFolder = ""
    var modulePrefix = ""
    var platform = "linuxX64"
    var inlineMode = InlineMode.Enable
    var suspendMode = SuspendMode.Enable
    var releaseMode = true
    var fastMode = false
    var dryMode = false
    var ideaBuildfile = false
    var buildLibrary = true
    for (arg in args) {
        when {
            arg == "--inline" -> inlineMode = InlineMode.Enable
            arg == "--noinline" -> inlineMode = InlineMode.Disable
            arg == "--suspend" -> suspendMode = SuspendMode.Enable
            arg == "--nosuspend" -> suspendMode = SuspendMode.Disable
            arg == "--release" -> releaseMode = true
            arg == "--debug" -> releaseMode = false
            arg == "--fast" -> fastMode = true
            arg == "--dry" -> dryMode = true
            arg == "--idea" -> {
                dryMode = true
                ideaBuildfile = true
            }
            arg.startsWith("--module=") -> moduleName = arg.substring("--module=".length)
            arg.startsWith("--src=") -> moduleFolder = arg.substring("--src=".length).replace("/", pathSeparator).replace("\\", pathSeparator)
            arg.startsWith("--platform=") -> platform = arg.substring("--platform=".length)
            arg.startsWith("--prefix=") -> modulePrefix = arg.substring("--prefix=".length).replace("/", pathSeparator).replace("\\", pathSeparator)
        }
    }
    if (moduleFolder == "") {
        moduleFolder = "src${pathSeparator}${moduleName.toLowerCase()}"
    }
    if (modulePrefix == "") {
        modulePrefix = moduleName
    } else if (modulePrefix == "Luposdate3000_Main") {
        buildLibrary = false
    }
    println("generating buildfile for $moduleName")
    val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")
    if (!validPlatforms.contains(platform)) {
        throw Exception("unsupported platform $platform")
    }
    if (moduleFolder.startsWith("/")) {//TODO same for Windows
        throw Exception("only relative paths allowed")
    }
    var shortFolder = "./${moduleFolder}"//TODO does this work as intended on windows
    shortFolder = shortFolder.substring(shortFolder.lastIndexOf(pathSeparator) + 1)
    File("src.generated").deleteRecursively()
    if (!ideaBuildfile) {
        File("src.generated").mkdirs()
        val p = Paths.get(moduleFolder)
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
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("jvm.*Main", "jvmMain")))
                } else if (f.startsWith("js")) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("js.*Main", "jsMain")))
                } else if (f.startsWith("native")) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("native.*Main", "${platform}Main")))
                } else if (f.startsWith(platform)) {
                    File(tmp).copyRecursively(File("src.generated${pathSeparator}" + f.replace("${platform}.*Main", "${platform}Main")))
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
        if (ideaBuildfile && !buildForIDE) {
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
            out.println("    jvm()")
            if (!fastMode) {
                out.println("    js {")
                out.println("        moduleName = \"${moduleName}\"")
                out.println("        browser {")
                out.println("        }")
                out.println("        nodejs {")
                out.println("        }")
                out.println("    }")
                out.println("    $platform(\"$platform\") {")
                out.println("        binaries {")
                if (buildLibrary) {
                    if (releaseMode) {
                        out.println("            sharedLib (listOf(RELEASE)){")
                    } else {
                        out.println("            sharedLib (listOf(DEBUG)){")
                    }
                    out.println("                baseName = \"${modulePrefix}\"")
                    out.println("            }")
                } else {
                    if (releaseMode) {
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
                        out.println("                compileOnly(project(\":src:${d.substring("luposdate3000:".length, d.lastIndexOf(":")).toLowerCase()}\"))")
                    } else {
                        out.println("                compileOnly(\"$d\")")
                    }
                } else {
                    out.println("                implementation(\"$d\")")
                }
            }
            out.println("            }")
            out.println("        }")
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
            if (!fastMode) {
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
                out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"commonMain${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"..${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}commonMain${pathSeparator}kotlin\")")
                out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"jvmMain${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"..${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}jvmMain${pathSeparator}kotlin\")")
                if (!fastMode) {
                    out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"jsMain${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"..${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}jsMain${pathSeparator}kotlin\")")
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"nativeMain${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}nativeMain${pathSeparator}kotlin\")")
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"${platform}Main${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"..${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}${pathSeparator}${platform}Main${pathSeparator}kotlin\")")
                }
            } else {
                out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}commonMain${pathSeparatorEscaped}kotlin\")")
                out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}jvmMain${pathSeparatorEscaped}kotlin\")")
                if (!fastMode) {
                    out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}jsMain${pathSeparatorEscaped}kotlin\")")
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}nativeMain${pathSeparatorEscaped}kotlin\")")
                    out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated${pathSeparatorEscaped}${platform}Main${pathSeparatorEscaped}kotlin\")")
                }
            }
            out.println("}")
        }
    }
    if (!ideaBuildfile) {
        File("src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}").mkdirs()
    }
    val typeAliasAll = mutableMapOf<String, Pair<String, String>>()
    val typeAliasUsed = mutableMapOf<String, Pair<String, String>>()
    if (releaseMode) {
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
    for (f in File("src${pathSeparator}luposdate3000_shared_inline").walk()) {
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
            if (!ideaBuildfile) {
                val f2 = File(f.toString().replace("src${pathSeparator}luposdate3000_shared_inline", "src.generated"))
                f2.mkdirs()
            }
        }
    }
    if (!ideaBuildfile) {
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
                    val dest = File(fname.replace("src${pathSeparator}luposdate3000_shared_inline", "src.generated"))
                    src.copyTo(dest)
                }
            }
            println(classNamesUsed.keys)
        }
    }
    println(typeAliasUsed.keys)
    println()
var configFile:String
    if (ideaBuildfile) {
var configPathBase="src${pathSeparator}xxx_generated_xxx${pathSeparator}${moduleName}"
var configPath="${configPathBase}${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc"
File(configPath).mkdirs()


 File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}commonMain").copyRecursively(File("${configPathBase}${pathSeparator}commonMain"))
 File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}jvmMain").copyRecursively(File("${configPathBase}${pathSeparator}jvmMain"))
 File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}jsMain").copyRecursively(File("${configPathBase}${pathSeparator}jsMain"))
 File("src${pathSeparator}luposdate3000_shared_inline${pathSeparator}nativeMain").copyRecursively(File("${configPathBase}${pathSeparator}nativeMain"))



configFile="${configPath}${pathSeparator}Config-${moduleName}.kt"
}else{
configFile="src.generated${pathSeparator}commonMain${pathSeparator}kotlin${pathSeparator}lupos${pathSeparator}s00misc${pathSeparator}Config-${moduleName}.kt"
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
if (!ideaBuildfile) {
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
    if (!dryMode) {
        if (onWindows) {
            var path = System.getProperty("user.dir")
            runCommand(listOf("gradle.bat", "build"), File(path))
            runCommand(listOf("gradle.bat", "publishToMavenLocal"), File(path))
        } else if (onLinux) {
            runCommand(listOf("gradle", "build"), File("."))
            runCommand(listOf("gradle", "publishToMavenLocal"), File("."))
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
    if (!dryMode) {
        try {
            Files.move(Paths.get("build"), Paths.get("build-cache${pathSeparator}build-${shortFolder}"))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    if (!ideaBuildfile) {
        try {
            Files.move(Paths.get("src.generated"), Paths.get("build-cache${pathSeparator}src-${shortFolder}"))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    try {
        val localMavenRepositoryRoot = System.getProperty("user.home") + "${pathSeparator}.m2${pathSeparator}repository${pathSeparator}luposdate3000${pathSeparator}"
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    if (!dryMode) {
        try {
            Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}libs${pathSeparator}${moduleName}-jvm-0.0.1.jar"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}${moduleName}-jvm.jar"), StandardCopyOption.REPLACE_EXISTING)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
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
        if (platform == "linuxX64") {
            try {
                if (buildLibrary) {
                    if (releaseMode) {
                        Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                        Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}releaseShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                    } else {
                        Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${modulePrefix}.so"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.so"), StandardCopyOption.REPLACE_EXISTING)
                        Files.copy(Paths.get("build-cache${pathSeparator}build-${shortFolder}${pathSeparator}bin${pathSeparator}linuxX64${pathSeparator}debugShared${pathSeparator}lib${modulePrefix}_api.h"), Paths.get("build-cache${pathSeparator}bin${pathSeparator}lib${moduleName}-linuxX64.h"), StandardCopyOption.REPLACE_EXISTING)
                    }
                } else {
                    if (releaseMode) {
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

fun runCommand(command: List<String>, workingDir: File) {
    ProcessBuilder(command)
            .directory(workingDir)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
}

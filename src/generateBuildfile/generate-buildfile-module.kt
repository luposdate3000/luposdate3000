import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun createBuildFileForModule(args: Array<String>) {
    val moduleName = args[0]
    val moduleFolder = args[1]
    val platform = args[2]
    val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")
    if (!validPlatforms.contains(platform)) {
        throw Exception("unsupported platform $platform")
    }
    if (moduleFolder.startsWith("/")) {
        throw Exception("only relative paths allowed")
    }
    var inlineMode = InlineMode.Enable
    var suspendMode = SuspendMode.Enable
    var releaseMode = true
    if (args.contains("--inline")) {
        inlineMode = InlineMode.Enable
    }
    if (args.contains("--noinline")) {
        inlineMode = InlineMode.Disable
    }
    if (args.contains("--suspend")) {
        suspendMode = SuspendMode.Enable
    }
    if (args.contains("--nosuspend")) {
        suspendMode = SuspendMode.Disable
    }
    if (args.contains("--release")) {
        releaseMode = true
    }
    if (args.contains("--debug")) {
        releaseMode = false
    }
    File("src.generated").deleteRecursively()
    File("src.generated").mkdirs()
    File("src/luposdate3000_shared_inline").copyRecursively(File("src.generated"))
    val p = Paths.get(moduleFolder)
    Files.walk(p, 1).forEach { it ->
        val tmp = it.toString()
        if (tmp.length > p.toString().length) {
            println("found tmp :: $tmp $p")
            val idx = tmp.lastIndexOf("/")
            val f: String
            if (idx >= 0) {
                f = tmp.substring(idx + 1)
            } else {
                f = tmp
            }
            println("use tmp as :: $f")
            if (f.startsWith("common")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("common.*Main", "commonMain")))
            } else if (f.startsWith("jvm")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("jvm.*Main", "jvmMain")))
            } else if (f.startsWith("js")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("js.*Main", "jsMain")))
            } else if (f.startsWith("native")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("native.*Main", "${platform}Main")))
            } else if (f.startsWith(platform)) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("${platform}.*Main", "${platform}Main")))
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
    File("build.gradle.kts").printWriter().use { out ->
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
        out.println("plugins {")
        out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"1.4.255-SNAPSHOT\"")
        out.println("}")
        out.println("repositories {")
        out.println("    mavenLocal()")
        out.println("    mavenCentral()")
        out.println("}")
        out.println("group = \"luposdate3000\"")//maven-groupID
        out.println("version = \"0.0.1\"")//maven-version
        out.println("apply(plugin = \"maven-publish\")")
        out.println("kotlin {")
        out.println("    jvm()")
        out.println("    js {")
        out.println("        browser {")
        out.println("        }")
        out.println("        nodejs {")
        out.println("        }")
        out.println("    }")
        out.println("    $platform(\"$platform\") {")
        out.println("        binaries {")
        out.println("            sharedLib {")
        out.println("                baseName = \"${moduleName}\"")
        out.println("            }")
        out.println("        }")
        out.println("    }")
        out.println("    sourceSets {")
        out.println("        val commonMain by getting {")
        out.println("            dependencies {")
        val commonDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT")
        if (moduleName != "Luposdate3000_Shared") {
            commonDependencies.add("luposdate3000:Luposdate3000_Shared:0.0.1")
        }
        if (File("${moduleFolder}/commonDependencies").exists()) {
            File("${moduleFolder}/commonDependencies").forEachLine {
                if (it.length > 0) {
                    commonDependencies.add(it)
                }
            }
        }
        for (d in commonDependencies) {
            out.println("                implementation(\"$d\")")
        }
        out.println("            }")
        out.println("        }")
        out.println("        val jvmMain by getting {")
        out.println("            dependencies {")
        val jvmDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.255-SNAPSHOT")
        if (File("${moduleFolder}/jvmDependencies").exists()) {
            File("${moduleFolder}/jvmDependencies").forEachLine {
                if (it.length > 0) {
                    jvmDependencies.add(it)
                }
            }
        }
        for (d in jvmDependencies) {
            out.println("                implementation(\"$d\")")
        }
        out.println("            }")
        out.println("        }")
        out.println("        val jsMain by getting {")
        out.println("            dependencies {")
        val jsDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-js:1.4.255-SNAPSHOT")
        if (File("${moduleFolder}/jsDependencies").exists()) {
            File("${moduleFolder}/jsDependencies").forEachLine {
                if (it.length > 0) {
                    jsDependencies.add(it)
                }
            }
        }
        for (d in jsDependencies) {
            out.println("                implementation(\"$d\")")
        }
        out.println("            }")
        out.println("        }")
        out.println("        val ${platform}Main by getting {")
        out.println("            dependencies {")
        val nativeDependencies = mutableSetOf<String>()
        if (File("${moduleFolder}/nativeDependencies").exists()) {
            File("${moduleFolder}/nativeDependencies").forEachLine {
                if (it.length > 0) {
                    nativeDependencies.add(it)
                }
            }
        }
        for (d in nativeDependencies) {
            out.println("                implementation(\"$d\")")
        }
        out.println("            }")
        out.println("        }")
        out.println("    }")
        out.println("   sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated/commonMain/kotlin\")")
        out.println("   sourceSets[\"jvmMain\"].kotlin.srcDir(\"src.generated/jvmMain/kotlin\")")
        out.println("   sourceSets[\"jsMain\"].kotlin.srcDir(\"src.generated/jsMain/kotlin\")")
        out.println("   sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated/nativeMain/kotlin\")")
        out.println("   sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated/${platform}Main/kotlin\")")
        out.println("}")
    }
    File("src.generated/commonMain/kotlin/lupos/s00misc/").mkdirs()
    File("src.generated/commonMain/kotlin/lupos/s00misc/Config.kt").printWriter().use { out ->
        out.println("package lupos.s00misc")
        if (releaseMode) {
            out.println("internal typealias SanityCheck = SanityCheckOff")
        } else {
            out.println("internal typealias SanityCheck = SanityCheckOn")
        }
        if (File("${moduleFolder}/configOptions").exists()) {
            File("${moduleFolder}/configOptions").forEachLine {
                val opt = it.split(",")
                if (opt.size == 4) {
                    var value = opt[3]
                    for (a in args) {
                        if (a.startsWith("--${opt[0]}") && a.contains("=")) {
                            value = a.substring(a.indexOf("=") + 1)
                        }
                    }
                    out.println("${opt[1]} ${opt[0]}: ${opt[2]} = $value")
                }
            }
        }
    }
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

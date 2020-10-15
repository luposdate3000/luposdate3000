import java.nio.file.Files
import java.nio.file.Paths
import java.io.File

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
    File("src.generated").deleteRecursively()
    File("src.generated").mkdirs()
    var hadCommon = false
    var hadJVM = false
    var hadJS = false
    var hadNative = false
    File("src/luposdate3000_interfaces").copyRecursively(File("src.generated"))
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
                hadCommon = true
            } else if (f.startsWith("jvm")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("jvm.*Main", "jvmMain")))
                hadJVM = true
            } else if (f.startsWith("js")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("js.*Main", "jsMain")))
                hadJS = true
            } else if (f.startsWith("native")) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("native.*Main", "${platform}Main")))
                hadNative = true
            } else if (f.startsWith(platform)) {
                File(tmp).copyRecursively(File("src.generated/" + f.replace("${platform}.*Main", "${platform}Main")))
                hadNative = true
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
        if (hadCommon || hadJVM) {
            out.println("    jvm()")
        }
        if (hadCommon || hadJS) {
            out.println("    js {")
            out.println("        browser {")
            out.println("        }")
            out.println("        nodejs {")
            out.println("        }")
            out.println("    }")
        }
        if (hadCommon || hadNative) {
            out.println("    $platform(\"$platform\") {")
            out.println("        binaries {")
            out.println("            sharedLib {")
            out.println("                baseName = \"${moduleName}\"")
            out.println("            }")
            out.println("        }")
            out.println("    }")
        }
        out.println("    sourceSets {")
        out.println("        val commonMain by getting {")
        out.println("            dependencies {")
        val commonDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT")
        if (File("${moduleFolder}/commonDependencies").exists()) {
            File("${moduleFolder}/commonDependencies").forEachLine {
 if(it.length>0){
               commonDependencies.add(it)
}
            }
        }
        for (d in commonDependencies) {
            out.println("                implementation(\"$d\")")
        }
        out.println("            }")
        out.println("        }")
        if (hadJVM) {
            out.println("        val jvmMain by getting {")
            out.println("            dependencies {")
            val jvmDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.255-SNAPSHOT")
            if (File("${moduleFolder}/jvmDependencies").exists()) {
                File("${moduleFolder}/jvmDependencies").forEachLine {
if(it.length>0){
                    jvmDependencies.add(it)
}
                }
            }
            for (d in jvmDependencies) {
                out.println("                implementation(\"$d\")")
            }
            out.println("            }")
            out.println("        }")
        }
        if (hadJS) {
            out.println("        val jsMain by getting {")
            out.println("            dependencies {")
            val jsDependencies = mutableSetOf("org.jetbrains.kotlin:kotlin-stdlib-js:1.4.255-SNAPSHOT")
            if (File("${moduleFolder}/jsDependencies").exists()) {
                File("${moduleFolder}/jsDependencies").forEachLine {
if(it.length>0){
                    jsDependencies.add(it)
}
                }
            }
            for (d in jsDependencies) {
                out.println("                implementation(\"$d\")")
            }
            out.println("            }")
            out.println("        }")
        }
        if (hadNative) {
            out.println("        val ${platform}Main by getting {")
            out.println("            dependencies {")
            val nativeDependencies = mutableSetOf<String>()
            if (File("${moduleFolder}/nativeDependencies").exists()) {
                File("${moduleFolder}/nativeDependencies").forEachLine {
if(it.length>0){
                    nativeDependencies.add(it)
}
                }
            }
            for (d in nativeDependencies) {
                out.println("                implementation(\"$d\")")
            }
            out.println("            }")
            out.println("        }")
        }
        out.println("    }")
        out.println("   sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated/commonMain/kotlin\")")
        if (hadJVM) {
            out.println("   sourceSets[\"jvmMain\"].kotlin.srcDir(\"src.generated/jvmMain/kotlin\")")
        }
        if (hadJS) {
            out.println("   sourceSets[\"jsMain\"].kotlin.srcDir(\"src.generated/jsMain/kotlin\")")
        }
        if (hadNative) {
            out.println("   sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated/${platform}Main/kotlin\")")
        }
        out.println("}")
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

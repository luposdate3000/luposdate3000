import java.nio.file.Paths
import java.nio.file.Files
fun createBuildFileForModule(moduleName: String, moduleFolder: String, platform: String, args: Array<String>) {
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
    if (args.contains("--suspend")) {
        suspendMode = SuspendMode.Disable
    }
    File("src.generated").deleteRecursively()
    File("src.generated").mkdirs()
    var hadCommon = false
    var hadJVM = false
    var hadJS = false
    var hadNative = false
File("src/luposdate3000_interfaces").copyRecursively(File("src.generated"))
val p=Paths.get(moduleFolder)
    Files.walk(p, 1).forEach { it ->
        val tmp = it.toString()
if(tmp.length>p.toString().length){
println("found tmp :: $tmp $p")
        val idx = tmp.lastIndexOf("/")
        val f: String
        if (idx >= 0) {
            f = tmp.substring(idx+1)
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
    File("build.gradle.kts").printWriter().use { out ->
        out.println("buildscript {")
        out.println("    repositories {")
        out.println("        mavenLocal()")
        out.println("        mavenCentral()")
        out.println("    }")
        out.println("}")
        out.println("plugins {")
        out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"1.4.255-SNAPSHOT\"")
        out.println("}")
        out.println("repositories {")
        out.println("    mavenLocal()")
        out.println("    mavenCentral()")
        out.println("}")
        out.println("group \"luposdate3000.${moduleName}\"")
        out.println("version \"0.0.1\"")
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
            out.println("                implementation(\"org.jetbrains.kotlin:kotlin-stdlib-common:1.4.255-SNAPSHOT\")")
            out.println("            }")
            out.println("        }")
        if (hadJVM) {
            out.println("        val jvmMain by getting {")
            out.println("            dependencies {")
            out.println("                implementation(\"org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.255-SNAPSHOT\")")
            out.println("            }")
            out.println("        }")
        }
        if (hadJS) {
            out.println("        val jsMain by getting {")
            out.println("            dependencies {")
            out.println("                implementation(\"org.jetbrains.kotlin:kotlin-stdlib-js:1.4.255-SNAPSHOT\")")
            out.println("            }")
            out.println("        }")
        }
        if (hadNative) {
            out.println("        val ${platform}Main by getting {")
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

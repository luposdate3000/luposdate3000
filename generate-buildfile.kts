#!/bin/kscript
import java.io.File


abstract class ChooseableOption(val label: String, val internalID: String) : Comparable<ChooseableOption> {
    companion object {
        val mapLabel = mutableMapOf<String, ChooseableOption>()
        val mapID = mutableMapOf<String, ChooseableOption>()
        operator fun invoke(internalID: String): ChooseableOption {
            var res = mapID[internalID]
            if (res != null) {
                return res
            }
            return ChooseableOptionSimple(internalID)
        }
    }

    init {
        mapLabel[label] = this
        mapID[internalID] = this
    }

    override fun equals(other: Any?) = other is ChooseableOption && internalID == other.internalID
    override fun hashCode() = internalID.hashCode()
    override operator fun compareTo(other: ChooseableOption) = internalID.compareTo(other.internalID)
}

class ChooseableOptionSymbolic(label: String, internalID: String) : ChooseableOption(label, internalID)
class ChooseableOptionDirectory(label: String, val directory: String) : ChooseableOption(label, directory) {
    constructor(directory: String) : this(directory, directory)
}

class ChooseableOptionSimple(label: String) : ChooseableOption(label, label)

class ChooseableGroup(val name: String) : Comparable<ChooseableGroup> {
    override fun equals(other: Any?) = other is ChooseableGroup && name == other.name
    override fun hashCode() = name.hashCode()
    override operator fun compareTo(other: ChooseableGroup) = name.compareTo(other.name)
}

var allChoicesString = ""
var choicesCount = 0
fun presentChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
    when (options.size) {
        0 -> throw Exception("script error")
        1 -> return options[0]
        else -> {
            println("selecting ${group.name}: choose one of ${options.map { it.label }}")
            while (true) {
                val input = if (choicesCount < args.size) {
                    args[choicesCount++]
                } else
                    readLine()
                if (input != null) {
                    if (options.map { it.label }.contains(input)) {
                        allChoicesString += "_$input"
                        for (o in options) {
                            if (o.label == input)
                                return o
                        }
                        require(false)
                    }
                    try {
                        val i = input.toInt()
                        if (i < options.size) {
                            allChoicesString += "_${options[i]}"
                            return options[i]
                        } else
                            throw Exception("")
                    } catch (e: Throwable) {
                    }
                }
            }
        }
    }
}

/*
	options ending with Target are just Symbolic helpers to manage dependencies
		all other options are used directly as source folders
*/
val options = mapOf<ChooseableGroup, List<ChooseableOption>>(
        ChooseableGroup("Launch Type") to listOf(
                ChooseableOptionDirectory("SparqlTestSuite", "commonS00LaunchSparqlTestSuiteMain"),
                ChooseableOptionDirectory("BinaryTests", "commonS00LaunchBinaryTestsMain"),
                ChooseableOptionDirectory("Endpoint", "commonS00LaunchEndpointMain"),
                ChooseableOptionDirectory("Benchmark", "commonS00LaunchBenchmarkMain"),
                ChooseableOptionDirectory("JavaFuzz", "jvmS00LaunchJavaFuzzMain"),
                ChooseableOptionDirectory("WarnkeFuzz", "jvmS00LaunchWarnkeFuzzMain")
        ),
        ChooseableGroup("Sanity Checks") to listOf(
                ChooseableOptionDirectory("On", "commonS00SanityChecksOnMain"),
                ChooseableOptionDirectory("Off", "commonS00SanityChecksOffMain")
        ),
        ChooseableGroup("ResultFlow") to listOf(
                ChooseableOptionDirectory("Fast", "commonS00ResultFlowFastMain"),
                ChooseableOptionDirectory("Tests", "commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableGroup("Execution") to listOf(
                ChooseableOptionDirectory("Sequential", "commonS00ExecutionSequentialMain"),
                ChooseableOptionDirectory("Parallel", "commonS00ExecutionParallelMain")
        ),
        ChooseableGroup("Buffer Manager Type") to listOf(
                ChooseableOptionDirectory("Heap", "commonS01HeapMain"),
                ChooseableOptionDirectory("MemoryMapped", "jvmS01BufferMemoryMappedMain"),
                ChooseableOptionDirectory("MemoryMappedUnsafe", "jvmS01BufferMemoryMappedUnsafeMain"),
                ChooseableOptionDirectory("RandomAccess", "jvmS01BufferRandomAccessMain"),
                ChooseableOptionDirectory("Unsafe", "jvmS01BufferUnsafeMain")
        ),
        ChooseableGroup("Dictionary") to listOf(
                ChooseableOptionDirectory("Int", "commonS03DictionaryIntArrayMain")
        ),
        ChooseableGroup("Triple Store") to listOf(
                ChooseableOptionDirectory("MapMapList", "commonS05MapMapListMain"),
                ChooseableOptionDirectory("SingleList", "commonS05SingleListMain")
        ),
        ChooseableGroup("P2P") to listOf(
                ChooseableOptionDirectory("Dummy", "commonS12DummyMain"),
                ChooseableOptionDirectory("Local", "commonS12LocalMain")
        ),
        ChooseableGroup("Server implementation") to listOf(
                ChooseableOptionDirectory("Korio", "jvmS14ServerKorioMain"),
                ChooseableOptionDirectory("none", "commonS14ServerNoneMain")
        ),
        ChooseableGroup("Client implementation") to listOf(
                ChooseableOptionDirectory("Korio", "jvmS14ClientKorioMain"),
                ChooseableOptionDirectory("none", "commonS14ClientNoneMain"),
                ChooseableOptionSymbolic("Ktor", "jvmS14ClientKtorTarget"),
                ChooseableOptionSymbolic("Ktor", "nativeS14ClientKtorTarget")
        ),
        ChooseableGroup("Triple Store Interface") to listOf(
                ChooseableOptionDirectory("Local", "commonS15LocalMain"),
                ChooseableOptionDirectory("Distributed", "commonS15DistributedMain")
        ),
        ChooseableGroup("Include Jena Wrapper") to listOf(
                ChooseableOptionDirectory("On", "jvmS00WrapperJenaOnMain"),
                ChooseableOptionDirectory("Off", "commonS00WrapperJenaOffMain")
        )
)
val conflicts = listOf(
        setOf("commonS12LocalMain", "commonS15DistributedMain"),
        setOf("commonS12LocalMain", "jvmS14ServerKorioMain"),
        setOf("commonS12LocalMain", "jvmS14ClientKorioMain"),
        setOf("commonS12LocalMain", "jvmS14ClientKtorTarget", "nativeS14ClientKtorTarget"),
        setOf("commonS00LaunchEndpointMain", "commonS00ResultFlowExecuteTestsMain"),
        setOf("commonS00LaunchSparqlTestSuiteMain", "commonS00ResultFlowExecuteTestsMain")
)
val platformPrefix = mapOf(
        "jvm" to listOf("common", "jvm"),
        "linuxX64" to listOf("common", "linuxX64", "native"),
        "macosX64" to listOf("common", "macosX64", "native"),
        "mingw64" to listOf("common")
)
val additionalSources = mapOf(
        ChooseableOption("commonS00ResultFlowExecuteTestsMain") to listOf(
                ChooseableOption("commonTest")
        ),
        ChooseableOption("commonS01HeapMain") to listOf(
                ChooseableOption("commonS01BufferMainmemoryMain")
        ),
        ChooseableOption("jvmS01BufferMemoryMappedMain") to listOf(
                ChooseableOption("commonS01BufferDiskbasedMain")
        ),
        ChooseableOption("jvmS01BufferMemoryMappedUnsafeMain") to listOf(
                ChooseableOption("commonS01BufferDiskbasedMain"),
                ChooseableOption("jvmS01BufferUnsafeHelperMain")
        ),
        ChooseableOption("jvmS01BufferUnsafeMain") to listOf(
                ChooseableOption("commonS01BufferMainmemoryMain"),
                ChooseableOption("jvmS01BufferUnsafeHelperMain")
        ),
        ChooseableOption("jvmS01BufferRandomAccessMain") to listOf(
                ChooseableOption("commonS01BufferDiskbasedMain")
        ),
        ChooseableOption("linuxX64Main") to listOf(
                ChooseableOption("nativeMain")
        ),
        ChooseableOption("macosX64Main") to listOf(
                ChooseableOption("nativeMain")
        ),
        ChooseableOption("commonS00LaunchBinaryTestsMain") to listOf(
                ChooseableOption("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("jvmS00LaunchJavaFuzzMain") to listOf(
                ChooseableOption("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("jvmS00LaunchWarnkeFuzzMain") to listOf(
                ChooseableOption("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("jvmS14ClientKtorTarget") to listOf(
                ChooseableOption("commonS14ClientKtorMain")
        ),
        ChooseableOption("nativeS14ClientKtorTarget") to listOf(
                ChooseableOption("commonS14ClientKtorMain")
        )
)
val ktorVersion = presentChoice(ChooseableGroup("ktor-version"), listOf(ChooseableOption("1.3.1"))).label
val kotlinVersion = presentChoice(ChooseableGroup("kotlin-version"), listOf(ChooseableOption("1.3.70"))).label
val platform = presentChoice(ChooseableGroup("Platform"), platformPrefix.keys.toList().map { ChooseableOption(it) }).label
val dependencies = mapOf(
        ChooseableOption("jvmS00WrapperJenaOnMain") to listOf(
                "org.apache.jena:jena-core:3.14.0",
                "org.apache.jena:jena-arq:3.14.0"
        ),
        ChooseableOption("commonMain") to listOf(
                "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion",
                "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion",
                "com.benasher44:uuid:0.0.7",
                "com.soywiz.korlibs.krypto:krypto:1.9.1"
        ),
        ChooseableOption("jvmMain") to listOf(
                "com.soywiz.korlibs.klock:klock:1.7.0",
                "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion",
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion",
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"
        ),
        ChooseableOption("jvmS00LaunchJavaFuzzMain") to listOf(
                "dev.fuzzit.javafuzz:core:1.22",
                "org.jacoco:org.jacoco.agent:0.8.5:runtime"
        ),
        ChooseableOption("nativeMain") to listOf(
                "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3"
        ),
        ChooseableOption("jvmS14ServerKorioMain") to listOf(
                "com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT"
        ),
        ChooseableOption("jvmS14ClientKorioMain") to listOf(
                "com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT"
        ),
        ChooseableOption("jvmS14ClientKtorTarget") to listOf(
                "io.ktor:ktor-client-logging-jvm:$ktorVersion",
                "io.ktor:ktor-client-core-jvm:$ktorVersion"
        ),
        ChooseableOption("nativeS14ClientKtorTarget") to listOf(
                "io.ktor:ktor-client-core-native:$ktorVersion",
                "io.ktor:ktor-client-logging-native:$ktorVersion"
        ),
        ChooseableOption("commonS14ClientKtorMain") to listOf(
                "io.ktor:ktor-client-core:$ktorVersion",
                "io.ktor:ktor-client-cio:$ktorVersion",
                "io.ktor:ktor-client-logging:$ktorVersion",
                "org.slf4j:slf4j-nop:1.7.25"
        ),
        ChooseableOption("linuxX64Main") to listOf(
                "com.soywiz.korlibs.klock:klock-linuxx64:1.8.7"
        ),
        ChooseableOption("macosX64Main") to listOf(
                "com.soywiz.korlibs.klock:klock-macosx64:1.8.9"
        )
)
val cinterops = mapOf(
        ChooseableOption("nativeMain") to listOf(
                "dirent",
                "stdio",
                "unistd"
        )
)
val sourceFolders = mutableSetOf<ChooseableOption>(ChooseableOptionDirectory("commonMain"), ChooseableOptionDirectory("commonConfig"))
sourceFolders.add(ChooseableOptionDirectory("${platform}Main"))
for ((k, choices) in options) {
    var alreadyChoosen = false
    for (choice in choices)
        if (sourceFolders.contains(choice)) {
            alreadyChoosen = true
            break
        }
    if (!alreadyChoosen) {
        val remainingChoices = mutableListOf<ChooseableOption>()
        for (choice in choices) {
            var ok = false
            for (prefix in platformPrefix[platform]!!)
                if (choice.internalID.startsWith(prefix)) {
                    ok = true
                    break
                }
            for (conflict in conflicts)
                if (conflict.contains(choice.internalID))
                    for (sourceFolder in sourceFolders)
                        if (conflict.contains(sourceFolder.internalID)) {
                            ok = false
                        }
            if (ok)
                remainingChoices.add(choice)
        }
        val choice = presentChoice(k, remainingChoices)
        sourceFolders.add(choice)
        addAdditionalSources()
    }
}
fun addAdditionalSources() {
    var changed = true
    while (changed) {
        changed = false
        for (sourceFolder in sourceFolders) {
            val additionalSource = additionalSources[sourceFolder]
            if (additionalSource != null)
                for (s in additionalSource)
                    if (!sourceFolders.contains(s)) {
                        sourceFolders.add(s)
                        changed = true
                    }
            if (changed)
                break
        }
    }
}

val sourceDependencies = mutableSetOf<String>()
for (sourceFolder in sourceFolders) {
    val dependency = dependencies[sourceFolder]
    if (dependency != null)
        sourceDependencies.addAll(dependency)
}
println("result sourceFolders :: ")
for (sourceFolder in sourceFolders.sorted())
    println(sourceFolder.internalID)
println("result dependencies:: ")
for (sourceDependency in sourceDependencies.sorted())
    println(sourceDependency)
println("build.gradle :: ")
allChoicesString = allChoicesString.replace("Main", "").replace("common", "")

File("build.gradle.kts").printWriter().use { out ->
    out.println("""buildscript {
    repositories {
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
        maven("http://oss.sonatype.org/content/repositories/snapshots")
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26")
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}""")
    when (platform) {
        "jvm" -> {
            out.println("""plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
    application
}
application {
    mainClassName = "MainKt"
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
project.buildDir = file("build/build$allChoicesString")
dependencies {""")
            for (sourceDependency in sourceDependencies.sorted())
                out.println("    implementation(\"$sourceDependency\")")
            out.println("""}""")
            for (sourceFolder in sourceFolders.sorted())
                if (sourceFolder is ChooseableOptionDirectory)
                    out.println("sourceSets[\"main\"].java.srcDir(\"src/${sourceFolder.internalID}/kotlin\")")
        }
        else -> {
            out.println("""plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
kotlin {
    project.buildDir = file("build/build$allChoicesString")
    ${platform}("${platform}") {
        val main by compilations.getting""")
            for (sourceFolder in sourceFolders.sorted()) {
                val interop = cinterops[sourceFolder]
                if (interop != null)
                    for (i in interop.sorted())
                        out.println("        val $i by main.cinterops.creating")
            }
            out.println("""        binaries {
            executable()
        }
    }
    sourceSets["commonMain"].dependencies {""")
            for (sourceDependency in sourceDependencies.sorted())
                out.println("        implementation(\"$sourceDependency\")")
            out.println("""    }""")
            for (sourceFolder in sourceFolders.sorted())
                if (sourceFolder is ChooseableOptionDirectory) {
                    if (sourceFolder.internalID.startsWith("common"))
                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src/${sourceFolder.internalID}/kotlin\")")
                    else
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src/${sourceFolder.internalID}/kotlin\")")
                }
            out.println("""}""")
        }
    }
}
try {
    File("build.gradle.kts").copyTo(File("build/script${allChoicesString}.gradle.kts"))
} catch (e: FileAlreadyExistsException) {
}

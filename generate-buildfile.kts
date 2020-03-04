var allChoicesString = ""
fun presentChoice(options: List<String>): String {
    when (options.size) {
        0 -> throw Exception("script error")
        1 -> return options[0]
        else -> {
            println("choose one of $options")
            while (true) {
                val input = readLine()
                if (input != null)
                    if (options.contains(input)) {
                        allChoicesString += "-$input"
                        return input
                    }
            }
        }
    }
}

val options = mapOf(
        "chooseS00ResultFlow" to listOf("commonS00ResultFlowGenerateTestsMain", "commonS00ResultFlowFastMain", "commonS00ResultFlowExecuteTestsMain"),
        "chooseS00Execution" to listOf("commonS00ExecutionSequentialMain", "commonS00ExecutionParallelMain"),
        "chooseS00Trace" to listOf("commonS00TraceOnMain", "commonS00TraceOffMain"),
        "commonS01Buffer" to listOf("commonS01BufferDiskbasedMain", "commonS01BufferMainmemoryMain"),
        "commonS01Heap" to listOf("commonS01HeapMain"),
        "chooseS03" to listOf("commonS03DictionaryNoneMain", "commonS03DictionaryIntArrayMain"),
        "chooseS05" to listOf("commonS05HashMapMain"),
        "chooseS12" to listOf("jvmS12DummyMain", "commonS12LocalMain"),
        "chooseS14" to listOf("jvmS14KorioMain", "commonS14NoneMain"),
        "chooseS15" to listOf("commonS15LocalMain", "commonS15DistributedMain")
)
val conflicts = listOf(
        setOf("commonS00ExecutionParallelMain", "commonS00TraceOnMain"),
        setOf("commonS03DictionaryNoneMain", "commonS00ResultFlowGenerateTestsMain", "commonS00ResultFlowExecuteTestsMain"),
        setOf("commonS12LocalMain", "commonS15DistributedMain"),
        setOf("commonS12LocalMain", "jvmS14KorioMain"),
        setOf("jvmS12DummyMain", "commonS03DictionaryNoneMain"),
        setOf("commonS00ResultFlowGenerateTestsMain", "commonS15LocalMain")
)
val platformPrefix = mapOf(
        "jvm" to listOf("common", "jvm"),
        "linuxX64" to listOf("common", "linuxX64", "native"),
        "macosX64" to listOf("common", "macosX64", "native"),
        "mingw64" to listOf("common")
)
val fastBuildHelper = setOf(
        "commonS00ResultFlowGenerateTestsMain",
        "commonS00ResultFlowExecuteTestsMain",
        "commonS00ExecutionSequentialMain",
        "commonS00TraceOnMain"
)
val ktorVersion = presentChoice(listOf("1.3.1"))
val kotlinVersion = presentChoice(listOf("1.3.70"))
val platform = presentChoice(platformPrefix.keys.toList())
val dependencies = mapOf(
        "commonMain" to listOf(
                "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion",
                "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion",
                "com.benasher44:uuid:0.0.7",
                "com.soywiz.korlibs.krypto:krypto:1.9.1",
                "io.ktor:ktor-client-core:$ktorVersion",
                "io.ktor:ktor-client-cio:$ktorVersion",
                "io.ktor:ktor-client-logging:$ktorVersion",
                "org.slf4j:slf4j-nop:1.7.25"
        ),
        "jvmMain" to listOf(
                "com.soywiz.korlibs.klock:klock:1.7.0",
                "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion",
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion",
                "io.ktor:ktor-client-logging-jvm:$ktorVersion",
                "io.ktor:ktor-client-core-jvm:$ktorVersion"
        ),
        "nativeMain" to listOf(
                "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3",
                "io.ktor:ktor-client-core-native:$ktorVersion",
                "io.ktor:ktor-client-logging-native:$ktorVersion"
        ),
        "jvmS14KorioMain" to listOf(
                "com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT"
        ),
        "linuxX64Main" to listOf(
                "com.soywiz.korlibs.klock:klock-linuxx64:1.8.7"
        ),
        "macosX64Main" to listOf(
                "com.soywiz.korlibs.klock:klock-macosx64:1.8.9"
        )
)
val cinterops = mapOf(
        "nativeMain" to listOf(
                "dirent",
                "stdio",
                "unistd"
        )
)
val sourceFolders = mutableSetOf("commonMain")
sourceFolders.add("${platform}Main")
for ((k, choices) in options) {
    val remainingChoices = mutableListOf<String>()
    for (choice in choices) {
        var ok = false
        for (prefix in platformPrefix[platform]!!)
            if (choice.startsWith(prefix)) {
                ok = true
                break
            }
        for (conflict in conflicts)
            if (conflict.contains(choice))
                for (sourceFolder in sourceFolders)
                    if (conflict.contains(sourceFolder))
                        ok = false
        if (ok)
            remainingChoices.add(choice)
    }
    val choice = presentChoice(remainingChoices)
    sourceFolders.add(choice)
}
val sourceDependencies = mutableSetOf<String>()
for (sourceFolder in sourceFolders) {
    val dependency = dependencies[sourceFolder]
    if (dependency != null)
        for (d in dependency)
            sourceDependencies.add(d)
}
println("result sourceFolders :: ")
for (sourceFolder in sourceFolders)
    println(sourceFolder)
println("result dependencies:: ")
for (sourceDependency in sourceDependencies)
    println(sourceDependency)
println("build.gradle :: ")


println("""buildscript {
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
}
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
    application
}
application {
    mainClassName = "MainKt"
}
repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    google()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
project.buildDir = file("build$allChoicesString")
dependencies {""")
for (sourceDependency in sourceDependencies)
    println("    implementation(\"$sourceDependency\")")
println("""}""")
for (sourceFolder in sourceFolders)
    println("sourceSets[\"main\"].java.srcDir(\"src/$sourceFolder/kotlin\")")

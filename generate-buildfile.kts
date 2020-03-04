val options = mapOf(
        "chooseS00ResultFlow" to listOf("commonS00ResultFlowGenerateTestsMain", "commonS00ResultFlowFastMain", "commonS00ResultFlowExecuteTestsMain"),
        "chooseS00Execution" to listOf("commonS00ExecutionSequentialMain", "commonS00ExecutionParallelMain"),
        "chooseS00Trace" to listOf("commonS00TraceOnMain", "commonS00TraceOffMain"),
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
val fastBuildHelper = setOf(
        "commonS00ResultFlowGenerateTestsMain",
        "commonS00ResultFlowExecuteTestsMain",
        "commonS00ExecutionSequentialMain",
        "commonS00TraceOnMain"
)
val ktorVersion = "1.3.1"
val dependencies = mapOf(
        "commonMain" to listOf(
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8:KOTLIN_VERSION",
                "org.jetbrains.kotlin:kotlin-stdlib-common:KOTLIN_VERSION",
                "org.jetbrains.kotlin:kotlin-stdlib:KOTLIN_VERSION",
                "com.benasher44:uuid:0.0.7",
                "com.soywiz.korlibs.krypto:krypto:1.9.1",
                "com.soywiz.korlibs.klock:klock:1.7.0",
                "org.jetbrains.kotlin:kotlin-reflect:KOTLIN_VERSION",
                "io.ktor:ktor-client-core:$ktorVersion",
                "io.ktor:ktor-client-core-native:$ktorVersion",
                "io.ktor:ktor-client-cio:$ktorVersion",
                "io.ktor:ktor-client-logging:$ktorVersion",
                "io.ktor:ktor-client-logging-jvm:$ktorVersion",
                "org.slf4j:slf4j-nop:1.7.25"),
        "jvmS14KorioMain" to listOf(
                "com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT"))

fun presentChoice(options: List<String>): String {
    when (options.size) {
        0 -> throw Exception("script error")
        1 -> return options[0]
        else -> {
            println("choose one of $options")
            while (true) {
                val input = readLine()
                if (input != null)
                    if (options.contains(input))
                        return input
            }
        }
    }
}

val kotlinVersion = presentChoice(listOf("1.3.70"))
val sourceFolders = mutableSetOf("commonMain")
for ((k, choices) in options) {
    val remainingChoices = mutableListOf<String>()
    for (choice in choices) {
        var ok = true
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
            sourceDependencies.add(d.replace("KOTLIN_VERSION", kotlinVersion))
}
println("result sourceFolders :: ")
for (sourceFolder in sourceFolders)
    println(sourceFolder)
println("result dependencies:: ")
for (sourceDependency in sourceDependencies)
    println(sourceDependency)
println("build.gradle :: ")
println("""
buildscript {
    repositories {
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
        maven {
            url 'http://oss.sonatype.org/content/repositories/snapshots'
        }
        maven {
            url "https://plugins.gradle.org/m2/"
        }
        maven {
            url "https://dl.bintray.com/kotlin/kotlin-eap"
        }
    }

    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        classpath "org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26"
        classpath "com.moowork.gradle:gradle-node-plugin:1.2.0"
    }
}
apply plugin: 'kotlin-platform-jvm'
apply plugin: 'application'
application {
    mainClassName = 'MainKt'
}
repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    google()
    maven {
        url 'http://dl.bintray.com/kotlin/kotlin-eap-1.2'
    }
}
tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
    kotlinOptions {
        freeCompilerArgs["-Xuse-experimental=kotlin.ExperimentalStdlibApi"]
    }
}
dependencies {
""")
for (sourceDependency in sourceDependencies)
    println("    implementation '$sourceDependency'")
println("""
}
jar {
    manifest {
        attributes 'Main-Class': 'lupos/TestKt'
    }

    from {
        configurations.compile.collect {
            it.isDirectory() ? it : zipTree(it)
        }
    }
}
compileKotlin {
    kotlinOptions.freeCompilerArgs += [
            '-Xno-call-assertions',
            '-Xno-receiver-assertions',
            '-Xno-param-assertions'
    ]
}
sourceSets {
""")
for (sourceFolder in sourceFolders)
    println("    main.kotlin.srcDirs += 'src/$sourceFolder/kotlin'")
println("""
}
test {
    testLogging {
        events "failed", "skipped", "passed"
    }
}
""")














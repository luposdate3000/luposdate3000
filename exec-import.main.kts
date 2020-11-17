#!/usr/bin/env kotlin
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.StandardOpenOption
import java.nio.file.Files
import java.nio.file.Paths

val LUPOS_HOME = "/tmp/luposdate3000-test/"
File("log").mkdirs()
File(LUPOS_HOME).deleteRecursively()
val jars = mutableListOf(
        "build-cache/bin/Luposdate3000_Buffer_Manager_Inmemory-jvm.jar",
        "build-cache/bin/Luposdate3000_Dictionary_Inmemory-jvm.jar",
        "build-cache/bin/Luposdate3000_Endpoint-jvm.jar",
        "build-cache/bin/Luposdate3000_Operators-jvm.jar",
        "build-cache/bin/Luposdate3000_Parser-jvm.jar",
        "build-cache/bin/Luposdate3000_Result_Format-jvm.jar",
        "build-cache/bin/Luposdate3000_Shared-jvm.jar",
        "build-cache/bin/Luposdate3000_Test-jvm.jar",
        "build-cache/bin/Luposdate3000_Triple_Store_All-jvm.jar",
        "build-cache/bin/Luposdate3000_Triple_Store_Id_Triple-jvm.jar",
        "build-cache/bin/Luposdate3000_Optimizer_NoPartitions-jvm.jar",
        "build-cache/bin/Luposdate3000_Endpoint_None-jvm.jar",
        "build-cache/bin/Luposdate3000_Jena_Wrapper_Off-jvm.jar",
        "build-cache/bin/Luposdate3000_Launch_Import-jvm.jar",
)
val userHome = System.getProperty("user.home")
for (f in File("$userHome/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-jvm/1.9.1/").walk()) {
    if (f.isFile()) {
        if (f.getName() == "krypto-jvm-1.9.1.jar") {
            jars.add(f.toString())
        }
    }
}
for (f in File("$userHome/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.4.255-SNAPSHOT").walk()) {
    if (f.isFile()) {
        if (f.getName() == "kotlin-stdlib-1.4.255-SNAPSHOT.jar") {
            jars.add(f.toString())
        }
    }
}
var classpath = ""
for (jar in jars) {
    if (classpath == "") {
        classpath = jar
    } else {
        classpath = "$classpath:$jar"
    }
}
val cmd=mutableListOf("java", "-Xmx60g", "-cp", classpath, "MainKt")
cmd.addAll(args)
ProcessBuilder(cmd)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor()

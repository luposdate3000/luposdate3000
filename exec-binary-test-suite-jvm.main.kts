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
        "build-cache/bin/Luposdate3000_Optimizer-jvm.jar",
        "build-cache/bin/Luposdate3000_Endpoint_None-jvm.jar",
        "build-cache/bin/Luposdate3000_Jena_Wrapper_Off-jvm.jar",
        "build-cache/bin/Luposdate3000_Launch_Binary_Test_Suite-jvm.jar",
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
println(classpath)
val p = ProcessBuilder("java", "-Xmx60g", "-cp", classpath, "MainKt")
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
p.waitFor()
if (p.exitValue() != 0) {
    throw Exception("exit-code:: " + p.exitValue())
}
val configOld = mutableMapOf<String, String>()
File("resources/binary/config").forEachLine {
    val a = it.split("=")
    configOld[a[0]] = a[1]
}
val configNew = mutableMapOf<String, String>()
File("resources/binary/config2").forEachLine {
    val a = it.split("=")
    configNew[a[0]] = a[1]
}
for ((k, v) in configOld) {
    val v2 = configNew[k]
    if (v != v2) {
        println("$k $v -> $v2")
    }
}

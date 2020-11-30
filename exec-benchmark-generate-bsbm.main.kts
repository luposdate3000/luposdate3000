#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_scripting/exec-import.kt")
@file:CompilerOptions("-Xmulti-platform")

import java.io.PrintWriter
import java.io.FileWriter
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

val targetBaseFolder = "${Platform.getEnv("LUPOS_BENCHMARK_HOME", "${Platform.getPathSeparator()}mnt")!!}${Platform.getPathSeparator()}luposdate-testdata${Platform.getPathSeparator()}bsbm"
val sp2bGeneratorHome = "${Platform.getPathSeparator()}opt${Platform.getPathSeparator()}bsbmtools-0.2"

var targetCount = 1
while (targetCount <= 2097152) {
    val targetFolder = "$targetBaseFolder${Platform.getPathSeparator()}$targetCount"
    File(targetFolder).deleteRecursively()
    File(targetFolder).mkdirs()
    val targetFile = "$targetFolder${Platform.getPathSeparator()}complete.n3"
    ProcessBuilder("./generate", "-s", "ttl", "-pc", "$targetCount")
            .directory(File(sp2bGeneratorHome))
            .redirectOutput(File(Platform.getNullFileName()))
            .redirectError(File(Platform.getNullFileName()))
            .start()
            .waitFor()
    Files.move(Paths.get("$sp2bGeneratorHome${Platform.getPathSeparator()}dataset.ttl"), Paths.get(targetFile))
    execImport(arrayOf(targetFile))
    val size = File(targetFile).length()
    val count = File("${targetFile}.triples").length() / 12
    val sizeIntermediate = File("${targetFile}.triples").length() + File("${targetFile}.dictionary").length() + File("${targetFile}.dictionaryoffset").length() + File("${targetFile}.stat").length()
    val fileWriter = FileWriter("${targetBaseFolder}${Platform.getPathSeparator()}stat.csv", true)
    val printWriter = PrintWriter(fileWriter)
    printWriter.println("$targetCount,$count,$size,$sizeIntermediate")
    printWriter.close()
    targetCount *= 2
}

#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_scripting/exec-import.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

val targetBastFolder = "${Platform.getPathSeparator()}mnt${Platform.getPathSeparator()}luposdate-testdata${Platform.getPathSeparator()}sp2b"
val sp2bGeneratorHome = "${Platform.getPathSeparator()}opt${Platform.getPathSeparator()}sp2b${Platform.getPathSeparator()}bin"

var targetCount = 1024
while (targetCount <= 1024) {
    val targetFolder = "$targetBastFolder${Platform.getPathSeparator()}$targetCount"
    File(targetFolder).deleteRecursively()
    File(targetFolder).mkdirs()
    val targetFile = "$targetFolder${Platform.getPathSeparator()}complete.n3"
    ProcessBuilder("./sp2b_gen", "-t", "$targetCount")
            .directory(File(sp2bGeneratorHome))
            .redirectOutput(File(Platform.getNullFileName()))
            .redirectError(File(Platform.getNullFileName()))
            .start()
            .waitFor()
    Files.move(Paths.get("$sp2bGeneratorHome${Platform.getPathSeparator()}sp2b.n3"), Paths.get(targetFile))
    execImport(arrayOf(targetFile))
    val size = File(targetFile).length()
    val count = File("${targetFile}.triples").length() / 12
    val sizeIntermediate = File("${targetFile}.triples").length() + File("${targetFile}.dictionary").length() + File("${targetFile}.dictionaryoffset").length() + File("${targetFile}.stat").length()
    Files.write(Paths.get("${targetBastFolder}${Platform.getPathSeparator()}stat.csv"), "$targetCount,$count,$size,$sizeIntermediate\n".toByteArray(), StandardOpenOption.APPEND)
    targetCount *= 2
}

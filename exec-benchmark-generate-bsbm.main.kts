#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
@file:Import("src/luposdate3000_scripting/exec-import.kt")
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

val targetBastFolder = "/mnt/luposdate-testdata/bsbm"
val sp2bGeneratorHome="/opt/bsbmtools-0.2"

var targetCount = 1
while (targetCount <= 2097152) {
    val targetFolder = "$targetBastFolder/$targetCount"
    File(targetFolder).deleteRecursively()
    File(targetFolder).mkdirs()
    val targetFile = "$targetFolder/complete.n3"
    ProcessBuilder("./generate","-s","ttl","-pc", "$targetCount")
            .directory(File(sp2bGeneratorHome))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    Files.move(Paths.get("$sp2bGeneratorHome/dataset.ttl"), Paths.get(targetFile))
execImport(arrayOf(targetFile))
    val size = File(targetFile).length()
    val count = File("${targetFile}.targetCount").length() / 12
    val sizeIntermediate = File("${targetFile}.targetCount").length() + File("${targetFile}.dictionary").length() + File("${targetFile}.dictionaryoffset").length() + File("${targetFile}.stat").length()
    Files.write(Paths.get("/mnt/luposdate-testdata/sp2b/stat.csv"), "$targetCount,$count,$size,$sizeIntermediate\n".toByteArray(), StandardOpenOption.APPEND)
    targetCount *= 2
}

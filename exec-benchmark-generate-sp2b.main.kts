#!/usr/bin/env kotlin
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

val targetBastFolder = "/mnt/luposdate-testdata/sp2b"
val sp2bGeneratorHome="/opt/sp2b/bin"

var targetCount = 1024
while (targetCount <= 536870912) {
    val targetFolder = "$targetBastFolder/$targetCount"
    File(targetFolder).deleteRecursively()
    File(targetFolder).mkdirs()
    val targetFile = "$targetFolder/complete.n3"
    ProcessBuilder("./sp2b_gen", "-t", "$targetCount")
            .directory(File(sp2bGeneratorHome))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    Files.move(Paths.get("$sp2bGeneratorHome/sp2b.n3"), Paths.get(targetFile))
    ProcessBuilder("./exec-import.sh", targetFile)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    val size = File(targetFile).length()
    val count = File("${targetFile}.targetCount").length() / 12
    val sizeIntermediate = File("${targetFile}.targetCount").length() + File("${targetFile}.dictionary").length() + File("${targetFile}.dictionaryoffset").length() + File("${targetFile}.stat").length()
    Files.write(Paths.get("/mnt/luposdate-testdata/sp2b/stat.csv"), "$targetCount,$count,$size,$sizeIntermediate\n".toByteArray(), StandardOpenOption.APPEND)
    targetCount *= 2
}

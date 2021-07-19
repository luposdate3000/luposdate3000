#!/usr/bin/env kotlin
/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared/inline/Platform.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/Config.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.shared.LUPOS_REAL_WORLD_DATA_ROOT
import lupos.shared.inline.Platform
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

val targetBaseFolder = "${LUPOS_REAL_WORLD_DATA_ROOT}${Platform.getPathSeparator()}sp2b"
val sp2bGeneratorHome = "${Platform.getPathSeparator()}opt${Platform.getPathSeparator()}sp2b${Platform.getPathSeparator()}bin"
var targetCount = 1024
while (targetCount <= 134217728) {
    val targetFolder = "$targetBaseFolder${Platform.getPathSeparator()}$targetCount"
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
    val size = File(targetFile).length()
    val count = File("$targetFile.triples").length() / 12
    val fileWriter = FileWriter("${targetBaseFolder}${Platform.getPathSeparator()}stat.csv", true)
    val printWriter = PrintWriter(fileWriter)
    printWriter.println("$targetCount,$count,$size")
    printWriter.close()
    targetCount *= 2
}

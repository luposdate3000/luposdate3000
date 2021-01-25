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
@file:Import("../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("generate-data.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import kotlin.math.pow
val triplesFiles = "${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4/intermediate.n3"
val minimumTime = 10.0
for (output_count in listOf(512, 2048, 8192, 32768)) {
    for (join_count in listOf(1, 2, 4, 8, 16)) {
        for (join in listOf(2, 4, 8, 16, 32, 64)) {
            try {
                val count2 = (output_count / (join.toDouble().pow(1 + join_count.toDouble()))).toInt()
                if (count2 == 0) {
                    continue
                }
                generateTriples("${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4", count2, 0, join, join_count)
                val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", triplesFiles, "$minimumTime", "$output_count", "0", "$join", "$join_count")
                cmd.addAll(args)
                ProcessBuilder(cmd)
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)) {
            try {
                generateTriples("${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4", output_count, trash, 1, join_count)
                val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", triplesFiles, "$minimumTime", "$output_count", "$trash", "1", "$join_count")
                cmd.addAll(args)
                ProcessBuilder(cmd)
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

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
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/_Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_scripting/parsergenerator.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/_File.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_File.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/File.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/IMyInputStream.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/IMyOutputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/MyCharIterator.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/MyOutputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/MyInputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/_MyOutputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_MyOutputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/_MyInputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_MyInputStream.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/ByteArrayHelper.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_ByteArrayHelper.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/Exceptions.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/SanityCheckOn.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/ParallelThread.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/ParallelThread.kt")
@file:Import("src/luposdate3000_shared/src/jvmMain/kotlin/lupos/s00misc/ParallelThreadJob.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ParallelThreadJob.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/ParallelThreadCondition.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/ParallelThreadCondition.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/ParallelThreadQueue.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/ParallelThreadQueue.kt")
@file:CompilerOptions("-Xmulti-platform")

package lupos.s00misc

import lupos.modulename.ParallelThread
import lupos.modulename.SanityCheckOn

internal typealias Parallel = ParallelThread
internal typealias SanityCheck = SanityCheckOn

var byteBuf = ByteArray(1)
var id = 0
File(args[0]).withInputStream { input ->
    try {
        while (true) {
            val len = input.readInt()
            input.read(byteBuf)
            val buf = ByteArray(len)
            val l = input.read(buf)
            if (l == 0) {
                break
            }
            println("$id :=: ${byteBuf[0]} : ${buf.decodeToString()}")
            id++
        }
    } catch (e: Throwable) {
    }
}

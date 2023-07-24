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
package lupos.launch.import_benchmark

import lupos.endpoint.LuposdateEndpoint
import lupos.parser.InputToIntermediate
import lupos.shared.DateHelperRelative
import lupos.shared.inline.ParallelThread

internal fun mainFunc(inputFileName: String): Unit = ParallelThread.runBlocking {
    val timer = DateHelperRelative.markNow()
    val instance = LuposdateEndpoint.initialize()
    var counter = 0
    var time = 0.0
    while (true) {
        counter++
        InputToIntermediate.process(inputFileName, instance)
        time = DateHelperRelative.elapsedSeconds(timer)
        if (time > 10) {
            break
        }
    }
    println("imported $inputFileName $counter times in $time Seconds - that is ${time / counter} Seconds per run or ${counter / time} runs per Second")
}

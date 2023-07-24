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
package lupos.launch.display_triple_contents

import lupos.shared.inline.ParallelThread
import lupos.shared.inline.fileformat.TriplesIntermediateReader

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(inputFileName: String, indexPattern: String): Unit = ParallelThread.runBlocking {
    when (indexPattern) {
        "SPO" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[0]} ${row[1]} ${row[2]}") }
        "SOP" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[0]} ${row[2]} ${row[1]}") }
        "PSO" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[1]} ${row[0]} ${row[2]}") }
        "POS" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[2]} ${row[0]} ${row[1]}") }
        "OSP" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[1]} ${row[2]} ${row[0]}") }
        "OPS" -> TriplesIntermediateReader(inputFileName).readAll { row -> println("${row[2]} ${row[1]} ${row[0]}") }
    }
}

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

package lupos.simulator_iot.unit
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.simulator_iot.MultipleSimulationRuns
import lupos.simulator_iot.measure.MeasurementPrinter
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test

internal class MultipleSimulationRunsTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/multipleSimulationRunsTest"
    }

    @Test
    fun runMultipleStarNetworkSimulations() {
        val runs = MultipleSimulationRuns(
            json = JsonParser().fileToJson("$prefix/runMultipleStarNetworkSimulations.json") as JsonParserObject,
            numberOfRepetitions = 3,
            printer = MeasurementPrinter("${FilePaths.outputDir}/test")
        )
        runs.startSimulationRuns()
    }

    @Test
    fun test_1() {
        val runs = MultipleSimulationRuns(
            json = JsonParser().fileToJson("$prefix/meshToDODAG.json") as JsonParserObject,
            numberOfRepetitions = 2,
            printer = MeasurementPrinter("${FilePaths.outputDir}/test")
        )
        runs.startSimulationRuns()
    }
}

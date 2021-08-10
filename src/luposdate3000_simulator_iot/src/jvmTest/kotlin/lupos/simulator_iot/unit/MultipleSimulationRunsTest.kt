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
import lupos.simulator_iot.ISimRunPreparation
import lupos.simulator_iot.MultipleSimulationRuns
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.measure.MeasurementPrinter
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test

internal class MultipleSimulationRunsTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/multipleSimulationRunsTest"
    }

    @Test
    fun runMultipleStarNetworkSimulations() {
        val callback = object : ISimRunPreparation {
            override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                jsonObjects.randomStarNetwork[0].number = 4
            }
        }
        val runs = MultipleSimulationRuns(
            configFileName = "$prefix/runMultipleStarNetworkSimulations.json",
            numberOfRepetitions = 3,
            callback = callback,
            printer = MeasurementPrinter("test")
        )
        runs.startSimulationRuns()
    }

    @Test
    fun test_1() {
        val callback = object : ISimRunPreparation {
            override fun prepareJsonObjects(jsonObjects: JsonObjects) {
            }
        }
        val runs = MultipleSimulationRuns(
            configFileName = "$prefix/meshToDODAG.json",
            numberOfRepetitions = 2,
            callback = callback,
            printer = MeasurementPrinter("test")
        )
        runs.startSimulationRuns()
    }
}

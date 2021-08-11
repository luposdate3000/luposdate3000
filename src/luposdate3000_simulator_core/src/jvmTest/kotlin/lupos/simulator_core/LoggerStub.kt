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

package lupos.simulator_core

internal class LoggerStub(private val simulation: Simulation) : ISimulationLifeCycle {

    override fun onStartUp() {
        logSimulationStart()
    }

    override fun onSteadyState() {
        logSimulationSteadyStateReached()
    }

    override fun onShutDown() {
        logSimulationEnd()
    }

    private fun log(content: String) {
        println(content)
    }

    private fun logSimulationStart() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("Number of entities: ${simulation.numberOfEntities()}")
        log("")
    }

    private fun logSimulationSteadyStateReached() {
        log("Steady state is reached. Measurements can start.")
    }

    private fun logSimulationEnd() {
        log("")
        log("Number of processed events: ${simulation.addedEventCounter}")
        log("Simulation clock: ${simulation.clock}")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")
    }
}

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

package lupos.simulator_iot

import lupos.parser.IJsonParserValue
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.shared.inline.File
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration

public class SimulationRun {

    public lateinit var sim: Simulation

    internal val randGenerator = RandomGenerator()
    public val config: Configuration = Configuration(this)

    public val logger: Loggers = Loggers(mutableListOf())

    public var notInitializedClock: Long = -1

    public var simSteadyClock: Long = notInitializedClock

    public var simMaxClock: Long = notInitializedClock

    public fun parseConfig(json: IJsonParserValue, fileName: String, autocorrect: Boolean = true): Configuration {
        return parseConfig(json as JsonParserObject, fileName, autocorrect)
    }

    public fun parseConfig(json: JsonParserObject, fileName: String, autocorrect: Boolean = true): Configuration {
        config.parse(json, fileName, autocorrect)
        return config
    }

    public fun parseConfig(fileName: String, autocorrect: Boolean = true, modifyJson: (JsonParserObject) -> Unit = {}): Configuration {
        val fileStr = File(fileName).readAsString()
        val json = JsonParser().stringToJson(fileStr) as JsonParserObject
        modifyJson(json)
        return parseConfig(json, fileName, autocorrect)
    }

    public fun startSimulation(configuration: Configuration) {
        sim = Simulation(configuration.getEntities())
        sim.logger = logger
        sim.maxClock = if (simMaxClock == notInitializedClock) sim.maxClock else simMaxClock
        sim.steadyClock = if (simSteadyClock == notInitializedClock) sim.steadyClock else simSteadyClock
        logger.onStartSimulation()
        sim.startSimulation()
        logger.onStopSimulation()
    }
}

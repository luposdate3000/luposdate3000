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
import lupos.simulator_iot.measure.Logger
import lupos.simulator_iot.measure.Measurement
import lupos.simulator_iot.measure.TimeMeasurer
import lupos.simulator_iot.utils.FilePaths
import lupos.visualize.distributed.database.VisualisationConnection
import lupos.visualize.distributed.database.VisualisationDevice
import lupos.visualize.distributed.database.VisualisationNetwork

public class SimulationRun {

    init {
        createOutputDirectory()
        refreshDatabaseDirectories()
    }

    public var visualisationNetwork: VisualisationNetwork = VisualisationNetwork()

    public lateinit var sim: Simulation

    internal val randGenerator = RandomGenerator()

    internal val config = Configuration(this)

    internal val timeMeasurer = TimeMeasurer(this)

    internal var measurement = Measurement()

    internal var logger: Logger? = null

    public var notInitializedClock: Long = -1

    public var simSteadyClock: Long = notInitializedClock

    public var simMaxClock: Long = notInitializedClock

    private fun createOutputDirectory() {
        val directory = File(FilePaths.outputDir)
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }

    private fun refreshDatabaseDirectories() {
        File(FilePaths.queryResult).deleteRecursively()
        File(FilePaths.dbStates).deleteRecursively()
        File(FilePaths.queryResult).mkdirs()
        File(FilePaths.dbStates).mkdirs()
    }

    public fun parseConfig(json: IJsonParserValue, fileName: String, autocorrect: Boolean = true): Configuration {
        return parseConfig(json as JsonParserObject, fileName, autocorrect)
    }
    public fun parseConfig(json: JsonParserObject, fileName: String, autocorrect: Boolean = true): Configuration {
        visualisationNetwork.setup(json)
        config.parse(json, fileName, autocorrect)
        return config
    }
    public fun parseConfig(fileName: String, autocorrect: Boolean = true): Configuration {
        val fileStr = File(fileName).readAsString()
        val json = JsonParser().stringToJson(fileStr) as JsonParserObject
        return parseConfig(json, fileName, autocorrect)
    }

    internal fun startSimulation(configuration: Configuration) {
        sim = Simulation(configuration.getEntities())
        sim.callback = LifeCycleImpl(this)
        sim.maxClock = if (simMaxClock == notInitializedClock) sim.maxClock else simMaxClock
        sim.steadyClock = if (simSteadyClock == notInitializedClock) sim.steadyClock else simSteadyClock
        logger = if (configuration.jsonObjects.logging) Logger(configuration, measurement) else null
        setUpVisualization(configuration)
        sim.startSimulation()
    }

    private fun setUpVisualization(configuration: Configuration) {
        for (d in configuration.devices) {
            val vis = VisualisationDevice(d.address, d.hasDatabaseStore, d.hasDatabaseQuery, d.sensor != null)
            vis.x = d.location.longitude
            vis.y = d.location.latitude
            visualisationNetwork.addDevice(vis)
            for (n in d.linkManager.getNeighbours()) {
                visualisationNetwork.addConnection(VisualisationConnection(d.address, n))
            }
        }
    }

    internal fun getCurrentSimulationClock(): Long {
        return sim.clock
    }

    internal fun measureOnStartUp() {
        measurement.numberOfDevices = config.getNumberOfDevices().toDouble()
        measurement.numberOfSensorDevices = config.numberOfSensors.toDouble()
        measurement.numberOfDatabaseDevices = config.numberOfDatabases.toDouble()
        measurement.numberOfQuerySenders = config.querySenders.size.toDouble()
        measurement.initializationDurationInSec = timeMeasurer.getInitDurationInSec()
        measurement.realStartUpTimeStampInISO = timeMeasurer.getStartUpTimeString()
        measurement.numberOfLinks = config.linker.numberOfLinks.toDouble()
    }

    internal fun measureOnShutDown() {
        measurement.realSimulationDurationInSec = timeMeasurer.getRealSimulationDurationInSec()
        measurement.simulationDurationInSec = timeMeasurer.getSimulationDurationInSec()
        measurement.shutDownTimeStampInISO = timeMeasurer.getShutDownTimeString()
        measurement.realShutDownTimeStampInISO = timeMeasurer.getRealShutDownTimeString()
    }

    internal fun incNumberOfSentPackages() {
        measurement.numberOfSentPackages++
    }

    internal fun incNetworkTraffic(bytesSent: Int) {
        measurement.networkTrafficInKiloBytes += (bytesSent.toDouble() / 1000)
    }

    internal fun incNumberOfParkingSamples() {
        measurement.numberOfParkingSamplesMade++
    }

    internal fun incNumberOfQueries() {
        measurement.numberOfQueriesRequested++
    }

    internal fun incNumberOfSentDAOPackages() {
        measurement.numberOfSentDAOPackages++
    }

    internal fun incNumberOfSentDIOPackages() {
        measurement.numberOfSentDIOPackages++
    }

    internal fun incNumberOfSentDatabasePackages() {
        measurement.numberOfSentDatabasePackages++
    }

    internal fun incNumberOfSentSamplePackages() {
        measurement.numberOfSentSamplePackages++
    }

    internal fun incNumberOfForwardedPackages() {
        measurement.numberOfForwardedPackages++
    }
}

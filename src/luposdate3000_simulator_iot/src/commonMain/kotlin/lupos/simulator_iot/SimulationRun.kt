package lupos.simulator_iot

import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.log.Logger

internal class SimulationRun {

    private lateinit var sim: Simulation

    internal val randGenerator = RandomGenerator()

    internal val config = Configuration(this)

    internal val timeMeasurer = TimeMeasurer(this)

    internal var measurement = Measurement()

    internal val logger = Logger(config, measurement)

    private var notInitializedClock: Long = -1

    internal var simSteadyClock: Long = notInitializedClock

    internal var simMaxClock: Long = notInitializedClock


    private inner class LifeCycleImpl() : ISimulationLifeCycle {

        override fun onStartUp() {
            timeMeasurer.onStartUp()
            measureOnStartUp()
            logger.logStartUp()
        }

        override fun onSteadyState() {
        }

        override fun onShutDown() {
            timeMeasurer.onShutDown()
            measureOnShutDown()
            logger.logShutDown()
        }
    }

    internal fun parseConfigFile(fileName: String): JsonObjects {
        return config.readJsonFile(fileName)
    }

    internal fun parseJsonObjects(jsonObjects: JsonObjects): Configuration {
        config.parse(jsonObjects)
        return config
    }

    internal fun startSimulation(configuration: Configuration) {
        sim = Simulation(configuration.getEntities())
        sim.callback = LifeCycleImpl()
        sim.maxClock = if(simMaxClock == notInitializedClock) sim.maxClock else simMaxClock
        sim.steadyClock = if(simSteadyClock == notInitializedClock) sim.steadyClock else simSteadyClock
        sim.startSimulation()
    }

    internal fun getCurrentSimulationClock(): Long {
        return sim.clock
    }

    private fun measureOnStartUp() {
        measurement.numberOfDevices = config.getNumberOfDevices()
        measurement.numberOfSensorsDevices = config.numberOfSensors
        measurement.numberOfDatabasesDevices = config.numberOfDatabases
        measurement.numberOfQuerySenders = config.querySenders.size
        measurement.initializationDurationInSec = timeMeasurer.getInitDuration()
        measurement.realStartUpTimeStampInISO = timeMeasurer.getStartUpTimeString()
        measurement.numberOfLinks = config.linker.numberOfLinks
    }

    private fun measureOnShutDown() {
        measurement.realSimulationDurationInSec = timeMeasurer.getRealSimulationDuration()
        measurement.simulationDurationInSec = timeMeasurer.getSimulationDuration()
        measurement.shutDownTimeStampInISO = timeMeasurer.getShutDownTimeString()
        measurement.realShutDownTimeStampInISO = timeMeasurer.getRealShutDownTimeString()
    }

    internal fun incNumberOfSentPackages() {
        measurement.numberOfSentPackages++
    }

    internal fun incNetworkTraffic(bytesSent: Int) {
        measurement.networkTrafficInBytes += bytesSent.toLong()
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




}

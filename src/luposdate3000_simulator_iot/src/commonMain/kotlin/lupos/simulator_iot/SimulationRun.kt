package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.log.Logger
import lupos.visualize.distributed.database.VisualisationConnection
import lupos.visualize.distributed.database.VisualisationDevice

public class SimulationRun {

    public var sim: Simulation = Simulation(mutableListOf(), LifeCycleImpl(this))

    internal val randGenerator = RandomGenerator()

    internal val config = Configuration(this)

    internal val timeMeasure = TimeMeasure(sim)

    internal val logger = Logger(timeMeasure, config)

    public var notInitializedClock: Long = -1

    public var simSteadyClock: Long = notInitializedClock

    public var simMaxClock: Long = notInitializedClock

    public fun parseConfigFile(fileName: String): JsonObjects {
        return config.readJsonFile(fileName)
    }

    public fun parseJsonObjects(jsonObjects: JsonObjects): Configuration {
        config.parse(jsonObjects)
        return config
    }

    internal fun startSimulation(configuration: Configuration) {
        sim = Simulation(configuration.getEntities(), LifeCycleImpl(this))
        for (d in configuration.devices) {
            sim.visualisationNetwork.addDevice(VisualisationDevice(d.address, d.database != null, d.sensor != null)) // public val id:Int,public val hasDatabase:Boolean
            for (n in d.linkManager.getNeighbours()) {
                sim.visualisationNetwork.addConnection(VisualisationConnection(d.address, n))
            }
        }
        sim.maxClock = if (simMaxClock == notInitializedClock) sim.maxClock else simMaxClock
        sim.steadyClock = if (simSteadyClock == notInitializedClock) sim.steadyClock else simSteadyClock
        sim.startSimulation()
    }

    internal fun getNumberOfDevices(): Int {
        return config.devices.size
    }

    internal fun getDeviceByAddress(address: Int): Device {
        return config.devices[address]
    }

    internal fun getDeviceByName(name: String): Device {
        return config.getNamedDevice(name)
    }

    internal fun getCurrentSimulationClock(): Long {
        return sim.clock
    }
}

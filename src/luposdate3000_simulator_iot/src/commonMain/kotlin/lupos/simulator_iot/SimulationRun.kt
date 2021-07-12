package lupos.simulator_iot

import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.log.Logger

internal class SimulationRun {

    private var sim = Simulation(mutableListOf(), LifeCycleImpl())

    internal val randGenerator = RandomGenerator()

    internal val config = Configuration(this)

    internal val timeMeasure = TimeMeasure(sim)

    internal val logger = Logger(timeMeasure, config)

    private var notInitializedClock: Long = -1

    internal var simSteadyClock: Long = notInitializedClock

    internal var simMaxClock: Long = notInitializedClock

    private inner class LifeCycleImpl() : ISimulationLifeCycle {

        override lateinit var simulation: Simulation // TODO wird nicht gebraucht!

        override fun onStartUp() {
            timeMeasure.onStartUp()
            logger.logStartUp()
        }

        override fun onSteadyState() {
        }

        override fun onShutDown() {
            timeMeasure.onShutDown()
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
        sim = Simulation(configuration.getEntities(), LifeCycleImpl())
        sim.maxClock = if(simMaxClock == notInitializedClock) sim.maxClock else simMaxClock
        sim.steadyClock = if(simSteadyClock == notInitializedClock) sim.steadyClock else simSteadyClock
        sim.startSimulation()
    }



    internal fun getCurrentSimulationClock(): Long {
        return sim.clock
    }


}

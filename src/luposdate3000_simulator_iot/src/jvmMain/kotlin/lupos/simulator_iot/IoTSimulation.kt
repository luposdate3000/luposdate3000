package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger
import lupos.simulator_iot.log.LoggerCollection

public actual class IoTSimulation {
    public actual constructor() {}
    public actual fun simulate(configFileName: String) {
        Configuration.parse(configFileName)
        val sim = Simulation(
            entities = Configuration.getEntities(),
            callback = Logger
        )
        sim.startSimulation()
    }

    public actual fun measureStarPerformance(withDummyDatabase: Boolean) {
        val loggerCollection = LoggerCollection()
        var arr: IntArray = if (!withDummyDatabase) {
            buildNodeSizesArray(130, 10) // OutOfMemoryError >1330 DBs with 2048 heap space
        } else {
            buildNodeSizesArray(100, 1000)
        }

        for (numberOfChilds in arr)
            runSimulationStarPerformance(numberOfChilds, loggerCollection, withDummyDatabase)
    }

    private fun buildNodeSizesArray(arrSize: Int, delta: Int): IntArray {
        val arr = IntArray(arrSize) { 0 }
        for (i in arr.withIndex())
            arr[i.index] = i.index * delta
        return arr
    }

    private fun runSimulationStarPerformance(numberOfChilds: Int, collection: LoggerCollection, withDummyDatabase: Boolean) {
        Logger.reset()
        val configFileName = "${FilePaths.jvmResource}/starPerformance.json"
        val jsonObjects = Configuration.readJsonFile(configFileName)
        jsonObjects.randomStarNetwork[0].number = numberOfChilds
        jsonObjects.dummyDatabase = withDummyDatabase
        Configuration.parse(jsonObjects)
        val entities = Configuration.getEntities()
        val sim = Simulation(entities = entities, callback = Logger)
        sim.startSimulation()
        collection.add(Logger, entities.size)
    }
}

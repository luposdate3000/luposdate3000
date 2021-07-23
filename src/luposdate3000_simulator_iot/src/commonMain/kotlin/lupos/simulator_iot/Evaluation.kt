package lupos.simulator_iot

import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.measure.MeasurementPrinter
import lupos.simulator_iot.queryproc.SemanticData
import lupos.simulator_iot.utils.FilePaths

public class Evaluation {
    public constructor() {}
    public fun simulate(configFileName: String) {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile(configFileName)
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)
    }


    private fun buildNodeSizesArray(arrSize: Int, delta: Int): IntArray {
        val arr = IntArray(arrSize) { 0 }
        for (i in arr.withIndex()) {
                arr[i.index] = i.index * delta
        }

        return arr
    }

    private fun getMeshPerfRanges(): IntArray {
        val arrSize = IntArray(20)
        arrSize[0] = 600
        println(arrSize[0])
        for (index in 1 until arrSize.size) {
                arrSize[index] = arrSize[index-1] - 30
                println(arrSize[index])
        }
        return arrSize
    }

    private fun getQueriesAsArray(): Array<String> {
        return arrayOf(
            SemanticData.getAllTriples(),
            SemanticData.getAllParkingAreas(),
            SemanticData.getAllSpacesOfParkingArea("<http://parkingArea/10>"),
            SemanticData.getSampleNumberOfSensor("<http://sensor/9/67>"),
            SemanticData.getLastSampleOfSensor("<http://sensor/7/55>"),
            SemanticData.getLastResultsOfEachSensorInArea("<http://parkingArea/9>"),
            SemanticData.getLastResultsOfEachSensorInManyAreas("<http://parkingArea/9>, <http://parkingArea/8>, <http://parkingArea/2>"),
        )
    }

    public fun evalQueryProcessingCentralizedCase() {
        val configFileName = "${FilePaths.jvmResource}/campusCentralCase.json"
        val ranges = getQueriesAsArray()
        val printer = MeasurementPrinter("campusCentralCase")
        for ((index, range) in ranges.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.querySender[0].query = range
                }
            }
            MultipleSimulationRuns(configFileName, 1, prep, printer).startSimulationRuns()
            println("evalQueryProcessingCentralizedCase: Run ${index + 1} finished. ${ranges.size - index - 1 } runs left..")
        }
    }

    public fun evalQueryProcessingDistributedCase() {
        val configFileName = "${FilePaths.jvmResource}/campusDistributedCase.json"
        val ranges = getQueriesAsArray()
        val printer = MeasurementPrinter("campusDistributedCase")
        for ((index, range) in ranges.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.querySender[0].query = range
                }
            }
            MultipleSimulationRuns(configFileName, 1, prep, printer).startSimulationRuns()
            println("evalQueryProcessingDistributedCase: Run ${index + 1} finished. ${ranges.size - index - 1 } runs left..")
        }
    }


    public fun evalMeshPerformance() {
        val configFileName = "${FilePaths.jvmResource}/meshPerformance.json"
        var ranges = getMeshPerfRanges()
        ranges = addInitialBuffer(ranges, 3)
        val printer = MeasurementPrinter("meshPerf")
        for ((index, range) in ranges.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.linkType[0].rangeInMeters = range
                }
            }
            MultipleSimulationRuns(configFileName, 1, prep, printer).startSimulationRuns()
            println("evalMeshPerformance: Run ${index + 1} finished. ${ranges.size - index - 1 } runs left..")
        }
    }


    public fun evalStarPerformanceWithLuposdate() {
        val configFileName = "${FilePaths.jvmResource}/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(110, 10) // max. 1171 instances are possible.
        nodeSizes = addInitialBuffer(nodeSizes, 3)
        val printer = MeasurementPrinter("starPerf_Luposdate")
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.randomStarNetwork[0].number = numberOfNodes
                    jsonObjects.deviceType[0].database = true
                    jsonObjects.dummyDatabase = false
                }
            }
            MultipleSimulationRuns(configFileName, 50, prep, printer).startSimulationRuns()
            println("evalStarPerformanceWithLuposdate: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }


    public fun evalStarPerformanceWithDummy() {
        val configFileName = "${FilePaths.jvmResource}/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(200, 10)
        nodeSizes = addInitialBuffer(nodeSizes, 3)
        val printer = MeasurementPrinter("starPerf_Dummy")
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.randomStarNetwork[0].number = numberOfNodes
                    jsonObjects.deviceType[0].database = true
                    jsonObjects.dummyDatabase = true
                }
            }
            MultipleSimulationRuns(configFileName, 50, prep, printer).startSimulationRuns()
            println("evalStarPerfWithDummy: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }


    public fun evalStarPerformance() {
        val configFileName = "${FilePaths.jvmResource}/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(200, 100)
        nodeSizes = addInitialBuffer(nodeSizes, 3)
        val printer = MeasurementPrinter("starPerf_Without")
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val prep = object : ISimRunPreparation {
                override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                    jsonObjects.randomStarNetwork[0].number = numberOfNodes
                    jsonObjects.deviceType[0].database = false
                    jsonObjects.dummyDatabase = true
                }
            }
            MultipleSimulationRuns(configFileName, 50, prep, printer).startSimulationRuns()
            println("evalStarPerf: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }

    private fun addInitialBuffer(arr: IntArray, bufferSize: Int): IntArray {
        val updatedArray = IntArray(arr.size + bufferSize)
        for (i in updatedArray.indices) {
            if(i < bufferSize) {
                updatedArray[i] = arr[i]
            }
            else {
                updatedArray[i] = arr[i-bufferSize]
            }
        }
        return updatedArray
    }
}

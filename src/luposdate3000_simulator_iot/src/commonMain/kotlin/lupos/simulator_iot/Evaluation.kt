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
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.shared.inline.File
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.queryproc.SemanticData
public class Evaluation {
    public constructor() {}
    public fun simulate(configFileName: String) {
        val simRun = SimulationRun()
        val config = simRun.parseConfig(configFileName)
        simRun.startSimulation(config)
    }

    private fun buildNodeSizesArray(arrSize: Int, delta: Int): IntArray {
        val arr = IntArray(arrSize) { 0 }
        for (i in arr.withIndex()) {
            arr[i.index] = i.index * delta
            if (i.index != 0) {
                arr[i.index] -= 1
            }
        }

        return arr
    }

    private fun getMeshPerfRanges(): IntArray {
        val arrSize = IntArray(20)
        arrSize[0] = 600
        println(arrSize[0])
        for (index in 1 until arrSize.size) {
            arrSize[index] = arrSize[index - 1] - 30
            println(arrSize[index])
        }
        return arrSize
    }

    private fun getQueriesAsArray(): Array<String> {
        return arrayOf(
            "",
            SemanticData.getAllTriples(),
            SemanticData.getAllParkingAreas(),
            SemanticData.getAllSpacesOfParkingArea(10),
            SemanticData.getSampleNumberOfSensor(9, 67),
            SemanticData.getLastSampleOfSensor(7, 55),
            SemanticData.getLastResultsOfEachSensorInArea(9),
            SemanticData.getLastResultsOfEachSensorInManyAreas(setOf(9, 8, 2)),
            SemanticData.getNumberOfCurrentlyFreeSpacesInArea(9)
        )
    }

    public fun evalConfigFile(configFileName: String) {
        val json = JsonParser().fileToJson(configFileName)as JsonParserObject
        json.getOrDefault("outputDirectory", Configuration.defaultOutputDirectory + "/" + configFileName.substring(configFileName.lastIndexOf("/") + 1, configFileName.lastIndexOf(".")))
        val runs = MultipleSimulationRuns(json, json.getOrDefault("repeatSimulationCount", 1))
        runs.startSimulationRuns()
        File(configFileName).withOutputStream { out -> // this reformats the json file, such that all files are structurally equal
            out.println(JsonParser().jsonToString(json, true))
        }
    }
    public fun evalConfigFileMerge(configFileNames: List<String>) {
        val json = JsonParser().fileMergeToJson(configFileNames)as JsonParserObject
        var outputdirectoryTmp = Configuration.defaultOutputDirectory + "/"
        for (n in configFileNames) {
            val a = n.lastIndexOf("/") + 1
            val b = n.lastIndexOf(".")
            val t = if (a >= 0) {
                if (b >= 0) {
                    n.substring(a, b)
                } else {
                    n.substring(a, n.length)
                }
            } else {
                if (b >= 0) {
                    n.substring(0, b)
                } else {
                    n
                }
            }
            if (outputdirectoryTmp == "") {
                outputdirectoryTmp += t
            } else {
                outputdirectoryTmp += "_$t"
            }
        }
        val outputdirectory = json.getOrDefault("outputDirectory", outputdirectoryTmp) + "/"
        File(outputdirectory).mkdirs()
        File(outputdirectory + ".generated.parsed.json").withOutputStream { out -> // this reformats the json file, such that all files are structurally equal
            out.println(JsonParser().jsonToString(json, false))
        }
        val runs = MultipleSimulationRuns(json, json.getOrDefault("repeatSimulationCount", 1))
        runs.startSimulationRuns()
        File(outputdirectory + ".generated.used.json").withOutputStream { out -> // this reformats the json file, such that all files are structurally equal
            out.println(JsonParser().jsonToString(json, true))
        }
    }
    public fun evalConfigFiles(configFileNames: Set<String>) {
        for ((index, configFileName) in configFileNames.withIndex()) {
            evalConfigFile(configFileName)
            println("evalQueryProcessingCentralizedCase: Run ${index + 1} finished. ${configFileNames.size - index - 1 } runs left..")
        }
    }
    public fun evalConfigFilesMerge(configFileNames: Set<List<String>>) {
        for ((index, configFileName) in configFileNames.withIndex()) {
            evalConfigFileMerge(configFileName)
            println("evalQueryProcessingCentralizedCase: Run ${index + 1} finished. ${configFileNames.size - index - 1 } runs left..")
        }
    }
    public fun evalQueryProcessingCentralizedCase() {
        evalConfigFilesMerge(
            setOf(
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q0.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q1.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q2.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q3.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q4.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q5.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q6.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q7.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/central.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q8.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
            )
        )
    }

    public fun evalQueryProcessingDistributedCaseDummy() {
        evalConfigFileMerge(listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/dummy.json"))
    }

    public fun evalQueryProcessingDistributedCase() {
        evalConfigFilesMerge(
            setOf(
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q0.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q1.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q2.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q3.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q4.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q5.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q6.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q7.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
                listOf("src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/Q8.json", "src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json",),
            )
        )
    }

    private fun getSamplingNumber(): IntArray {
        val arr = IntArray(12)
        arr[0] = 0
        arr[1] = 1
        arr[2] = 50
        for (index in 3 until 12) {
            arr[index] = arr[index - 1] + 50
        }
        return arr
    }

    public fun evalCampusNumberOfSamplings() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/campusNumberOfSampling.json"
        val range = getSamplingNumber()
        for ((run, numOfSamples) in range.withIndex()) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val sensorType = json.getOrEmptyArray("sensorType")
            val sensorType0 = sensorType.firstOrEmptyObject()
            sensorType0["maxSamples"] = numOfSamples
            MultipleSimulationRuns(json, 1).startSimulationRuns()
            println("evalQueryProcessingDistributedCase: Run ${run + 1} finished. ${501 - run - 1 } runs left..")
        }
    }

    public fun evalCampusDistributedSampling() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/campusDistributedSampling.json"
        for (run in 0 until 11) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val deviceType = json.getOrEmptyArray("deviceType")
            for (instance in 1 until run + 1) {
                val dev = deviceType[instance] as JsonParserObject
                dev["database"] = true
            }
            MultipleSimulationRuns(json, 1).startSimulationRuns()
            println("evalQueryProcessingDistributedCase: Run ${run + 1} finished. ${11 - run - 1 } runs left..")
        }
    }

    public fun evalMeshPerformance() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/meshPerformance.json"
        var ranges = getMeshPerfRanges()
        ranges = addInitialBuffer(ranges, 4)
        for ((index, range) in ranges.withIndex()) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val linkType = json.getOrEmptyArray("linkType")
            val linkType0 = linkType.firstOrEmptyObject()
            linkType0["rangeInMeters"] = range
            MultipleSimulationRuns(json, 1).startSimulationRuns()
            println("evalMeshPerformance: Run ${index + 1} finished. ${ranges.size - index - 1 } runs left..")
        }
    }

    public fun evalStarPerformanceWithLuposdate() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(22, 50) // max. 1171 instances are possible.
        nodeSizes = addInitialBuffer(nodeSizes, 4)
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val randomStarNetwork = json.getOrEmptyArray("randomStarNetwork")
            val randomStarNetwork0 = randomStarNetwork.firstOrEmptyObject()
            val deviceType = json.getOrEmptyArray("deviceType")
            val deviceType0 = deviceType.firstOrEmptyObject()
            val database = json.getOrEmptyObject("database")
            randomStarNetwork0["number"] = numberOfNodes
            deviceType0["database"] = true
            database["type"] = "Luposdate3000"
            MultipleSimulationRuns(json, 100).startSimulationRuns()
            println("evalStarPerformanceWithLuposdate: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }

    public fun evalStarPerformanceWithDummy() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(22, 50)
        nodeSizes = addInitialBuffer(nodeSizes, 4)
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val randomStarNetwork = json.getOrEmptyArray("randomStarNetwork")
            val randomStarNetwork0 = randomStarNetwork.firstOrEmptyObject()
            val deviceType = json.getOrEmptyArray("deviceType")
            val deviceType0 = deviceType.firstOrEmptyObject()
            val database = json.getOrEmptyObject("database")
            randomStarNetwork0["number"] = numberOfNodes
            deviceType0["database"] = true
            database["type"] = "Dummy"
            MultipleSimulationRuns(json, 100).startSimulationRuns()
            println("evalStarPerfWithDummy: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }

    public fun evalStarPerformance() {
        val configFileName = "src/luposdate3000_simulator_iot/src/jvmMain/resources/starPerformance.json"
        var nodeSizes = buildNodeSizesArray(21, 1000)
        nodeSizes = addInitialBuffer(nodeSizes, 4)
        for ((index, numberOfNodes) in nodeSizes.withIndex()) {
            val json = JsonParser().fileToJson(configFileName)as JsonParserObject
            val randomStarNetwork = json.getOrEmptyArray("randomStarNetwork")
            val randomStarNetwork0 = randomStarNetwork.firstOrEmptyObject()
            val deviceType = json.getOrEmptyArray("deviceType")
            val deviceType0 = deviceType.firstOrEmptyObject()
            val database = json.getOrEmptyObject("database")
            randomStarNetwork0["number"] = numberOfNodes
            deviceType0["database"] = false
            database["type"] = "Dummy"
            MultipleSimulationRuns(json, 100).startSimulationRuns()
            println("evalStarPerf: Run ${index + 1} finished. ${nodeSizes.size - index - 1 } runs left..")
        }
    }

    private fun addInitialBuffer(arr: IntArray, bufferSize: Int): IntArray {
        val updatedArray = IntArray(arr.size + bufferSize)
        for (i in updatedArray.indices) {
            if (i < bufferSize) {
                updatedArray[i] = arr[i]
            } else {
                updatedArray[i] = arr[i - bufferSize]
            }
        }
        return updatedArray
    }
}

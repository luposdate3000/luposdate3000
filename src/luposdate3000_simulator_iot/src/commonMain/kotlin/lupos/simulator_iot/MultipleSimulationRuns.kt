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
import lupos.parser.JsonParserObject
import lupos.shared.inline.File
import kotlin.math.sqrt
internal class MultipleSimulationRuns(
    private val json: JsonParserObject,
    private val numberOfRepetitions: Int,
) {
    init {
        json.getOrEmptyObject("logging").getOrEmptyObject("measure").set("enabled", true)
    }
    private val measurements: MutableList<LoggerMeasure> = mutableListOf()

    internal fun startSimulationRuns() {
        for (repetition in 1..numberOfRepetitions) {
            startSimulationRun()
        }
        evaluate()
    }

    private fun startSimulationRun() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig(json, "", false)
        simRun.startSimulation(config)
        for (logger in simRun.logger.loggers) {
            if (logger is LoggerMeasure) {
                measurements.add(logger)
            }
        }
    }

    private fun evaluate() {
        fun appendLineToFile(f: File, l: String, append: Boolean) {
            val stream = f.openOutputStream(append)
            stream.println(l)
            stream.close()
        }
        if (measurements.size> 0) {
            val firstLogger = measurements.first()
            val outputDirectory = json.getOrDefault("outputDirectory", "simulation_output")
            File(outputDirectory).mkdirs()
            val fileMea = File(outputDirectory + "/measurement.csv")
            val fileAvg = File(outputDirectory + "/average.csv")
            val fileDev = File(outputDirectory + "/deviation.csv")
            val fileDevp = File(outputDirectory + "/deviationPercent.csv")
            if (!fileMea.exists()) {
                appendLineToFile(fileMea, firstLogger.headers.toList().joinToString(","), false)
            }
            if (!fileAvg.exists()) {
                appendLineToFile(fileAvg, firstLogger.headers.toList().joinToString(","), false)
            }
            if (!fileDev.exists()) {
                appendLineToFile(fileDev, firstLogger.headers.toList().joinToString(","), false)
            }
            if (!fileDevp.exists()) {
                appendLineToFile(fileDevp, firstLogger.headers.toList().joinToString(","), false)
            }
            val dataAvg = DoubleArray(LoggerMeasure.StatCounter)
            val dataDev = DoubleArray(LoggerMeasure.StatCounter)
            val dataDevp = DoubleArray(LoggerMeasure.StatCounter)
            for (i in 0 until LoggerMeasure.StatCounter) {
                var sum = 0.0
                for (m in measurements) {
                    sum += m.data[i]
                }
                val avg = sum / measurements.size
                var dev = 0.0
                for (m in measurements) {
                    dev += (m.data[i] - avg) * (m.data[i] - avg)
                }
                val devPercent = if (avg == 0.0) {
                    0.0
                } else {
                    sqrt(dev / measurements.size) * 100 / avg
                }
                dataAvg[i] = avg
                dataDev[i] = dev
                dataDevp[i] = devPercent
            }
for (m in measurements) {
                appendLineToFile(fileMea, m.data.toList().joinToString(","), true)
}
            appendLineToFile(fileAvg, dataAvg.toList().joinToString(","), true)
            appendLineToFile(fileDev, dataDev.toList().joinToString(","), true)
            appendLineToFile(fileDevp, dataDevp.toList().joinToString(","), true)
        }
    }
}

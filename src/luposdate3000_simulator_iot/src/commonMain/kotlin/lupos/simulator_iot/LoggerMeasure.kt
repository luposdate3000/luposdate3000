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
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import lupos.shared.XMLElement
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
import lupos.simulator_db.dummyImpl.ChoosenOperatorPackage
import lupos.simulator_db.dummyImpl.PreprocessingPackage
import lupos.simulator_db.dummyImpl.ResultPackage
import lupos.simulator_db.luposdate3000.MySimulatorAbstractPackage
import lupos.simulator_db.luposdate3000.MySimulatorOperatorGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.models.routing.DAO
import lupos.simulator_iot.models.routing.DIO
import lupos.simulator_iot.models.sensor.ParkingSample
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
public class LoggerMeasure public constructor(private val simRun: SimulationRun) : ILogger {
    public companion object {
        public var StatCounter: Int = 0
        public val StatNumberOfDevices: Int = StatCounter++
        public val StatNumberOfSensorDevices: Int = StatCounter++
        public val StatNumberOfDatabaseDevices: Int = StatCounter++

        public val StatSimulationStartupDurationReal: Int = StatCounter++
        public val StatSimulationShutdownDurationReal: Int = StatCounter++
        public val StatSimulationDurationReal: Int = StatCounter++
        public val StatSimulationDurationVirtual: Int = StatCounter++

        public val StatNetworkCounterParkingSample: Int = StatCounter++
        public val StatNetworkCounterQuery: Int = StatCounter++
        public val StatNetworkCounterResponse: Int = StatCounter++
        public val StatNetworkCounterOperatorGraph: Int = StatCounter++
        public val StatNetworkCounterIntermediateResults: Int = StatCounter++
        public val StatNetworkCounterGraphCreate: Int = StatCounter++
        public val StatNetworkCounterGraphUpdate: Int = StatCounter++
        public val StatNetworkCounterDictionary: Int = StatCounter++

        public val StatNetworkCounterRoutingDIO: Int = StatCounter++
        public val StatNetworkCounterRoutingDAO: Int = StatCounter++
        public val StatNetworkCounterForwarded: Int = StatCounter++
        public val StatNetworkCounter: Int = StatCounter++

        public val StatNetworkTrafficParkingSample: Int = StatCounter++
        public val StatNetworkTrafficQuery: Int = StatCounter++
        public val StatNetworkTrafficResponse: Int = StatCounter++
        public val StatNetworkTrafficOperatorGraph: Int = StatCounter++
        public val StatNetworkTrafficIntermediateResults: Int = StatCounter++
        public val StatNetworkTrafficGraphCreate: Int = StatCounter++
        public val StatNetworkTrafficGraphUpdate: Int = StatCounter++
        public val StatNetworkTrafficDictionary: Int = StatCounter++

        public val StatNetworkTrafficRouting: Int = StatCounter++
        public val StatNetworkTrafficForwarded: Int = StatCounter++
        public val StatNetworkTraffic: Int = StatCounter++
    }
    public val data: DoubleArray = DoubleArray(StatCounter)
    public val headers: Array<String> = Array(StatCounter) {
        when (it) {
            StatNumberOfDevices -> "number of devices"
            StatNumberOfSensorDevices -> "number of sensor devices"
            StatNumberOfDatabaseDevices -> "number of database devices"

            StatSimulationStartupDurationReal -> "simulation startup duration real (Seconds)"
            StatSimulationShutdownDurationReal -> "simulation shutdown duration real (Seconds)"
            StatSimulationDurationReal -> "simulation duration real (Seconds)"
            StatSimulationDurationVirtual -> "simulation duration virtual (Seconds)"

            StatNetworkCounterParkingSample -> "number of virtually sent parking samples packages"
            StatNetworkCounterQuery -> "number of virtually sent query packages"
            StatNetworkCounterResponse -> "number of virtually sent response packages"
            StatNetworkCounterOperatorGraph -> "number of virtually sent operatorGraph packages"
            StatNetworkCounterIntermediateResults -> "number of virtually sent intermediate results packages"
            StatNetworkCounterGraphCreate -> "number of virtually sent graph create packages"
            StatNetworkCounterGraphUpdate -> "number of virtually sent graph update packages"
            StatNetworkCounterDictionary -> "number of virtually sent dictionary packages"

            StatNetworkCounterRoutingDIO -> "number of sent DIO packages"
            StatNetworkCounterRoutingDAO -> "number of sent DAO packages"
            StatNetworkCounterForwarded -> "number of forwarded packages"
            StatNetworkCounter -> "number of sent packages"

            StatNetworkTrafficParkingSample -> "virtual network traffic parking samples(Bytes)"
            StatNetworkTrafficQuery -> "virtual network traffic query(Bytes)"
            StatNetworkTrafficResponse -> "virtual network traffic response(Bytes)"
            StatNetworkTrafficOperatorGraph -> "virtual network traffic operatorGraph(Bytes)"
            StatNetworkTrafficIntermediateResults -> "virtual network traffic intermediate results(Bytes)"
            StatNetworkTrafficGraphCreate -> "virtual network traffic graph create(Bytes)"
            StatNetworkTrafficGraphUpdate -> "virtual network traffic graph update(Bytes)"
            StatNetworkTrafficDictionary -> "virtual network traffic dictionary(Bytes)"

            StatNetworkTrafficRouting -> "network traffic routing(Bytes)"
            StatNetworkTrafficForwarded -> "network traffic forwarded(Bytes)"
            StatNetworkTraffic -> "network traffic total (Bytes)"

            else -> TODO("$it")
        }
    }
    private lateinit var startSimulationTimeStamp: Instant
    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant

    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {
        data[StatNetworkTraffic] += pck.getSizeInBytes().toDouble()
        if (dest != hop) {
            data[StatNetworkCounterForwarded]++
            data[StatNetworkTrafficForwarded] += pck.getSizeInBytes().toDouble()
        }
    }
    override fun onReceiveNetworkPackage(address: Int, pck: IPayload) {
        data[StatNetworkCounter]++
        when (pck) {
            is DIO -> {
                data[StatNetworkCounterRoutingDIO]++
                data[StatNetworkTrafficRouting] += pck.getSizeInBytes().toDouble()
            }
            is DAO -> {
                data[StatNetworkCounterRoutingDAO]++
                data[StatNetworkTrafficRouting] += pck.getSizeInBytes().toDouble()
            }
        }
    }
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {
/*
        if (pck is MySimulatorAbstractPackage) {
            if (pck.path == "/distributed/query/dictionary/register" || pck.path == "/distributed/query/dictionary/remove") {
                TODO("this is completely wasted network traffic")
            }
        }
*/
    }
    override fun onReceivePackage(address: Int, pck: IPayload) {
        when (pck) {
            is MySimulatorTestingImportPackage, is MySimulatorTestingExecute, is MySimulatorTestingCompareGraphPackage -> { // testing only ... this must not crash, but does not count for network traffic
            }
            is PreprocessingPackage, is ResultPackage, is ChoosenOperatorPackage -> { // dummyImpl
            }
            is MySimulatorAbstractPackage -> {
                when (pck.path) {
                    "/distributed/query/dictionary/register", "/distributed/query/dictionary/remove" -> {
                        data[StatNetworkTrafficDictionary] += pck.getSizeInBytes().toDouble()
                        data[StatNetworkCounterDictionary]++
                    }
                    "/distributed/graph/create" -> {
                        data[StatNetworkTrafficGraphCreate] += pck.getSizeInBytes().toDouble()
                        data[StatNetworkCounterGraphCreate]++
                    }
                    "/distributed/graph/modify" -> {
                        data[StatNetworkTrafficGraphUpdate] += pck.getSizeInBytes().toDouble()
                        data[StatNetworkCounterGraphUpdate]++
                    }
                    "simulator-intermediate-result" -> {
                        data[StatNetworkTrafficIntermediateResults] += pck.getSizeInBytes().toDouble()
                        data[StatNetworkCounterIntermediateResults]++
                    }
                    else -> TODO(pck.path)
                }
            }
            is MySimulatorOperatorGraphPackage -> {
                data[StatNetworkTrafficOperatorGraph] += pck.getSizeInBytes().toDouble()
                data[StatNetworkCounterOperatorGraph]++
            }
            is QueryResponsePackage -> {
                data[StatNetworkTrafficResponse] += pck.getSizeInBytes().toDouble()
                data[StatNetworkCounterResponse]++
            }
            is ParkingSample -> {
                data[StatNetworkTrafficParkingSample] += pck.getSizeInBytes().toDouble()
                data[StatNetworkCounterParkingSample]++
            }
            is QueryPackage -> {
                data[StatNetworkTrafficQuery] += pck.getSizeInBytes().toDouble()
                data[StatNetworkCounterQuery]++
            }
            else -> TODO("$pck")
        }
    }
    override fun addWork(queryID: Int, address: Int, operatorGraph: XMLElement, keysIn: Set<String>, keysOut: Set<String>) {}
    override fun addOperatorGraph(queryId: Int, operatorGraph: MutableMap<String, XMLElement>) {}
    override fun addConnectionTable(src: Int, dest: Int, hop: Int) { }
    override fun addConnectionTableDB(src: Int, dest: Int, hop: Int) {}

    override fun onStartSimulation() { // phase 1
        startSimulationTimeStamp = Clock.System.now()
    }
    override fun onStartUp() { // phase 2
        startUpTimeStamp = Clock.System.now()
        data[StatSimulationStartupDurationReal] = (startUpTimeStamp - startSimulationTimeStamp).inWholeNanoseconds.toDouble() / 1000000000.0
    }
    override fun onSteadyState() { // phase 3
    }
    override fun onShutDown() { // phase 4
        var shutDownTimeStampVirtual = startSimulationTimeStamp.plus(simRun.sim.clock, DateTimeUnit.NANOSECOND, TimeZone.UTC)
        shutDownTimeStamp = Clock.System.now()
        data[StatSimulationDurationReal] = (shutDownTimeStamp - startSimulationTimeStamp).inWholeNanoseconds.toDouble() / 1000000000.0
        data[StatSimulationDurationVirtual] = (shutDownTimeStampVirtual - startSimulationTimeStamp).inWholeNanoseconds.toDouble() / 1000000000.0
    }
    override fun onStopSimulation() { // phase 5
        val stopSimulationTimeStamp = Clock.System.now()
        data[StatSimulationShutdownDurationReal] = (stopSimulationTimeStamp - shutDownTimeStamp).inWholeNanoseconds.toDouble() / 1000000000.0
    }
    override fun addDevice(address: Int, x: Double, y: Double, hasDBStore: Boolean, hasDBQuery: Boolean, hasSensor: Boolean) {
        data[StatNumberOfDevices]++
        if (hasSensor) {
            data[StatNumberOfSensorDevices]++
        }
        if (hasDBStore || hasDBQuery) {
            data[StatNumberOfDatabaseDevices]++
        }
    }
}

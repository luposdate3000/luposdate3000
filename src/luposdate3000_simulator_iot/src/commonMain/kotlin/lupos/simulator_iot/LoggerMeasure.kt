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
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_db.QueryPackage
import lupos.simulator_iot.models.routing.DAO
import lupos.simulator_iot.models.routing.DIO
import lupos.simulator_iot.models.sensor.ParkingSample
import kotlin.time.ExperimentalTime
@OptIn(ExperimentalTime::class)
public class LoggerMeasure public constructor(private val simRun: SimulationRun) : ILogger {
    public companion object {
        public const val StatNumberOfDevices: Int = 0
        public const val StatNumberOfSensorDevices: Int = 1
        public const val StatNumberOfDatabaseDevices: Int = 2
        public const val StatNumberOfQuerySenders: Int = 3 // TODO
        public const val StatNumberOfLinks: Int = 4 // TODO
        public const val StatSimulationStartupDurationReal: Int = 5
        public const val StatSimulationShutdownDurationReal: Int = 6
        public const val StatSimulationDurationReal: Int = 7
        public const val StatSimulationDurationVirtual: Int = 8
        public const val StatNumberOfSentSamplePackages: Int = 9
        public const val StatNumberOfQueriesRequested: Int = 10
        public const val StatNumberOfSentDatabasePackages: Int = 11
        public const val StatNumberOfSentDIOPackages: Int = 12
        public const val StatNumberOfSentDAOPackages: Int = 13
        public const val StatNumberOfForwardedPackages: Int = 14
        public const val StatNumberOfSentPackages: Int = 15
        public const val StatNetworkTrafficInBytes: Int = 16
        public const val StatCounter: Int = 17
    }
    public val data: DoubleArray = DoubleArray(StatCounter)
    public val headers: Array<String> = Array(StatCounter) {
        when (it) {
            StatNumberOfDevices -> "number of devices"
            StatNumberOfSensorDevices -> "number of sensor devices"
            StatNumberOfDatabaseDevices -> "number of database devices"
            StatNumberOfQuerySenders -> "number of query senders"
            StatNumberOfLinks -> "number of links"
            StatSimulationStartupDurationReal -> "simulation startup duration real (Seconds)"
            StatSimulationShutdownDurationReal -> "simulation shutdown duration real (Seconds)"
            StatSimulationDurationReal -> "simulation duration real (Seconds)"
            StatSimulationDurationVirtual -> "simulation duration virtual (Seconds)"
            StatNumberOfSentPackages -> "number of sent packages"
            StatNetworkTrafficInBytes -> "network traffic (Bytes)"
            StatNumberOfSentSamplePackages -> "number of sent sample packages"
            StatNumberOfQueriesRequested -> "number of queries requested"
            StatNumberOfSentDatabasePackages -> "number of sent database packages"
            StatNumberOfSentDIOPackages -> "number of sent DIO packages"
            StatNumberOfSentDAOPackages -> "number of sent DAO packages"
            StatNumberOfForwardedPackages -> "number of forwarded packages"
            else -> TODO("$it")
        }
    }
    private lateinit var startSimulationTimeStamp: Instant
    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant

    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {
        data[StatNetworkTrafficInBytes] += pck.getSizeInBytes().toDouble()
        if (dest != hop) {
            data[StatNumberOfForwardedPackages]++
        }
    }
    override fun onReceiveNetworkPackage(address: Int, pck: IPayload) {
        data[StatNumberOfSentPackages]++
        when (pck) {
            is DIO -> data[StatNumberOfSentDIOPackages]++
            is DAO -> data[StatNumberOfSentDAOPackages]++
        }
    }
    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {}
    override fun onReceivePackage(address: Int, pck: IPayload) {
        when (pck) {
            is ParkingSample -> data[StatNumberOfSentSamplePackages]++
            is QueryPackage -> data[StatNumberOfQueriesRequested]++
            is IDatabasePackage -> data[StatNumberOfSentDatabasePackages]++
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

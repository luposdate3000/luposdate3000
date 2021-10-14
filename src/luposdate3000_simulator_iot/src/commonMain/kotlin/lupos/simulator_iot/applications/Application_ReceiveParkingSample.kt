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
package lupos.simulator_iot.applications

import lupos.shared.SanityCheck
import lupos.simulator_iot.IPayload
import lupos.simulator_iot.Package_Query
import lupos.simulator_iot.Package_QueryResponse

public class Application_ReceiveParkingSample(private val ownAddress: Int) : IApplicationStack_Actuator {
    private lateinit var parent: IApplicationStack_Middleware
    private val idCacheRequested = mutableMapOf<Int, Int>() // queryID -> sensorID
    private val pendingPackages = mutableListOf<Package_Application_ParkingSample>()
    private val idSampleInserted2 = mutableSetOf<Int>() // sensorID
    private val idSampleInserted3 = mutableSetOf<Int>() // queryID

    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun startUp() {
    }

    override fun shutDown() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/Application_ReceiveParkingSample.kt:38"/*SOURCE_FILE_END*/ },
            { idSampleInserted3.size == 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/Application_ReceiveParkingSample.kt:42"/*SOURCE_FILE_END*/ },
            { idCacheRequested.size == 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/Application_ReceiveParkingSample.kt:46"/*SOURCE_FILE_END*/ },
            { pendingPackages.size == 0 }
        )
    }

    private fun sendPackage(pck: Package_Application_ParkingSample) {
        val query = StringBuilder()
        query.appendLine("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>")
        query.appendLine("PREFIX parking: <https://github.com/luposdate3000/parking#>")
        query.appendLine("PREFIX sosa: <http://www.w3.org/ns/sosa/>")
        query.appendLine("PREFIX ssn: <http://www.w3.org/ns/ssn/>")
        query.appendLine("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>")
        query.appendLine("")
        query.appendLine("INSERT {")
        query.appendLine("_:Observation a sosa:Observation .")
        query.appendLine("_:Observation sosa:hasFeatureOfInterest parking:AvailableParkingSpaces .")
        query.appendLine("_:Observation sosa:hasSimpleResult \"${pck.isOccupied}\"^^xsd:boolean .")
        query.appendLine("_:Observation sosa:madeBySensor ?Sensor .")
        query.appendLine("_:Observation sosa:observedProperty ?ParkingSlotLocation .")
        query.appendLine("_:Observation sosa:phenomenonTime \"${pck.sampleTime}\"^^xsd:dateTime .")
        query.appendLine("_:Observation sosa:resultTime \"${pck.sampleTime}\"^^xsd:dateTime .")
        query.appendLine("_:Observation sosa:usedProcedure parking:SensorOnEachSlot .")
        query.appendLine("_:Observation ssn:wasOriginatedBy parking:CarMovement .")
        query.appendLine("?Sensor sosa:madeObservation _:Observation .")
        query.appendLine("} WHERE {")
        query.appendLine("?ParkingSlotLocation sosa:isObservedBy ?Sensor .")
        query.appendLine("?Sensor parking:sensorID \"${pck.sensorID}\"^^xsd:integer .")
        query.appendLine("}")
        val pckQuery = Package_Query(ownAddress, query.toString().encodeToByteArray())
        idSampleInserted3.add(pckQuery.queryID)
        parent.send(ownAddress, pckQuery)
    }

    override fun receive(pck: IPayload): IPayload? {
        if (pck is Package_Application_ParkingSample) {
            if (idCacheRequested.values.contains(pck.sensorID)) {
                pendingPackages.add(pck)
            } else if (idSampleInserted2.contains(pck.sensorID)) {
                sendPackage(pck)
            } else {
                val query = StringBuilder()
                query.appendLine("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>")
                query.appendLine("PREFIX parking: <https://github.com/luposdate3000/parking#>")
                query.appendLine("PREFIX sosa: <http://www.w3.org/ns/sosa/>")
                query.appendLine("PREFIX ssn: <http://www.w3.org/ns/ssn/>")
                query.appendLine("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>")
                query.appendLine("")
                query.appendLine("INSERT DATA {")
                query.appendLine("parking:AvailableParkingSpaces a sosa:FeatureOfInterest .")
                query.appendLine("parking:CarMovement a ssn:Stimulus .")
                query.appendLine("parking:SensorOnEachSlot a sosa:Procedure .")
                query.appendLine("parking:AvailableParkingSpaces ssn:hasProperty _:ParkingSlotLocation .")
                query.appendLine("parking:CarMovement ssn:isProxyFor _:ParkingSlotLocation .")
                query.appendLine("_:ParkingSlotLocation a sosa:ObservableProperty .")
//                query.appendLine("_:ParkingSlotLocation geo:lat ${latitude} .")
//                query.appendLine("_:ParkingSlotLocation geo:long ${longitude} .")
                query.appendLine("_:ParkingSlotLocation parking:area \"${pck.area}\"^^xsd:integer .")
                query.appendLine("_:ParkingSlotLocation parking:spotInArea \"${pck.spotInArea}\"^^xsd:integer .")
                query.appendLine("_:ParkingSlotLocation sosa:isObservedBy _:Sensor .")
                query.appendLine("_:ParkingSlotLocation ssn:isPropertyOf parking:AvailableParkingSpaces .")
                query.appendLine("_:Sensor a sosa:Sensor .")
                query.appendLine("_:Sensor parking:sensorID \"${pck.sensorID}\"^^xsd:integer .")
                query.appendLine("_:Sensor sosa:observes _:ParkingSlotLocation .")
                query.appendLine("_:Sensor ssn:detects parking:CarMovement .")
                query.appendLine("_:Sensor ssn:implements parking:SensorOnEachSlot .")
                query.appendLine("}")
                val pckQuery = Package_Query(ownAddress, query.toString().encodeToByteArray())
                idCacheRequested[pckQuery.queryID] = pck.sensorID
                pendingPackages.add(pck)
                parent.send(ownAddress, pckQuery)
            }
            return null
        } else if (pck is Package_QueryResponse) {
            val sensorID = idCacheRequested[pck.queryID]
            if (sensorID != null) {
                idSampleInserted2.add(sensorID)
                idCacheRequested.remove(pck.queryID)
                for (i in 0 until pendingPackages.size) {
                    val pck = pendingPackages[pendingPackages.size - 1 - i]
                    if (idSampleInserted2.contains(pck.sensorID)) {
                        sendPackage(pck)
                        pendingPackages.removeAt(i)
                    }
                }
                return null
            } else if (idSampleInserted3.contains(pck.queryID)) {
                idSampleInserted3.remove(pck.queryID)
                return null
            } else {
                return pck
            }
        } else {
            return pck
        }
    }
}

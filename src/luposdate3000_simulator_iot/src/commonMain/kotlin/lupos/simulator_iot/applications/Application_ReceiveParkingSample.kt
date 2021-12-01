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

import lupos.simulator_iot.IPayload
import lupos.simulator_iot.Package_Query

public class Application_ReceiveParkingSample(private val ownAddress: Int) : IApplicationStack_Actuator {
    private lateinit var parent: IApplicationStack_Middleware
    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun startUp() {
    }

    override fun shutDown() {
    }

    override fun receive(pck: IPayload): IPayload? {
        if (pck is Package_Application_ParkingSample) {
            val query = StringBuilder()
            query.appendLine("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>")
            query.appendLine("PREFIX parking: <https://github.com/luposdate3000/parking#>")
            query.appendLine("")
            query.appendLine("INSERT DATA {")
            query.appendLine(" _:b0 a parking:Observation ;")
            query.appendLine(" parking:area \"${pck.area}\"^^xsd:integer ;")
            query.appendLine(" parking:sensorID \"${pck.sensorID}\"^^xsd:integer ;")
            query.appendLine(" parking:spotInArea \"${pck.spotInArea}\"^^xsd:integer ;")
            query.appendLine(" parking:isOccupied \"${pck.isOccupied}\"^^xsd:boolean ;")
            query.appendLine(" parking:resultTime \"${pck.sampleTime}\"^^xsd:dateTime .")
            query.appendLine("}")
            parent.send(ownAddress, Package_Query(ownAddress, query.toString().encodeToByteArray()))
            return null
        } else {
            return pck
        }
    }
}

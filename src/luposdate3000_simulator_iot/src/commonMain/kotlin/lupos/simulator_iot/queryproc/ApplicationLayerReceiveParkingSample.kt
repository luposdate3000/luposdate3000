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
package lupos.simulator_iot.queryproc
import lupos.simulator_db.IPayload
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.Package_Query
import lupos.simulator_iot.models.sensor.ParkingSample
public class ApplicationLayerReceiveParkingSample(private val ownAddress: Int) : IApplicationStack_Actuator {
    private lateinit var parent: IApplicationStack_Middleware
    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }
    override fun startUp() {
    }
    override fun shutDown() {
    }
    override fun receive(pck: IPayload): IPayload? {
        if (pck is ParkingSample) {
            val query = SemanticData.getInsertQueryString(pck)
            parent.send(ownAddress, Package_Query(ownAddress, query.encodeToByteArray()))
            return null
        } else {
            return pck
        }
    }
    override fun timerEvent() {}
}

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
package lupos.simulator_db.luposdate3000

import lupos.shared.inline.File
import lupos.simulator_core.ITimer
import simora.simulator_iot.IPackage_Database
import simora.simulator_iot.IPayload
import simora.simulator_iot.applications.IApplicationStack_Actuator
import simora.simulator_iot.applications.IApplicationStack_Middleware

public class Application_OntologySender(
    internal val startClockInSec: Int,
    internal val queryPck: IPackage_Database,
    internal val receiver: Int,
) : IApplicationStack_Actuator, ITimer {
    public constructor(
        startClockInSec: Int,
        ontologyFileName: String,
        receiver: Int
    ) : this(startClockInSec, Package_Luposdate3000_Abstract(-1, "/shacl/ontology/import", mapOf("data" to File(ontologyFileName).readAsString())), receiver)

    private lateinit var parent: IApplicationStack_Middleware
    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun startUp() {
        parent.registerTimer(startClockInSec.toLong() * 1000000000L, this)
    }

    override fun shutDown() {
    }

    override fun receive(pck: IPayload): IPayload? {
        return pck
    }

    override fun onTimerExpired(clock: Long) {
        parent.send(receiver, queryPck)
        parent.flush()
    }
}

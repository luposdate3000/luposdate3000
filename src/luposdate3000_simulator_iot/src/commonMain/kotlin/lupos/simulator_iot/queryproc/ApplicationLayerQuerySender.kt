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
import lupos.simulator_db.IPackage_Database
import lupos.simulator_db.IPayload
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.Package_Query
public class ApplicationLayerQuerySender(
    internal val startClockInSec: Int,
    internal val sendRateInSec: Int,
    internal val maxNumberOfQueries: Int,
    internal val queryPck: IPackage_Database,
    internal val receiver: Int,
) : IApplicationStack_Actuator {
    public constructor(
        startClockInSec: Int,
        sendRateInSec: Int,
        maxNumberOfQueries: Int,
        query: String,
        receiver: Int
    ) : this(startClockInSec, sendRateInSec, maxNumberOfQueries, Package_Query(receiver, query.encodeToByteArray()), receiver)
    private lateinit var parent: IApplicationStack_Middleware
    private var queryCounter = 0
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
    override fun timerEvent() {
        if (queryCounter <maxNumberOfQueries || maxNumberOfQueries == -1) {
            queryCounter++
            parent.send(receiver, queryPck)
            parent.flush()
            parent.registerTimer(sendRateInSec.toLong() * 1000000000L, this)
        }
    }
}

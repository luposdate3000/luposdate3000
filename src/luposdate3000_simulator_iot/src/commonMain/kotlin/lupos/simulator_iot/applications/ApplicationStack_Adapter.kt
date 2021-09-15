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
import lupos.simulator_core.ITimer
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_BothDirections
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.IPayload
import lupos.simulator_iot.models.Device

public class ApplicationStack_Adapter(
    internal val device: Device,
    private val child: IApplicationStack_Actuator,
    private val hostNameLookUpTable: MutableMap<String, Int>,
) : IApplicationStack_BothDirections {
    init {
        child.setRouter(this)
    }
    override fun setRouter(router: IApplicationStack_Middleware): Unit = TODO("this must not be called as this is the topmost layer")
    override fun getAllChildApplications(): Set<IApplicationStack_Actuator> {
        var res = mutableSetOf<IApplicationStack_Actuator>()
        res.add(child)
        val c = child
        if (c is IApplicationStack_Middleware) {
            res.addAll(c.getAllChildApplications())
        }
        return res
    }
    public override fun receive(pck: IPayload): IPayload? {
        var res = child.receive(pck)
        if (res != null) {
            TODO("$res")
        }
        return null
    }

    public override fun startUp(): Unit = child.startUp()
    public override fun shutDown(): Unit = child.shutDown()
    override fun send(destinationAddress: Int, pck: IPayload): Unit = device.sendRoutedPackage(device.address, destinationAddress, pck)
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray = device.router.getNextDatabaseHops(destinationAddresses)
    override fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = device.setTimer(durationInNanoSeconds, entity)
    override fun flush() {}
    override fun resolveHostName(name: String): Int = hostNameLookUpTable[name]!!
}

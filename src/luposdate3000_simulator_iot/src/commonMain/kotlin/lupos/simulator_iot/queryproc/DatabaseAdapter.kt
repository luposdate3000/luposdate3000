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
import lupos.simulator_db.IUserApplication
import lupos.simulator_db.IUserApplicationBoth
import lupos.simulator_db.IUserApplicationLayer
import lupos.simulator_iot.models.Device

public class DatabaseAdapter(
    private val device: Device,
    private val child: IUserApplication,
) : IUserApplicationBoth {
    init {
        child.setRouter(this)
    }
    override fun setRouter(router: IUserApplicationLayer) {
        TODO("this must not be called as this is the topmost layer")
    }
    override fun getAllChildApplications(): Set<IUserApplication> {
        var res = mutableSetOf<IUserApplication>()
        res.add(child)
        val c = child
        if (c is IUserApplicationLayer) {
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

    public override fun startUp() {
        child.startUp()
    }

    public override fun shutDown() {
        child.shutDown()
    }

    override fun send(destinationAddress: Int, pck: IPayload) {
        device.sendRoutedPackage(device.address, destinationAddress, pck)
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return device.router.getNextDatabaseHops(destinationAddresses)
    }
    override fun timerEvent() {}
    override fun registerTimer(durationInNanoSeconds: Long, entity: IUserApplication) {
        device.setTimer(durationInNanoSeconds, entity::timerEvent)
    }
}

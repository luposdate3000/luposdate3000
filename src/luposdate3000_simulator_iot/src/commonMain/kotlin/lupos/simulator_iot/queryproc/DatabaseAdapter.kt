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

import lupos.shared.inline.File
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IPayload
import lupos.simulator_db.IUserApplication
import lupos.simulator_db.IUserApplicationLayer
import lupos.simulator_db.QueryResponsePackage
import lupos.simulator_db.luposdate3000.PostProcessSend
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.queryproc.pck.DBQueryResultPackage
import lupos.simulator_iot.utils.FilePaths

public class DatabaseAdapter(
    private val device: Device,
) : IUserApplicationLayer {
    private lateinit var child: IUserApplication
    private var pathToStateOfThisDevice = "${FilePaths.dbStates}/device${device.address}"

    override fun getAllChildApplications(): Set<IUserApplication> {
        var res = mutableSetOf<IUserApplication>()
        res.add(child)
        val c = child
        if (c is IUserApplicationLayer) {
            res.addAll(c.getAllChildApplications())
        }
        return res
    }
    override fun addChildApplication(child: IUserApplication) {
        this.child = child
    }
    public override fun receive(pck: IPayload) {
        when (pck) {
            is DBQueryResultPackage -> processDBQueryResultPackage(pck)
            is IDatabasePackage -> {
                child.receive(pck)
            }
            else -> TODO("$pck")
        }
    }

    init {
        File(pathToStateOfThisDevice).mkdirs()
    }

    public override fun startUp() {
        child.startUp()
    }

    public override fun shutDown() {
        child.shutDown()
    }

    private fun processDBQueryResultPackage(pck: DBQueryResultPackage) {
        // TODO write the result with the corresponding query in a file
    }

    override fun send(destinationAddress: Int, pck: IPayload) {
        if (pck is IDatabasePackage) {
            PostProcessSend.process(device.address, destinationAddress, device.simRun.sim.clock, device.simRun.visualisationNetwork, pck)
        }
        if (pck is QueryResponsePackage) {
            val pck2 = DBQueryResultPackage(device.address, destinationAddress, pck.result)
            if (device.address == destinationAddress) {
                processDBQueryResultPackage(pck2)
            } else {
                device.simRun.incNumberOfSentDatabasePackages()
                device.sendRoutedPackage(device.address, destinationAddress, pck2)
            }
        } else {
            if (device.address == destinationAddress) {
                if (pck is IDatabasePackage) {
                    child.receive(pck)
                } else {
                    TODO("$pck")
                }
            } else {
                device.simRun.incNumberOfSentDatabasePackages()
                device.sendRoutedPackage(device.address, destinationAddress, pck)
            }
        }
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return device.router.getNextDatabaseHops(destinationAddresses)
    }
}

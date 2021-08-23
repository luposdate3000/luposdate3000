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
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IPayload
import lupos.simulator_db.IRouter
import lupos.simulator_db.QueryResponsePackage
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.PostProcessSend
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.queryproc.pck.DBQueryResultPackage
import lupos.simulator_iot.utils.FilePaths

public class DatabaseAdapter(internal val device: Device) : IRouter {

    private var pathToStateOfThisDevice = "${FilePaths.dbStates}/device${device.address}"

    public val db: IDatabase = when (device.simRun.config.jsonObjects.database.getOrDefault("type", "Dummy")) {
        "Dummy" -> DatabaseSystemDummy(device.simRun.config.jsonObjects.database)
        "Luposdate3000" -> DatabaseHandle(device.simRun.config.jsonObjects.database)
        else -> TODO()
    }

    private var isActive = false

    init {
        File(pathToStateOfThisDevice).mkdirs()
    }

    internal fun startUp() {
        startSession()
        deactivateSession()
    }

    private fun activateSession() {
        db.activate()
        isActive = true
    }

    private fun deactivateSession() {
        db.deactivate()
        isActive = false
    }

    private fun endSession() {
        db.end()
        isActive = false
    }

    private fun startSession() {
        db.start(object : DatabaseState(
            visualisationNetwork = device.simRun.visualisationNetwork,
            ownAddress = device.address,
            allAddressesStore = device.simRun.config.dbDeviceAddressesStore,
            allAddressesQuery = device.simRun.config.dbDeviceAddressesQuery,
            sender = this@DatabaseAdapter,
            absolutePathToDataDirectory = pathToStateOfThisDevice,
        ) {})
        isActive = true
    }

    internal fun shutDown() {
        activateSession()
        endSession()
    }

    override fun receive(payload: IPayload) {
        when (payload) {
            is DBQueryResultPackage -> processDBQueryResultPackage(payload)
            is IDatabasePackage -> {
                activateSession()
                db.receive(pck)
                deactivateSession()
            }
            else -> TODO("$payload")
        }
    }

    private fun processDBQueryResultPackage(pck: DBQueryResultPackage) {
        // TODO write the result with the corresponding query in a file
    }

    override fun send(destinationAddress: Int, pck: IPayload) {
        require(isActive) { "This DBMS Instance is not active!" }
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
                    db.receive(pck)
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

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
import lupos.simulator_db.IRouter
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.PostProcessSend
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.IPayload
import lupos.simulator_iot.models.sensor.ParkingSample
import lupos.simulator_iot.queryproc.pck.DBInternPackage
import lupos.simulator_iot.queryproc.pck.DBQueryResultPackage
import lupos.simulator_iot.queryproc.pck.DBQuerySenderPackage
import lupos.simulator_iot.queryproc.pck.DBSequenceEndPackage
import lupos.simulator_iot.queryproc.pck.SequencedPackage
import lupos.simulator_iot.utils.FilePaths

public class DatabaseAdapter(internal val device: Device) : IRouter {

    private var pathToStateOfThisDevice = "${FilePaths.dbStates}/device${device.address}"

    private val sequenceKeeper = SequenceKeeper(SequencePackageSenderImpl())

    public val db: IDatabase = when (device.simRun.config.jsonObjects.database.getOrDefault("type", "Dummy")) {
        "Dummy" -> DatabaseSystemDummy(device.simRun.config.jsonObjects.database)
        "Luposdate3000" -> DatabaseHandle(device.simRun.config.jsonObjects.database)
        else -> TODO()
    }

    private lateinit var currentState: DatabaseState

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
        sequenceKeeper.markSequenceEnd()
        isActive = false
    }

    private fun endSession() {
        db.end()
        sequenceKeeper.markSequenceEnd()
        currentState = buildInitialStateObject()
        isActive = false
    }

    private fun startSession() {
        currentState = buildInitialStateObject()
        db.start(currentState)
        isActive = true
    }

    private fun buildInitialStateObject(): DatabaseState {
        return object : DatabaseState(
            visualisationNetwork = device.simRun.visualisationNetwork,
            ownAddress = device.address,
            allAddresses = device.simRun.config.dbDeviceAddresses,
            sender = this@DatabaseAdapter,
            absolutePathToDataDirectory = pathToStateOfThisDevice,
        ) {}
    }

    internal fun shutDown() {
        activateSession()
        endSession()
    }

    internal fun processPackage(payload: IPayload) {
        when (payload) {
            is DBInternPackage -> sequenceKeeper.receive(payload)
            is DBQueryResultPackage -> sequenceKeeper.receive(payload)
            is DBSequenceEndPackage -> sequenceKeeper.receive(payload)
            is DBQuerySenderPackage -> processIDatabasePackage(payload.content)
            else -> throw Exception("undefined payload")
        }
    }

    private fun processDBQueryResultPackage(pck: DBQueryResultPackage) {
        // TODO write the result with the corresponding query in a file
    }

    internal fun processIDatabasePackage(pck: IDatabasePackage) {
        activateSession()
        db.receive(pck)
        deactivateSession()
    }

    internal fun saveParkingSample(sample: ParkingSample) {
        val query = SemanticData.getInsertQueryString(sample)
        val bytes = query.encodeToByteArray()
        val pck = QueryPackage(device.address, bytes)
        PostProcessSend.process(device.address, device.address, device.simRun.sim.clock, device.simRun.visualisationNetwork, pck)
        processIDatabasePackage(pck)
    }

    internal fun isDatabasePackage(pck: IPayload): Boolean {
        return pck is DBInternPackage ||
            pck is DBQueryResultPackage ||
            pck is DBSequenceEndPackage ||
            pck is DBQuerySenderPackage
    }

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
        checkActivation()
        PostProcessSend.process(device.address, destinationAddress, device.simRun.sim.clock, device.simRun.visualisationNetwork, pck)
        if (pck is QueryResponsePackage) {
            sendQueryResponse(destinationAddress, pck)
        } else {
            sendDBInternPackage(destinationAddress, pck)
        }
    }

    private fun sendQueryResponse(destinationAddress: Int, pck: QueryResponsePackage) {
        val myResponsePackage = DBQueryResultPackage(device.address, destinationAddress, pck.result)
        if (device.address == destinationAddress) {
            processDBQueryResultPackage(myResponsePackage)
        } else {
            sequenceKeeper.sendSequencedPackage(myResponsePackage)
        }
    }

    private fun sendDBInternPackage(destinationAddress: Int, pck: IDatabasePackage) {
        val internPck = DBInternPackage(device.address, destinationAddress, pck)
        sequenceKeeper.sendSequencedPackage(internPck)
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return device.router.getNextDatabaseHops(destinationAddresses)
    }

    private fun checkActivation() {
        require(isActive) { "This DBMS Instance is not active!" }
    }

    private inner class SequencePackageSenderImpl : ISequencePackageSender {

        override fun send(pck: SequencedPackage) {
            checkActivation()
            if (pck.destinationAddress != device.address) {
                // ignore self packages
                device.simRun.incNumberOfSentDatabasePackages()
            }
            device.sendRoutedPackage(pck.sourceAddress, pck.destinationAddress, pck as IPayload)
        }

        override fun receive(pck: SequencedPackage) {
            device.simRun.logger?.log("> DB of Device $device receives $pck at clock ${device.simRun.getCurrentSimulationClock()}")
            when (pck) {
                is DBInternPackage -> processIDatabasePackage(pck.content)
                is DBQueryResultPackage -> processDBQueryResultPackage(pck)
                else -> throw Exception("undefined payload")
            }
        }

        override fun getSenderAddress(): Int {
            return device.address
        }
    }
}

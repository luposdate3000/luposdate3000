package lupos.simulator_iot.db

import lupos.shared.inline.File
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IRouter
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.Device
import lupos.simulator_iot.FilePaths
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger
import lupos.simulator_iot.net.IPayload
import lupos.simulator_iot.sensor.ParkingSample

internal class DatabaseAdapter(internal val device: Device, private val isDummy: Boolean) : IRouter {

    private var resultCounter = 0

    private var resultDevicePath = "${FilePaths.queryResult}/device${device.address}"

    private var resultFileName = "$resultDevicePath/file.txt"

    private var pathDevice = "${FilePaths.dbStates}/device${device.address}"

    private val sequenceKeeper = SequenceKeeper(SequencePackageSenderImpl())

    private val db: IDatabase = if (isDummy) DatabaseSystemDummy() else DatabaseHandle()

    private lateinit var currentState: DatabaseState

    internal fun startUp() {
        createFiles()
        currentState = buildInitialStateObject()
        db.start(currentState)
        db.deactivate()
        sequenceKeeper.markSequenceEnd()
    }

    private fun buildInitialStateObject(): DatabaseState {
        return object : DatabaseState(
            ownAddress = device.address,
            allAddresses = Configuration.dbDeviceAddresses,
            sender = this@DatabaseAdapter,
            absolutePathToDataDirectory = pathDevice,
        ) {}
    }

    internal fun shutDown() {
        db.activate()
        db.end()
        sequenceKeeper.markSequenceEnd()
        if (!isDummy) {
            File(pathDevice).deleteRecursively()
        }
        currentState = buildInitialStateObject()
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

    private fun createFiles() {
        File(pathDevice).mkdirs()
        File(resultDevicePath).mkdirs()
        File(resultFileName).withOutputStream { }
    }

    private fun processDBQueryResultPackage(pck: DBQueryResultPackage) {
        val stream = File(resultFileName).openOutputStream(true)
        resultCounter++
        stream.println("Query result number $resultCounter")
        stream.println()
        stream.println(pck.result.decodeToString())
        stream.close()
    }

    internal fun processIDatabasePackage(pck: IDatabasePackage) {
        db.activate()
        db.receive(pck)
        db.deactivate()
        sequenceKeeper.markSequenceEnd()
    }

    internal fun saveParkingSample(sample: ParkingSample) {
        val query = SemanticData.getInsertQueryString(sample)
        val bytes = query.encodeToByteArray()
        val pck = QueryPackage(device.address, bytes)
        processIDatabasePackage(pck)
    }

    internal fun isDatabasePackage(pck: IPayload): Boolean {
        return pck is DBInternPackage
            || pck is DBQueryResultPackage
            || pck is DBSequenceEndPackage
            || pck is DBQuerySenderPackage
    }

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
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

    private inner class SequencePackageSenderImpl : ISequencePackageSender {

        override fun send(pck: SequencedPackage) {
            device.sendRoutedPackage(pck.sourceAddress, pck.destinationAddress, pck as IPayload)
        }

        override fun receive(pck: SequencedPackage) {
            Logger.log("> DB of Device $device receives $pck at clock ${device.simulation.getCurrentClock()}")
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

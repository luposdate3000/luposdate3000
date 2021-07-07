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
            else -> throw Exception("undefined payload")
        }
        sequenceKeeper.markSequenceEndings()
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

    private fun processDBInternPackage(pck: DBInternPackage) {
        db.activate()
        db.receive(pck.content)
        db.deactivate()
    }

    internal fun saveParkingSample(sample: ParkingSample) {
        val query = buildInsertQuery(sample)
        val bytes = query.encodeToByteArray()
        receiveQuery(bytes)
    }

    internal fun processQuery(query: String) {
        val queryBytes = query.encodeToByteArray()
        receiveQuery(queryBytes)
    }

    private fun buildInsertQuery(s: ParkingSample): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "INSERT DATA {\n" +
            "  <observation/${s.sampleID}/sensor/${s.area}/${s.sensorID}> a sosa:Observation;\n" +
            "  sosa:hasFeatureOfInterest <parkingArea/${s.area}>;\n" +
            "  sosa:observedProperty <parkingSpace/${s.parkingSpotID}>;\n" +
            "  sosa:madeBySensor <sensor/${s.area}/${s.sensorID}>;\n" +
            "  sosa:hasSimpleResult \"${s.isOccupied}\"^^xsd:boolean;\n" +
            "  sosa:resultTime \"${s.sampleTime}\"^^xsd:dateTime.\n" +
            "}\n"
    }

    //TODO nutze direkt das QueryPackage
    private fun receiveQuery(data: ByteArray) {
        db.activate()
        db.receive(QueryPackage(device.address, data))
        db.deactivate()
    }

    internal fun isDatabasePackage(pck: IPayload): Boolean {
        return pck is DBInternPackage || pck is DBQueryResultPackage || pck is DBSequenceEndPackage
    }

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
        if (pck is QueryResponsePackage)
            sendQueryResponse(destinationAddress, pck)
         else
            sendDBInternPackage(destinationAddress, pck)
    }

    private fun sendQueryResponse(destinationAddress: Int, pck: QueryResponsePackage) {
        val myResponsePackage = DBQueryResultPackage(device.address, destinationAddress, pck.result)
        if (device.address == destinationAddress)
            processDBQueryResultPackage(myResponsePackage)
        else
            sequenceKeeper.sendSequencedPackage(myResponsePackage)
    }

    private fun sendDBInternPackage(destinationAddress: Int, pck: IDatabasePackage) {
        val internPck = DBInternPackage(device.address, destinationAddress, pck)
        sequenceKeeper.sendSequencedPackage(internPck)
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return device.router.getNextDatabaseHops(destinationAddresses)
    }

    private inner class SequencePackageSenderImpl: ISequencePackageSender {

        override fun send(pck: SequencedPackage) {
            device.sendRoutedPackage(pck.sourceAddress, pck.destinationAddress, pck as IPayload)
        }

        override fun receive(pck: SequencedPackage) {
            when (pck) {
                is DBInternPackage -> processDBInternPackage(pck)
                is DBQueryResultPackage -> processDBQueryResultPackage(pck)
                else -> throw Exception("undefined payload")
            }
        }

        override fun getSenderAddress(): Int {
            return device.address
        }
    }

}

package lupos.simulator_iot.db

import lupos.shared.inline.File
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IRouter
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
            is DBInternPackage -> receive(payload.content)
            is DBQueryResultPackage -> useQueryResult(payload.result)
            else -> throw Exception("undefined payload")
        }
    }

    private fun createFiles() {
        File(pathDevice).mkdirs()
        File(resultDevicePath).mkdirs()
        File(resultFileName).withOutputStream { }
    }

    private fun useQueryResult(result: ByteArray) {
        val stream = File(resultFileName).openOutputStream(true)
        resultCounter++
        stream.println("Query result number $resultCounter")
        stream.println()
        stream.println(result.decodeToString())
        stream.close()
    }

    private fun receive(pck: IDatabasePackage) {
        db.activate()
        db.receive(pck)
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

    private fun receiveQuery(data: ByteArray) {
        db.activate()
        db.receive(QueryPackage(device.address, data))
        db.deactivate()
    }

    internal fun isDatabasePackage(pck: IPayload): Boolean = pck is DBInternPackage

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
        println("send $destinationAddress $pck")
if(pck is QueryResponsePackage){
if (device.address == destinationAddress) {
            println("sendQueryResult deviceAddress == $destinationAddress")
            useQueryResult(pck.result)
        } else {
            println("sendQueryResult route forward to $destinationAddress")
            device.sendRoutedPackage(device.address, destinationAddress, DBQueryResultPackage(pck.result))
        }
}else{
        device.sendRoutedPackage(device.address, destinationAddress, DBInternPackage(pck))
}
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        device.router.getNextDatabaseHops(destinationAddresses)
}

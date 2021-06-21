package lupos.simulator_iot

import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IDatabaseState
import lupos.simulator_db.IRouter
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.IPayload
import lupos.simulator_iot.sensor.ParkingSample


public class DatabaseAdapter(public val device: Device, private val isDummy: Boolean) : IRouter {

    private var resultCounter = 0
    private var resultDevicePath = IoTSimLifeCycle.pathToQueryResult + "\\device${device.address}"

    private var pathDevice = IoTSimLifeCycle.pathToDatabaseData + "\\device${device.address}"


    private val db: IDatabase = if(isDummy) DatabaseSystemDummy() else  DatabaseHandle()


    private lateinit var currentState: IDatabaseState

    public fun startUp() {
        lupos.shared.inline.File(pathDevice).mkdirs()
        lupos.shared.inline.File(resultDevicePath).mkdirs()
        currentState = buildInitialStateObject()
        db.start(currentState)
        db.deactivate()
    }

    private fun buildInitialStateObject(): IDatabaseState {
        return object : IDatabaseState {
            override val ownAddress: Int
                get() = device.address
            override var allAddresses: IntArray
                get() = Configuration.dbDeviceAddresses
                set(value) {}
            override val sender: IRouter
                get() = this@DatabaseAdapter
            override val absolutePathToDataDirectory: String
                get() = pathDevice
        }
    }

    public fun shutDown() {
        db.activate()
        db.end()
        if(!isDummy)
            lupos.shared.inline.File(pathDevice).deleteRecursively()
        currentState = buildInitialStateObject()
    }

    public fun processPackage(payload: IPayload) {
        when (payload) {
            is DBInternData -> receive(payload.content)
            is DBQueryResult -> useQueryResult(payload.result)
            else -> throw Exception("undefined payload")
        }
    }


    private fun useQueryResult(result: ByteArray) {
        val fileName = "$resultDevicePath\\file.txt"
        lupos.shared.inline.File(fileName).withOutputStream { }
        val stream = lupos.shared.inline.File(fileName).openOutputStream(true)
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

    public fun saveParkingSample(sample: ParkingSample) {
        val query = buildInsertQuery(sample)
        val bytes = query.toByteArray()
        receiveQuery(bytes)
    }

    public fun processQuery(query: String) {
        val queryBytes = query.toByteArray()
        receiveQuery(queryBytes)
    }

//    private var myhelper = 0
//    private fun buildInsertQuery(s: ParkingSample): String {
//        if (myhelper++ == 0) {
//            return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
//                "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
//                "\n" +
//                "INSERT DATA {\n" +
//                "  <observation/${s.sampleID}/sensor/${s.area}/${s.sensorID}> a sosa:Observation;\n" +
//                "  sosa:hasFeatureOfInterest <parkingArea/${s.area}>;\n" +
//                "  sosa:observedProperty <parkingSpace/${s.parkingSpotID}>;\n" +
//                "  sosa:madeBySensor <sensor/${s.area}/${s.sensorID}>;\n" +
//                "  sosa:hasSimpleResult \"${s.isOccupied}\"^^xsd:boolean;\n" +
//                "  sosa:resultTime \"${s.sampleTime}\"^^xsd:dateTime.\n" +
//                "}\n"
//        } else {
//            return "SELECT * WHERE {?s ?p ?o . }"
//        }
//    }



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
        db.receiveQuery(device.address, data)
        db.deactivate()
    }


    public fun isDatabasePackage(pck: IPayload): Boolean = pck is DBInternData

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
        device.sendRoutedPackage(device.address, destinationAddress, DBInternData(pck))
    }

    override fun sendQueryResult(destinationAddress: Int, result: ByteArray) {
        if (device.address == destinationAddress) {
            println("sendQueryResult deviceAddress == $destinationAddress")
            useQueryResult(result)
        }
        else {
            println("sendQueryResult route forward to $destinationAddress")
            device.sendRoutedPackage(device.address, destinationAddress, DBQueryResult(result))
        }

    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        device.router.getNextDatabaseHops(destinationAddresses)


    public class DBInternData(public val content: IDatabasePackage): IPayload {
        override fun getSizeInBytes(): Int {
            return content.getPackageSizeInBytes()
        }
    }

    public class DBQueryResult(public val result: ByteArray): IPayload {
        override fun getSizeInBytes(): Int {
            return result.size
        }
    }
}

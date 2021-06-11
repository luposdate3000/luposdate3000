package lupos.simulator_iot

import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IDatabaseState
import lupos.simulator_db.IRouter
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.sensor.ParkingSample
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

public class DatabaseAdapter(public val device: Device) : IRouter {

    private var path: Path = Paths.get("src", "db", "device${device.address}")
    private var absolutePath = ""
    private val dbother: IDatabase = DatabaseHandle() // just to keep the imports on formatting code
    private val db: IDatabase = DatabaseSystemDummy()
    private lateinit var currentState: IDatabaseState

    public fun startUp() {
        path = Files.createDirectories(path)
        absolutePath = path.toFile().absolutePath
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
                get() = absolutePath
        }
    }

    public fun shutDown() {
        db.activate()
        db.end()
        deleteDirectory(path.toFile())
        currentState = buildInitialStateObject()
    }

    public fun receive(pck: IDatabasePackage) {
        db.activate()
        db.receive(pck)
        db.deactivate()
    }

    public fun saveParkingSample(sample: ParkingSample) {
        val query = buildInsertQuery(sample)
        val bytes = toBytes(query)
        saveData(bytes)
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

    private fun saveData(data: ByteArray) {
        db.activate()
        db.receiveQuery(device.address, data)
        db.deactivate()
    }

    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null)
            for (file in allContents)
                deleteDirectory(file)

        return directoryToBeDeleted.delete()
    }

    private fun toBytes(s: String) = s.toByteArray(StandardCharsets.UTF_8)

    public fun isDatabasePackage(pck: Any): Boolean = pck is IDatabasePackage

    override fun send(destinationAddress: Int, pck: IDatabasePackage) {
        device.sendRoutedPackage(device.address, destinationAddress, pck)
    }

    override fun sendQueryResult(destinationAddress: Int, result: ByteArray) {
        device.sendRoutedPackage(device.address, destinationAddress, result) // TODO
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        device.router.getNextDatabaseHops(destinationAddresses)
}

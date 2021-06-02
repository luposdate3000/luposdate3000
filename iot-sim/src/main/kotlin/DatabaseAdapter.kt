import config.Configuration
import dummyImpl.DatabaseSystemDummy
import sensor.ParkingSample
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


class DatabaseAdapter(val device: Device): IRouter {

    private var path: Path = Paths.get("src","db", "device${device.address}")
    private var absolutePath = ""
    private val db: IDatabase = DatabaseSystemDummy()
    private lateinit var currentState: IDatabaseState

    fun startUp() {
        path = Files.createDirectories(path)
        absolutePath = path.toFile().absolutePath
        currentState = buildInitialStateObject()
        db.start(currentState)
        currentState = db.deactivate()
    }

    private fun buildInitialStateObject() : IDatabaseState {
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


    fun shutDown() {
        db.activate(currentState)
        db.end()
        deleteDirectory(path.toFile())
        currentState = buildInitialStateObject()
    }

    fun receive(pck: IDatabasePackage) {
        db.activate(currentState)
        db.receive(pck)
        currentState = db.deactivate()
    }

    fun saveParkingSample(sample: ParkingSample) {
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
                "  sosa:hasFeatureOfInterest <parkingSpaceOccupancy/space/${s.area}>;\n" +
                "  sosa:observedProperty <parkingSpot/${s.parkingSpotID}>;\n" +
                "  sosa:madeBySensor <sensor/${s.area}/${s.sensorID}>;\n" +
                "  sosa:hasSimpleResult \"${s.isOccupied}\"^^xsd:boolean;\n" +
                "  sosa:resultTime \"${s.sampleTime}\"^^xsd:dateTime.\n" +
                "}\n"
    }

    private fun saveData(data: ByteArray) {
        db.activate(currentState)
        db.receiveQuery(device.address, data)
        currentState = db.deactivate()
    }

    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null)
            for (file in allContents)
                deleteDirectory(file)

        return directoryToBeDeleted.delete()
    }

    private fun toBytes(s: String) = s.toByteArray(StandardCharsets.UTF_8)

    fun isDatabasePackage(pck: Any) = pck is IDatabasePackage


    override fun send(to: Int, pck: IDatabasePackage) {
        device.sendRoutedPackage(device.address, to, pck)
    }

    override fun sendQueryResult(to: Int, result: ByteArray) {
        device.sendRoutedPackage(device.address, to, result) //TODO
    }



    override fun getNextDBHopsFor(destinationAddresses: IntArray)
        = device.router.getNextDatabaseHops(destinationAddresses)


}
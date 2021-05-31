import dummyImpl.DatabaseSystemDummy
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
        val rdfString = sample.toRDFString()
        val bytes = toBytes(rdfString)
        saveData(bytes)
    }

    private fun saveData(data: ByteArray) {
        db.activate(currentState)
        db.saveData(data)
        currentState = db.deactivate()
    }

    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null)
            for (file in allContents)
                deleteDirectory(file)

        return directoryToBeDeleted.delete()
    }

    fun toBytes(s: String) = s.toByteArray(StandardCharsets.UTF_8)

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
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


class DatabaseAdapter(val device: Device): Network {

    var path: Path = Paths.get("src","db", "device${device.address}")
    val db: Database = DatabaseStub()

    fun startUp() {
        path = Files.createDirectories(path)
        val absolutePath = path.toFile().absolutePath
        db.start(absolutePath, device.address, Configuration.dbDeviceAddresses, "", this)
        db.deactivate()
    }

    fun shutDown() {
        db.activate()
        db.end()
        deleteDirectory(path.toFile())
    }

    override fun send(header: Header, payload: ByteArray) {
        val pck = DatabasePackage(header, payload)
        device.sendRoutedPackage(device.address, header.destinationAddress, pck)
    }

    fun receive(pck: DatabasePackage) {
        db.activate()
        db.receive(pck.header, pck.payload)
        db.deactivate()
    }

    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null)
            for (file in allContents)
                deleteDirectory(file)

        return directoryToBeDeleted.delete()
    }

    class DatabasePackage(val header: Header, val payload: ByteArray)

}
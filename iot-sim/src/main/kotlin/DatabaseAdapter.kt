import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


class DatabaseAdapter(val device: Device): DatabaseHolder {

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
        db.finish()
        deleteDirectory(path.toFile())
    }



    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null) {
            for (file in allContents) {
                deleteDirectory(file)
            }
        }
        return directoryToBeDeleted.delete()
    }

    override fun send(destinationAddress: Int, data: ByteArray) {
        device.sendRoutedPackage(device.address, destinationAddress, data)
    }

    fun receive(sourceAddress: Int, data: ByteArray) {
        db.activate()
        db.receive(sourceAddress, data)
        db.deactivate()
    }

}
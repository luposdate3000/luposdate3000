import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class DatabaseStub : Database {

    var dbFile: File? = null


    override fun start(
        absolutePathToDirectory: String,
        ownAddress: Int,
        allAddresses: IntArray,
        absolutePathToOntologyFile: String,
        network: Network
    ) {
        dbFile = File("$absolutePathToDirectory\\file.txt")
        dbFile!!.createNewFile()
    }

    override fun activate() {
        //DB Threads can work
    }

    override fun deactivate() {
        //DB Threads sleep until activate() is called
    }

    override fun end() {

    }

    override fun receive(header: Header, payload: ByteArray) {
        Files.write(Paths.get(dbFile!!.toURI()), payload, StandardOpenOption.APPEND);
    }


}
import java.io.File

class DatabaseStub : Database {

    var dbFile: File? = null


    override fun start(
        absolutePathToDirectory: String,
        ownAddress: Int,
        allAddresses: IntArray,
        absolutePathToOntologyFile: String,
        holder: DatabaseHolder
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

    override fun finish() {

    }

    override fun receive(sourceAddress: Int, data: ByteArray) {

    }


    //if insert Statement
    //The database must use the triples to determine
    // which database should store the triples
}
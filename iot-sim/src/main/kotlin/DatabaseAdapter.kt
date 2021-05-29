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

    private fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null)
            for (file in allContents)
                deleteDirectory(file)

        return directoryToBeDeleted.delete()
    }

    fun stringToBytes(s: String) = s.toByteArray(StandardCharsets.UTF_8)

    fun isDatabasePackage(pck: Any) = pck is IDatabasePackage


    override fun send(to: Int, pck: IDatabasePackage) {
        device.sendRoutedPackage(device.address, to, pck)
    }

    override fun sendQueryResult(to: Int, result: ByteArray) {
        device.sendRoutedPackage(device.address, to, result) //TODO
    }

    override fun getNextHopsFor(destinationAddresses: IntArray): IntArray {
        val hops = IntArray(destinationAddresses.size)
        for ((index, dest) in destinationAddresses.withIndex())
            hops[index] = device.router.getNextHop(dest)

        return hops
    }




    /*    override fun send(pck: DatabasePackage) {
        val map = HashMap<Int, MutableSet<Int>>(pck.destinationAddresses.size)
        for(address in pck.destinationAddresses) {
            val hop = device.router.getNextHop(address)
            if(!map.containsKey(hop))
                map[hop] = mutableSetOf()
            map.getValue(hop).add(address)
        }
        val isParticipant = map.size > 1
        var actualParticipants = pck.participants
        if(isParticipant) {
            actualParticipants = addToArray(pck.participants, device.address)
        }


        val pck = DatabasePackage(pck, payload)
        device.sendRoutedPackage(device.address, pck.destinationAddress, pck)
    }

    private fun addToArray(arr: IntArray, newValue: Int): IntArray {
        val newArray = IntArray(arr.size + 1)
        for ((index, value) in newArray.withIndex()) {
            newArray[index] = value
        }
        newArray[arr.size] = newValue
        return newArray
    }*/


}
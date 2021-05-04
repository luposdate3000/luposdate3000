interface Database {

    fun start(absolutePathToDirectory: String,
              ownAddress: Int,
              allAddresses: IntArray,
              absolutePathToOntologyFile: String,
              holder: DatabaseHolder)

    fun activate()

    fun deactivate()

    fun finish()

    fun receive(sourceAddress: Int, data: ByteArray)
}

interface DatabaseHolder {

    fun send(destinationAddress: Int, data: ByteArray)
}


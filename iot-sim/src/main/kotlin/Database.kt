interface Database {

    fun start(absolutePathToDirectory: String,
              ownAddress: Int,
              allAddresses: IntArray,
              absolutePathToOntologyFile: String,
              network: Network)

    fun activate()

    fun deactivate()

    fun end()

    fun receive(header: Header, payload: ByteArray)
}

interface Network {
    fun send(header: Header, payload: ByteArray)
}

interface Header {
    val sourceAddress: Int
    val destinationAddress: Int
    val participation: Boolean
    val operate: Boolean
    val participantsList: IntArray
}


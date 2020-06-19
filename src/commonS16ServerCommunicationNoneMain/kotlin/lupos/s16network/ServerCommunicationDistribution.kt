package lupos.s16network

object ServerCommunicationDistribution {
    val knownHosts = mutableListOf<ServerCommunicationKnownHost>()
    fun printKnownHosts(): StringBuilder {
        return StringBuilder("networking is disabled - there is only this one node.")
    }

package lupos.s16network

object ServerCommunicationDistribution {
    val knownHosts = mutableListOf<String>()
    fun printKnownHosts(): StringBuilder {
        return StringBuilder("networking is disabled - there is only this one node.")
    }
}

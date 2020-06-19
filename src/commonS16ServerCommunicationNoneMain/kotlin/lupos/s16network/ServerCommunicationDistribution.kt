package lupos.s16network

import lupos.s00misc.Coverage

object ServerCommunicationDistribution {
    val knownHosts = mutableListOf<String>()
    fun printKnownHosts(): StringBuilder {
        return StringBuilder("networking is disabled - there is only this one node.")
    }
}

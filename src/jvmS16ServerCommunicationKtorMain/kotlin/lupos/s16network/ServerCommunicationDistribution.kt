package lupos.s16network

import kotlin.jvm.JvmField
import kotlin.math.ceil
import kotlin.math.pow
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase

class ServerCommunicationKnownHost(hostname: String, port: Int) : ServerCommunicationKnownHostBase(hostname, port) {
/*
 * this class uses inheritance, to be easily excanged later _for other distribution strategies requireing different parameters
 */
}

object ServerCommunicationDistribution {
    val knownHosts = mutableListOf<ServerCommunicationKnownHost>()
    /*
     * refer to k in the project-proposal page 9
     * TODO redistribution on change
     * k is choosen as the cubic root of the number of known hosts, rounded up, such that k is never 0
     * value is updated on new host discovery, such that the triples are distributed evenly to the nodes,
     * _if the hash of a single String is evenly distributed.
     *
     * assuming k does not change after first triple insertion
     */
    var k = 1

    /*
     * refer to h(s), h(p), h(o) in the project-proposal page 9
     * assuming k does not change after first usage
     * the codomain k is guaranteed by the modulo operation
     * TODO _if k is a power of 2, then binary-AND-operator should be preferred
     */
    fun h(str: String) = str.hashCode() % k

    fun registerKnownHost(hostname: String, port: Int) {
        knownHosts.add(ServerCommunicationKnownHost(hostname, port))
        k = ceil(knownHosts.size.toDouble().pow(1 / 3.toDouble())).toInt()
    }

    /*
     * use during insertion and deletion
     */
    fun getHostForFullTriple(values: Array<Value>, query: Query, idx: EIndexPattern): ServerCommunicationKnownHost {
        return getHostForFullTriple(values.map { query.dictionary.getValue(values[it]).toSparql() }.toTypedArray(), idx)
    }

    /*
     * use during bulk-import
     */
    fun getHostForFullTriple(str: Array<String>, idx: EIndexPattern): ServerCommunicationKnownHost {
        require(str.size == 3)
        var hash = 0
        for (i in idx.tripleIndicees) {
            /*
             * idx.tripleIndicees contains the numbers 0, 1 and 2
             * these numbers specify the order of s, p and o in the index
             */
            hash = hash * k + h(str[i])
        }
        /*
         * the value of 'hash' is at most k^3-1
         * _if the number of nodes is exactly k^3, then each node covers the exact same range of possible triples
         * otherwise some nodes cover a larger range of Strings compared others (k is rounded up) ..
         * therefore modulo the number of known hosts, to guarantee a valid result
         */
        return knownHosts[hash % knownHosts.size]
    }

    /*
     * use during query processing
     */
    fun getHostForPartialTriple(values: Array<AOPBase>, idx: EIndexPattern): List<ServerCommunicationKnownHost> {
        require(values.size == 3)
        /*
         * 'hash' contains at least 1 entry all the time
         */
        var hash = mutableSetOf(0)
        for (i in idx.tripleIndicees) {
            val v = values[i]
            /*
             * idx.tripleIndicees contains the numbers 0, 1 and 2
             * these numbers specify the order of s, p and o in the index
             */
            var hash2 = mutableSetOf(0)
            for (hh in hash) {
                if (v is AOPConstant) {
                    /*
                     * _if there is a constant, use the corresponding hash value
                     */
                    hash2.add(hh * k + h(v.toSparql()))
                } else {
                    /*
                     * otherwise add all possible hash values.
                     * in the project-proposal this is specified by the random variables 'X' and 'Y'
                     */
                    for (x in 0 until k) {
                        hash2.add(hh * k + x)
                    }
                }
            }
            hash = hash2
        }
        /*
         * each value of 'hash' is at most k^3-1
         * _if the number of nodes is exactly k^3, then each node covers the exact same range of possible triples
         * otherwise some nodes cover a larger range of Strings compared others (k is rounded up) ..
         * therefore modulo the number of known hosts, to guarantee a valid result
         * _if the number of nodes is not exactly k^3, than 'hash' may contain duplicated nodes therefore the following distinct needs to be mapped first.
         */
        return hash.map { it % knownHosts.size }.distinct().map { knownHosts[it] }
    }
}

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
Coverage.funStart(16541)
        knownHosts.add(ServerCommunicationKnownHost(hostname, port))
Coverage.statementStart(16542)
        k = ceil(knownHosts.size.toDouble().pow(1 / 3.toDouble())).toInt()
Coverage.statementStart(16543)
    }
    /*
     * use during insertion and deletion
     */
    fun getHostForFullTriple(values: Array<Value>, query: Query, idx: EIndexPattern): ServerCommunicationKnownHost {
Coverage.funStart(16544)
        return getHostForFullTriple(values.map { query.dictionary.getValue(values[it]).toSparql() }.toTypedArray(), idx)
    }
    /*
     * use during bulk-import
     */
    fun getHostForFullTriple(str: Array<String>, idx: EIndexPattern): ServerCommunicationKnownHost {
Coverage.funStart(16545)
        require(str.size == 3)
Coverage.statementStart(16546)
        var hash = 0
Coverage.statementStart(16547)
        for (i in idx.tripleIndicees) {
Coverage.forLoopStart(16548)
            /*
Coverage.statementStart(16549)
             * idx.tripleIndicees contains the numbers 0, 1 and 2
Coverage.statementStart(16550)
             * these numbers specify the order of s, p and o in the index
Coverage.statementStart(16551)
             */
Coverage.statementStart(16552)
            hash = hash * k + h(str[i])
Coverage.statementStart(16553)
        }
Coverage.statementStart(16554)
        /*
Coverage.statementStart(16555)
         * the value of 'hash' is at most k^3-1
Coverage.statementStart(16556)
         * _if the number of nodes is exactly k^3, then each node covers the exact same range of possible triples
Coverage.statementStart(16557)
         * otherwise some nodes cover a larger range of Strings compared others (k is rounded up) ..
Coverage.statementStart(16558)
         * therefore modulo the number of known hosts, to guarantee a valid result
Coverage.statementStart(16559)
         */
Coverage.statementStart(16560)
        return knownHosts[hash % knownHosts.size]
    }
    /*
     * use during query processing
     */
    fun getHostForPartialTriple(values: Array<AOPBase>, idx: EIndexPattern): List<ServerCommunicationKnownHost> {
Coverage.funStart(16561)
        require(values.size == 3)
Coverage.statementStart(16562)
        /*
Coverage.statementStart(16563)
         * 'hash' contains at least 1 entry all the time
Coverage.statementStart(16564)
         */
Coverage.statementStart(16565)
        var hash = mutableSetOf(0)
Coverage.statementStart(16566)
        for (i in idx.tripleIndicees) {
Coverage.forLoopStart(16567)
            val v = values[i]
Coverage.statementStart(16568)
            /*
Coverage.statementStart(16569)
             * idx.tripleIndicees contains the numbers 0, 1 and 2
Coverage.statementStart(16570)
             * these numbers specify the order of s, p and o in the index
Coverage.statementStart(16571)
             */
Coverage.statementStart(16572)
            var hash2 = mutableSetOf(0)
Coverage.statementStart(16573)
            for (hh in hash) {
Coverage.forLoopStart(16574)
                if (v is AOPConstant) {
Coverage.ifStart(16575)
                    /*
Coverage.statementStart(16576)
                     * _if there is a constant, use the corresponding hash value
Coverage.statementStart(16577)
                     */
Coverage.statementStart(16578)
                    hash2.add(hh * k + h(v.toSparql()))
Coverage.statementStart(16579)
                } else {
Coverage.ifStart(16580)
                    /*
Coverage.statementStart(16581)
                     * otherwise add all possible hash values.
Coverage.statementStart(16582)
                     * in the project-proposal this is specified by the random variables 'X' and 'Y'
Coverage.statementStart(16583)
                     */
Coverage.statementStart(16584)
                    for (x in 0 until k) {
Coverage.forLoopStart(16585)
                        hash2.add(hh * k + x)
Coverage.statementStart(16586)
                    }
Coverage.statementStart(16587)
                }
Coverage.statementStart(16588)
            }
Coverage.statementStart(16589)
            hash = hash2
Coverage.statementStart(16590)
        }
Coverage.statementStart(16591)
        /*
Coverage.statementStart(16592)
         * each value of 'hash' is at most k^3-1
Coverage.statementStart(16593)
         * _if the number of nodes is exactly k^3, then each node covers the exact same range of possible triples
Coverage.statementStart(16594)
         * otherwise some nodes cover a larger range of Strings compared others (k is rounded up) ..
Coverage.statementStart(16595)
         * therefore modulo the number of known hosts, to guarantee a valid result
Coverage.statementStart(16596)
         * _if the number of nodes is not exactly k^3, than 'hash' may contain duplicated nodes therefore the following distinct needs to be mapped first.
Coverage.statementStart(16597)
         */
Coverage.statementStart(16598)
        return hash.map { it % knownHosts.size }.distinct().map { knownHosts[it] }
    }
}

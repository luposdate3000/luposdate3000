package lupos.s15tripleStoreDistributed

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreLocal
import lupos.s12p2p.*


val uuid = ThreadSafeUuid()

class TripleStoreIteratorGlobal : POPTripleStoreIteratorBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private var sNew: Variable
    private var pNew: Variable
    private val resultSetNew: ResultSet
    private var oNew: Variable
    private val nodeNameIterator: Iterator<String>
    private var xmlIterator: Iterator<XMLElement>? = null
    private val transactionID: Long
    private val graphName: String
    private val idx: EIndexPattern

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String) : this(transactionID, dictionary, graphName, EIndexPattern.SPO)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, index: EIndexPattern) {
        idx = index
        this.graphName = graphName
        this.dictionary = dictionary
        this.transactionID = transactionID
        resultSetNew = ResultSet(dictionary)
        sNew = resultSetNew.createVariable(nameS)
        pNew = resultSetNew.createVariable(nameP)
        oNew = resultSetNew.createVariable(nameO)
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleStoreIteratorGlobal").addAttribute("uuid", "" + uuid).addAttribute("nameS", nameS).addAttribute("nameP", nameP).addAttribute("nameO", nameO).addAttribute("name", getGraphName())
    }

    override fun getGraphName(): String {
        return graphName
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(nameS, nameP, nameO)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun next(): ResultRow {
        println("globalIterator.next start")
        val data = xmlIterator!!.next()
        val res = resultSetNew.createResultRow()
        data.childs.forEach {
            val v: Variable = when (it.attributes["name"]) {
                "s" -> sNew
                "p" -> pNew
                "o" -> oNew
                else -> throw Exception("unknown triple attribute")
            }
            val c = it.childs.first()
            when (c.tag) {
                "uri" -> res[v] = resultSetNew.createValue("<" + c.content + ">")
                "literal" -> {
                    val l = c.attributes["xml:lang"]
                    val t = c.attributes["datatype"]
                    if (l != null)
                        res[v] = resultSetNew.createValue("\"${c.content}\"@$l")
                    else if (t != null)
                        res[v] = resultSetNew.createValue("\"${c.content}\"^^<$t>")
                    else
                        res[v] = resultSetNew.createValue("\"${c.content}\"")
                }
                "bnode" -> res[v] = resultSetNew.createValue("_:" + c.content)
                else -> require(false)
            }
        }
        println("globalIterator.next end")
        return res
    }

    override fun hasNext(): Boolean {
        println("globalIterator.hasNext start")
        while (xmlIterator == null || !xmlIterator!!.hasNext()) {
            if (!nodeNameIterator.hasNext()) {
                println("globalIterator.hasNext end1")
                return false
            }
            val xml = P2P.execTripleGet(nodeNameIterator.next(), graphName, transactionID, idx)
            require(xml.tag == "sparql")
            xmlIterator = xml["results"]!!.childs.iterator()
        }
        println("globalIterator.hasNext end2")
        return true
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun setMNameS(n: String) {
        sNew = resultSetNew.renameVariable(nameS, n)
        nameS = n
    }

    override fun setMNameP(n: String) {
        pNew = resultSetNew.renameVariable(nameP, n)
        nameP = n
    }

    override fun setMNameO(n: String) {
        oNew = resultSetNew.renameVariable(nameO, n)
        nameO = n
    }


}

class DistributedGraph(val name: String) {
    val K = 8 // defined in project.pdf

    inline fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0)
            return (-c) % d
        return c % d
    }

    inline fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int {
        when (idx) {
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
            EIndexPattern.SOP -> return myHashCode("" + s + "-" + o + "-" + p, d)
            EIndexPattern.PSO -> return myHashCode("" + p + "-" + s + "-" + o, d)
            EIndexPattern.POS -> return myHashCode("" + p + "-" + o + "-" + s, d)
            EIndexPattern.OPS -> return myHashCode("" + o + "-" + p + "-" + s, d)
            EIndexPattern.OSP -> return myHashCode("" + o + "-" + s + "-" + p, d)
        }
    }

    inline fun calculateNodeForDataFull(s: String, p: String, o: String, idx: EIndexPattern): String {
        val sh = +myHashCode(s, K)
        val ph = +myHashCode(p, K)
        val oh = +myHashCode(o, K)
        return P2P.getKnownClientsCopy()[myHashCode(sh, ph, oh, P2P.knownClients.size, idx)]
    }

    inline fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> {
        val res = mutableSetOf<String>()
        val sr = if (sv) {
            val h = myHashCode(s, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val pr = if (pv) {
            val h = myHashCode(p, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val or = if (ov) {
            val h = myHashCode(o, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        for (si in sr) {
            for (pi in pr) {
                for (oi in or) {
                    res.add(P2P.getKnownClientsCopy()[myHashCode(si, pi, oi, P2P.knownClients.size, idx)])
                }
            }
        }
        return res
    }

    fun addData(transactionID: Long, t: List<String?>) {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0]!!, t[1]!!, t[2]!!, it)
            P2P.execTripleAdd(node, name, transactionID, t[0]!!, t[1]!!, t[2]!!, it)
        }
    }

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        require(t[0].second && t[1].second && t[2].second)
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0].first, t[1].first, t[2].first, it)
            P2P.execTripleAdd(node, name, transactionID, t[0].first, t[1].first, t[2].first, it)
        }
    }

    fun deleteData(transactionID: Long, t: List<String?>) {
        val l = mutableListOf<Pair<String, Boolean>>()
        if (t[0] != null)
            l.add(Pair(t[0]!!, true))
        else
            l.add(Pair("s", false))
        if (t[1] != null)
            l.add(Pair(t[1]!!, true))
        else
            l.add(Pair("p", false))
        if (t[2] != null)
            l.add(Pair(t[2]!!, true))
        else
            l.add(Pair("o", false))
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(l[0].first, l[1].first, l[2].first, l[0].second, l[1].second, l[2].second, it)) {
                P2P.execTripleDelete(node, name, transactionID, l, it)
            }
        }
    }

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0].first, t[1].first, t[2].first, t[0].second, t[1].second, t[2].second, it))
                P2P.execTripleDelete(node, name, transactionID, listOf(t[0], t[1], t[2]), it)
        }
    }

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        val rs = iterator.getResultSet()
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        while (iterator.hasNext()) {
            val v = iterator.next()
            val s = rs.getValue(v[ks])
            val p = rs.getValue(v[kp])
            val o = rs.getValue(v[ko])
            addData(transactionID, listOf(s, p, o))
        }
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary): POPTripleStoreIteratorBase {
        return TripleStoreIteratorGlobal(transactionID, dictionary, name)
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String): POPTripleStoreIteratorBase {
        val res = TripleStoreIteratorGlobal(transactionID, dictionary, name)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    }
}

object DistributedTripleStore {
    val localStore = PersistentStoreLocal()
    fun nextTransactionID(): Long {
        return localStore.nextTransactionID()
    }

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        return localStore.getGraphNames(includeDefault)
    }

    fun createGraph(name: String): DistributedGraph {
        P2P.execGraphOperation(name, EGraphOperationType.CREATE)
        return DistributedGraph(name)
    }

    fun dropGraph(name: String) {
        P2P.execGraphOperation(name, EGraphOperationType.DROP)
    }

    fun clearGraph(name: String) {
        println("DistributedTripleStore.clearGraph $name")
        P2P.execGraphOperation(name, EGraphOperationType.CLEAR)
    }

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph {
        if (create && !(localStore.getGraphNames(true).contains(name)))
            createGraph(name)
        return DistributedGraph(name)
    }

    fun getDefaultGraph(): DistributedGraph {
        return DistributedGraph(localStore.defaultGraphName)
    }

    fun commit(transactionID: Long) {
        P2P.execCommit(transactionID)
    }
}

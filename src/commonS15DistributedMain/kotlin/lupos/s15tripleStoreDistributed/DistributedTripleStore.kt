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

    private val sFilter: String?
    private val pFilter: String?
    private val oFilter: String?

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String) : this(transactionID, dictionary, graphName, EIndexPattern.SPO)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, index: EIndexPattern) : this(transactionID, dictionary, graphName, null, null, null, false, false, false, index)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, s: String?, p: String?, o: String?, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern) {
        idx = index
        this.graphName = graphName
        this.dictionary = dictionary
        this.transactionID = transactionID
        resultSetNew = ResultSet(dictionary)
        sNew = resultSetNew.createVariable(nameS)
        pNew = resultSetNew.createVariable(nameP)
        oNew = resultSetNew.createVariable(nameO)
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
        if (sv)
            sFilter = s
        else {
            sFilter = null
            if (s != null)
                setMNameS(s)
        }
        if (pv)
            pFilter = p
        else {
            pFilter = null
            if (p != null)
                setMNameP(p)
        }
        if (ov)
            oFilter = o
        else {
            oFilter = null
            if (o != null)
                setMNameO(o)
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("TripleStoreIteratorGlobalFilter").addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName())
        if (sFilter == null)
            res.addAttribute("nameS", nameS)
        else
            res.addAttribute("filterS", sFilter)
        if (pFilter == null)
            res.addAttribute("nameP", nameP)
        else
            res.addAttribute("filterP", pFilter)
        if (oFilter == null)
            res.addAttribute("nameO", nameO)
        else
            res.addAttribute("filterO", oFilter)
        return res
    }

    override fun getGraphName(): String = Trace.trace({ "TripleStoreIteratorGlobal.getGraphName" }, {
        return graphName
    })as String

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(nameS, nameP, nameO)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun next(): ResultRow = Trace.trace({ "TripleStoreIteratorGlobal.next" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "globalIterator.next start" })
        val data = xmlIterator!!.next()
        val res = resultSetNew.createResultRow()
        data.childs.forEach {
            val v: Variable = when (it.attributes["name"]) {
                "s" -> sNew
                "p" -> pNew
                "o" -> oNew
                else -> throw Exception("unknown triple attribute ${it.attributes["name"]}")
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
        GlobalLogger.log(ELoggerType.DEBUG, { "globalIterator.next end" })
        return res
    }) as ResultRow

    override fun hasNext(): Boolean = Trace.trace({ "TripleStoreIteratorGlobal.hasNext" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "globalIterator.hasNext start" })
        while (xmlIterator == null || !xmlIterator!!.hasNext()) {
            if (!nodeNameIterator.hasNext()) {
                GlobalLogger.log(ELoggerType.DEBUG, { "globalIterator.hasNext end1" })
                return false
            }
            val s = if (sFilter == null)
                "s"
            else
                sFilter
            val p = if (pFilter == null)
                "p"
            else
                pFilter
            val o = if (oFilter == null)
                "o"
            else
                oFilter
            val sv = sFilter != null
            val pv = pFilter != null
            val ov = oFilter != null
            val nodeName = nodeNameIterator.next()
            GlobalLogger.log(ELoggerType.DEBUG, { "nodeName :: ${nodeName} $s $p $o $sv $pv $ov" })
            val xml = P2P.execTripleGet(nodeName, graphName, transactionID, s, p, o, sv, pv, ov, idx)
            GlobalLogger.log(ELoggerType.DEBUG, { "xml :: $xml" })
            require(xml.tag == "sparql")
            xmlIterator = xml["results"]!!.childs.iterator()
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "globalIterator.hasNext end2" })
        return true
    })as Boolean

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

    inline fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int = Trace.trace({ "DistributedGraph.myHashCode" }, {
        when (idx) {
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
            EIndexPattern.SOP -> return myHashCode("" + s + "-" + o + "-" + p, d)
            EIndexPattern.PSO -> return myHashCode("" + p + "-" + s + "-" + o, d)
            EIndexPattern.POS -> return myHashCode("" + p + "-" + o + "-" + s, d)
            EIndexPattern.OPS -> return myHashCode("" + o + "-" + p + "-" + s, d)
            EIndexPattern.OSP -> return myHashCode("" + o + "-" + s + "-" + p, d)
        }
    })as Int

    inline fun calculateNodeForDataFull(s: String, p: String, o: String, idx: EIndexPattern): String = Trace.trace({ "DistributedGraph.calculateNodeForDataFull" }, {
        val sh = +myHashCode(s, K)
        val ph = +myHashCode(p, K)
        val oh = +myHashCode(o, K)
        return P2P.getKnownClientsCopy()[myHashCode(sh, ph, oh, P2P.knownClients.size, idx)]
    })as String

    inline fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> = Trace.trace({ "DistributedGraph.calculateNodeForDataMaybe" }, {
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
        GlobalLogger.log(ELoggerType.DEBUG, { "maybe :: " + res })
        return res
    })as Set<String>

    fun addData(transactionID: Long, t: List<String?>)  = Trace.trace({ "DistributedGraph.addData a" }, {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0]!!, t[1]!!, t[2]!!, it)
            P2P.execTripleAdd(node, name, transactionID, t[0]!!, t[1]!!, t[2]!!, it)
        }
    })

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>)  = Trace.trace({ "DistributedGraph.addDataVar" }, {
        require(t[0].second && t[1].second && t[2].second)
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0].first, t[1].first, t[2].first, it)
            P2P.execTripleAdd(node, name, transactionID, t[0].first, t[1].first, t[2].first, it)
        }
    })

    fun deleteData(transactionID: Long, t: List<String?>)  = Trace.trace({ "DistributedGraph.deleteData" }, {
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
    })

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>)  = Trace.trace({ "DistributedGraph.deleteDataVar" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0].first, t[1].first, t[2].first, t[0].second, t[1].second, t[2].second, it))
                P2P.execTripleDelete(node, name, transactionID, listOf(t[0], t[1], t[2]), it)
        }
    })

    fun addData(transactionID: Long, iterator: ResultSetIterator) = Trace.trace({ "DistributedGraph.addData b" }, {
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
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase  = Trace.trace({ "DistributedGraph.getIterator c" }, {
        return TripleStoreIteratorGlobal(transactionID, dictionary, name, index)
    })as POPTripleStoreIteratorBase

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator b" }, {
        val res = TripleStoreIteratorGlobal(transactionID, dictionary, name, index)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    })as POPTripleStoreIteratorBase

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator a" }, {
        val res = TripleStoreIteratorGlobal(transactionID, dictionary, name, s, p, o, sv, pv, ov, index)
        return res
    })as POPTripleStoreIteratorBase
}

object DistributedTripleStore {
    val localStore = PersistentStoreLocal()
    fun nextTransactionID(): Long = Trace.trace({ "DistributedTripleStore.nextTransactionID" }, {
        return localStore.nextTransactionID()
    })as Long

    fun getGraphNames(includeDefault: Boolean = false): List<String>  = Trace.trace({ "DistributedTripleStore.getGraphNames" }, {
        return localStore.getGraphNames(includeDefault)
    })as List<String>

    fun createGraph(name: String): DistributedGraph = Trace.trace({ "DistributedTripleStore.createGraph" }, {
        P2P.execGraphOperation(name, EGraphOperationType.CREATE)
        return DistributedGraph(name)
    })as DistributedGraph

    fun dropGraph(name: String) = Trace.trace({ "DistributedTripleStore.dropGraph" }, {
        P2P.execGraphOperation(name, EGraphOperationType.DROP)
    })

    fun clearGraph(name: String) = Trace.trace({ "DistributedTripleStore.clearGraph" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
        P2P.execGraphOperation(name, EGraphOperationType.CLEAR)
    })

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph = Trace.trace({ "DistributedTripleStore.getNamedGraph" }, {
        if (create && !(localStore.getGraphNames(true).contains(name)))
            createGraph(name)
        return DistributedGraph(name)
    })as DistributedGraph

    fun getDefaultGraph(): DistributedGraph = Trace.trace({ "DistributedTripleStore.getDefaultGraph" }, {
        return DistributedGraph(localStore.defaultGraphName)
    })as DistributedGraph

    fun commit(transactionID: Long) = Trace.trace({ "DistributedTripleStore.commit" }, {
        P2P.execCommit(transactionID)
    })
}

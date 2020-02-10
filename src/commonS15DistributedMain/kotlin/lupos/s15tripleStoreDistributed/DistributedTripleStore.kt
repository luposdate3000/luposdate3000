package lupos.s15tripleStoreDistributed

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.IndexPattern
import lupos.s05tripleStore.ModifyType
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

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String) : this(transactionID, dictionary, graphName, IndexPattern.S)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, index: IndexPattern) {
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
val c =it.childs.first()
            when (c.tag) {
                "uri" -> res[v] = resultSetNew.createValue("<"+c.content+">")
		"literal"->{
	val		l=c.attributes["xml:lang"]
	val		t=c.attributes["datatype"]
			if(l!=null)
				res[v] = resultSetNew.createValue("\"${c.content}\"@$l")
			else if(t!=null)
				res[v] = resultSetNew.createValue("\"${c.content}\"^^<$t>")
			else
				 res[v] = resultSetNew.createValue("\"${c.content}\"")
		}
		"bnode"->res[v] = resultSetNew.createValue("_:"+c.content)
                else -> require(false)
            }
        }
println("globalIterator.next end")
        return res
    }

    override fun hasNext(): Boolean {
println("globalIterator.hasNext start")
        while (xmlIterator == null || !xmlIterator!!.hasNext()) {
            if (!nodeNameIterator.hasNext()){
println("globalIterator.hasNext end1")
                return false
	}
            val xml = P2P.execTripleGet(nodeNameIterator.next(), graphName, transactionID)
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

    inline fun calculateNodeForData(s: String?, p: String?, o: String?): String {
        return P2P.getKnownClientsCopy()[uuid.next().toInt() % P2P.knownClients.size]
    }

    fun addData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name).addData(transactionID, t)
    }

    fun deleteData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name).deleteData(transactionID, t)
    }

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name).addDataVar(transactionID, t)
    }

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name).deleteDataVar(transactionID, t)
    }

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        DistributedTripleStore.localStore.getNamedGraph(name).addData(transactionID, iterator)
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
        P2P.execGraphOperation(name, GraphOperationType.CREATE)
        return DistributedGraph(name)
    }

    fun dropGraph(name: String) {
        P2P.execGraphOperation(name, GraphOperationType.DROP)
    }

    fun clearGraph(name: String) {
        println("DistributedTripleStore.clearGraph $name")
        P2P.execGraphOperation(name, GraphOperationType.CLEAR)
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
        localStore.commit(transactionID)
    }
}

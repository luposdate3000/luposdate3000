package lupos.s08tripleStore

import lupos.s06resultRepresentation.ResultSetIterator
import lupos.s07physicalOperators.POPBase
import lupos.s08tripleStore.*

enum class IndexPattern {
    S, P, O, SP, SO, PO, SPO, SOP, PSO, POS, OSP, OPS
}

abstract class POPTripleStoreIteratorBase() : POPBase() {
    var nameS = "#s" + uuid
    var nameP = "#p" + uuid
    var nameO = "#o" + uuid
    abstract fun setMNameS(n: String)
    abstract fun setMNameP(n: String)
    abstract fun setMNameO(n: String)
	abstract fun getGraphName():String
}

val globalStore=PersistentStore()

class PersistentStore(){
	val stores=mutableMapOf<String,TripleStore>()
	fun getNamedGraph(name:String):TripleStore{
		val tmp=stores[name]
		if(tmp!=null)
			return tmp
		val tmp2=TripleStore(name)
		stores[name]=tmp2
		return tmp2
	}
	fun getDefaultGraph():TripleStore{
		return getNamedGraph("")
	}
}

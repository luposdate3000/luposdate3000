package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class Query(@JvmField val dictionary: ResultSetDictionary = ResultSetDictionary(), @JvmField val transactionID: Long = global_transactionID.next()) {
    @JvmField
    var _workingDirectory = ""
    var workingDirectory: String
        set(value) {
            if (value.endsWith("/")) {
                _workingDirectory = value
            } else {
                _workingDirectory = value + "/"
            }
        }
        get() = _workingDirectory

    @JvmField
    var commited = false

var generatedNameCounter=0
var generatedNameByBase=mutableMapOf<String,String>()
fun getUniqueVariableName(name:String):String{
val tmp=generatedNameByBase[name]
if(tmp!=null){
return tmp
}else{
val tmp2=getUniqueVariableName()
generatedNameByBase[name]=tmp2
return tmp2
}
}
fun getUniqueVariableName()="#+${generatedNameCounter++}"
fun isGeneratedVariableName(name:String)=name.startsWith('#')

    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }

    fun commit() {
        DistributedTripleStore.commit(this)
        commited = true
    }

    protected fun finalize() {
    }
}

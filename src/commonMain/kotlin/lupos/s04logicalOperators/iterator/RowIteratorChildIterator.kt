package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

class RowIteratorChildIterator(columns:Array<String>) : RowIterator() {
    val childs = mutableListOf(RowIterator())
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements
    init {
this.columns=columns
        next = {
            var res=-1
            while (childs.size > 0) {
                res = childs[0].next()
buf=childs[0].buf
                if (res == -1) {
                    childs.removeAt(0)
                } else {
                    break
                }
            }
            if (res == -1) {
                onNoMoreElements()
                if (childs.size == 0) {
                    close()
                }else{
                res = next()
if(res!=-1){
buf=childs[0].buf
}
}
            }
SanityCheck.check{res==-1||buf==childs[0].buf}
            /*return*/res
        }
        close = {
            onNoMoreElements = ::_onNoMoreElements
            for (child in childs) {
                child.close()
            }
            _close()
        }
    }

    suspend fun _onNoMoreElements() {
        close()
    }
}

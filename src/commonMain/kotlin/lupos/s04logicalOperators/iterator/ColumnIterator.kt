package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.ResultIterator

class ColumnIteratorRow(val columns:Map<String,ColumnIterator>)

open class ColumnIterator() {

    var next: suspend () -> Value? = ::_next
    var close: () -> Unit = ::_close
    var onNoMoreElements:suspend()->Unit=::_onNoMoreElements
    fun _close() {
        next = ::_next
        close = ::_close
onNoMoreElements=::_onNoMoreElements
    }
suspend fun _onNoMoreElements(){
close()
}
    suspend fun _next(): Value? = null
}

class ColumnIteratorRepeatValue(val count: Int, val value: Value) : ColumnIterator() {
    var index = 0

    init {
        next = {
            if (index == count) {
                null
            } else {
                index++
                value
            }
        }
    }
}

class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0
//TODO use pages instead
    val data= mutableListOf<Value>()

    init {
        require(count > 1)
        next = {
            val tmp = child.next()
            if (tmp == null) {
                index = 1
                next = {
                    if (index2 < data.size) {
                        data[index2]
                    } else {
                        if (index < count) {
                            index++
                            index2 = 0
                            data[index2]
                        } else {
                            null
                        }
                    }
                }
                next()
            } else {
                data.add(tmp)
                tmp
            }
        }
        close = {
            child.close()
            _close()
        }
    }
}

class ColumnIteratorMultiValue(val values: List<Value>) : ColumnIterator() {
    var index = 0

    init {
        next = {
            if (index == values.size) {
                null
            } else {
                values[index++]
            }
        }
    }
}

class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res = childs[index].next()
            while (res == null && index < childs.size) {
                res = childs[++index].next()
            }
            res
        }
        close = {
            for (c in childs) {
                c.close()
            }
            _close()
        }
    }
}
class ColumnIteratorChildIterator(var child:ColumnIterator) : ColumnIterator() {

    init {
        next = {
val res=            child.next()
if(res==null){
onNoMoreElements()
res=            child.next()
}
res
        }
        close = {
                child.close()
            _close()
        }
    }
}

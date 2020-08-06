package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
class ColumnIteratorChildIteratorEmpty():ColumnIteratorChildIterator(){ 
override fun close(){ 
_close()
}
override fun onNoMoreElements(){
}
}

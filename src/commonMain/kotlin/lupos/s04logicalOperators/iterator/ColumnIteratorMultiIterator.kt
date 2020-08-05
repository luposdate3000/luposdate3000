package lupos.s04logicalOperators.iterator
import lupos.s04logicalOperators.iterator.ColumnIteratorNext
import lupos.s00misc.Coverage

class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0

    init {
        next = ColumnIteratorNext("ColumnIteratorMultiIterator.next"){
            var res = childs[index].next()
            while (res == null && ++index < childs.size) {
                res = childs[index].next()
            }
/*return*/res
        }
        close = {
            for (c in childs) {
                c.close()
            }
            _close()
        }
    }
}

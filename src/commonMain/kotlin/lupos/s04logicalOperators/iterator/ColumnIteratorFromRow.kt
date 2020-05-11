package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

object ColumnIteratorFromRow {
    operator fun invoke(iterator: RowIterator): Map<String, ColumnIterator> {
        var res = mutableMapOf<String, ColumnIterator>()
        var iterators = Array(iterator.columns.size) { ColumnIteratorQueue() }
        for (i in 0 until iterator.columns.size) {
            iterators[i].onEmptyQueue = {
                var res2 = iterator.next()
                if (res2 >= 0) {
                    for (j in 0 until iterator.columns.size) {
                        iterators[j].queue.add(iterator.buf[res2 + j])
                    }
                }
            }
            iterators[i].close = {
                iterator.close()
                iterators[i]._close()
            }
            res[iterator.columns[i]] = iterators[i]
        }
        return res
    }
}

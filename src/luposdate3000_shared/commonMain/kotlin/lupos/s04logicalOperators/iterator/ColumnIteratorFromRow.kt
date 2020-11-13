package lupos.s04logicalOperators.iterator

object ColumnIteratorFromRow {
    operator fun invoke(iterator: RowIterator): Map<String, ColumnIterator> {
        val res = mutableMapOf<String, ColumnIterator>()
        val iterators = mutableListOf<ColumnIteratorQueue>()
        for (i in 0 until iterator.columns.size) {
            val iterator2 = object : ColumnIteratorQueue() {
                override /*suspend*/ fun next(): Int {
                    return ColumnIteratorQueueExt.nextHelper(this, {
                        val res2 = iterator.next()
                        if (res2 >= 0) {
                            for (j in iterator.columns.indices) {
                                iterators[j].queue.add(iterator.buf[res2 + j])
                            }
                        }
                    }, {
                        if (label != 0) {
                            ColumnIteratorQueueExt._close(this)
                            iterator.close()
                        }
                    })
                }

                override /*suspend*/ fun close() {
                    if (label != 0) {
                        ColumnIteratorQueueExt._close(this)
                        iterator.close()
                    }
                }
            }
            iterators.add(iterator2)
            res[iterator.columns[i]] = iterator2
        }
        return res
    }
}

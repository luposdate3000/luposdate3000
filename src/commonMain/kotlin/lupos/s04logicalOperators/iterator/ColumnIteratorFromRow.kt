package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
object ColumnIteratorFromRow {
    operator fun invoke(iterator: RowIterator): Map<String, ColumnIterator> {
Coverage.funStart(3478)
        var res = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(3479)
        var iterators = Array(iterator.columns.size) { ColumnIteratorQueue() }
Coverage.statementStart(3480)
        for (i in 0 until iterator.columns.size) {
Coverage.forLoopStart(3481)
            iterators[i].onEmptyQueue = {
Coverage.statementStart(3482)
                var res2 = iterator.next()
Coverage.statementStart(3483)
                if (res2 >= 0) {
Coverage.ifStart(3484)
                    for (j in 0 until iterator.columns.size) {
Coverage.forLoopStart(3485)
                        iterators[j].queue.add(iterator.buf[res2 + j])
Coverage.statementStart(3486)
                    }
Coverage.statementStart(3487)
                }
Coverage.statementStart(3488)
            }
Coverage.statementStart(3489)
            iterators[i].close = {
Coverage.statementStart(3490)
                iterator.close()
Coverage.statementStart(3491)
                iterators[i]._close()
Coverage.statementStart(3492)
            }
Coverage.statementStart(3493)
            res[iterator.columns[i]] = iterators[i]
Coverage.statementStart(3494)
        }
Coverage.statementStart(3495)
        return res
    }
}

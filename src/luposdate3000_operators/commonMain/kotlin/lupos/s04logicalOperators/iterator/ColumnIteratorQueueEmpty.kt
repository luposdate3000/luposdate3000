package lupos.s04logicalOperators.iterator

class ColumnIteratorQueueEmpty() : ColumnIteratorQueue() {
    override suspend fun close() {
        ColumnIteratorQueueExt._close(this)
    }

    override suspend fun next(): Int {
        return ColumnIteratorQueueExt.next_helper(this, {}, { ColumnIteratorQueueExt._close(this) })
    }
}

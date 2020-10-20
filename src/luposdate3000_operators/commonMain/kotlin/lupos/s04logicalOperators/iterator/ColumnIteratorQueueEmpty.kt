package lupos.s04logicalOperators.iterator



class ColumnIteratorQueueEmpty() : ColumnIteratorQueue() {
    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Int {
        return next_helper({}, { _close() })
    }
}

package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

class ColumnIteratorChildIteratorEmpty() : ColumnIteratorChildIterator() {
    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        return next_helper ({},{_close()})
    }
}

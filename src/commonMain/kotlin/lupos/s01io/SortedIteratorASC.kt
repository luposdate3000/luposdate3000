package lupos.s01io

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class SortedIteratorASC<T> : Iterator<T> {
    val data: SortedArrayBase<T>
    var node: SortedDataPageBase<T>
    var idx = 0
    var closed = false

    constructor(data: SortedArrayBase<T>) {
        this.data = data
        node = data.data
        CoroutinesHelper.runBlock {
            data.lock.readLock()
        }
    }

    override fun hasNext(): Boolean {
        if (closed)
            return false
        if (idx < node.size)
            return true
        node = node.next
        idx = 0
        if (node == data.data) {
            CoroutinesHelper.runBlock {
                data.lock.readUnlock()
            }
            closed = true
            return false
        }
        return true
    }

    override fun next(): T {
        return node.data[idx++]
    }
}

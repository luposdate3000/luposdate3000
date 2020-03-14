package lupos.s01io

import lupos.s00misc.*


class SortedIteratorDESC<T> : Iterator<T> {
    val data: SortedArrayBase<T>
    var node: SortedDataPageBase<T>
    var idx = 0
    var closed = false

    constructor(data: SortedArrayBase<T>) {
        this.data = data
        node = data.data.prev
        idx = node.size
        CoroutinesHelper.runBlock {
            data.lock.readLock()
        }
    }

    override fun hasNext(): Boolean {
        if (closed)
            return false
        if (idx > 0)
            return true
        node = node.prev
        idx = node.size
        if (node == data.data.prev) {
            CoroutinesHelper.runBlock {
                data.lock.readUnlock()
            }
            closed = true
            return false
        }
        return true
    }

    override fun next(): T {
        return node.data[--idx]
    }
}

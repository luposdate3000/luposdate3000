package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage

abstract class TripleIterator() {
    var value = IntArray(3)
    abstract fun hasNext(): Boolean
    abstract fun next(component: Int): Int//write the current triple-data into "value" and update offset
    fun nextS(): Int {
        return next(0)
    }

    fun nextP(): Int {
        return next(1)
    }

    fun nextO(): Int {
        return next(2)
    }

    fun next(): IntArray {
        next(0)
        return value
    }
}

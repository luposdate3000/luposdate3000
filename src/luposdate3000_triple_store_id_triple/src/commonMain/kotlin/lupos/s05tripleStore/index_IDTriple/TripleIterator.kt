package lupos.s05tripleStore.index_IDTriple
internal abstract class TripleIterator {
    var value = IntArray(3)
    abstract fun hasNext(): Boolean
    abstract fun next(component: Int): Int // write the current triple-data into "value" and update offset
    public fun next(): IntArray {
        next(0)
        return value
    }
}

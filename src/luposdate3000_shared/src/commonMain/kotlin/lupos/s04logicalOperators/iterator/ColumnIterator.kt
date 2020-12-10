package lupos.s04logicalOperators.iterator
abstract class ColumnIterator {
    abstract /*suspend*/ fun next(): Int
    abstract /*suspend*/ fun close()
    open /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
        result[0] = 0
        result[1] = next()
    }
    open /*suspend*/ fun skipSIP(skipCount: Int): Int {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}

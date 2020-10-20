package lupos.s04logicalOperators.iterator

abstract class ColumnIterator() {
    abstract suspend fun next(): Int
    abstract suspend fun close()
    suspend open fun nextSIP(minValue: Int, result: IntArray) {
        result[0] = 0
        result[1] = next()
    }

    suspend open fun skipSIP(skipCount: Int): Int {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}

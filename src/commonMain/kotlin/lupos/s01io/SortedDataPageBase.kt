package lupos.s01io

import lupos.s00misc.*


abstract class SortedDataPageBase<T>(val comparator: Comparator<T>, val arrayAllocator: (Int) -> Array<T>, val pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>) {
    companion object {
        val capacity = 4
    }
 fun concatCompleteLoop( b: SortedDataPageBase<T>): SortedDataPageBase<T> {
        prev.next = b
        b.prev.next = this
        val c = prev
        prev = b.prev
        b.prev = c
        return this
    }

    val data = arrayAllocator(capacity)
    var size = 0
    var internal_sortuntil = 0
    var prev = this
    var next = this
    override fun toString(): String {
        var res = StringBuilder("")
        res.append("(")
        if (size > 0) {
            res.append(data[0])
            for (i in 1 until size)
                res.append("," + data[i])
        }
        res.append(")")
        return res.toString()
    }

    abstract fun append(value: T): Boolean
    abstract fun internal_sort(): Int
}

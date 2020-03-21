package lupos.s01io

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

class SortedDataPage<T>(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>, pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>) : SortedDataPageBase<T>(comparator, arrayAllocator, pageAllocator) {
    override fun append(value: T): Boolean {
        if (size < capacity) {
            data[size] = value
            if (size == 0)
                sortuntil = 1
            size++
        } else {
            val res = pageAllocator(comparator, arrayAllocator)
            res.data[0] = value
            res.size = 1
            res.sortuntil = 1
            next.concatCompleteLoop(res)
        }
        return true
    }

    fun internal_sort(a: Array<T>, b: Array<T>): Array<T> {
        var aIdx = 0
        var bIdx = 0
        var res = arrayAllocator(a.size + b.size)
        for (i in 0 until res.size)
            if (aIdx < a.size && (bIdx == b.size || comparator.compare(a[aIdx], b[bIdx]) <= 0))
                res[i] = a[aIdx++]
            else
                res[i] = b[bIdx++]
        return res
    }

    fun internal_sort(first: Int, last: Int): Array<T> {
        if (first == last) {
            var res = arrayAllocator(1)
            res[0] = data[first]
            return res
        }
        val middle = (first + last) / 2
        return internal_sort(internal_sort(first, middle), internal_sort(middle + 1, last))
    }

    override fun internal_sort(): Int {
        val oldSize = size
        if (size > 1 && sortuntil < size) {
            val tmp = internal_sort(0, size - 1)
            for (i in 0 until size)
                data[i] = tmp[i]
            sortuntil = size
        }
        return 0
    }
}

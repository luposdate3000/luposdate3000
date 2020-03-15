package lupos.s01io

import lupos.s00misc.*


class SortedDistinctDataPage<T>(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>, pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>) : SortedDataPageBase<T>(comparator, arrayAllocator, pageAllocator) {
    override fun append(value: T): Boolean {
        //println("tryappend $this $value")
        if (size == 0 || comparator.compare(data[size - 1], value) != 0) {
            if (size < capacity) {
                data[size] = value
                if (size == 0)
                    sortuntil = 1
                size++
                //println("tryappend true $this $value")
            } else {
                val res = pageAllocator(comparator, arrayAllocator)
                res.data[0] = value
                res.size = 1
                res.sortuntil = 1
                next.concatCompleteLoop(res)
            }
            return true
        }
        //println("tryappend false $this $value")
        return false
    }

    fun internal_sort(a: Array<T>, b: Array<T>, aSize: Int, bSize: Int): Pair<Array<T>, Int> {
        var aIdx = 0
        var bIdx = 0
        var res = arrayAllocator(aSize + bSize)
        var idx = 0
        var duplicates = 0
        var s = ""
        for (i in 0 until aSize)
            s += "${a[i]} "
        s += "and"
        for (i in 0 until bSize)
            s += " ${b[i]}"
        s += " to"
        for (i in 0 until res.size) {
            val next =
                    if (aIdx < aSize && (bIdx == bSize || comparator.compare(a[aIdx], b[bIdx]) <= 0))
                        a[aIdx++]
                    else
                        b[bIdx++]
            if (idx == 0 || comparator.compare(res[idx - 1], next) != 0)
                res[idx++] = next
            else
                duplicates++
        }
        for (i in 0 until (res.size - duplicates))
            s += " ${res[i]}"
        //println("internal_sort $size ${duplicates} ${res.size - duplicates} ${res.size} $aSize $bSize :: $s")
        return Pair(res, res.size - duplicates)
    }

    fun internal_sort(first: Int, last: Int): Pair<Array<T>, Int> {
        if (first == last) {
            var res = arrayAllocator(1)
            res[0] = data[first]
            return Pair(res, 1)
        }
        val middle = (first + last) / 2
        val a = internal_sort(first, middle)
        val b = internal_sort(middle + 1, last)
        return internal_sort(a.first, b.first, a.second, b.second)
    }

    override fun internal_sort(): Int {
        if (size > 1 && sortuntil < size) {
            val tmp = internal_sort(0, size - 1)
            size = tmp.second
            for (i in 0 until size)
                data[i] = tmp.first[i]
            sortuntil = size
        }
        return 0
    }
}

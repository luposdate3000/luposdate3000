package lupos.s01io

import lupos.s00misc.*


class SortedDataPage<T>(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>, pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>) : SortedDataPageBase<T>(comparator, arrayAllocator, pageAllocator) {

    override fun append(value: T): Boolean {
        if (size < capacity) {
            data[size] = value
            if (size == 0)
                internal_sortuntil = 1
            size++
        } else {
            val res = pageAllocator(comparator, arrayAllocator)
            res.data[0] = value
            res.size = 1
            res.internal_sortuntil = 1
//println("ptr a $this $res")
            res.next = next
            next = res
            res.prev = this
            res.next.prev = res
var tmp:SortedDataPageBase<T> =this.next
var tmp2:SortedDataPageBase<T> =this
require(tmp.prev==tmp2)
while(tmp!=this){
tmp2=tmp
tmp=tmp.next
require(tmp.prev==tmp2)
}
        }
        return true
    }

    fun internal_sort(a: Array<T>, b: Array<T>): Array<T> {
        var aIdx = 0
        var bIdx = 0
        var res = arrayAllocator(a.size + b.size)
        for (i in 0 until res.size) {
            if (aIdx == a.size)
                res[i] = b[bIdx++]
            else if (bIdx == b.size)
                res[i] = a[aIdx++]
            else if (comparator.compare(a[aIdx], b[bIdx]) > 0)
                res[i] = b[bIdx++]
            else
                res[i] = a[aIdx++]
        }
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
        if (size > 1 && internal_sortuntil < size) {
            val tmp = internal_sort(0, size - 1)
            for (i in 0 until size)
                data[i] = tmp[i]
            internal_sortuntil = size
        }
        return oldSize - size
    }
}

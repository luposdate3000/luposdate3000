package lupos.s01io

import lupos.s00misc.*


open class SortedArrayBase<T>(//
        val comparator: Comparator<T>,//
        val arrayAllocator: (Int) -> Array<T>,//
        val pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>//
) {
    var duplicates = 0
    var data = pageAllocator(comparator, arrayAllocator)
    var size = 0
    var internal_sortuntil = 0
    var lock = ReadWriteLock()
    override fun toString(): String {
        var res = StringBuilder("")
        var tmp = data
        res.append("<")
        while (tmp != data.prev) {
            res.append(tmp.toString())
            tmp = tmp.next
        }
        res.append(tmp.toString())
        res.append(">")
        return res.toString()
    }

    fun iterator(asc: Boolean): Iterator<T> {
        sort()
        if (asc)
            return SortedIteratorASC(this)
        return SortedIteratorDESC(this)
    }

    fun add(value: T) = lock.withWriteLock {
        size++
        data.prev.append(value)
    }

    fun forEach(action: (T) -> Unit) {
        sort()
        lock.withReadLock {
            var tmp = data
            for (i in 0 until tmp.size)
                action(tmp.data[i])
            while (tmp != data.prev) {
                tmp = tmp.next
                for (i in 0 until tmp.size)
                    action(tmp.data[i])
            }
        }
    }

    fun internal_sort(a: SortedDataPageBase<T>, b: SortedDataPageBase<T>): SortedDataPageBase<T> {
        var res = pageAllocator(comparator, arrayAllocator)
        var aIdx = 0
        var bIdx = 0
        var aPage = a
        var bPage = b
        if (comparator.compare(a.prev.data[a.prev.size - 1], b.data[0]) <= 0) {
            a.prev.next = b
            b.prev.next = a
            a.prev = b.prev
            b.prev = a
            return a
        }
        if (comparator.compare(b.prev.data[b.prev.size - 1], a.data[0]) <= 0) {
            a.prev.next = b
            b.prev.next = a
            b.prev = a.prev
            a.prev = b
            return b
        }
        while (true) {
            when {
                aIdx == aPage.size -> {
                    aIdx = 0
                    aPage = aPage.next
                    if (aPage == a) {
                        while (bIdx < bPage.size) {
                            val success = res.prev.append(bPage.data[bIdx++])
                            if (!success)
                                duplicates++
                        }
                        bPage = bPage.next
                        bIdx = 0
                        while (bPage != b) {
                            while (bIdx < bPage.size) {
                                val success = res.prev.append(bPage.data[bIdx++])
                                if (!success)
                                    duplicates++
                            }
                            bPage = bPage.next
                            bIdx = 0
                        }
                        return res
                    }
                }
                bIdx == bPage.size -> {
                    bIdx = 0
                    bPage = bPage.next
                    if (bPage == b) {
                        while (aIdx < aPage.size) {
                            val success = res.prev.append(aPage.data[aIdx++])
                            if (!success)
                                duplicates++
                        }
                        aPage = aPage.next
                        aIdx = 0
                        while (aPage != a) {
                            while (aIdx < aPage.size) {
                                val success = res.prev.append(aPage.data[aIdx++])
                                if (!success)
                                    duplicates++
                            }
                            aPage = aPage.next
                            aIdx = 0
                        }
                        return res
                    }
                }
                comparator.compare(aPage.data[aIdx], bPage.data[bIdx]) > 0 -> {
                    val success = res.prev.append(bPage.data[bIdx++])
                    if (!success)
                        duplicates++
                }
                else -> {
                    val success = res.prev.append(aPage.data[aIdx++])
                    if (!success)
                        duplicates++
                }
            }
        }
    }

    fun internal_sort(first: SortedDataPageBase<T>, last: SortedDataPageBase<T>, count: Int): SortedDataPageBase<T> {
        if (count == 1)
            return first
        val half = count / 2
        val half2 = count - half
        var middle = first
        for (i in 1 until half)
            middle = middle.next
        val middle2 = middle.next
        middle.next = first
        first.prev = middle
        middle2.prev = last
        last.next = middle2
        return internal_sort(internal_sort(first, middle, half), internal_sort(middle2, last, half2))
    }

    fun sort() = lock.withWriteLock {
        duplicates = 0
        if (internal_sortuntil < size) {
            var pageCount = 1
            var tmp = data
            tmp.internal_sort()
            while (tmp != data.prev) {
                tmp = tmp.next
                duplicates += tmp.internal_sort()
                pageCount++
            }
            if (pageCount > 1) {
                data = internal_sort(data, data.prev, pageCount)
                tmp = data
                while (tmp != data.prev) {
                    tmp.internal_sortuntil = tmp.size
                    tmp = tmp.next
                }
            }
            size -= duplicates
            internal_sortuntil = size
        }
    }
}


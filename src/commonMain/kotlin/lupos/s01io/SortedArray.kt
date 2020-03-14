package lupos.s01io

import lupos.s00misc.*


class SortedArray<T>(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>) : SortedArrayBase<T>(comparator, arrayAllocator, ::SortedDataPageAllocator) {
    fun forEachUnordered(action: (T) -> Unit) {
        var tmp = data
        lock.withReadLock {
            for (i in 0 until tmp.size)
                action(tmp.data[i])
            while (tmp != data.prev) {
                tmp = tmp.next
                for (i in 0 until tmp.size)
                    action(tmp.data[i])
            }
        }
    }

    override fun internal_sort(a: SortedDataPageBase<T>, b: SortedDataPageBase<T>): SortedDataPageBase<T> {
        var res = pageAllocator(comparator, arrayAllocator)
        var aIdx = 0
        var bIdx = 0
        var aPage = a
        var bPage = b
        if (comparator.compare(a.prev.data[a.prev.size - 1], b.data[0]) <= 0)
            return a.concatCompleteLoop( b)
        if (comparator.compare(b.prev.data[b.prev.size - 1], a.data[0]) <= 0)
            return b.concatCompleteLoop( a)
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
}

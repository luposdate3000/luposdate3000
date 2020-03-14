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
}

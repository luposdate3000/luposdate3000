package lupos.s01io

import lupos.s00misc.*


abstract class SortedArrayBase<T>(//
        val comparator: Comparator<T>,//
        val arrayAllocator: (Int) -> Array<T>,//
        val pageAllocator: (Comparator<T>, (Int) -> Array<T>) -> SortedDataPageBase<T>//
) {
    var duplicates = 0
    var data = pageAllocator(comparator, arrayAllocator)
    var size = 0
    var sortuntil = 0
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
        internalAddWithLock(value)
    }

    fun internalAddWithLock(value: T) {
        size++
        data.prev.append(value)
    }

    fun get(value: T, cmp: Comparator<T> = comparator): T? = findAction(value, cmp)
    fun set(value: T, cmp: Comparator<T> = comparator): T? = findAction(value, cmp, true, { value }, { Pair(value, true) })
    fun delete(value: T, cmp: Comparator<T> = comparator): T? = findAction(value, cmp, true, delete = true)
    fun update(value: T, cmp: Comparator<T> = comparator, onCreate: () -> T?, onUpdate: (T) -> T?): T? = findAction(value, cmp, true, onCreate, { val x = onUpdate(it);Pair(x, x != null) })

    fun findAction(value: T, cmp: Comparator<T> = comparator, isModify: Boolean = false, onCreate: () -> T? = { null }, onUpdate: (T) -> Pair<T?, Boolean>? = { null }, delete: Boolean = false): T? {
//this function assumes that the provided get-comparator is compatible to the one provided at allocation time
        var res: T? = null
        CoroutinesHelper.runBlock {
            if (isModify) {
                lock.writeLock()
                internalSortWithLock()
            } else {
                lock.readLock()
                while (sortuntil != size) {
                    lock.readUnlock()
                    sort()
                    lock.readLock()
                }
            }
            if (size > 0) {
                var tmp = data
                loop@ while (true) {
                    if (cmp.compare(tmp.data[tmp.size - 1], value) >= 0)
                        for (i in 0 until tmp.size)
                            if (cmp.compare(tmp.data[i], value) == 0) {
                                res = tmp.data[i]
                                if (isModify) {
                                    var del = delete
                                    val x = onUpdate(tmp.data[i])
                                    if (x != null) {
                                        if (x.first != null) {
                                            tmp.data[i] = x.first!!
                                        } else
                                            del = true
                                    }
                                    if (del) {
                                        tmp.delete(i)
                                        tmp.internal_sort()
                                        size--
                                        sortuntil--
                                        if (size > 0 && tmp.size == 0) {
                                            val nextpage = tmp.removePage()
                                            if (tmp == data)
                                                data = nextpage!!
                                        }
                                    }
                                }
                                break@loop
                            }
                    tmp = tmp.next
                    if (tmp == data)
                        break
                }
            }
            if (res == null && isModify) {
                val x = onCreate()
                if (x != null)
                    internalAddWithLock(x)
            }
            if (isModify)
                lock.writeUnlock()
            else
                lock.readUnlock()
        }
        return res
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

    abstract fun internal_sort(a: SortedDataPageBase<T>, b: SortedDataPageBase<T>): SortedDataPageBase<T>
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
        internalSortWithLock()
    }

    fun internalSortWithLock() {
        duplicates = 0
        if (sortuntil < size) {
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
                    tmp.sortuntil = tmp.size
                    tmp = tmp.next
                }
            }
            size -= duplicates
            sortuntil = size
        }
    }
}


package lupos.s01io

import lupos.s00misc.*


open class MySortedDataPage<T>(val comparator: Comparator<T>, val arrayAllocator: (Int) -> Array<T>,val pageAllocator:(Comparator<T>,(Int) -> Array<T>)->MySortedDataPage<T>) {
    companion object {
        val capacity = 4
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

    fun append(value: T) {
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
            res.next = next
            next = res
            res.prev = this
            res.next.prev = res
        }
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

    fun internal_sort() {
        if (size > 1 && internal_sortuntil < size) {
            val tmp = internal_sort(0, size - 1)
            for (i in 0 until size)
                data[i] = tmp[i]
            internal_sortuntil = size
        }
    }
}

class MyIteratorASC<T> : Iterator<T> {
    val data: SortedArrayBase<T>
    var node: MySortedDataPage<T>
    var idx = 0
    var closed = false

    constructor(data: SortedArrayBase<T>) {
        this.data = data
        node = data.data
        CoroutinesHelper.runBlock {
            data.lock.readLock()
        }
    }

    override fun hasNext(): Boolean {
        if (closed)
            return false
        if (idx < node.size)
            return true
        node = node.next
        idx = 0
        if (node == data.data) {
            CoroutinesHelper.runBlock {
                data.lock.readUnlock()
            }
            closed = true
            return false
        }
        return true
    }

    override fun next(): T {
        return node.data[idx++]
    }
}

class MyIteratorDESC<T> : Iterator<T> {
    val data: SortedArrayBase<T>
    var node: MySortedDataPage<T>
    var idx = 0
    var closed = false

    constructor(data: SortedArrayBase<T>) {
        this.data = data
        node = data.data.prev
        idx = node.size
        CoroutinesHelper.runBlock {
            data.lock.readLock()
        }
    }

    override fun hasNext(): Boolean {
        if (closed)
            return false
        if (idx > 0)
            return true
        node = node.prev
        idx = node.size
        if (node == data.data.prev) {
            CoroutinesHelper.runBlock {
                data.lock.readUnlock()
            }
            closed = true
            return false
        }
        return true
    }

    override fun next(): T {
        return node.data[--idx]
    }
}

fun <T> MySortedDataPageAllocator(comparator:Comparator<T>,arrayAllocator:(Int) -> Array<T>):MySortedDataPage<T>{
return MySortedDataPage<T>(comparator, arrayAllocator,::MySortedDataPageAllocator)
}

class SortedArray<T>( comparator: Comparator<T>,  arrayAllocator: (Int) -> Array<T>):SortedArrayBase<T>(comparator,arrayAllocator,::MySortedDataPageAllocator)

open class SortedArrayBase<T>(//
val comparator: Comparator<T>,//
 val arrayAllocator: (Int) -> Array<T>,//
val pageAllocator:(Comparator<T>,(Int) -> Array<T>)->MySortedDataPage<T>//
) {
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
            return MyIteratorASC(this)
        return MyIteratorDESC(this)
    }

    fun add(value: T) = lock.withWriteLock {
        size++
        data.prev.append(value)
    }

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

    fun internal_sort(a: MySortedDataPage<T>, b: MySortedDataPage<T>, aCount: Int, bCount: Int): MySortedDataPage<T> {
        var res = pageAllocator(comparator, arrayAllocator)
        var aCounter = 0
        var bCounter = 0
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
        while (aCounter < aCount && bCounter < bCount) {
            when {
                aIdx == aPage.size -> {
                    aIdx = 0
                    aCounter++
                    aPage = aPage.next
                }
                bIdx == bPage.size -> {
                    bIdx = 0
                    bCounter++
                    bPage = bPage.next
                }
                comparator.compare(aPage.data[aIdx], bPage.data[bIdx]) > 0 ->
                    res.prev.append(bPage.data[bIdx++])
                else ->
                    res.prev.append(aPage.data[aIdx++])
            }
        }
        if (aCounter == aCount) {
            while (bIdx < bPage.size)
                res.prev.append(bPage.data[bIdx++])
            bCounter++
            bPage = bPage.next
            while (bCounter < bCount) {
                bIdx = 0
                while (bIdx < bPage.size)
                    res.prev.append(bPage.data[bIdx++])
                bPage = bPage.next
                bCounter++
            }
        } else if (bCounter == bCount) {
            while (aIdx < aPage.size)
                res.prev.append(aPage.data[aIdx++])
            aCounter++
            aPage = aPage.next
            while (aCounter < aCount) {
                aIdx = 0
                while (aIdx < aPage.size)
                    res.prev.append(aPage.data[aIdx++])
                aPage = aPage.next
                aCounter++
            }
        }
        return res
    }

    fun internal_sort(first: MySortedDataPage<T>, last: MySortedDataPage<T>, count: Int): MySortedDataPage<T> {
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
        return internal_sort(internal_sort(first, middle, half), internal_sort(middle2, last, half2), half, half2)
    }

    fun sort() = lock.withWriteLock {
        if (internal_sortuntil < size) {
            var pageCount = 1
            var tmp = data
            tmp.internal_sort()
            while (tmp != data.prev) {
                tmp = tmp.next
                tmp.internal_sort()
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
            internal_sortuntil = size
        }
    }
}

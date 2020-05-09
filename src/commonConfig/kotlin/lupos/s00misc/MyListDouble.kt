/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc


/* Substitutions :: Double,,,DoubleArray, */
class MyListDouble {
    class MyListDoublePage(val version: Int) {
        var next: MyListDoublePage? = null
        var size = 0/*local*/
        val data = MyListDoubleSmall()
    }

    @JvmField
    var version = 0
    @JvmField
    var pagecount = 1
    @JvmField
    var size = 0
    @JvmField
    var page = MyListDoublePage(version)
    @JvmField
    var lastpage = page

    fun shrinkToFit() {
        if (pagecount > 5) {
            if (pagecount * ARRAY_LIST_BLOCK_CAPACITY > size * 2) {
                var c = 1
                val b = MyListDoublePage(version)
                var t = b
                var it = iterator()
                while (it.hasNext()) {
                    var j = 0
                    while (it.hasNext() && j < ARRAY_LIST_BLOCK_CAPACITY) {
                        t.data[j] = it.next()
                        j++
                    }
                    t.size = j
                    if (it.hasNext()) {
                        t.next = MyListDoublePage(version)
                        t = t.next!!
                        c++
                    }
                }
                pagecount = c
                page = b
                lastpage = t
            }
        }
    }

    inline fun reserve(capacity: Int) {
    }

    constructor() {
    }

    constructor(value: Double) {
        size = 1
        page.size = 1
        page.data[0] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> Double) {
        size = initialCapacity
        var i = 0
        var tmp = page
        while (i < size) {
            var j = tmp.size
            while (tmp.size < ARRAY_LIST_BLOCK_CAPACITY && i < size) {
                tmp.data[j] = init(i++)
                j++
            }
            tmp.size = j
            if (i < size) {
                val p = MyListDoublePage(version)
                pagecount++
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
    }

    fun clear() {
        version++
        size = 0
        page = MyListDoublePage(version)
        pagecount = 1
        lastpage = page
    }

    fun set(location: MyListDoubleFastAccess, value: Double) {
        if (location.version == version) {
            location.page.data[location.idx] = value
        } else {
            this[location.globalIdx] = value
        }
    }

    class MyListDoubleFastAccess(val page: MyListDoublePage, val idx: Int, val version: Int, val globalIdx: Int)

    fun getNullPointer() = MyListDoubleFastAccess(page, 0, version - 1, 0)
    fun addAndGetPointer(value: Double): MyListDoubleFastAccess {
        if (lastpage.size < ARRAY_LIST_BLOCK_CAPACITY) {
            lastpage.data[lastpage.size] = value
            lastpage.size++
        } else {
            val next = MyListDoublePage(version)
            lastpage.next = next
            lastpage = next
            lastpage.data[lastpage.size] = value
            lastpage.size++
            pagecount++
        }
        size++
        shrinkToFit()
        return MyListDoubleFastAccess(lastpage, lastpage.size - 1, version, size - 1)
    }

    fun add(value: Double) {
        if (lastpage.size < ARRAY_LIST_BLOCK_CAPACITY) {
            lastpage.data[lastpage.size] = value
            lastpage.size++
        } else {
            val next = MyListDoublePage(version)
            lastpage.next = next
            lastpage = next
            lastpage.data[lastpage.size] = value
            lastpage.size++
            pagecount++
        }
        size++
        shrinkToFit()
    }

    inline operator fun get(idx: Int): Double {
        SanityCheck.check({ idx < size }, { "a" })
        var tmp = page
        var offset = 0
        while (offset + tmp.size <= idx) {
            offset += tmp.size
            SanityCheck.check({ tmp.next != null }, { "b" })
            tmp = tmp.next!!
        }
        return tmp.data[idx - offset] as Double
    }

    fun removeInternal(prev: MyListDoublePage, tmp: MyListDoublePage, i: Int) {
        if (tmp.size == 1) {
            if (tmp == page) {
                if (tmp.next != null) {
//first page must not be null, and therefore is allowed to be empty
                    page = tmp.next!!
                }
            } else {
//remove page in the middle
                SanityCheck.check({ prev != tmp }, { "c" })
                prev.next = tmp.next
            }
        } else {
            for (j in i until tmp.size - 1) {
                tmp.data[j] = tmp.data[j + 1]
            }
        }
        tmp.size--
        size--
    }

    fun remove(value: Double): Boolean {
        var i = 0
        var tmp = page
        var prev = page
        while (i < size) {
            var j = 0
            while (j < tmp!!.size) {
                if (tmp!!.data[j] == value) {
                    removeInternal(prev, tmp, j)
                    return true
                }
                j++
                i++
            }
            prev = tmp
            tmp = tmp.next!!
        }
        return false
    }

    fun removeAt(idx: Int): Double {
        SanityCheck.check({ idx < size }, { "d" })
        var prev = page
        var tmp = page
        var offset = 0
        while (offset + tmp.size < idx) {
            offset += tmp.size
            prev = tmp
            tmp = tmp.next!!
        }
        var i = idx - offset
        var res = tmp.data[i] as Double
        removeInternal(prev, tmp, i)
        return res
    }

    inline operator fun set(idx: Int, value: Double) {
        SanityCheck.check({ idx <= size }, { "e" })
        if (idx == size) {
            if (lastpage.size < ARRAY_LIST_BLOCK_CAPACITY) {
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
                lastpage.next = MyListDoublePage(version)
                pagecount++
                lastpage = lastpage.next!!
                lastpage.size = 1
                lastpage.data[0] = value
            }
            size++
        } else {
            var tmp = page
            var offset = 0
            var t = idx
            while (t >= tmp.size) {
                offset += tmp.size
                t = idx - offset
                tmp = tmp.next!!
            }
            tmp.data[t] = value
        }
        shrinkToFit()
    }

    fun add(idx: Int, value: Double) {
        SanityCheck.check({ idx <= size }, { "f" })
        if (idx == size) {
            if (lastpage.size < ARRAY_LIST_BLOCK_CAPACITY) {
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
                lastpage.next = MyListDoublePage(version)
                pagecount++
                lastpage = lastpage.next!!
                lastpage.data[lastpage.size] = value
                lastpage.size++
            }
        } else {
            var tmp = page
            var offset = 0
            var t = idx
            while (t > tmp.size) {
                offset += tmp.size
                t = idx - offset
                tmp = tmp.next!!
            }
            if (t == tmp.size && tmp.size < ARRAY_LIST_BLOCK_CAPACITY) {
                tmp.data[t] = value
                tmp.size++
            } else {
                if (t == tmp.size) {
                    offset += tmp.size
                    t = idx - offset
                    tmp = tmp.next!!
                }
                if (tmp.size < ARRAY_LIST_BLOCK_CAPACITY) {
                    for (i in tmp.size downTo t + 1) {
                        tmp.data[i] = tmp.data[i - 1]
                    }
                    tmp.data[t] = value
                    tmp.size++
                } else {
                    var p = MyListDoublePage(version)
                    pagecount++
                    p.next = tmp.next
                    tmp.next = p
                    var j = 0
                    for (i in t until ARRAY_LIST_BLOCK_CAPACITY) {
                        p.data[j] = tmp.data[i]
                        j++
                    }
                    tmp.size = t + 1
                    p.size = j
                    tmp.data[t] = value
                    if (lastpage == tmp) {
                        lastpage = p
                    }
                }
            }
        }
        size++
        shrinkToFit()
    }

    fun debug(): String {
        var res = StringBuilder()
        var totalsize = 0
        res.append("Double debug $size [")
        var tmp = page
        while (true) {
            totalsize += tmp.size
            res.append("" + tmp.size + "{")
            for (i in 0 until tmp.size) {
                res.append("" + tmp.data[i] + ", ")
            }
            res.append("}, ")
            if (tmp.next == null) {
                break
            }
            tmp = tmp.next!!
        }
        res.append("]")
        SanityCheck.check({ totalsize == size }, { "g" })
        SanityCheck.check({ tmp == lastpage }, { "h" })
        return res.toString()
    }

    inline fun forEach(crossinline action: (Double) -> Unit) {
        var tmp = page
        while (true) {
            for (i in 0 until tmp.size) {
                action(tmp.data[i] as Double)
            }
            if (tmp.next == null) {
                break
            } else {
                tmp = tmp.next!!
            }
        }
    }

    inline operator fun iterator(): MyListDoubleIterator {
        return MyListDoubleIterator(this)
    }

    class MyListDoubleIterator(@JvmField val data: MyListDouble) : Iterator<Double> {
        var tmp = data.page
        var idx = 0
        override fun hasNext() = idx < tmp.size || tmp.next != null
        override fun next(): Double {
            if (idx == tmp.size) {
                tmp = tmp.next!!
                idx = 0
            }
            val res = tmp.data[idx] as Double
            idx++
            return res
        }
    }

    class MyListDoubleSmall {
        @JvmField
        var size = 0
        @JvmField
        var capacity = 1
        @JvmField
        var data: DoubleArray

        inline fun reserve(capacity: Int) {
            SanityCheck.check({ capacity <= ARRAY_LIST_BLOCK_CAPACITY }, { "i" })
            if (this.capacity < capacity) {
                this.capacity = capacity
                val tmp = DoubleArray(capacity) 
                        data.copyInto(tmp)
                data = tmp
            }
        }

        constructor() {
            data = DoubleArray(capacity) 
        }

        constructor(value: Double) {
            data = DoubleArray(capacity) 
                    data[size] = value
            size++
        }

        constructor(initialCapacity: Int, init: (Int) -> Double) {
            capacity = initialCapacity
            size = capacity
            data = DoubleArray(capacity) { init(it) }
        }

        fun clear() {
            size = 0
        }

        fun add(value: Double) {
            if (size >= capacity) {
                reserve(capacity * 2)
            }
            data[size] = value
            size++
        }

        inline operator fun get(idx: Int): Double {
            return data.get(idx) as Double
        }

        inline operator fun set(idx: Int, value: Double) {
            SanityCheck.check({ idx <= size }, { "j" })
            if (idx == size) {
                add(value)
            } else {
                data.set(idx, value)
            }
        }

        fun remove(value: Double): Boolean {
            for (idx in 0 until size) {
                if (data[idx] == value) {
                    removeAt(idx)
                    return true
                }
            }
            return false
        }

        fun removeAt(idx: Int): Double {
            val res = data[idx]
            SanityCheck.check({ idx < size }, { "k" })
            for (i in idx until size) {
                data[i] = data[i + 1]
            }
            size--
            return res as Double
        }

        fun add(idx: Int, value: Double) {
            if (size >= capacity) {
                reserve(capacity * 2)
            }
            if (idx < size) {
                size++
                for (i in size downTo idx + 1) {
                    data[i] = data[i - 1]
                }
                data[idx] = value
            } else {
                data[size] = value
                size++
            }
        }

        inline operator fun iterator(): MyListDoubleSmallIterator {
            return MyListDoubleSmallIterator(this)
        }
    }

    class MyListDoubleSmallIterator(@JvmField val data: MyListDoubleSmall) : Iterator<Double> {
        var index = 0
        override fun hasNext(): Boolean {
            return index < data.size
        }

        override fun next(): Double {
            val res = data.data[index] as Double
            index++
            return res
        }
    }
}

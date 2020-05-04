/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: Generic,<Generic>,<Generic>,Array<Any?>,{null} */
class MyListGeneric<Generic> {
    companion object {
        val capacity = 1024
    }

    class MyListGenericPage<Generic>() {
        var next: MyListGenericPage<Generic>? = null
        var size = 0/*local*/
        val data = MyListGenericSmall<Generic>()
    }

    var pagecount = 1
    @JvmField
    var size = 0
    @JvmField
    var page = MyListGenericPage<Generic>()
    @JvmField
    var lastpage = page

    fun shrinkToFit() {
        if (pagecount > 5) {
            if (pagecount * capacity > size * 2) {
                var c = 1
                val b = MyListGenericPage<Generic>()
                var t = b
                var it = iterator()
                while (it.hasNext()) {
                    var j = 0
                    while (it.hasNext() && j < capacity) {
                        t.data[j] = it.next()
                        j++
                    }
                    t.size = j
                    if (it.hasNext()) {
                        t.next = MyListGenericPage<Generic>()
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

    constructor(value: Generic) {
        size = 1
        page.size = 1
        page.data[0] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> Generic) {
        size = initialCapacity
        var i = 0
        var tmp = page
        while (i < size) {
            var j = tmp.size
            while (tmp.size < capacity && i < size) {
                tmp.data[j] = init(i++)
                j++
            }
            tmp.size = j
            if (i < size) {
                val p = MyListGenericPage<Generic>()
                pagecount++
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
    }

    fun clear() {
        size = 0
        page = MyListGenericPage<Generic>()
        pagecount = 1
        lastpage = page
    }

    fun add(value: Generic) {
        if (lastpage.size < capacity) {
            lastpage.data[lastpage.size] = value
            lastpage.size++
        } else {
            val next = MyListGenericPage<Generic>()
            lastpage.next = next
            lastpage = next
            lastpage.data[lastpage.size] = value
            lastpage.size++
            pagecount++
        }
        size++
        shrinkToFit()
    }

    inline operator fun get(idx: Int): Generic {
        SanityCheck.check { idx < size }
        var tmp = page
        var offset = 0
        while (offset + tmp.size <= idx) {
            offset += tmp.size
            SanityCheck.check { tmp.next != null }
            tmp = tmp.next!!
        }
        return tmp.data[idx - offset] as Generic
    }

    fun removeInternal(prev: MyListGenericPage<Generic>, tmp: MyListGenericPage<Generic>, i: Int) {
        if (tmp.size == 1) {
            if (tmp == page) {
                if (tmp.next != null) {
//first page must not be null, and therefore is allowed to be empty
                    page = tmp.next!!
                }
            } else {
//remove page in the middle
SanityCheck.check{prev!=tmp}
                prev.next = tmp.next
            }
        } else {
            for (j in i until tmp.size - 1){
                tmp.data[j] = tmp.data[j + 1]
	    }
        }
        tmp.size--
        size--
    }

    fun remove(value: Generic): Boolean {
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

    fun removeAt(idx: Int): Generic {
        SanityCheck.check { idx < size }
        var prev = page
        var tmp = page
        var offset = 0
        while (offset + tmp.size < idx) {
            offset += tmp.size
            prev = tmp
            tmp = tmp.next!!
        }
        var i = idx - offset
        var res = tmp.data[i] as Generic
        removeInternal(prev, tmp, i)
        return res
    }

    inline operator fun set(idx: Int, value: Generic) {
        SanityCheck.check { idx <= size }
        if (idx == size) {
            if (lastpage.size < capacity) {
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
                lastpage.next = MyListGenericPage<Generic>()
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

    fun add(idx: Int, value: Generic) {
        SanityCheck.check { idx <= size }
        if (idx == size) {
            if (lastpage.size < capacity) {
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
                lastpage.next = MyListGenericPage<Generic>()
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
            if (t == tmp.size && tmp.size < capacity) {
                tmp.data[t] = value
                tmp.size++
            } else {
                if (t == tmp.size) {
                    offset += tmp.size
                    t = idx - offset
                    tmp = tmp.next!!
                }
                if (tmp.size < capacity) {
                    for (i in tmp.size downTo t + 1) {
                        tmp.data[i] = tmp.data[i - 1]
                    }
                    tmp.data[t] = value
                    tmp.size++
                } else {
                    var p = MyListGenericPage<Generic>()
                    pagecount++
                    p.next = tmp.next
                    tmp.next = p
                    var j = 0
                    for (i in t until capacity) {
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
        res.append("Generic<Generic> debug $size [")
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
        SanityCheck.check { totalsize == size }
        SanityCheck.check { tmp == lastpage }
        return res.toString()
    }

    inline operator fun iterator(): MyListGenericIterator<Generic> {
        return MyListGenericIterator(this)
    }

    class MyListGenericIterator<Generic>(@JvmField val data: MyListGeneric<Generic>) : Iterator<Generic> {
        var tmp = data.page
        var idx = 0
        override fun hasNext() = idx < tmp.size || tmp.next != null
        override fun next(): Generic {
            if (idx == tmp.size) {
                tmp = tmp.next!!
                idx = 0
            }
            val res = tmp.data[idx] as Generic
            idx++
            return res
        }
    }

    class MyListGenericSmall<Generic> {
        @JvmField
        var size = 0
        @JvmField
        var capacity = 1
        @JvmField
        var data: Array<Any?>

        inline fun reserve(capacity: Int) {
            SanityCheck.check { capacity <= MyListGeneric.capacity }
            if (this.capacity < capacity) {
                this.capacity = capacity
                val tmp = Array<Any?>(capacity) {null}
                        data.copyInto(tmp)
                data = tmp
            }
        }

        constructor() {
            data = Array<Any?>(capacity) {null}
        }

        constructor(value: Generic) {
            data = Array<Any?>(capacity) {null}
                    data[size] = value
            size++
        }

        constructor(initialCapacity: Int, init: (Int) -> Generic) {
            capacity = initialCapacity
            size = capacity
            data = Array<Any?>(capacity) { init(it) }
        }

        fun clear() {
            size = 0
        }

        fun add(value: Generic) {
            if (size >= capacity) {
                reserve(capacity * 2)
            }
            data[size] = value
            size++
        }

        inline operator fun get(idx: Int): Generic {
            return data.get(idx) as Generic
        }

        inline operator fun set(idx: Int, value: Generic) {
            SanityCheck.check { idx <= size }
            if (idx == size) {
                add(value)
            } else {
                data.set(idx, value)
            }
        }

        fun remove(value: Generic): Boolean {
            for (idx in 0 until size) {
                if (data[idx] == value) {
                    removeAt(idx)
                    return true
                }
            }
            return false
        }

        fun removeAt(idx: Int): Generic {
            val res = data[idx]
            SanityCheck.check { idx < size }
            for (i in idx until size) {
                data[i] = data[i + 1]
            }
            size--
            return res as Generic
        }

        fun add(idx: Int, value: Generic) {
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

        inline operator fun iterator(): MyListGenericSmallIterator<Generic> {
            return MyListGenericSmallIterator(this)
        }
    }

    class MyListGenericSmallIterator<Generic>(@JvmField val data: MyListGenericSmall<Generic>) : Iterator<Generic> {
        var index = 0
        override fun hasNext(): Boolean {
            return index < data.size
        }

        override fun next(): Generic {
            val res = data.data[index] as Generic
            index++
            return res
        }
    }
}

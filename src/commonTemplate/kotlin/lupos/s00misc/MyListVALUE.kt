package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: VALUE,GDEF,GUSE,ARRAYTYPE,ARRAYINITIALIZER */
class MyListVALUEGDEF {
    companion object {
        val capacity = 1024
    }

    class MyListVALUEPageGDEF() {
        var next: MyListVALUEPageGDEF? = null
        var size = 0/*local*/
        val data = MyListVALUESmallGDEF()
    }

    var pagecount = 1
    @JvmField
    var size = 0
    @JvmField
    var page = MyListVALUEPageGDEF()
    @JvmField
    var lastpage = page

    fun shrinkToFit() {
//println("shrink a")
        if (pagecount > 5) {
//println("shrink b")
            if (pagecount * capacity > size * 2) {
//println("shrink c")
                var c = 1
                val b = MyListVALUEPageGDEF()
                var t = b
                var it = iterator()
                while (it.hasNext()) {
//println("shrink d")
                    var j = 0
                    while (it.hasNext() && j < capacity) {
//println("shrink e $j $capacity")
                        t.data[j] = it.next()
                        j++
                    }
                    t.size = j
                    if (it.hasNext()) {
//println("shrink f")
                        t.next = MyListVALUEPageGDEF()
                        t = t.next!!
                        c++
                    }
                }
                pagecount = c
                page = b
                lastpage = t
            }
        }
        //debug()
    }

    inline fun reserve(capacity: Int) {
    }

    constructor() {
    }

    constructor(value: VALUE) {
        size = 1
        page.size = 1
        page.data[0] = value
        //debug()
    }

    constructor(initialCapacity: Int, init: (Int) -> VALUE) {
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
                val p = MyListVALUEPageGDEF()
                pagecount++
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
        //debug()
    }

    fun clear() {
        size = 0
        page = MyListVALUEPageGDEF()
        pagecount = 1
        lastpage = page
        //debug()
    }

    fun add(value: VALUE) {
//println("addd a")
        if (lastpage.size < capacity) {
//println("addd b")
            //println("lastpage.size < capacity ${lastpage.size} ${capacity}")
            lastpage.data[lastpage.size] = value
            lastpage.size++
        } else {
//println("addd c")
            lastpage.next = MyListVALUEPageGDEF()
            pagecount++
            lastpage = lastpage.next!!
            lastpage.data[lastpage.size] = value
            lastpage.size++
        }
        size++
        //debug()
        shrinkToFit()
    }

    inline operator fun get(idx: Int): VALUE {
        require(idx < size)
        var tmp = page
        var offset = 0
        while (offset + tmp.size <= idx) {
            offset += tmp.size
            require(tmp.next != null) { debug() + " $offset $idx" }
            tmp = tmp.next!!
        }
        //debug()
        return tmp.data[idx - offset] as VALUE
    }

    fun remove(value: VALUE): Boolean {
        var i = 0
        var tmp: MyListVALUEPageGDEF? = page
        while (i < size) {
            var j = 0
            while (j < tmp!!.size) {
                if (tmp!!.data[j] == value) {
                    while (j < tmp!!.size - 1) {
                        tmp.data[j] = tmp.data[j + 1]
                        j++
                    }
                    tmp.size--
                    size--
                    //debug()
                    return true
                }
                j++
                i++
            }
            tmp = tmp.next
        }
        //debug()
        return false
    }

    fun removeAt(idx: Int): VALUE {
        var tmp = page
        var offset = 0
        while (offset + tmp.size < idx) {
            offset += tmp.size
            tmp = tmp.next!!
        }
        var i = idx - offset
        var res = tmp.data[i] as VALUE
        while (i < tmp.size - 1) {
            tmp.data[i] = tmp.data[i + 1]
            i++
        }
        tmp.size--
        size--
        //debug()
        return res
    }

    inline operator fun set(idx: Int, value: VALUE) {
        require(idx <= size)
//println("set a")
        if (idx == size) {
//println("set b")
            if (lastpage.size < capacity) {
//println("set c")
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
//println("set d")
                lastpage.next = MyListVALUEPageGDEF()
                pagecount++
                lastpage = lastpage.next!!
                lastpage.size = 1
                lastpage.data[0] = value
            }
            size++
        } else {
//println("set e")
            var tmp = page
            var offset = 0
            var t = idx
            while (t >= tmp.size) {
//println("set f")
                offset += tmp.size
                t = idx - offset
                tmp = tmp.next!!
            }
            tmp.data[t] = value
        }
        //debug()
        shrinkToFit()
    }

    fun add(idx: Int, value: VALUE) {
        require(idx <= size)
//println("add a")
        if (idx == size) {
//println("add b")
            if (lastpage.size < capacity) {
//println("add c")
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
//println("add d")
                lastpage.next = MyListVALUEPageGDEF()
                pagecount++
                lastpage = lastpage.next!!
                lastpage.data[lastpage.size] = value
                lastpage.size++
            }
        } else {
//println("add e")
            var tmp = page
            var offset = 0
            var t = idx
            while (t > tmp.size) {
//println("add f")
                offset += tmp.size
                t = idx - offset
                tmp = tmp.next!!
            }
            if (t == tmp.size && tmp.size < capacity) {
//println("add g")
                tmp.data[t] = value
                tmp.size++
            } else {
//println("add h")
                if (t == tmp.size) {
//println("add i")
                    offset += tmp.size
                    t = idx - offset
                    tmp = tmp.next!!
                }
                if (tmp.size < capacity) {
//println("add j")
                    for (i in tmp.size downTo t + 1) {
//println("add k")
                        tmp.data[i] = tmp.data[i - 1]
                    }
                    tmp.data[t] = value
                    tmp.size++
                } else {
//println("add l")
                    var p = MyListVALUEPageGDEF()
                    pagecount++
                    p.next = tmp.next
                    tmp.next = p
                    var j = 0
                    for (i in t until capacity) {
//println("add m")
                        p.data[j] = tmp.data[i]
                        j++
                    }
                    tmp.size = t + 1
                    p.size = j
                    tmp.data[t] = value
                    if (lastpage == tmp) {
//println("add n")
                        lastpage = p
                    }
                }
            }
        }
        size++
        //debug()
        shrinkToFit()
    }

    fun debug(): String {
        var res = StringBuilder()
        var totalsize = 0
        res.append("VALUEGDEF debug $size [")
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
        require(totalsize == size, { "size incorrect ${res.toString()}" })
        require(tmp == lastpage, { "lastpage incorrect ${res.toString()}" })
        return res.toString()
    }

    inline operator fun iterator(): MyListVALUEIteratorGUSE {
        return MyListVALUEIterator(this)
    }

    class MyListVALUEIteratorGDEF(@JvmField val data: MyListVALUEGUSE) : Iterator<VALUE> {
        var tmp = data.page
        var globalidx = 0
        var idx = 0
        override fun hasNext() = idx < tmp.size || tmp.next != null
        override fun next(): VALUE {
            if (idx == tmp.size) {
                globalidx += idx
                tmp = tmp.next!!
                idx = 0
            }
            val res = tmp.data[idx] as VALUE
            idx++
            return res
        }
    }

    class MyListVALUESmallGDEF {
        @JvmField
        var size = 0
        @JvmField
        var capacity = 1
        @JvmField
        var data: ARRAYTYPE

        inline fun reserve(capacity: Int) {
            require(capacity <= MyListVALUE.capacity, { "capacity too large" })
            if (this.capacity < capacity) {
                this.capacity = capacity
                val tmp = ARRAYTYPE(capacity) ARRAYINITIALIZER
                        data.copyInto(tmp)
                data = tmp
            }
        }

        constructor() {
            data = ARRAYTYPE(capacity) ARRAYINITIALIZER
        }

        constructor(value: VALUE) {
            data = ARRAYTYPE(capacity) ARRAYINITIALIZER
                    data[size] = value
            size++
        }

        constructor(initialCapacity: Int, init: (Int) -> VALUE) {
            capacity = initialCapacity
            size = capacity
            data = ARRAYTYPE(capacity) { init(it) }
        }

        fun clear() {
            size = 0
        }

        fun add(value: VALUE) {
            //println("size capacity $size $capacity ${capacity * 2}")
            if (size >= capacity) {
                reserve(capacity * 2)
            }
            data[size] = value
            size++
        }

        inline operator fun get(idx: Int): VALUE {
            return data.get(idx) as VALUE
        }

        inline operator fun set(idx: Int, value: VALUE) {
            //println("idx size $idx $size")
            require(idx <= size)
            if (idx == size) {
                add(value)
            } else {
                data.set(idx, value)
            }
        }

        fun remove(value: VALUE): Boolean {
            for (idx in 0 until size) {
                if (data[idx] == value) {
                    removeAt(idx)
                    return true
                }
            }
            return false
        }

        fun removeAt(idx: Int): VALUE {
            val res = data[idx]
            require(idx < size)
            for (i in idx until size) {
                data[i] = data[i + 1]
            }
            size--
            return res as VALUE
        }

        fun add(idx: Int, value: VALUE) {
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

        inline operator fun iterator(): MyListVALUESmallIteratorGUSE {
            return MyListVALUESmallIterator(this)
        }
    }

    class MyListVALUESmallIteratorGDEF(@JvmField val data: MyListVALUESmallGUSE) : Iterator<VALUE> {
        var index = 0
        override fun hasNext(): Boolean {
            return index < data.size
        }

        override fun next(): VALUE {
            val res = data.data[index] as VALUE
            index++
            return res
        }
    }
}

package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: VALUE,GDEF,GUSE,ARRAYTYPE,ARRAYINITIALIZER */
class MyListVALUEGDEF {

    class MyListVALUEPageGDEF() {
        var next: MyListVALUEPageGDEF? = null
        var size = 0/*local*/
        val data: ARRAYTYPE = ARRAYTYPE(50) ARRAYINITIALIZER
    }

    @JvmField
    var size = 0
    @JvmField
    var page: MyListVALUEPageGDEF = MyListVALUEPageGDEF()
    @JvmField
    var lastpage = page

    inline fun reserve(capacity: Int) {
    }

    constructor() {
    }

    constructor(value: VALUE) {
        size = 1
        page = MyListVALUEPageGDEF()
        page.size = 1
        page.data[0] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> VALUE) {
        size = initialCapacity
        var i = 0
        page = MyListVALUEPageGDEF()
        var tmp = page
        while (i < size) {
            var j = tmp.size
            while (tmp.size < tmp.data.size && i < size) {
                tmp.data[j++] = init(i++)
            }
            tmp.size = j
            if (i < size) {
                val p = MyListVALUEPageGDEF()
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
    }

    fun clear() {
        size = 0
        page = MyListVALUEPageGDEF()
        lastpage = page
    }

    fun add(value: VALUE) {
        if (lastpage.size < lastpage.data.size) {
            lastpage.data[lastpage.size++] = value
        } else {
            lastpage.next = MyListVALUEPageGDEF()
            lastpage = lastpage.next!!
            lastpage.data[lastpage.size++] = value
        }
        size++
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
                    return true
                }
                j++
                i++
            }
            tmp = tmp.next
        }
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
        return res
    }

    inline operator fun set(idx: Int, value: VALUE) {
        require(idx <= size)
        if (idx == size) {
            if (lastpage.size < lastpage.data.size) {
                lastpage.data[lastpage.size] = value
                lastpage.size++
            } else {
                lastpage.next = MyListVALUEPageGDEF()
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
    }

    fun add(idx: Int, value: VALUE) {
        require(idx <= size)
        if (idx == size) {
            if (lastpage.size < lastpage.data.size) {
                lastpage.data[lastpage.size++] = value
            } else {
                lastpage.next = MyListVALUEPageGDEF()
                lastpage = lastpage.next!!
                lastpage.data[lastpage.size++] = value
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
            if (t == tmp.size && tmp.size < tmp.data.size) {
                tmp.data[t] = value
                tmp.size++
            } else {
                if (t == tmp.size) {
                    offset += tmp.size
                    t = idx - offset
                    tmp = tmp.next!!
                }
                if (tmp.size < tmp.data.size) {
                    for (i in tmp.size downTo t + 1) {
                        tmp.data[i] = tmp.data[i - 1]
                    }
                    tmp.data[t] = value
                    tmp.size++
                } else {
                    var p = MyListVALUEPageGDEF()
                    p.next = tmp.next
                    tmp.next = p
                    var j = 0
                    for (i in t until tmp.data.size) {
                        p.data[j++] = tmp.data[i]
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

    inline operator fun iterator() = MyListVALUEIterator(this)
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
            return tmp.data[idx++] as VALUE
        }
    }
}

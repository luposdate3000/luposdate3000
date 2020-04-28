package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: VALUE,GDEF,GUSE,ARRAYTYPE,ARRAYINITIALIZER */
class MyListVALUEGDEF {
    companion object {
        var instanceCounter = 0
    }

    var uuid = instanceCounter++

    class MyListVALUEPageGDEF() {
        var next: MyListVALUEPageGDEF? = null
        var size = 0/*local*/
        val data: ARRAYTYPE = ARRAYTYPE(5) ARRAYINITIALIZER
    }

    @JvmField
    var size = 0
    @JvmField
    var page: MyListVALUEPageGDEF = MyListVALUEPageGDEF()
    @JvmField
    var lastpage = page

    inline fun reserve(capacity: Int) {
        println("VALUEGDEF $uuid reserve $capacity")
    }

    constructor() {
        println("VALUEGDEF $uuid constructor 0")
    }

    constructor(value: VALUE) {
        println("VALUEGDEF $uuid constructor 1")
        size = 1
        page = MyListVALUEPageGDEF()
        page.size = 1
        page.data[0] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> VALUE) {
        println("VALUEGDEF $uuid constructor init $initialCapacity")
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
        println(debug())
    }

    fun clear() {
        println("VALUEGDEF $uuid clear")
        size = 0
        page = MyListVALUEPageGDEF()
        lastpage = page
        println(debug())
    }

    fun add(value: VALUE) {
        println("VALUEGDEF $uuid add")
        if (lastpage.size < lastpage.data.size) {
            lastpage.data[lastpage.size++] = value
        } else {
            lastpage.next = MyListVALUEPageGDEF()
            lastpage = lastpage.next!!
            lastpage.data[lastpage.size++] = value
        }
        size++
        println(debug())
    }

    inline operator fun get(idx: Int): VALUE {
        println("VALUEGDEF $uuid get $idx")
        require(idx < size)
        var tmp = page
        var offset = 0
        while (offset + tmp.size <= idx) {
            offset += tmp.size
            require(tmp.next != null) { debug() + " $offset $idx" }
            tmp = tmp.next!!
        }
        println(debug())
        return tmp.data[idx - offset] as VALUE
    }

    fun remove(value: VALUE): Boolean {
        println("VALUEGDEF $uuid remove $size")
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
                    println(debug())
                    return true
                }
                j++
                i++
            }
            tmp = tmp.next
        }
        println(debug())
        return false
    }

    fun removeAt(idx: Int): VALUE {
        println("VALUEGDEF $uuid removeAt $idx $size")
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
        println(debug())
        return res
    }

    inline operator fun set(idx: Int, value: VALUE) {
        println("VALUEGDEF $uuid set $idx $size")
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
        println(debug())
    }

    fun add(idx: Int, value: VALUE) {
        println("VALUEGDEF $uuid add $idx $size")
        require(idx <= size)
        if (idx == size) {
            if (lastpage.size < lastpage.data.size) {
                println("a")
                lastpage.data[lastpage.size++] = value
            } else {
                println("b")
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
                    println("marker c $t ${tmp.size}")
                    for (i in tmp.size downTo t + 1) {
                        println("marker cc $i")
                        tmp.data[i] = tmp.data[i - 1]
                    }
                    tmp.data[t] = value
                    tmp.size++
                } else {
                    println("d")
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
        println(debug())
    }

    fun debug(): String {
        var res = StringBuilder()
        var totalsize = 0
        res.append("VALUEGDEF $uuid debug $size [")
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
            println("VALUEGDEF ${data.uuid} iterator $idx $globalidx")
            if (idx == tmp.size) {
                globalidx += idx
                tmp = tmp.next!!
                idx = 0
            }
            return tmp.data[idx++] as VALUE
        }
    }
}

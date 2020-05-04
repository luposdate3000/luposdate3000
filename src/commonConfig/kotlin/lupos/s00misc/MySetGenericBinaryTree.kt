package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
/* Substitutions :: Generic,<Generic : Comparable<Generic>>,<Generic> */
class MySetGenericBinaryTree<Generic : Comparable<Generic>> {
    @JvmField
    var data = MyListGeneric<Generic>()
    var size: Int = 0
        get() = data.size

    inline fun clear() {
        data.clear()
    }

    operator fun iterator(): Iterator<Generic> {
        return data.iterator()
    }

    constructor() {
    }

    constructor(value: Generic) : this() {
        data.add(value)
    }

    fun appendAssumeSorted(value: Generic) {
        data.add(value)
    }

    inline fun reserve(capacity: Int) {
        data.reserve(capacity)
    }

    inline fun internal(value: Generic, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
        if (data.size == 0) {
            onCreate(0)
        } else if (data.size == 1) {
            val d = data[0]
            if (d < value) {
                onCreate(1)
            } else if (d > value) {
                onCreate(0)
            } else {
                onExists(0)
            }
        } else {
            var l = 0
            var r = data.size - 1
            while (r - l > 1) {
                var m = (r + l) / 2
                val d = data[m]
                if (d < value) {
                    l = m
                } else if (d > value) {
                    r = m
                } else {
                    onExists(m)
                    return
                }
            }
            val dl = data[l]
            val dr = data[r]
            if (dr < value) {
                onCreate(r + 1)
            } else if (dl > value) {
                onCreate(l)
            } else if (dl == value) {
                onExists(l)
            } else if (dr > value && dl < value) {
                onCreate(r)
            } else {
                onExists(r)
            }
        }
    }

    inline fun remove(value: Generic, crossinline onExists: (it: Int) -> Unit = {}) {
        internal(value, {
        }, {
            onExists(it)
            data.removeAt(it)
        })
    }

    inline fun contains(value: Generic): Boolean {
        var res = false
        internal(value, {
        }, {
            res = true
        })
        return res
    }

    inline fun find(value: Generic, crossinline onExists: (it: Int) -> Unit) {
        internal(value, {
        }, {
            onExists(it)
        })
    }

    inline fun add(value: Generic, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
        if (data.size > 0 && value > data[data.size - 1]) {
            val it = data.size
            data.add(it, value)
            onCreate(it)
        } else {
            internal(value, {
                data.add(it, value)
                onCreate(it)
            }, {
                onExists(it)
            })
        }
    }

    inline fun toList(): MyListGeneric<Generic> {
        return data
    }
}

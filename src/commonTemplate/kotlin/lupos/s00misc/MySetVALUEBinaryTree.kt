package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: VALUE,GDEF,GUSE */
class MySetVALUEBinaryTreeGDEF {
    @JvmField
    var data = MyListVALUEGUSE()
    var size: Int = 0
        get() = data.size

    inline fun clear() {
        data.clear()
    }

    operator fun iterator(): Iterator<VALUE> {
        return data.iterator()
    }

    constructor() {
    }

    constructor(value: VALUE) : this() {
        data.add(value)
    }

    fun appendAssumeSorted(value: VALUE) {
        data.add(value)
    }

    inline fun reserve(capacity: Int) {
        data.reserve(capacity)
    }

    inline fun internal(value: VALUE, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
        if (data.size == 0) {
            onCreate(0)
        } else if (data.size == 1) {
            if (data[0] < value) {
                onCreate(1)
            } else if (data[0] > value) {
                onCreate(0)
            } else {
                onExists(0)
            }
        } else {
            var l = 0
            var r = data.size - 1
            while (r - l > 1) {
                var m = (r + l) / 2
                if (data[m] < value) {
                    l = m
                } else if (data[m] > value) {
                    r = m
                } else {
                    onExists(m)
                    return
                }
            }
            if (data[r] < value) {
                onCreate(r + 1)
            } else if (data[l] > value) {
                onCreate(l)
            } else if (data[r] > value && data[l] < value) {
                onCreate(r)
            } else if (data[l] == value) {
                onExists(l)
            } else {
                onExists(r)
            }
        }
    }

    inline fun remove(value: VALUE, crossinline onExists: (it: Int) -> Unit = {}) {
        internal(value, {
        }, {
            onExists(it)
            data.removeAt(it)
        })
    }

    inline fun contains(value: VALUE): Boolean {
        var res = false
        internal(value, {
        }, {
            res = true
        })
        return res
    }

    inline fun find(value: VALUE, crossinline onExists: (it: Int) -> Unit) {
        internal(value, {
        }, {
            onExists(it)
        })
    }

    inline fun add(value: VALUE, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
        internal(value, {
            data.add(it, value)
            onCreate(it)
        }, {
            onExists(it)
        })
    }

    inline fun toList(): MyListVALUEGUSE {
        return data
    }
}

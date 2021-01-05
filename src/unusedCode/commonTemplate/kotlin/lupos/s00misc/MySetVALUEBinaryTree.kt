package lupos.s00misc
import kotlin.jvm.JvmName
/* Substitutions :: VALUE,GDEF,GUSE */
class MySetVALUEBinaryTreeGDEF {
    @JvmField
    var data = MyListVALUEGUSE()
    var size: Int = 0
        get() = data.size
     internal inline fun clear() {
        data.clear()
    }
    operator fun iterator(): Iterator<VALUE> {
        return data.iterator()
    }
    fun forEach(action: (VALUE) -> Unit) {
        var it = iterator()
        while (it.hasNext()) {
            val v = it.next()
            action(v)
        }
    }
    constructor() {
    }
    constructor(value: VALUE) : this() {
        data.add(value)
    }
    fun appendAssumeSorted(value: VALUE) {
        data.add(value)
    }
     internal inline fun reserve(capacity: Int) {
        data.reserve(capacity)
    }
     internal inline fun internal(value: VALUE, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
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
     internal inline fun remove(value: VALUE, crossinline onExists: (it: Int) -> Unit = {}) {
        internal(
            value,
            {
            },
            {
                onExists(it)
                data.removeAt(it)
            }
        )
    }
     internal inline fun contains(value: VALUE): Boolean {
        var res = false
        internal(
            value,
            {
            },
            {
                res = true
            }
        )
        return res
    }
     internal inline fun find(value: VALUE, crossinline onExists: (it: Int) -> Unit) {
        internal(
            value,
            {
            },
            {
                onExists(it)
            }
        )
    }
     internal inline fun add(value: VALUE, crossinline onCreate: (it: Int) -> Unit = {}, crossinline onExists: (it: Int) -> Unit = {}) {
        if (data.size > 0 && value > data[data.size - 1]) {
            val it = data.size
            data.add(it, value)
            onCreate(it)
        } else {
            internal(
                value,
                {
                    data.add(it, value)
                    onCreate(it)
                },
                {
                    onExists(it)
                }
            )
        }
    }
     internal inline fun toList(): MyListVALUEGUSE {
        return data
    }
}

package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapKNAMEVNAMEBTreeGDEF(val t: Int) {
    var root: MyMapKNAMEVNAMEBTreeNodeGUSE? = null
    var size = 0

    constructor() : this(512)

open    class MyMapKNAMEVNAMEBTreeNodeIteratorGDEF(val node: MyMapKNAMEVNAMEBTreeNodeGUSE?) : Iterator<KEY> {

        var i = 0
        var childIterator = node!!.C[0]!!.iterator()
        var v: VALUE = node!!.values[0] as VALUE
        override fun hasNext(): Boolean {
            if (node!!.leaf) {
                return i < node.n
            } else {
                return i < node.n || (i == node.n && childIterator.hasNext())
            }
        }

        override fun next(): KEY {
            if (node!!.leaf) {
                v = node.values[i] as VALUE
                return node.keys[i++] as KEY
            } else {
                if (childIterator.hasNext()) {
                    return childIterator.next()
                } else {
                    childIterator = node.C[i + 1]!!.iterator()
                    v = node.values[i] as VALUE
                    return node.keys[i++] as KEY
                }
            }
        }
fun value()=v
    }

    class MyMapKNAMEVNAMEBTreeNodeGDEF(val t: Int, val leaf: Boolean) {
        val keys = ARRAYKTYPE(2 * t - 1) ARRAYKINITIALIZER
        val values = ARRAYVTYPE(2 * t - 1) ARRAYVINITIALIZER
        val C = Array<MyMapKNAMEVNAMEBTreeNodeGUSE?>(2 * t) { null }
        var n = 0
        fun free() {
            /*later when buffer-manager is used*/
        }

        fun iterator() = MyMapKNAMEVNAMEBTreeNodeIteratorGUSE(this)
        fun findKEY(k: KEY): Int {
            var idx = 0
            while (idx < n && (keys[idx] as KEY) < k) {
                idx++
            }
            return idx
        }

        fun remove(k: KEY): Pair<KEY, VALUE>? {
            val idx = findKEY(k)
            val key = keys[idx] as KEY
            val value = values[idx] as VALUE
            if (idx < n && key == k) {
                if (leaf) {
                    removeFromLeaf(idx)
                } else {
                    removeFromNonLeaf(idx)
                }
                return Pair(key, value)
            } else if (!leaf) {
                val flag = idx == n
                if (C[idx]!!.n < t) {
                    fill(idx)
                }
                if (flag && idx > n) {
                    return C[idx - 1]!!.remove(k)
                } else {
                    return C[idx]!!.remove(k)
                }
            } else {
                return null
            }
        }

        fun removeFromLeaf(idx: Int) {
            for (i in idx + 1 until n) {
                keys[i - 1] = keys[i]
                values[i - 1] = values[i]
            }
            n--
        }

        fun removeFromNonLeaf(idx: Int) {
            val k = keys[idx] as KEY
            if (C[idx]!!.n >= t) {
                var cur = C[idx]!!
                while (!cur.leaf) {
                    cur = cur.C[cur.n]!!
                }
                val pred = cur.keys[cur.n - 1] as KEY
                keys[idx] = pred
                values[idx] = cur.values[cur.n - 1] as VALUE
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= t) {
                var cur = C[idx + 1]!!
                while (!cur.leaf) {
                    cur = cur.C[0]!!
                }
                val succ = cur.keys[0] as KEY
                keys[idx] = succ
                values[idx] = cur.values[0] as VALUE
                C[idx + 1]!!.remove(succ)
            } else {
                merge(idx)
                C[idx]!!.remove(k)
            }
        }

        fun fill(idx: Int) {
            if (idx != 0 && C[idx - 1]!!.n >= t) {
                borrowFromPrev(idx)
            } else if (idx != n && C[idx + 1]!!.n >= t) {
                borrowFromNext(idx)
            } else if (idx != n) {
                merge(idx)
            } else {
                merge(idx - 1)
            }
        }

        fun borrowFromPrev(idx: Int) {
            val child = C[idx]!!
            val sibling = C[idx - 1]!!
            var i = child.n - 1
            while (i >= 0) {
                child.keys[i + 1] = child.keys[i]
                child.values[i + 1] = child.values[i]
                i--
            }
            if (!child.leaf) {
                i = child.n
                while (i >= 0) {
                    child.C[i + 1] = child.C[i]
                    i--
                }
                child.keys[0] = keys[idx - 1]
                child.values[0] = values[idx - 1]
                if (!child.leaf) {
                    child.C[0] = sibling.C[sibling.n]
                }
                keys[idx - 1] = sibling.keys[sibling.n - 1]
                values[idx - 1] = sibling.values[sibling.n - 1]
                child.n++
                sibling.n--
            }
        }

        fun borrowFromNext(idx: Int) {
            val child = C[idx]!!
            val sibling = C[idx + 1]!!
            child.keys[child.n] = keys[idx]
            child.values[child.n] = values[idx]
            if (!child.leaf) {
                child.C[child.n + 1] = sibling.C[0]
            }
            keys[idx] = sibling.keys[0]
            values[idx] = sibling.values[0]
            for (i in 1 until sibling.n) {
                sibling.keys[i - 1] = sibling.keys[i]
                sibling.values[i - 1] = sibling.values[i]
            }
            if (!sibling.leaf) {
                for (i in 1 until sibling.n + 1) {
                    sibling.C[i - 1] = sibling.C[i]
                }
            }
            child.n++
            sibling.n--
        }

        fun merge(idx: Int) {
            val child = C[idx]!!
            val sibling = C[idx + 1]!!
            child.keys[t - 1] = keys[idx]
            child.values[t - 1] = values[idx]
            for (i in 0 until sibling.n) {
                child.keys[i + t] = sibling.keys[i]
                child.values[i + t] = sibling.values[i]
            }
            if (!child.leaf) {
                for (i in 0 until sibling.n + 1) {
                    child.C[i + t] = sibling.C[i]
                }
            }
            for (i in idx + 1 until n) {
                keys[i - 1] = keys[i]
                values[i - 1] = values[i]
            }
            for (i in idx + 2 until n + 1) {
                C[i - 1] = C[i]
            }
            child.n += sibling.n + 1
            n--
            sibling.free()
        }

        fun forEach(action: (KEY, VALUE) -> Unit) {
            for (i in 0 until n) {
                if (!leaf) {
                    C[i]!!.forEach(action)
                }
                action(keys[i] as KEY, values[i] as VALUE)
            }
            if (!leaf) {
                C[n]!!.forEach(action)
            }
        }

        fun search(k: KEY): Boolean {
            var i = 0
            while (i < n && k > (keys[i] as KEY)) {
                i++
            }
            if ((keys[i] as KEY) == k) {
                return true
            } else if (leaf) {
                return false
            } else {
                return C[i]!!.search(k)
            }
        }

        fun insertNonFull(k: KEY, v: VALUE, onCreate: () -> Unit = {}, onExists: (KEY, VALUE) -> Unit = {a,b->}) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as KEY == k) {
                    onExists(keys[j] as KEY, values[j] as VALUE)
                    found = true
                    break
                }
            }
            if (!found) {
                if (leaf) {
                    while (i >= 0 && (keys[i] as KEY > k)) {
                        keys[i + 1] = keys[i]
                        values[i + 1] = values[i]
                        i--
                    }
                    keys[i + 1] = k
                    values[i + 1] = v
                    n++
                } else {
                    while (i >= 0 && (keys[i] as KEY) > k) {
                        i--
                    }
                    if (C[i + 1]!!.n == 2 * t - 1) {
                        splitChild(i + 1, C[i + 1]!!)
                        if ((keys[i + 1] as KEY) < k) {
                            i++
                        }
                    }
                    C[i + 1]!!.insertNonFull(k, v, onCreate, onExists)
                }
            }
        }

        fun splitChild(i: Int, y: MyMapKNAMEVNAMEBTreeNodeGUSE) {
            val z = MyMapKNAMEVNAMEBTreeNodeGUSE(y.t, y.leaf)
            z.n = t - 1
            for (j in 0 until t - 1) {
                z.keys[j] = y.keys[j + t]
                z.values[j] = y.values[j + t]
            }
            if (leaf == false) {
                for (j in 0 until t) {
                    z.C[j] = y.C[j + t]
                }
            }
            y.n = t - 1
            var j = n
            while (j >= i + 1) {
                C[j + 1] = C[j]
                j--
            }
            C[i + 1] = z
            j = n - 1
            while (j >= i) {
                keys[j + 1] = keys[j]
                values[j + 1] = values[j]
                j--
            }
            keys[i] = y.keys[t - 1]
            values[i] = y.values[t - 1]
            n++
        }
    }

    fun add(k: KEY, v: VALUE, onCreate: () -> Unit = {}, onExists: (KEY, VALUE) -> Unit = {a,b->}) {
        if (root == null) {
            root = MyMapKNAMEVNAMEBTreeNodeGUSE(t, true)
            root!!.keys[0] = k
            root!!.values[0] = v
            root!!.n = 1
            size++
            onCreate()
        } else if (root!!.n == 2 * t - 1) {
            val s = MyMapKNAMEVNAMEBTreeNodeGUSE(t, false)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as KEY) < k) {
                i++
            }
            s.C[i]!!.insertNonFull(k, v, {
                size++
                onCreate()
            }, onExists)
            root = s
        } else {
            root!!.insertNonFull(k, v, {
                size++
                onCreate()
            }, onExists)
        }
    }

    fun forEach(action: (KEY, VALUE) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: KEY): Boolean {
        if (root == null) {
            return false
        } else {
            return root!!.search(k)
        }
    }

    fun remove(k: KEY): Pair<KEY, VALUE>? {
        if (root != null) {
            val res = root!!.remove(k)
            if (res != null) {
                size--
            }
            if (root!!.n == 0) {
                val tmp = root!!
                if (root!!.leaf) {
                    root == null
                } else {
                    root = root!!.C[0]
                }
                tmp.free()
            }
            return res
        }
        return null
    }

    fun appendAssumeSorted(key: KEY, value: VALUE) {
        add(key, value, {}, {a,b->})
    }

    fun iterator(): MyMapKNAMEVNAMEBTreeNodeIteratorGUSE {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIteratorGUSE()
        }
    }

    class EmptyIteratorGDEF : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE(null) {
        override fun hasNext() = false
        override fun next(): KEY = throw Exception("unreachable")
    }
}

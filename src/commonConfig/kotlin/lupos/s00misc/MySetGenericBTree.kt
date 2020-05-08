package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
class MySetGenericBTree<Generic : Comparable<Generic>>(val t: Int) {
    var root: MySetGenericBTreeNode<Generic>? = null
    var size = 0

    constructor() : this(512)

    class MySetGenericBTreeNodeIterator<Generic : Comparable<Generic>>(val node: MySetGenericBTreeNode<Generic>) : Iterator<Generic> {
        var i = 0
        var childIterator = node.C[0]!!.iterator()
        override fun hasNext(): Boolean {
            if (node.leaf) {
                return i < node.n
            } else {
                return i < node.n || (i == node.n && childIterator.hasNext())
            }
        }

        override fun next(): Generic {
            if (node.leaf) {
                return node.keys[i++] as Generic
            } else {
                if (childIterator.hasNext()) {
                    return childIterator.next()
                } else {
                    childIterator = node.C[i + 1]!!.iterator()
                    return node.keys[i++] as Generic
                }
            }
        }
    }

    class MySetGenericBTreeNode<Generic : Comparable<Generic>>(val t: Int, val leaf: Boolean) {
        val keys = Array<Any?>(2 * t - 1) { null }
        val C = Array<MySetGenericBTreeNode<Generic>?>(2 * t) { null }
        var n = 0
        fun free() {
            /*later when buffer-manager is used*/
        }

        fun iterator() = MySetGenericBTreeNodeIterator<Generic>(this)
        fun findGeneric(k: Generic): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Generic) < k) {
                idx++
            }
            return idx
        }

        fun remove(k: Generic): Generic? {
            val idx = findGeneric(k)
            val key = keys[idx] as Generic
            if (idx < n && key == k) {
                if (leaf) {
                    removeFromLeaf(idx)
                } else {
                    removeFromNonLeaf(idx)
                }
                return key
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
            }
            n--
        }

        fun removeFromNonLeaf(idx: Int) {
            val k = keys[idx] as Generic
            if (C[idx]!!.n >= t) {
                val pred = getPred(idx)
                keys[idx] = pred
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= t) {
                val succ = getSucc(idx)
                keys[idx] = succ
                C[idx + 1]!!.remove(succ)
            } else {
                merge(idx)
                C[idx]!!.remove(k)
            }
        }

        fun getPred(idx: Int): Generic {
            var cur = C[idx]!!
            while (!cur.leaf) {
                cur = cur.C[cur.n]!!
            }
            return cur.keys[cur.n - 1] as Generic
        }

        fun getSucc(idx: Int): Generic {
            var cur = C[idx + 1]!!
            while (!cur.leaf) {
                cur = cur.C[0]!!
            }
            return cur.keys[0] as Generic
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
                i--
            }
            if (!child.leaf) {
                i = child.n
                while (i >= 0) {
                    child.C[i + 1] = child.C[i]
                    i--
                }
                child.keys[0] = keys[idx - 1]
                if (!child.leaf) {
                    child.C[0] = sibling.C[sibling.n]
                }
                keys[idx - 1] = sibling.keys[sibling.n - 1]
                child.n++
                sibling.n--
            }
        }

        fun borrowFromNext(idx: Int) {
            val child = C[idx]!!
            val sibling = C[idx + 1]!!
            child.keys[child.n] = keys[idx]
            if (!child.leaf) {
                child.C[child.n + 1] = sibling.C[0]
            }
            keys[idx] = sibling.keys[0]
            for (i in 1 until sibling.n) {
                sibling.keys[i - 1] = sibling.keys[i]
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
            for (i in 0 until sibling.n) {
                child.keys[i + t] = sibling.keys[i]
            }
            if (!child.leaf) {
                for (i in 0 until sibling.n + 1) {
                    child.C[i + t] = sibling.C[i]
                }
            }
            for (i in idx + 1 until n) {
                keys[i - 1] = keys[i]
            }
            for (i in idx + 2 until n + 1) {
                C[i - 1] = C[i]
            }
            child.n += sibling.n + 1
            n--
            sibling.free()
        }

        fun forEach(action: (Generic) -> Unit) {
            for (i in 0 until n) {
                if (!leaf) {
                    C[i]!!.forEach(action)
                }
                action(keys[i] as Generic)
            }
            if (!leaf) {
                C[n]!!.forEach(action)
            }
        }

        fun search(k: Generic): Boolean {
            var i = 0
            while (i < n && k > (keys[i] as Generic)) {
                i++
            }
            if ((keys[i] as Generic) == k) {
                return true
            } else if (leaf) {
                return false
            } else {
                return C[i]!!.search(k)
            }
        }

        fun insertNonFull(k: Generic, onCreate: () -> Unit = {}, onExists: (Generic) -> Unit = {}) {
            var i = n - 1
            var found = false
            for (i in 0 until n) {
                if (keys[i] as Generic == k) {
                    onExists(keys[i] as Generic)
                    found = true
                    break
                }
            }
            if (!found) {
                if (leaf) {
                    while (i >= 0 && (keys[i] as Generic > k)) {
                        keys[i + 1] = keys[i]
                        i--
                    }
                    keys[i + 1] = k
                    n++
                } else {
                    while (i >= 0 && (keys[i] as Generic) > k) {
                        i--
                    }
                    if (C[i + 1]!!.n == 2 * t - 1) {
                        splitChild(i + 1, C[i + 1]!!)
                        if ((keys[i + 1] as Generic) < k) {
                            i++
                        }
                    }
                    C[i + 1]!!.insertNonFull(k, onCreate, onExists)
                }
            }
        }

        fun splitChild(i: Int, y: MySetGenericBTreeNode<Generic>) {
            val z = MySetGenericBTreeNode<Generic>(y.t, y.leaf)
            z.n = t - 1
            for (j in 0 until t - 1) {
                z.keys[j] = y.keys[j + t]
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
                j--
            }
            keys[i] = y.keys[t - 1]
            n++
        }
    }

    fun add(k: Generic, onCreate: () -> Unit = {}, onExists: (Generic) -> Unit = {}) {
        if (root == null) {
            root = MySetGenericBTreeNode<Generic>(t, true)
            root!!.keys[0] = k
            root!!.n = 1
            size++
            onCreate()
        } else if (root!!.n == 2 * t - 1) {
            val s = MySetGenericBTreeNode<Generic>(t, false)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as Generic) < k) {
                i++
            }
            s.C[i]!!.insertNonFull(k, {
                size++
                onCreate()
            }, onExists)
            root = s
        } else {
            root!!.insertNonFull(k, {
                size++
                onCreate()
            }, onExists)
        }
    }

    fun forEach(action: (Generic) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: Generic): Boolean {
        if (root == null) {
            return false
        } else {
            return root!!.search(k)
        }
    }

    fun remove(k: Generic): Generic? {
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

    fun appendAssumeSorted(key: Generic) {
        add(key, {}, {})
    }

    fun iterator(): Iterator<Generic> {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIterator<Generic>()
        }
    }

    class EmptyIterator<Generic : Comparable<Generic>> : Iterator<Generic> {
        override fun hasNext() = false
        override fun next(): Generic = throw Exception("unreachable")
    }
}

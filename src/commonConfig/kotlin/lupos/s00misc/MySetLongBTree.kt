package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
class MySetLongBTree() {
    companion object {
        var debughelperUUID = 0
    }

    val uuid = debughelperUUID++

    constructor(k: Long) : this() {
        appendAssumeSorted(k)
    }

    var root: MySetLongBTreeNode? = null
    var size = 0

    class MySetLongBTreeNodeIterator(val node: MySetLongBTreeNode) : Iterator<Long> {
        var i = 0
        var childIterator: Iterator<Long>? = null
        override fun hasNext(): Boolean {
            if (node.leaf) {
                return i < node.n
            } else {
                childIterator = node.C[0]!!.iterator()
                return i < node.n || (i == node.n && childIterator!!.hasNext())
            }
            /*Coverage Unreachable*/
        }

        override fun next(): Long {
            if (node.leaf) {
                return node.keys[i++] as Long
            } else {
                if (childIterator!!.hasNext()) {
                    return childIterator!!.next()
                } else {
                    childIterator = node.C[i + 1]!!.iterator()
                    return node.keys[i++] as Long
                }
                /*Coverage Unreachable*/
            }
            /*Coverage Unreachable*/
        }
    }

    class MySetLongBTreeNode(val leaf: Boolean, val parentuuid: Int) {
        companion object {
            var debughelperUUID = 0
        }

        val uuid = debughelperUUID++
        val keys = LongArray(2 * B_TREE_BRANCHING_FACTOR - 1)
        val C = Array<MySetLongBTreeNode?>(2 * B_TREE_BRANCHING_FACTOR) { null }
        var n = 0
        fun free() {
            /*later when buffer-manager is used*/
        }

        fun iterator(): MySetLongBTreeNodeIterator {
            return MySetLongBTreeNodeIterator(this)
        }

        fun findLong(k: Long): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Long) < k) {
                idx++
            }
            return idx
        }

        fun remove(k: Long): Long? {
            val idx = findLong(k)
            val key = keys[idx] as Long
            if (idx < n && key == k) {
                if (leaf) {
                    removeFromLeaf(idx)
                } else {
                    removeFromNonLeaf(idx)
                }
                return key
            } else if (!leaf) {
                val flag = idx == n
                if (C[idx]!!.n < B_TREE_BRANCHING_FACTOR) {
                    fill(idx)
                }
                if (flag && idx > n) {
                    return C[idx - 1]!!.remove(k)
                } else {
                    return C[idx]!!.remove(k)
                }
/*Coverage Unreachable*/
            } else {
                return null
            }
/*Coverage Unreachable*/
        }

        fun removeFromLeaf(idx: Int) {
            for (i in idx + 1 until n) {
                keys[i - 1] = keys[i]
            }
            n--
        }

        fun removeFromNonLeaf(idx: Int) {
            val k = keys[idx] as Long
            if (C[idx]!!.n >= B_TREE_BRANCHING_FACTOR) {
                val pred = getPred(idx)
                keys[idx] = pred
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= B_TREE_BRANCHING_FACTOR) {
                val succ = getSucc(idx)
                keys[idx] = succ
                C[idx + 1]!!.remove(succ)
            } else {
                merge(idx)
                C[idx]!!.remove(k)
            }
        }

        fun getPred(idx: Int): Long {
            var cur = C[idx]!!
            while (!cur.leaf) {
                cur = cur.C[cur.n]!!
            }
            return cur.keys[cur.n - 1] as Long
        }

        fun getSucc(idx: Int): Long {
            var cur = C[idx + 1]!!
            while (!cur.leaf) {
                cur = cur.C[0]!!
            }
            return cur.keys[0] as Long
        }

        fun fill(idx: Int) {
            if (idx != 0 && C[idx - 1]!!.n >= B_TREE_BRANCHING_FACTOR) {
                borrowFromPrev(idx)
            } else if (idx != n && C[idx + 1]!!.n >= B_TREE_BRANCHING_FACTOR) {
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
            child.keys[B_TREE_BRANCHING_FACTOR - 1] = keys[idx]
            for (i in 0 until sibling.n) {
                child.keys[i + B_TREE_BRANCHING_FACTOR] = sibling.keys[i]
            }
            if (!child.leaf) {
                for (i in 0 until sibling.n + 1) {
                    child.C[i + B_TREE_BRANCHING_FACTOR] = sibling.C[i]
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

        fun forEach(action: (Long) -> Unit) {
            for (i in 0 until n) {
                if (!leaf) {
                    C[i]!!.forEach(action)
                }
                action(keys[i] as Long)
            }
            if (!leaf) {
                C[n]!!.forEach(action)
            }
        }

        fun search(k: Long): Boolean {
            var i = 0
            while (i < n && k > (keys[i] as Long)) {
                i++
            }
            if ((keys[i] as Long) == k) {
                return true
            } else if (leaf) {
                return false
            } else {
                return C[i]!!.search(k)
            }
/*Coverage Unreachable*/
        }

        fun insertNonFull(k: Long, onCreate: () -> Unit = {}, onExists: (Long) -> Unit = {}) {
            var i = n - 1
            var found = false
            for (i in 0 until n) {
                if (keys[i] as Long == k) {
                    onExists(keys[i] as Long)
                    found = true
                    break
                }
            }
            if (!found) {
                if (leaf) {
                    while (i >= 0 && (keys[i] as Long > k)) {
                        keys[i + 1] = keys[i]
                        i--
                    }
                    keys[i + 1] = k
                    onCreate()
                    n++
                } else {
                    while (i >= 0 && (keys[i] as Long) > k) {
                        i--
                    }
                    if (C[i + 1]!!.n == 2 * B_TREE_BRANCHING_FACTOR - 1) {
                        splitChild(i + 1, C[i + 1]!!)
                        if ((keys[i + 1] as Long) < k) {
                            i++
                        }
                    }
                    C[i + 1]!!.insertNonFull(k, onCreate, onExists)
                }
            }
        }

        fun splitChild(i: Int, y: MySetLongBTreeNode) {
            val z = MySetLongBTreeNode(y.leaf, uuid)
            z.n = B_TREE_BRANCHING_FACTOR - 1
            for (j in 0 until B_TREE_BRANCHING_FACTOR - 1) {
                z.keys[j] = y.keys[j + B_TREE_BRANCHING_FACTOR]
            }
            if (leaf == false) {
                for (j in 0 until B_TREE_BRANCHING_FACTOR) {
                    z.C[j] = y.C[j + B_TREE_BRANCHING_FACTOR]
                }
            }
            y.n = B_TREE_BRANCHING_FACTOR - 1
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
            keys[i] = y.keys[B_TREE_BRANCHING_FACTOR - 1]
            n++
        }
    }

    fun add(k: Long, onCreate: () -> Unit = {}, onExists: (Long) -> Unit = {}) {
        println("MySetLongBTree add $uuid $k")
        var sanitycheckhelper: MutableSet<Long>? = null
        SanityCheck {
            sanitycheckhelper = mutableSetOf<Long>()
            forEach {
                sanitycheckhelper!!.add(it)
            }
            val sanitycheckhelper2 = mutableSetOf<Long>()
            val iterator = iterator()
            while (iterator.hasNext()) {
                sanitycheckhelper2!!.add(iterator.next())
            }
            require(size == sanitycheckhelper!!.size)
            require(size == sanitycheckhelper2!!.size)
            require(sanitycheckhelper!!.containsAll(sanitycheckhelper2))
            require(sanitycheckhelper2!!.containsAll(sanitycheckhelper!!))
        }
        if (root == null) {
            println("add A")
            root = MySetLongBTreeNode(true, uuid)
            root!!.keys[0] = k
            root!!.n = 1
            size = 1
            onCreate()
        } else if (root!!.n == 2 * B_TREE_BRANCHING_FACTOR - 1) {
            println("add B")
            val s = MySetLongBTreeNode(false, uuid)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as Long) < k) {
                println("add B1")
                i = 1
            }
            s.C[i]!!.insertNonFull(k, {
                size++
                onCreate()
            }, onExists)
            root = s
        } else {
            println("add C")
            root!!.insertNonFull(k, {
                size++
                onCreate()
            }, onExists)
        }
        SanityCheck {
            val sanitycheckhelper2 = mutableSetOf<Long>()
            forEach {
                sanitycheckhelper2!!.add(it)
            }
            require(sanitycheckhelper2!!.containsAll(sanitycheckhelper!!))
            require(sanitycheckhelper2!!.contains(k))
            val sanitycheckhelper3 = mutableSetOf<Long>()
            val iterator = iterator()
            while (iterator.hasNext()) {
                sanitycheckhelper3!!.add(iterator.next())
            }
            require(size == sanitycheckhelper3!!.size, { "c $size ${sanitycheckhelper3!!.size}" })
            require(size == sanitycheckhelper2!!.size, { "d $size ${sanitycheckhelper2!!.size}" })
            require(sanitycheckhelper3!!.containsAll(sanitycheckhelper2), { "a" })
            require(sanitycheckhelper2!!.containsAll(sanitycheckhelper3), { "b" })
        }
    }

    fun forEach(action: (Long) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: Long): Boolean {
        if (root == null) {
            return false
        } else {
            return root!!.search(k)
        }
/*Coverage Unreachable*/
    }

    fun remove(k: Long): Long? {
        println("MySetLongBTree remove $uuid $k")
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

    fun appendAssumeSorted(key: Long) {
        add(key, {}, {})
    }

    fun iterator(): Iterator<Long> {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIterator()
        }
/*Coverage Unreachable*/
    }

    class EmptyIterator : Iterator<Long> {
        override fun hasNext() = false
        override fun next(): Long = SanityCheck.checkUnreachable()
    }
}

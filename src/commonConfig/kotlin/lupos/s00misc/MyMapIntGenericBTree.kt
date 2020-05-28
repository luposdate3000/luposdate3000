package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
class MyMapIntGenericBTree<GenericV>(val t: Int) {
    var root: MyMapIntGenericBTreeNode<GenericV>? = null
    var size = 0
    fun clear() {
        /*todo release all pages*/
        root = null
        size = 0
    }

    constructor() : this(B_TREE_BRANCHING_FACTOR)
    constructor(d: Pair<Int, GenericV>) : this() {
        set(d.first, d.second)
    }

    abstract class MyMapIntGenericBTreeNodeIterator<GenericV>() : Iterator<Int> {
        abstract fun value(): GenericV
    }

    class MyMapIntGenericBTreeNodeIteratorLeaf<GenericV>(val node: MyMapIntGenericBTreeNodeLeaf<GenericV>) : MyMapIntGenericBTreeNodeIterator<GenericV>() {
        var i = 0
        var v: GenericV = node!!.values[0] as GenericV
        override fun hasNext(): Boolean {
            return i < node.n
        }

        override fun next(): Int {
            v = node.values[i] as GenericV
            return node.keys[i++] as Int
        }

        override fun value() = v
    }

    class MyMapIntGenericBTreeNodeIteratorNonLeaf<GenericV>(val node: MyMapIntGenericBTreeNodeNonLeaf<GenericV>) : MyMapIntGenericBTreeNodeIterator<GenericV>() {
        var i = 0
        var childIterator = node!!.C[0]!!.iterator()
        var v: GenericV = node!!.values[0] as GenericV
        override fun hasNext(): Boolean {
            return i < node.n || (i == node.n && childIterator.hasNext())
        }

        override fun next(): Int {
            if (childIterator.hasNext()) {
                return childIterator.next()
            } else {
                childIterator = node.C[i + 1]!!.iterator()
                v = node.values[i] as GenericV
                return node.keys[i++] as Int
            }
/*Coverage Unreachable*/
        }

        override fun value() = v
    }

    abstract class MyMapIntGenericBTreeNode<GenericV>(val t: Int) {
        val keys = IntArray(2 * t - 1)
        val values = Array<Any?>(2 * t - 1) { null }
        var n = 0
        abstract fun iterator(): MyMapIntGenericBTreeNodeIterator<GenericV>
        abstract fun free()
        abstract fun remove(k: Int): Pair<Int, GenericV>?
        abstract fun forEach(action: (Int, GenericV) -> Unit)
        abstract fun search(k: Int): GenericV?
        abstract fun insertNonFull(k: Int, onCreate: () -> GenericV, onExists: (Int, GenericV) -> GenericV)
    }

    class MyMapIntGenericBTreeNodeNonLeaf<GenericV>(t: Int) : MyMapIntGenericBTreeNode<GenericV>(t) {
        val C = Array<MyMapIntGenericBTreeNode<GenericV>?>(2 * t) { null }
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapIntGenericBTreeNodeIteratorNonLeaf<GenericV>(this)
        fun findInt(k: Int): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Int) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: Int): Pair<Int, GenericV>? {
            val idx = findInt(k)
            val key = keys[idx] as Int
            val value = values[idx] as GenericV
            if (idx < n && key == k) {
                removeFromNonLeaf(idx)
                return Pair(key, value)
            } else {
                val flag = idx == n
                if (C[idx]!!.n < t) {
                    fill(idx)
                }
                if (flag && idx > n) {
                    return C[idx - 1]!!.remove(k)
                } else {
                    return C[idx]!!.remove(k)
                }
/*Coverage Unreachable*/
            }
/*Coverage Unreachable*/
        }

        fun removeFromNonLeaf(idx: Int) {
            val k = keys[idx] as Int
            if (C[idx]!!.n >= t) {
                var cur = C[idx]!!
                while (cur is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                    cur = cur.C[cur.n]!!
                }
                val pred = cur.keys[cur.n - 1] as Int
                keys[idx] = pred
                values[idx] = cur.values[cur.n - 1] as GenericV
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= t) {
                var cur = C[idx + 1]!!
                while (cur is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                    cur = cur.C[0]!!
                }
                val succ = cur.keys[0] as Int
                keys[idx] = succ
                values[idx] = cur.values[0] as GenericV
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
            if (child is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                i = child.n
                while (i >= 0) {
                    child.C[i + 1] = child.C[i]
                    i--
                }
                child.keys[0] = keys[idx - 1]
                child.values[0] = values[idx - 1]
                if (sibling is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
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
            if (child is MyMapIntGenericBTreeNodeNonLeaf<GenericV> && sibling is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                child.C[child.n + 1] = sibling.C[0]
            }
            keys[idx] = sibling.keys[0]
            values[idx] = sibling.values[0]
            for (i in 1 until sibling.n) {
                sibling.keys[i - 1] = sibling.keys[i]
                sibling.values[i - 1] = sibling.values[i]
            }
            if (sibling is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
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
            if (child is MyMapIntGenericBTreeNodeNonLeaf<GenericV> && sibling is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
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

        override fun forEach(action: (Int, GenericV) -> Unit) {
            for (i in 0 until n) {
                C[i]!!.forEach(action)
                action(keys[i] as Int, values[i] as GenericV)
            }
            C[n]!!.forEach(action)
        }

        override fun search(k: Int): GenericV? {
            var i = 0
            while (i < n && k > (keys[i] as Int)) {
                i++
            }
            if ((keys[i] as Int) == k) {
                return values[i] as GenericV
            } else {
                return C[i]!!.search(k)
            }
/*Coverage Unreachable*/
        }

        override fun insertNonFull(k: Int, onCreate: () -> GenericV, onExists: (Int, GenericV) -> GenericV) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as Int == k) {
                    values[j] = onExists(keys[j] as Int, values[j] as GenericV)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as Int) > k) {
                    i--
                }
                if (C[i + 1]!!.n == 2 * t - 1) {
                    splitChild(i + 1, C[i + 1]!!)
                    if ((keys[i + 1] as Int) < k) {
                        i++
                    }
                }
                C[i + 1]!!.insertNonFull(k, onCreate, onExists)
            }
        }

        fun splitChild(i: Int, y: MyMapIntGenericBTreeNode<GenericV>) {
            val z = if (y is MyMapIntGenericBTreeNodeLeaf<GenericV>) {
                /*return*/        MyMapIntGenericBTreeNodeLeaf<GenericV>(y.t)
            } else {
/*return*/                MyMapIntGenericBTreeNodeNonLeaf<GenericV>(y.t)
            }
            z.n = t - 1
            for (j in 0 until t - 1) {
                z.keys[j] = y.keys[j + t]
                z.values[j] = y.values[j + t]
            }
            if (y is MyMapIntGenericBTreeNodeNonLeaf<GenericV> && z is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
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

    class MyMapIntGenericBTreeNodeLeaf<GenericV>(t: Int) : MyMapIntGenericBTreeNode<GenericV>(t) {
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapIntGenericBTreeNodeIteratorLeaf<GenericV>(this)
        fun findInt(k: Int): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Int) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: Int): Pair<Int, GenericV>? {
            val idx = findInt(k)
            val key = keys[idx] as Int
            val value = values[idx] as GenericV
            if (idx < n && key == k) {
                removeFromLeaf(idx)
                return Pair(key, value)
            } else {
                return null
            }
/*Coverage Unreachable*/
        }

        fun removeFromLeaf(idx: Int) {
            for (i in idx + 1 until n) {
                keys[i - 1] = keys[i]
                values[i - 1] = values[i]
            }
            n--
        }

        override fun forEach(action: (Int, GenericV) -> Unit) {
            for (i in 0 until n) {
                action(keys[i] as Int, values[i] as GenericV)
            }
        }

        override fun search(k: Int): GenericV? {
            var i = 0
            while (i < n && k > (keys[i] as Int)) {
                i++
            }
            if ((keys[i] as Int) == k) {
                return values[i] as GenericV
            } else {
                return null
            }
/*Coverage Unreachable*/
        }

        override fun insertNonFull(k: Int, onCreate: () -> GenericV, onExists: (Int, GenericV) -> GenericV) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as Int == k) {
                    values[j] = onExists(keys[j] as Int, values[j] as GenericV)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as Int > k)) {
                    keys[i + 1] = keys[i]
                    values[i + 1] = values[i]
                    i--
                }
                keys[i + 1] = k
                values[i + 1] = onCreate()
                n++
            }
        }
    }

    class MyMapIntGenericBTreeInitializer<GenericV>(val t: Int, val target: MyMapIntGenericBTree<GenericV>) {
        var size = 0
        val data = mutableListOf<MyMapIntGenericBTreeNode<GenericV>>()
        fun appendAssumeSorted(key: Int, value: GenericV): GenericV {
            val tmp: MyMapIntGenericBTreeNode<GenericV>
            if (data.size == 0 || data[data.size - 1].n == 2 * t - 1) {
                tmp = MyMapIntGenericBTreeNodeLeaf<GenericV>(t)
                data.add(tmp)
                tmp.keys[0] = key
                tmp.values[0] = value
                tmp.n = 1
            } else {
                tmp = data[data.size - 1]
            }
            tmp.keys[tmp.n] = key
            tmp.values[tmp.n] = value
            tmp.n++
            size++
            return value
        }

        fun apply() {
            var listA = data
            var listB = mutableListOf<MyMapIntGenericBTreeNode<GenericV>>()
            while (listA.size > 1) {
                SanityCheck {
                    var j = 0
                    for (x in listA) {
                        if (x is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                            for (i in 0 until x.n + 1) {
                                SanityCheck.check { x.C[i] != null }
                            }
                            j++
                        }
                    }
                }
                var n = listA.size  //total nodes in this level
                var n2 = (n + 2 * t) / (2 * t - 1)  //required nodes in the next level to hold all of the current nodes (round up)
                var n3 = n / n2 + 1       //average number of childs in the next level - prevent that the last node has 1 element and therefore a wrong tree depth
                for (i in 0 until n2) {
                    val node = MyMapIntGenericBTreeNodeNonLeaf<GenericV>(t)
                    listB.add(node)
                    for (j in 0 until n3) {
                        if (listA.size > 0) {
                            var tmp = listA.removeAt(0)
                            node.C[node.n] = tmp
                            if (j < n3 - 1 && listA.size > 0) {
                                var maxElement = tmp
                                while (maxElement is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                                    maxElement = maxElement.C[maxElement.n]!!
                                }
                                node.keys[node.n] = maxElement.keys[maxElement.n - 1]
                                node.values[node.n] = maxElement.values[maxElement.n - 1]
                                maxElement.n--
                                node.n++
                            }
                        } else {
                            break
                        }
                    }
                }
                SanityCheck.check { listA.size == 0 }
                listA = listB
                listB = mutableListOf<MyMapIntGenericBTreeNode<GenericV>>()
            }
            target.clear()
            target.root = listA[0]
            target.size = size
        }
    }

    fun withFastInitializer(action: (MyMapIntGenericBTreeInitializer<GenericV>) -> Unit) {
        val init = MyMapIntGenericBTreeInitializer<GenericV>(t, this)
        action(init)
        init.apply()
    }

    operator fun set(k: Int, v: GenericV) = insert(k, { v }, { a, b -> v })
    fun insert(k: Int, onCreate: () -> GenericV, onExists: (Int, GenericV) -> GenericV) {
        if (root == null) {
            root = MyMapIntGenericBTreeNodeLeaf<GenericV>(t)
            root!!.keys[0] = k
            root!!.values[0] = onCreate()
            root!!.n = 1
            size++
        } else if (root!!.n == 2 * t - 1) {
            val s = MyMapIntGenericBTreeNodeNonLeaf<GenericV>(t)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as Int) < k) {
                i++
            }
            s.C[i]!!.insertNonFull(k, {
                size++
                /*return*/ onCreate()
            }, onExists)
            root = s
        } else {
            root!!.insertNonFull(k, {
                size++
                /*return*/ onCreate()
            }, onExists)
        }
    }

    fun forEach(action: (Int, GenericV) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: Int) = get(k) == null
    operator fun get(k: Int): GenericV? {
        if (root == null) {
            return null
        } else {
            return root!!.search(k)
        }
/*Coverage Unreachable*/
    }

    fun remove(k: Int): Pair<Int, GenericV>? {
        if (root != null) {
            val res = root!!.remove(k)
            if (res != null) {
                size--
            }
            if (root!!.n == 0) {
                val tmp = root!!
                if (tmp is MyMapIntGenericBTreeNodeNonLeaf<GenericV>) {
                    root = tmp!!.C[0]
                } else {
                    root == null
                }
                tmp.free()
            }
            return res
        }
        return null
    }

    fun iterator(): MyMapIntGenericBTreeNodeIterator<GenericV> {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIterator<GenericV>()
        }
/*Coverage Unreachable*/
    }

    class EmptyIterator<GenericV> : MyMapIntGenericBTreeNodeIterator<GenericV>() {
        override fun hasNext() = false
        override fun next(): Int = SanityCheck.checkUnreachable()
        override fun value(): GenericV = SanityCheck.checkUnreachable()
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> GenericV): GenericV {
        var res: GenericV? = null
        insert(key, {
            res = onCreate()
            /*return*/res!!
        }, { a, b ->
            res = b
            /*return*/ res!!
        })
        return res!!
    }

    fun safeToFile(filename: String) {
        throw Exception("not Implemented")
    }
}

package lupos.s00misc

import kotlin.jvm.JvmField

class MyMapKNAMEVNAMEBTreeGDEF(@JvmField val t: Int) {
    @JvmField
    var root: MyMapKNAMEVNAMEBTreeNodeGUSE? = null

    @JvmField
    var size = 0
    fun clear() {
        /*todo release all pages*/
        root = null
        size = 0
    }

    constructor() : this(B_TREE_BRANCHING_FACTOR)
    constructor(d: Pair<KEY, VALUE>) : this() {
        set(d.first, d.second)
    }

    abstract class MyMapKNAMEVNAMEBTreeNodeIteratorGDEF() : Iterator<KEY> {
        abstract fun value(): VALUE
    }

    class MyMapKNAMEVNAMEBTreeNodeIteratorLeafGDEF(val node: MyMapKNAMEVNAMEBTreeNodeLeafGUSE) : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE() {
        var i = 0
        var v: VALUE = node!!.values[0] as VALUE
        override fun hasNext(): Boolean {
            return i < node.n
        }

        override fun next(): KEY {
            v = node.values[i] as VALUE
            return node.keys[i++] as KEY
        }

        override fun value() = v
    }

    class MyMapKNAMEVNAMEBTreeNodeIteratorNonLeafGDEF(val node: MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE() {
        var i = 0
        var childIterator = node!!.C[0]!!.iterator()
        var v: VALUE = node!!.values[0] as VALUE
        override fun hasNext(): Boolean {
            return i < node.n || (i == node.n && childIterator.hasNext())
        }

        override fun next(): KEY {
            if (childIterator.hasNext()) {
                return childIterator.next()
            } else {
                childIterator = node.C[i + 1]!!.iterator()
                v = node.values[i] as VALUE
                return node.keys[i++] as KEY
            }
/*Coverage Unreachable*/
        }

        override fun value() = v
    }

    abstract class MyMapKNAMEVNAMEBTreeNodeGDEF(@JvmField val t: Int) {
        @JvmField
        val keys = ARRAYKTYPE(2 * t - 1) ARRAYKINITIALIZER
                @JvmField
                val values = ARRAYVTYPE(2 * t - 1) ARRAYVINITIALIZER
                        @JvmField
                        var n = 0

        abstract fun iterator(): MyMapKNAMEVNAMEBTreeNodeIteratorGUSE
        abstract fun free()
        abstract fun remove(k: KEY): Pair<KEY, VALUE>?
        abstract fun forEach(action: (KEY, VALUE) -> Unit)
        abstract fun search(k: KEY): VALUE?
        abstract fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE)
    }

    class MyMapKNAMEVNAMEBTreeNodeNonLeafGDEF(t: Int) : MyMapKNAMEVNAMEBTreeNodeGUSE(t) {
        @JvmField
        val C = Array<MyMapKNAMEVNAMEBTreeNodeGUSE?>(2 * t) { null }
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapKNAMEVNAMEBTreeNodeIteratorNonLeafGUSE(this)
        fun findKEY(k: KEY): Int {
            var idx = 0
            while (idx < n && (keys[idx] as KEY) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: KEY): Pair<KEY, VALUE>? {
            val idx = findKEY(k)
            val key = keys[idx] as KEY
            val value = values[idx] as VALUE
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
            val k = keys[idx] as KEY
            if (C[idx]!!.n >= t) {
                var cur = C[idx]!!
                while (cur is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
                    cur = cur.C[cur.n]!!
                }
                val pred = cur.keys[cur.n - 1] as KEY
                keys[idx] = pred
                values[idx] = cur.values[cur.n - 1] as VALUE
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= t) {
                var cur = C[idx + 1]!!
                while (cur is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
                i = child.n
                while (i >= 0) {
                    child.C[i + 1] = child.C[i]
                    i--
                }
                child.keys[0] = keys[idx - 1]
                child.values[0] = values[idx - 1]
                if (sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
                child.C[child.n + 1] = sibling.C[0]
            }
            keys[idx] = sibling.keys[0]
            values[idx] = sibling.values[0]
            for (i in 1 until sibling.n) {
                sibling.keys[i - 1] = sibling.keys[i]
                sibling.values[i - 1] = sibling.values[i]
            }
            if (sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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

        override fun forEach(action: (KEY, VALUE) -> Unit) {
            for (i in 0 until n) {
                C[i]!!.forEach(action)
                action(keys[i] as KEY, values[i] as VALUE)
            }
            C[n]!!.forEach(action)
        }

        override fun search(k: KEY): VALUE? {
            var i = 0
            while (i < n && k > (keys[i] as KEY)) {
                i++
            }
            if ((keys[i] as KEY) == k && i < n) {
                return values[i] as VALUE
            } else {
                return C[i]!!.search(k)
            }
/*Coverage Unreachable*/
        }

        override fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as KEY == k) {
                    values[j] = onExists(keys[j] as KEY, values[j] as VALUE)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as KEY) > k) {
                    i--
                }
                if (C[i + 1]!!.n == 2 * t - 1) {
                    splitChild(i + 1, C[i + 1]!!)
                    if ((keys[i + 1] as KEY) < k) {
                        i++
                    }
                }
                C[i + 1]!!.insertNonFull(k, onCreate, onExists)
            }
        }

        fun splitChild(i: Int, y: MyMapKNAMEVNAMEBTreeNodeGUSE) {
            val z = if (y is MyMapKNAMEVNAMEBTreeNodeLeafGUSE) {
                MyMapKNAMEVNAMEBTreeNodeLeafGUSE(y.t)
            } else {
                MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(y.t)
            }
            z.n = t - 1
            for (j in 0 until t - 1) {
                z.keys[j] = y.keys[j + t]
                z.values[j] = y.values[j + t]
            }
            if (y is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && z is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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

    class MyMapKNAMEVNAMEBTreeNodeLeafGDEF(t: Int) : MyMapKNAMEVNAMEBTreeNodeGUSE(t) {
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapKNAMEVNAMEBTreeNodeIteratorLeafGUSE(this)
        fun findKEY(k: KEY): Int {
            var idx = 0
            while (idx < n && (keys[idx] as KEY) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: KEY): Pair<KEY, VALUE>? {
            val idx = findKEY(k)
            val key = keys[idx] as KEY
            val value = values[idx] as VALUE
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

        override fun forEach(action: (KEY, VALUE) -> Unit) {
            for (i in 0 until n) {
                action(keys[i] as KEY, values[i] as VALUE)
            }
        }

        override fun search(k: KEY): VALUE? {
            var i = 0
            while (i < n && k > (keys[i] as KEY)) {
                i++
            }
            if ((keys[i] as KEY) == k && i < n) {
                return values[i] as VALUE
            } else {
                return null
            }
/*Coverage Unreachable*/
        }

        override fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as KEY == k) {
                    values[j] = onExists(keys[j] as KEY, values[j] as VALUE)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as KEY > k)) {
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

    class MyMapKNAMEVNAMEBTreeInitializerGDEF(@JvmField val t: Int, @JvmField val target: MyMapKNAMEVNAMEBTreeGUSE) {
        @JvmField
        var size = 0

        @JvmField
        val data = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
        fun appendAssumeSorted(key: KEY, value: VALUE): VALUE {
            val tmp: MyMapKNAMEVNAMEBTreeNodeGUSE
            if (data.size == 0 || data[data.size - 1].n == 2 * t - 1) {
                tmp = MyMapKNAMEVNAMEBTreeNodeLeafGUSE(t)
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
            var listB = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
            while (listA.size > 1) {
                SanityCheck {
                    var j = 0
                    for (x in listA) {
                        if (x is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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
                    val node = MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(t)
                    listB.add(node)
                    for (j in 0 until n3) {
                        if (listA.size > 0) {
                            var tmp = listA.removeAt(0)
                            node.C[node.n] = tmp
                            if (j < n3 - 1 && listA.size > 0) {
                                var maxElement = tmp
                                while (maxElement is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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
                listB = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
            }
            target.clear()
            target.size = size
            if (size == 0) {
                target.root = null
            } else {
                target.root = listA[0]
            }
        }
    }

    fun withFastInitializer(action: (MyMapKNAMEVNAMEBTreeInitializerGUSE) -> Unit) {
        val init = MyMapKNAMEVNAMEBTreeInitializerGUSE(t, this)
        action(init)
        init.apply()
    }

    operator fun set(k: KEY, v: VALUE) = insert(k, { v }, { a, b -> v })
    fun insert(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
        if (root == null) {
            root = MyMapKNAMEVNAMEBTreeNodeLeafGUSE(t)
            root!!.keys[0] = k
            root!!.values[0] = onCreate()
            root!!.n = 1
            size++
        } else if (root!!.n == 2 * t - 1) {
            val s = MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(t)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as KEY) < k) {
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

    fun forEach(action: (KEY, VALUE) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: KEY) = get(k) == null
    operator fun get(k: KEY): VALUE? {
        if (root == null) {
            return null
        } else {
            return root!!.search(k)
        }
/*Coverage Unreachable*/
    }

    fun remove(k: KEY): Pair<KEY, VALUE>? {
        if (root != null) {
            val res = root!!.remove(k)
            if (res != null) {
                size--
            }
            if (root!!.n == 0) {
                val tmp = root!!
                if (tmp is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
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

    fun iterator(): MyMapKNAMEVNAMEBTreeNodeIteratorGUSE {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIteratorGUSE()
        }
/*Coverage Unreachable*/
    }

    class EmptyIteratorGDEF : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE() {
        override fun hasNext() = false
        override fun next(): KEY = SanityCheck.checkUnreachable()
        override fun value(): VALUE = SanityCheck.checkUnreachable()
    }

    inline fun getOrCreate(key: KEY, crossinline onCreate: () -> VALUE): VALUE {
        var res: VALUE? = null
        insert(key, {
            res = onCreate()
            res!!
        }, { a, b ->
            res = b
            res!!
        })
        return res!!
    }

    fun safeToFile(filename: String) {
        IOSTART1
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            forEach { k, v ->
                out.writeKEY(k)
                out.writeVALUE(v)
            }
        }
        IOEND1
    }

    fun loadFromFile(filename: String) {
        IOSTART2
        withFastInitializer { init ->
            File(filename).dataInputStream { fis ->
                var size = fis.readInt()
                for (i in 0 until size) {
                    val k = fis.readKEY()
                    val v = fis.readVALUE()
                    init.appendAssumeSorted(k, v)
                }
            }
        }
        IOEND2
    }
}

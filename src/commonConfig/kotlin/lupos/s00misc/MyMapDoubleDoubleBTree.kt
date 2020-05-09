/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapDoubleDoubleBTree(val t: Int) {
    var root: MyMapDoubleDoubleBTreeNode? = null
    var size = 0
    fun clear() {
        /*todo release all pages*/
        root = null
        size = 0
    }

    constructor() : this(B_TREE_BRANCHING_FACTOR)
    constructor(d: Pair<Double, Double>) : this() {
        set(d.first, d.second)
    }

    abstract class MyMapDoubleDoubleBTreeNodeIterator() : Iterator<Double> {
        abstract fun value(): Double
    }

    class MyMapDoubleDoubleBTreeNodeIteratorLeaf(val node: MyMapDoubleDoubleBTreeNodeLeaf) : MyMapDoubleDoubleBTreeNodeIterator() {
        var i = 0
        var v: Double = node!!.values[0] as Double
        override fun hasNext(): Boolean {
            return i < node.n
        }

        override fun next(): Double {
            v = node.values[i] as Double
            return node.keys[i++] as Double
        }

        override fun value() = v
    }

    class MyMapDoubleDoubleBTreeNodeIteratorNonLeaf(val node: MyMapDoubleDoubleBTreeNodeNonLeaf) : MyMapDoubleDoubleBTreeNodeIterator() {
        var i = 0
        var childIterator = node!!.C[0]!!.iterator()
        var v: Double = node!!.values[0] as Double
        override fun hasNext(): Boolean {
            return i < node.n || (i == node.n && childIterator.hasNext())
        }

        override fun next(): Double {
            if (childIterator.hasNext()) {
                return childIterator.next()
            } else {
                childIterator = node.C[i + 1]!!.iterator()
                v = node.values[i] as Double
                return node.keys[i++] as Double
            }
        }

        override fun value() = v
    }

    abstract class MyMapDoubleDoubleBTreeNode(val t: Int) {
        val keys = DoubleArray(2 * t - 1) 
        val values = DoubleArray(2 * t - 1) 
        var n = 0
        abstract fun iterator(): MyMapDoubleDoubleBTreeNodeIterator
        abstract fun free()
        abstract fun remove(k: Double): Pair<Double, Double>?
        abstract fun forEach(action: (Double, Double) -> Unit)
        abstract fun search(k: Double): Double?
        abstract fun insertNonFull(k: Double, onCreate: () -> Double, onExists: (Double, Double) -> Double)
    }

    class MyMapDoubleDoubleBTreeNodeNonLeaf(t: Int) : MyMapDoubleDoubleBTreeNode(t) {
        val C = Array<MyMapDoubleDoubleBTreeNode?>(2 * t) { null }
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapDoubleDoubleBTreeNodeIteratorNonLeaf(this)
        fun findDouble(k: Double): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Double) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: Double): Pair<Double, Double>? {
            val idx = findDouble(k)
            val key = keys[idx] as Double
            val value = values[idx] as Double
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
            }
        }

        fun removeFromNonLeaf(idx: Int) {
            val k = keys[idx] as Double
            if (C[idx]!!.n >= t) {
                var cur = C[idx]!!
                while (cur is MyMapDoubleDoubleBTreeNodeNonLeaf) {
                    cur = cur.C[cur.n]!!
                }
                val pred = cur.keys[cur.n - 1] as Double
                keys[idx] = pred
                values[idx] = cur.values[cur.n - 1] as Double
                C[idx]!!.remove(pred)
            } else if (C[idx + 1]!!.n >= t) {
                var cur = C[idx + 1]!!
                while (cur is MyMapDoubleDoubleBTreeNodeNonLeaf) {
                    cur = cur.C[0]!!
                }
                val succ = cur.keys[0] as Double
                keys[idx] = succ
                values[idx] = cur.values[0] as Double
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
            if (child is MyMapDoubleDoubleBTreeNodeNonLeaf) {
                i = child.n
                while (i >= 0) {
                    child.C[i + 1] = child.C[i]
                    i--
                }
                child.keys[0] = keys[idx - 1]
                child.values[0] = values[idx - 1]
                if (child is MyMapDoubleDoubleBTreeNodeNonLeaf && sibling is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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
            if (child is MyMapDoubleDoubleBTreeNodeNonLeaf && sibling is MyMapDoubleDoubleBTreeNodeNonLeaf) {
                child.C[child.n + 1] = sibling.C[0]
            }
            keys[idx] = sibling.keys[0]
            values[idx] = sibling.values[0]
            for (i in 1 until sibling.n) {
                sibling.keys[i - 1] = sibling.keys[i]
                sibling.values[i - 1] = sibling.values[i]
            }
            if (sibling is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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
            if (child is MyMapDoubleDoubleBTreeNodeNonLeaf && sibling is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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

        override fun forEach(action: (Double, Double) -> Unit) {
            for (i in 0 until n) {
                C[i]!!.forEach(action)
                action(keys[i] as Double, values[i] as Double)
            }
            C[n]!!.forEach(action)
        }

        override fun search(k: Double): Double? {
            var i = 0
            while (i < n && k > (keys[i] as Double)) {
                i++
            }
            if ((keys[i] as Double) == k) {
                return values[i] as Double
            } else {
                return C[i]!!.search(k)
            }
        }

        override fun insertNonFull(k: Double, onCreate: () -> Double, onExists: (Double, Double) -> Double) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as Double == k) {
                    values[j] = onExists(keys[j] as Double, values[j] as Double)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as Double) > k) {
                    i--
                }
                if (C[i + 1]!!.n == 2 * t - 1) {
                    splitChild(i + 1, C[i + 1]!!)
                    if ((keys[i + 1] as Double) < k) {
                        i++
                    }
                }
                C[i + 1]!!.insertNonFull(k, onCreate, onExists)
            }
        }

        fun splitChild(i: Int, y: MyMapDoubleDoubleBTreeNode) {
            val z = if (y is MyMapDoubleDoubleBTreeNodeLeaf) {
                MyMapDoubleDoubleBTreeNodeLeaf(y.t)
            } else {
                MyMapDoubleDoubleBTreeNodeNonLeaf(y.t)
            }
            z.n = t - 1
            for (j in 0 until t - 1) {
                z.keys[j] = y.keys[j + t]
                z.values[j] = y.values[j + t]
            }
            if (y is MyMapDoubleDoubleBTreeNodeNonLeaf && z is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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

    class MyMapDoubleDoubleBTreeNodeLeaf(t: Int) : MyMapDoubleDoubleBTreeNode(t) {
        override fun free() {
            /*later when buffer-manager is used*/
        }

        override fun iterator() = MyMapDoubleDoubleBTreeNodeIteratorLeaf(this)
        fun findDouble(k: Double): Int {
            var idx = 0
            while (idx < n && (keys[idx] as Double) < k) {
                idx++
            }
            return idx
        }

        override fun remove(k: Double): Pair<Double, Double>? {
            val idx = findDouble(k)
            val key = keys[idx] as Double
            val value = values[idx] as Double
            if (idx < n && key == k) {
                removeFromLeaf(idx)
                return Pair(key, value)
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

        override fun forEach(action: (Double, Double) -> Unit) {
            for (i in 0 until n) {
                action(keys[i] as Double, values[i] as Double)
            }
        }

        override fun search(k: Double): Double? {
            var i = 0
            while (i < n && k > (keys[i] as Double)) {
                i++
            }
            if ((keys[i] as Double) == k) {
                return values[i] as Double
            } else {
                return null
            }
        }

        override fun insertNonFull(k: Double, onCreate: () -> Double, onExists: (Double, Double) -> Double) {
            var i = n - 1
            var found = false
            for (j in 0 until n) {
                if (keys[j] as Double == k) {
                    values[j] = onExists(keys[j] as Double, values[j] as Double)
                    found = true
                    break
                }
            }
            if (!found) {
                while (i >= 0 && (keys[i] as Double > k)) {
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

    class MyMapDoubleDoubleBTreeInitializer(val t: Int, val target: MyMapDoubleDoubleBTree) {
        var size = 0
        val data = mutableListOf<MyMapDoubleDoubleBTreeNode>()
        fun appendAssumeSorted(key: Double, value: Double): Double {
            val tmp: MyMapDoubleDoubleBTreeNode
            if (data.size == 0 || data[data.size - 1].n == 2 * t - 1) {
                tmp = MyMapDoubleDoubleBTreeNodeLeaf(t)
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
            var listB = mutableListOf<MyMapDoubleDoubleBTreeNode>()
            while (listA.size > 1) {
                SanityCheck {
                    var j = 0
                    for (x in listA) {
                        if (x is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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
                    val node = MyMapDoubleDoubleBTreeNodeNonLeaf(t)
                    listB.add(node)
                    for (j in 0 until n3) {
                        if (listA.size > 0) {
                            var tmp = listA.removeAt(0)
                            node.C[node.n] = tmp
                            if (j < n3 - 1 && listA.size > 0) {
                                var maxElement = tmp
                                while (maxElement is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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
                listB = mutableListOf<MyMapDoubleDoubleBTreeNode>()
            }
            target.clear()
            target.root = listA[0]
            target.size = size
        }
    }

    fun withFastInitializer(action: (MyMapDoubleDoubleBTreeInitializer) -> Unit) {
        val init = MyMapDoubleDoubleBTreeInitializer(t, this)
        action(init)
        init.apply()
    }

    operator fun set(k: Double, v: Double) = insert(k, { v }, { a, b -> v })
    fun insert(k: Double, onCreate: () -> Double, onExists: (Double, Double) -> Double) {
        if (root == null) {
            root = MyMapDoubleDoubleBTreeNodeLeaf(t)
            root!!.keys[0] = k
            root!!.values[0] = onCreate()
            root!!.n = 1
            size++
        } else if (root!!.n == 2 * t - 1) {
            val s = MyMapDoubleDoubleBTreeNodeNonLeaf(t)
            s.C[0] = root
            s.splitChild(0, root!!)
            var i = 0
            if ((s.keys[0] as Double) < k) {
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

    fun forEach(action: (Double, Double) -> Unit) {
        if (root != null) {
            root!!.forEach(action)
        }
    }

    fun contains(k: Double) = get(k) == null
    operator fun get(k: Double): Double? {
        if (root == null) {
            return null
        } else {
            return root!!.search(k)
        }
    }

    fun remove(k: Double): Pair<Double, Double>? {
        if (root != null) {
            val res = root!!.remove(k)
            if (res != null) {
                size--
            }
            if (root!!.n == 0) {
                val tmp = root!!
                if (tmp is MyMapDoubleDoubleBTreeNodeNonLeaf) {
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

    fun iterator(): MyMapDoubleDoubleBTreeNodeIterator {
        if (root != null) {
            return root!!.iterator()
        } else {
            return EmptyIterator()
        }
    }

    class EmptyIterator : MyMapDoubleDoubleBTreeNodeIterator() {
        override fun hasNext() = false
        override fun next(): Double = throw Exception("unreachable")
        override fun value(): Double = throw Exception("unreachable")
    }

    inline fun getOrCreate(key: Double, crossinline onCreate: () -> Double): Double {
        var res: Double? = null
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
        
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            forEach { k, v ->
                out.writeDouble(k)
                out.writeDouble(v)
            }
        }
        
    }

    fun loadFromFile(filename: String) {
        
        withFastInitializer { init ->
            File(filename).dataInputStream { fis ->
                var size = fis.readInt()
                for (i in 0 until size) {
                    val k = fis.readDouble()
                    val v = fis.readDouble()
                    init.appendAssumeSorted(k, v)
                }
            }
        }
        
    }
}

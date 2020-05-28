package lupos.s00misc
import lupos.s00misc.Coverage
class MyMapKNAMEVNAMEBTreeGDEF(val t: Int) {
    var root: MyMapKNAMEVNAMEBTreeNodeGUSE? = null
    var size = 0
    fun clear() {
Coverage.funStart(14647)
        /*todo release all pages*/
Coverage.statementStart(14648)
        root = null
Coverage.statementStart(14649)
        size = 0
Coverage.statementStart(14650)
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
Coverage.funStart(14651)
            return i < node.n
        }
        override fun next(): KEY {
Coverage.funStart(14652)
            v = node.values[i] as VALUE
Coverage.statementStart(14653)
            return node.keys[i++] as KEY
        }
        override fun value() = v
    }
    class MyMapKNAMEVNAMEBTreeNodeIteratorNonLeafGDEF(val node: MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE() {
        var i = 0
        var childIterator = node!!.C[0]!!.iterator()
        var v: VALUE = node!!.values[0] as VALUE
        override fun hasNext(): Boolean {
Coverage.funStart(14654)
            return i < node.n || (i == node.n && childIterator.hasNext())
        }
        override fun next(): KEY {
Coverage.funStart(14655)
            if (childIterator.hasNext()) {
Coverage.ifStart(14656)
                return childIterator.next()
            } else {
Coverage.statementStart(14657)
                childIterator = node.C[i + 1]!!.iterator()
Coverage.statementStart(14658)
                v = node.values[i] as VALUE
Coverage.statementStart(14659)
                return node.keys[i++] as KEY
            }
Coverage.statementStart(14660)
        }
        override fun value() = v
    }
    abstract class MyMapKNAMEVNAMEBTreeNodeGDEF(val t: Int) {
        val keys = ARRAYKTYPE(2 * t - 1) ARRAYKINITIALIZER
        val values = ARRAYVTYPE(2 * t - 1) ARRAYVINITIALIZER
        var n = 0
        abstract fun iterator(): MyMapKNAMEVNAMEBTreeNodeIteratorGUSE
        abstract fun free()
        abstract fun remove(k: KEY): Pair<KEY, VALUE>?
        abstract fun forEach(action: (KEY, VALUE) -> Unit)
        abstract fun search(k: KEY): VALUE?
        abstract fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE)
    }
    class MyMapKNAMEVNAMEBTreeNodeNonLeafGDEF(t: Int) : MyMapKNAMEVNAMEBTreeNodeGUSE(t) {
        val C = Array<MyMapKNAMEVNAMEBTreeNodeGUSE?>(2 * t) { null }
        override fun free() {
Coverage.funStart(14661)
            /*later when buffer-manager is used*/
Coverage.statementStart(14662)
        }
        override fun iterator() = MyMapKNAMEVNAMEBTreeNodeIteratorNonLeafGUSE(this)
        fun findKEY(k: KEY): Int {
Coverage.funStart(14663)
            var idx = 0
Coverage.statementStart(14664)
            while (idx < n && (keys[idx] as KEY) < k) {
Coverage.whileLoopStart(14665)
                idx++
Coverage.statementStart(14666)
            }
Coverage.statementStart(14667)
            return idx
        }
        override fun remove(k: KEY): Pair<KEY, VALUE>? {
Coverage.funStart(14668)
            val idx = findKEY(k)
Coverage.statementStart(14669)
            val key = keys[idx] as KEY
Coverage.statementStart(14670)
            val value = values[idx] as VALUE
Coverage.statementStart(14671)
            if (idx < n && key == k) {
Coverage.ifStart(14672)
                removeFromNonLeaf(idx)
Coverage.statementStart(14673)
                return Pair(key, value)
            } else {
Coverage.statementStart(14674)
                val flag = idx == n
Coverage.statementStart(14675)
                if (C[idx]!!.n < t) {
Coverage.ifStart(14676)
                    fill(idx)
Coverage.statementStart(14677)
                }
Coverage.statementStart(14678)
                if (flag && idx > n) {
Coverage.ifStart(14679)
                    return C[idx - 1]!!.remove(k)
                } else {
Coverage.statementStart(14680)
                    return C[idx]!!.remove(k)
                }
Coverage.statementStart(14681)
            }
Coverage.statementStart(14682)
        }
        fun removeFromNonLeaf(idx: Int) {
Coverage.funStart(14683)
            val k = keys[idx] as KEY
Coverage.statementStart(14684)
            if (C[idx]!!.n >= t) {
Coverage.ifStart(14685)
                var cur = C[idx]!!
Coverage.statementStart(14686)
                while (cur is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.whileLoopStart(14687)
                    cur = cur.C[cur.n]!!
Coverage.statementStart(14688)
                }
Coverage.statementStart(14689)
                val pred = cur.keys[cur.n - 1] as KEY
Coverage.statementStart(14690)
                keys[idx] = pred
Coverage.statementStart(14691)
                values[idx] = cur.values[cur.n - 1] as VALUE
Coverage.statementStart(14692)
                C[idx]!!.remove(pred)
Coverage.statementStart(14693)
            } else if (C[idx + 1]!!.n >= t) {
Coverage.ifStart(14694)
                var cur = C[idx + 1]!!
Coverage.statementStart(14695)
                while (cur is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.whileLoopStart(14696)
                    cur = cur.C[0]!!
Coverage.statementStart(14697)
                }
Coverage.statementStart(14698)
                val succ = cur.keys[0] as KEY
Coverage.statementStart(14699)
                keys[idx] = succ
Coverage.statementStart(14700)
                values[idx] = cur.values[0] as VALUE
Coverage.statementStart(14701)
                C[idx + 1]!!.remove(succ)
Coverage.statementStart(14702)
            } else {
Coverage.ifStart(14703)
                merge(idx)
Coverage.statementStart(14704)
                C[idx]!!.remove(k)
Coverage.statementStart(14705)
            }
Coverage.statementStart(14706)
        }
        fun fill(idx: Int) {
Coverage.funStart(14707)
            if (idx != 0 && C[idx - 1]!!.n >= t) {
Coverage.ifStart(14708)
                borrowFromPrev(idx)
Coverage.statementStart(14709)
            } else if (idx != n && C[idx + 1]!!.n >= t) {
Coverage.ifStart(14710)
                borrowFromNext(idx)
Coverage.statementStart(14711)
            } else if (idx != n) {
Coverage.ifStart(14712)
                merge(idx)
Coverage.statementStart(14713)
            } else {
Coverage.ifStart(14714)
                merge(idx - 1)
Coverage.statementStart(14715)
            }
Coverage.statementStart(14716)
        }
        fun borrowFromPrev(idx: Int) {
Coverage.funStart(14717)
            val child = C[idx]!!
Coverage.statementStart(14718)
            val sibling = C[idx - 1]!!
Coverage.statementStart(14719)
            var i = child.n - 1
Coverage.statementStart(14720)
            while (i >= 0) {
Coverage.whileLoopStart(14721)
                child.keys[i + 1] = child.keys[i]
Coverage.statementStart(14722)
                child.values[i + 1] = child.values[i]
Coverage.statementStart(14723)
                i--
Coverage.statementStart(14724)
            }
Coverage.statementStart(14725)
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14726)
                i = child.n
Coverage.statementStart(14727)
                while (i >= 0) {
Coverage.whileLoopStart(14728)
                    child.C[i + 1] = child.C[i]
Coverage.statementStart(14729)
                    i--
Coverage.statementStart(14730)
                }
Coverage.statementStart(14731)
                child.keys[0] = keys[idx - 1]
Coverage.statementStart(14732)
                child.values[0] = values[idx - 1]
Coverage.statementStart(14733)
                if (sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14734)
                    child.C[0] = sibling.C[sibling.n]
Coverage.statementStart(14735)
                }
Coverage.statementStart(14736)
                keys[idx - 1] = sibling.keys[sibling.n - 1]
Coverage.statementStart(14737)
                values[idx - 1] = sibling.values[sibling.n - 1]
Coverage.statementStart(14738)
                child.n++
Coverage.statementStart(14739)
                sibling.n--
Coverage.statementStart(14740)
            }
Coverage.statementStart(14741)
        }
        fun borrowFromNext(idx: Int) {
Coverage.funStart(14742)
            val child = C[idx]!!
Coverage.statementStart(14743)
            val sibling = C[idx + 1]!!
Coverage.statementStart(14744)
            child.keys[child.n] = keys[idx]
Coverage.statementStart(14745)
            child.values[child.n] = values[idx]
Coverage.statementStart(14746)
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14747)
                child.C[child.n + 1] = sibling.C[0]
Coverage.statementStart(14748)
            }
Coverage.statementStart(14749)
            keys[idx] = sibling.keys[0]
Coverage.statementStart(14750)
            values[idx] = sibling.values[0]
Coverage.statementStart(14751)
            for (i in 1 until sibling.n) {
Coverage.forLoopStart(14752)
                sibling.keys[i - 1] = sibling.keys[i]
Coverage.statementStart(14753)
                sibling.values[i - 1] = sibling.values[i]
Coverage.statementStart(14754)
            }
Coverage.statementStart(14755)
            if (sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14756)
                for (i in 1 until sibling.n + 1) {
Coverage.forLoopStart(14757)
                    sibling.C[i - 1] = sibling.C[i]
Coverage.statementStart(14758)
                }
Coverage.statementStart(14759)
            }
Coverage.statementStart(14760)
            child.n++
Coverage.statementStart(14761)
            sibling.n--
Coverage.statementStart(14762)
        }
        fun merge(idx: Int) {
Coverage.funStart(14763)
            val child = C[idx]!!
Coverage.statementStart(14764)
            val sibling = C[idx + 1]!!
Coverage.statementStart(14765)
            child.keys[t - 1] = keys[idx]
Coverage.statementStart(14766)
            child.values[t - 1] = values[idx]
Coverage.statementStart(14767)
            for (i in 0 until sibling.n) {
Coverage.forLoopStart(14768)
                child.keys[i + t] = sibling.keys[i]
Coverage.statementStart(14769)
                child.values[i + t] = sibling.values[i]
Coverage.statementStart(14770)
            }
Coverage.statementStart(14771)
            if (child is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && sibling is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14772)
                for (i in 0 until sibling.n + 1) {
Coverage.forLoopStart(14773)
                    child.C[i + t] = sibling.C[i]
Coverage.statementStart(14774)
                }
Coverage.statementStart(14775)
            }
Coverage.statementStart(14776)
            for (i in idx + 1 until n) {
Coverage.forLoopStart(14777)
                keys[i - 1] = keys[i]
Coverage.statementStart(14778)
                values[i - 1] = values[i]
Coverage.statementStart(14779)
            }
Coverage.statementStart(14780)
            for (i in idx + 2 until n + 1) {
Coverage.forLoopStart(14781)
                C[i - 1] = C[i]
Coverage.statementStart(14782)
            }
Coverage.statementStart(14783)
            child.n += sibling.n + 1
Coverage.statementStart(14784)
            n--
Coverage.statementStart(14785)
            sibling.free()
Coverage.statementStart(14786)
        }
        override fun forEach(action: (KEY, VALUE) -> Unit) {
Coverage.funStart(14787)
            for (i in 0 until n) {
Coverage.forLoopStart(14788)
                C[i]!!.forEach(action)
Coverage.statementStart(14789)
                action(keys[i] as KEY, values[i] as VALUE)
Coverage.statementStart(14790)
            }
Coverage.statementStart(14791)
            C[n]!!.forEach(action)
Coverage.statementStart(14792)
        }
        override fun search(k: KEY): VALUE? {
Coverage.funStart(14793)
            var i = 0
Coverage.statementStart(14794)
            while (i < n && k > (keys[i] as KEY)) {
Coverage.whileLoopStart(14795)
                i++
Coverage.statementStart(14796)
            }
Coverage.statementStart(14797)
            if ((keys[i] as KEY) == k) {
Coverage.ifStart(14798)
                return values[i] as VALUE
            } else {
Coverage.statementStart(14799)
                return C[i]!!.search(k)
            }
Coverage.statementStart(14800)
        }
        override fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
Coverage.funStart(14801)
            var i = n - 1
Coverage.statementStart(14802)
            var found = false
Coverage.statementStart(14803)
            for (j in 0 until n) {
Coverage.forLoopStart(14804)
                if (keys[j] as KEY == k) {
Coverage.ifStart(14805)
                    values[j] = onExists(keys[j] as KEY, values[j] as VALUE)
Coverage.statementStart(14806)
                    found = true
Coverage.statementStart(14807)
                    break
                }
Coverage.statementStart(14808)
            }
Coverage.statementStart(14809)
            if (!found) {
Coverage.ifStart(14810)
                while (i >= 0 && (keys[i] as KEY) > k) {
Coverage.whileLoopStart(14811)
                    i--
Coverage.statementStart(14812)
                }
Coverage.statementStart(14813)
                if (C[i + 1]!!.n == 2 * t - 1) {
Coverage.ifStart(14814)
                    splitChild(i + 1, C[i + 1]!!)
Coverage.statementStart(14815)
                    if ((keys[i + 1] as KEY) < k) {
Coverage.ifStart(14816)
                        i++
Coverage.statementStart(14817)
                    }
Coverage.statementStart(14818)
                }
Coverage.statementStart(14819)
                C[i + 1]!!.insertNonFull(k, onCreate, onExists)
Coverage.statementStart(14820)
            }
Coverage.statementStart(14821)
        }
        fun splitChild(i: Int, y: MyMapKNAMEVNAMEBTreeNodeGUSE) {
Coverage.funStart(14822)
            val z = if (y is MyMapKNAMEVNAMEBTreeNodeLeafGUSE) {
Coverage.ifStart(14823)
                /*return*/        MyMapKNAMEVNAMEBTreeNodeLeafGUSE(y.t)
            } else {
Coverage.statementStart(14824)
/*return*/                MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(y.t)
            }
Coverage.statementStart(14825)
            z.n = t - 1
Coverage.statementStart(14826)
            for (j in 0 until t - 1) {
Coverage.forLoopStart(14827)
                z.keys[j] = y.keys[j + t]
Coverage.statementStart(14828)
                z.values[j] = y.values[j + t]
Coverage.statementStart(14829)
            }
Coverage.statementStart(14830)
            if (y is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE && z is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14831)
                for (j in 0 until t) {
Coverage.forLoopStart(14832)
                    z.C[j] = y.C[j + t]
Coverage.statementStart(14833)
                }
Coverage.statementStart(14834)
            }
Coverage.statementStart(14835)
            y.n = t - 1
Coverage.statementStart(14836)
            var j = n
Coverage.statementStart(14837)
            while (j >= i + 1) {
Coverage.whileLoopStart(14838)
                C[j + 1] = C[j]
Coverage.statementStart(14839)
                j--
Coverage.statementStart(14840)
            }
Coverage.statementStart(14841)
            C[i + 1] = z
Coverage.statementStart(14842)
            j = n - 1
Coverage.statementStart(14843)
            while (j >= i) {
Coverage.whileLoopStart(14844)
                keys[j + 1] = keys[j]
Coverage.statementStart(14845)
                values[j + 1] = values[j]
Coverage.statementStart(14846)
                j--
Coverage.statementStart(14847)
            }
Coverage.statementStart(14848)
            keys[i] = y.keys[t - 1]
Coverage.statementStart(14849)
            values[i] = y.values[t - 1]
Coverage.statementStart(14850)
            n++
Coverage.statementStart(14851)
        }
    }
    class MyMapKNAMEVNAMEBTreeNodeLeafGDEF(t: Int) : MyMapKNAMEVNAMEBTreeNodeGUSE(t) {
        override fun free() {
Coverage.funStart(14852)
            /*later when buffer-manager is used*/
Coverage.statementStart(14853)
        }
        override fun iterator() = MyMapKNAMEVNAMEBTreeNodeIteratorLeafGUSE(this)
        fun findKEY(k: KEY): Int {
Coverage.funStart(14854)
            var idx = 0
Coverage.statementStart(14855)
            while (idx < n && (keys[idx] as KEY) < k) {
Coverage.whileLoopStart(14856)
                idx++
Coverage.statementStart(14857)
            }
Coverage.statementStart(14858)
            return idx
        }
        override fun remove(k: KEY): Pair<KEY, VALUE>? {
Coverage.funStart(14859)
            val idx = findKEY(k)
Coverage.statementStart(14860)
            val key = keys[idx] as KEY
Coverage.statementStart(14861)
            val value = values[idx] as VALUE
Coverage.statementStart(14862)
            if (idx < n && key == k) {
Coverage.ifStart(14863)
                removeFromLeaf(idx)
Coverage.statementStart(14864)
                return Pair(key, value)
            } else {
Coverage.statementStart(14865)
                return null
            }
Coverage.statementStart(14866)
        }
        fun removeFromLeaf(idx: Int) {
Coverage.funStart(14867)
            for (i in idx + 1 until n) {
Coverage.forLoopStart(14868)
                keys[i - 1] = keys[i]
Coverage.statementStart(14869)
                values[i - 1] = values[i]
Coverage.statementStart(14870)
            }
Coverage.statementStart(14871)
            n--
Coverage.statementStart(14872)
        }
        override fun forEach(action: (KEY, VALUE) -> Unit) {
Coverage.funStart(14873)
            for (i in 0 until n) {
Coverage.forLoopStart(14874)
                action(keys[i] as KEY, values[i] as VALUE)
Coverage.statementStart(14875)
            }
Coverage.statementStart(14876)
        }
        override fun search(k: KEY): VALUE? {
Coverage.funStart(14877)
            var i = 0
Coverage.statementStart(14878)
            while (i < n && k > (keys[i] as KEY)) {
Coverage.whileLoopStart(14879)
                i++
Coverage.statementStart(14880)
            }
Coverage.statementStart(14881)
            if ((keys[i] as KEY) == k) {
Coverage.ifStart(14882)
                return values[i] as VALUE
            } else {
Coverage.statementStart(14883)
                return null
            }
Coverage.statementStart(14884)
        }
        override fun insertNonFull(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
Coverage.funStart(14885)
            var i = n - 1
Coverage.statementStart(14886)
            var found = false
Coverage.statementStart(14887)
            for (j in 0 until n) {
Coverage.forLoopStart(14888)
                if (keys[j] as KEY == k) {
Coverage.ifStart(14889)
                    values[j] = onExists(keys[j] as KEY, values[j] as VALUE)
Coverage.statementStart(14890)
                    found = true
Coverage.statementStart(14891)
                    break
                }
Coverage.statementStart(14892)
            }
Coverage.statementStart(14893)
            if (!found) {
Coverage.ifStart(14894)
                while (i >= 0 && (keys[i] as KEY > k)) {
Coverage.whileLoopStart(14895)
                    keys[i + 1] = keys[i]
Coverage.statementStart(14896)
                    values[i + 1] = values[i]
Coverage.statementStart(14897)
                    i--
Coverage.statementStart(14898)
                }
Coverage.statementStart(14899)
                keys[i + 1] = k
Coverage.statementStart(14900)
                values[i + 1] = onCreate()
Coverage.statementStart(14901)
                n++
Coverage.statementStart(14902)
            }
Coverage.statementStart(14903)
        }
    }
    class MyMapKNAMEVNAMEBTreeInitializerGDEF(val t: Int, val target: MyMapKNAMEVNAMEBTreeGUSE) {
        var size = 0
        val data = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
        fun appendAssumeSorted(key: KEY, value: VALUE): VALUE {
Coverage.funStart(14904)
            val tmp: MyMapKNAMEVNAMEBTreeNodeGUSE
Coverage.statementStart(14905)
            if (data.size == 0 || data[data.size - 1].n == 2 * t - 1) {
Coverage.ifStart(14906)
                tmp = MyMapKNAMEVNAMEBTreeNodeLeafGUSE(t)
Coverage.statementStart(14907)
                data.add(tmp)
Coverage.statementStart(14908)
                tmp.keys[0] = key
Coverage.statementStart(14909)
                tmp.values[0] = value
Coverage.statementStart(14910)
                tmp.n = 1
Coverage.statementStart(14911)
            } else {
Coverage.ifStart(14912)
                tmp = data[data.size - 1]
Coverage.statementStart(14913)
            }
Coverage.statementStart(14914)
            tmp.keys[tmp.n] = key
Coverage.statementStart(14915)
            tmp.values[tmp.n] = value
Coverage.statementStart(14916)
            tmp.n++
Coverage.statementStart(14917)
            size++
Coverage.statementStart(14918)
            return value
        }
        fun apply() {
Coverage.funStart(14919)
            var listA = data
Coverage.statementStart(14920)
            var listB = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
Coverage.statementStart(14921)
            while (listA.size > 1) {
Coverage.whileLoopStart(14922)
                SanityCheck {
Coverage.statementStart(14923)
                    var j = 0
Coverage.statementStart(14924)
                    for (x in listA) {
Coverage.forLoopStart(14925)
                        if (x is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(14926)
                            for (i in 0 until x.n + 1) {
Coverage.forLoopStart(14927)
                                SanityCheck.check { x.C[i] != null }
Coverage.statementStart(14928)
                            }
Coverage.statementStart(14929)
                            j++
Coverage.statementStart(14930)
                        }
Coverage.statementStart(14931)
                    }
Coverage.statementStart(14932)
                }
Coverage.statementStart(14933)
                var n = listA.size  //total nodes in this level
Coverage.statementStart(14934)
                var n2 = (n + 2 * t) / (2 * t - 1)  //required nodes in the next level to hold all of the current nodes (round up)
Coverage.statementStart(14935)
                var n3 = n / n2 + 1       //average number of childs in the next level - prevent that the last node has 1 element and therefore a wrong tree depth
Coverage.statementStart(14936)
                for (i in 0 until n2) {
Coverage.forLoopStart(14937)
                    val node = MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(t)
Coverage.statementStart(14938)
                    listB.add(node)
Coverage.statementStart(14939)
                    for (j in 0 until n3) {
Coverage.forLoopStart(14940)
                        if (listA.size > 0) {
Coverage.ifStart(14941)
                            var tmp = listA.removeAt(0)
Coverage.statementStart(14942)
                            node.C[node.n] = tmp
Coverage.statementStart(14943)
                            if (j < n3 - 1 && listA.size > 0) {
Coverage.ifStart(14944)
                                var maxElement = tmp
Coverage.statementStart(14945)
                                while (maxElement is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.whileLoopStart(14946)
                                    maxElement = maxElement.C[maxElement.n]!!
Coverage.statementStart(14947)
                                }
Coverage.statementStart(14948)
                                node.keys[node.n] = maxElement.keys[maxElement.n - 1]
Coverage.statementStart(14949)
                                node.values[node.n] = maxElement.values[maxElement.n - 1]
Coverage.statementStart(14950)
                                maxElement.n--
Coverage.statementStart(14951)
                                node.n++
Coverage.statementStart(14952)
                            }
Coverage.statementStart(14953)
                        } else {
Coverage.ifStart(14954)
                            break
                        }
Coverage.statementStart(14955)
                    }
Coverage.statementStart(14956)
                }
Coverage.statementStart(14957)
                SanityCheck.check { listA.size == 0 }
Coverage.statementStart(14958)
                listA = listB
Coverage.statementStart(14959)
                listB = mutableListOf<MyMapKNAMEVNAMEBTreeNodeGUSE>()
Coverage.statementStart(14960)
            }
Coverage.statementStart(14961)
            target.clear()
Coverage.statementStart(14962)
            target.root = listA[0]
Coverage.statementStart(14963)
            target.size = size
Coverage.statementStart(14964)
        }
    }
    fun withFastInitializer(action: (MyMapKNAMEVNAMEBTreeInitializerGUSE) -> Unit) {
Coverage.funStart(14965)
        val init = MyMapKNAMEVNAMEBTreeInitializerGUSE(t, this)
Coverage.statementStart(14966)
        action(init)
Coverage.statementStart(14967)
        init.apply()
Coverage.statementStart(14968)
    }
    operator fun set(k: KEY, v: VALUE) = insert(k, { v }, { a, b -> v })
    fun insert(k: KEY, onCreate: () -> VALUE, onExists: (KEY, VALUE) -> VALUE) {
Coverage.funStart(14969)
        if (root == null) {
Coverage.ifStart(14970)
            root = MyMapKNAMEVNAMEBTreeNodeLeafGUSE(t)
Coverage.statementStart(14971)
            root!!.keys[0] = k
Coverage.statementStart(14972)
            root!!.values[0] = onCreate()
Coverage.statementStart(14973)
            root!!.n = 1
Coverage.statementStart(14974)
            size++
Coverage.statementStart(14975)
        } else if (root!!.n == 2 * t - 1) {
Coverage.ifStart(14976)
            val s = MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE(t)
Coverage.statementStart(14977)
            s.C[0] = root
Coverage.statementStart(14978)
            s.splitChild(0, root!!)
Coverage.statementStart(14979)
            var i = 0
Coverage.statementStart(14980)
            if ((s.keys[0] as KEY) < k) {
Coverage.ifStart(14981)
                i++
Coverage.statementStart(14982)
            }
Coverage.statementStart(14983)
            s.C[i]!!.insertNonFull(k, {
Coverage.statementStart(14984)
                size++
Coverage.statementStart(14985)
                /*return*/ onCreate()
            }, onExists)
Coverage.statementStart(14986)
            root = s
Coverage.statementStart(14987)
        } else {
Coverage.ifStart(14988)
            root!!.insertNonFull(k, {
Coverage.statementStart(14989)
                size++
Coverage.statementStart(14990)
                /*return*/ onCreate()
            }, onExists)
Coverage.statementStart(14991)
        }
Coverage.statementStart(14992)
    }
    fun forEach(action: (KEY, VALUE) -> Unit) {
Coverage.funStart(14993)
        if (root != null) {
Coverage.ifStart(14994)
            root!!.forEach(action)
Coverage.statementStart(14995)
        }
Coverage.statementStart(14996)
    }
    fun contains(k: KEY) = get(k) == null
    operator fun get(k: KEY): VALUE? {
Coverage.funStart(14997)
        if (root == null) {
Coverage.ifStart(14998)
            return null
        } else {
Coverage.statementStart(14999)
            return root!!.search(k)
        }
Coverage.statementStart(15000)
    }
    fun remove(k: KEY): Pair<KEY, VALUE>? {
Coverage.funStart(15001)
        if (root != null) {
Coverage.ifStart(15002)
            val res = root!!.remove(k)
Coverage.statementStart(15003)
            if (res != null) {
Coverage.ifStart(15004)
                size--
Coverage.statementStart(15005)
            }
Coverage.statementStart(15006)
            if (root!!.n == 0) {
Coverage.ifStart(15007)
                val tmp = root!!
Coverage.statementStart(15008)
                if (tmp is MyMapKNAMEVNAMEBTreeNodeNonLeafGUSE) {
Coverage.ifStart(15009)
                    root = tmp!!.C[0]
Coverage.statementStart(15010)
                } else {
Coverage.ifStart(15011)
                    root == null
Coverage.statementStart(15012)
                }
Coverage.statementStart(15013)
                tmp.free()
Coverage.statementStart(15014)
            }
Coverage.statementStart(15015)
            return res
        }
Coverage.statementStart(15016)
        return null
    }
    fun iterator(): MyMapKNAMEVNAMEBTreeNodeIteratorGUSE {
Coverage.funStart(15017)
        if (root != null) {
Coverage.ifStart(15018)
            return root!!.iterator()
        } else {
Coverage.statementStart(15019)
            return EmptyIteratorGUSE()
        }
Coverage.statementStart(15020)
    }
    class EmptyIteratorGDEF : MyMapKNAMEVNAMEBTreeNodeIteratorGUSE() {
        override fun hasNext() = false
        override fun next(): KEY = throw Exception("unreachable")
        override fun value(): VALUE = throw Exception("unreachable")
    }
    inline fun getOrCreate(key: KEY, crossinline onCreate: () -> VALUE): VALUE {
Coverage.funStart(15021)
        var res: VALUE? = null
Coverage.statementStart(15022)
        insert(key, {
Coverage.statementStart(15023)
            res = onCreate()
Coverage.statementStart(15024)
            /*return*/res!!
        }, { a, b ->
Coverage.statementStart(15025)
            res = b
Coverage.statementStart(15026)
            /*return*/ res!!
        })
Coverage.statementStart(15027)
        return res!!
    }
    fun safeToFile(filename: String) {
Coverage.funStart(15028)
        IOSTART
Coverage.statementStart(15029)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(15030)
            out.writeInt(size)
Coverage.statementStart(15031)
            forEach { k, v ->
Coverage.statementStart(15032)
                out.writeKEY(k)
Coverage.statementStart(15033)
                out.writeVALUE(v)
Coverage.statementStart(15034)
            }
Coverage.statementStart(15035)
        }
Coverage.statementStart(15036)
        IOEND
Coverage.statementStart(15037)
    }
    fun loadFromFile(filename: String) {
Coverage.funStart(15038)
        IOSTART
Coverage.statementStart(15039)
        withFastInitializer { init ->
Coverage.statementStart(15040)
            File(filename).dataInputStream { fis ->
Coverage.statementStart(15041)
                var size = fis.readInt()
Coverage.statementStart(15042)
                for (i in 0 until size) {
Coverage.forLoopStart(15043)
                    val k = fis.readKEY()
Coverage.statementStart(15044)
                    val v = fis.readVALUE()
Coverage.statementStart(15045)
                    init.appendAssumeSorted(k, v)
Coverage.statementStart(15046)
                }
Coverage.statementStart(15047)
            }
Coverage.statementStart(15048)
        }
Coverage.statementStart(15049)
        IOEND
Coverage.statementStart(15050)
    }
}

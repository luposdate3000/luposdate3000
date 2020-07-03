package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage

class MyMapStringIntPatriciaTrie(@JvmField val undefinedValue: Int = Int.MAX_VALUE) {
    @JvmField
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNodeN()

    @JvmField
    var rootValue: Int = undefinedValue

    @JvmField
    var size = 0

    abstract class MyMapStringIntPatriciaTrieNode() {
        var str = ""
    }

    class MyMapStringIntPatriciaTrieNodeN() : MyMapStringIntPatriciaTrieNode() {
        var arr = IntArray(0)

        /*arr :: second half: child values, first half offsets in str*/
        var childs = arrayOf<MyMapStringIntPatriciaTrieNode?>()
    }

    class MyMapStringIntPatriciaTrieNode2() : MyMapStringIntPatriciaTrieNode() {
        var arr1: Int = 0
        var arr2: Int = 0
        var arr3: Int = 0
        var childs0: MyMapStringIntPatriciaTrieNode? = null
        var childs1: MyMapStringIntPatriciaTrieNode? = null
    }

    /*inline*/ fun walkInternal(_key: String, /*crossinline*/ onCreate: () -> Int, /*crossinline*/ onExist: (Int) -> Int, /*crossinline*/ onNotFound: () -> Unit, create: Boolean) {
        if (_key == "") {
            if (rootValue != undefinedValue) {
                rootValue = onExist(rootValue)
            } else if (create) {
                rootValue = onCreate()
            } else {
                onNotFound()
            }
        } else {
            var key = _key
            var prev: MyMapStringIntPatriciaTrieNode? = null
            var nextnode = root
            loop@ while (true) {
                SanityCheck.check { key.length > 0 }
                val node = nextnode
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
                        if (node.str[0] == key[0]) {
                            var childKeyStart = 0
                            var childKeyEnd = node.arr1
                            var childKey = node.str.substring(childKeyStart, childKeyEnd)
                            SanityCheck.check { childKey.length > 0 }
                            var commonKey = key.commonPrefixWith(childKey)
                            SanityCheck.check { commonKey.length > 0 }
                            if (commonKey.length == key.length && commonKey.length == childKey.length) {
                                if (node.arr2 != undefinedValue) {
                                    node.arr2 = onExist(node.arr2)
                                } else if (create) {
                                    onCreate()
                                } else {
                                    onNotFound()
                                }
                                return
                            } else if (commonKey.length == childKey.length) {
                                if (node.childs0 != null) {
                                    prev = node
                                    nextnode = node.childs0!!
                                    key = key.substring(childKey.length, key.length)
                                    SanityCheck.check { key.length > 0 }
                                    continue@loop
                                } else {
                                    if (create) {
                                        val newNode = MyMapStringIntPatriciaTrieNodeN()
                                        newNode.childs = arrayOf(null)
                                        newNode.arr = intArrayOf(0, onCreate())
                                        newNode.str = key.substring(commonKey.length, key.length)
                                        SanityCheck.check { newNode.str.length > 0 }
                                        node.childs0 = newNode
                                        size++
                                    } else {
                                        onNotFound()
                                    }
                                    return
                                }
                                /*Coverage Unreachable*/
                            } else {
                                if (create) {
                                    if (commonKey.length == key.length) {
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
                                        SanityCheck.check { otherKey.length > 0 }
                                        var newNode = MyMapStringIntPatriciaTrieNodeN()
                                        newNode.childs = arrayOf(node.childs0)
                                        newNode.str = otherKey
                                        newNode.arr = intArrayOf(0, node.arr2)
                                        node.childs0 = newNode
                                        node.arr2 = onCreate()
                                        node.str = commonKey + node.str.substring(childKeyEnd, node.str.length)
                                        SanityCheck.check { node.str.length > commonKey.length }
                                        node.arr1 = commonKey.length
                                        SanityCheck.check { node.arr1 < node.str.length }
                                    } else {
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
                                        SanityCheck.check { otherKey.length > 0 }
                                        var newKey = key.substring(commonKey.length, key.length)
                                        SanityCheck.check { newKey.length > 0 }
                                        var newNode = MyMapStringIntPatriciaTrieNode2()
                                        newNode.childs0 = node.childs0
                                        newNode.childs1 = null
                                        newNode.str = otherKey + newKey
                                        newNode.arr1 = otherKey.length
                                        newNode.arr2 = node.arr2
                                        newNode.arr3 = onCreate()
                                        node.childs0 = newNode
                                        node.arr2 = undefinedValue
                                        node.str = commonKey + node.str.substring(childKeyEnd, node.str.length)
                                        SanityCheck.check { node.str.length > commonKey.length }
                                        node.arr1 = commonKey.length
                                    }
                                    size++
                                } else {
                                    onNotFound()
                                }
                                return
                            }
                            /*Coverage Unreachable*/
                        } else if (node.str[node.arr1] == key[0]) {
                            var childKeyStart = node.arr1
                            var childKeyEnd = node.str.length
                            var childKey = node.str.substring(childKeyStart, childKeyEnd)
                            SanityCheck.check { childKey.length > 0 }
                            var commonKey = key.commonPrefixWith(childKey)
                            if (commonKey.length == key.length && commonKey.length == childKey.length) {
                                if (node.arr3 != undefinedValue) {
                                    node.arr3 = onExist(node.arr3)
                                } else if (create) {
                                    node.arr3 = onCreate()
                                } else {
                                    onNotFound()
                                }
                                return
                            } else if (commonKey.length == childKey.length) {
                                if (node.childs1 != null) {
                                    prev = node
                                    nextnode = node.childs1!!
                                    key = key.substring(childKey.length, key.length)
                                    SanityCheck.check { key.length > 0 }
                                    continue@loop
                                } else {
                                    if (create) {
                                        val newNode = MyMapStringIntPatriciaTrieNodeN()
                                        newNode.childs = arrayOf(null)
                                        newNode.arr = intArrayOf(0, onCreate())
                                        newNode.str = key.substring(commonKey.length, key.length)
                                        SanityCheck.check { newNode.str.length > 0 }
                                        node.childs1 = newNode
                                        size++
                                    } else {
                                        onNotFound()
                                    }
                                    return
                                }
                                /*Coverage Unreachable*/
                            } else {
                                if (create) {
                                    if (commonKey.length == key.length) {
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
                                        SanityCheck.check { otherKey.length > 0 }
                                        var newNode = MyMapStringIntPatriciaTrieNodeN()
                                        newNode.childs = arrayOf(node.childs1)
                                        newNode.str = otherKey
                                        newNode.arr = intArrayOf(0, node.arr3)
                                        node.childs1 = newNode
                                        node.arr3 = onCreate()
                                        node.str = node.str.substring(0, childKeyStart) + commonKey
                                        SanityCheck.check { node.str.length > commonKey.length }
                                    } else {
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
                                        var newKey = key.substring(commonKey.length, key.length)
                                        SanityCheck.check { newKey.length > 0 }
                                        var newNode = MyMapStringIntPatriciaTrieNode2()
                                        newNode.childs0 = node.childs1
                                        newNode.childs1 = null
                                        newNode.str = otherKey + newKey
                                        newNode.arr1 = otherKey.length
                                        newNode.arr2 = node.arr3
                                        newNode.arr3 = onCreate()
                                        node.childs1 = newNode
                                        node.arr3 = undefinedValue
                                        node.str = node.str.substring(0, childKeyStart) + commonKey
                                        SanityCheck.check { node.str.length > commonKey.length }
                                    }
                                    size++
                                } else {
                                    onNotFound()
                                }
                                return
                            }
                            /*Coverage Unreachable*/
                        } else if (create) {
                            var childs = arrayOf(node.childs0, node.childs1, null)
                            var arr = intArrayOf(0, node.arr1, node.str.length, node.arr2, node.arr3, onCreate())
                            var nodetmp = MyMapStringIntPatriciaTrieNodeN()
                            if (prev == null) {
                                root = nodetmp
                            } else {
                                if (prev is MyMapStringIntPatriciaTrieNode2) {
                                    if (prev.childs0 == node) {
                                        prev.childs0 = nodetmp
                                    } else {
                                        prev.childs1 = nodetmp
                                    }
                                } else {
                                    SanityCheck.check { prev is MyMapStringIntPatriciaTrieNodeN }
                                    for (i in 0 until (prev as MyMapStringIntPatriciaTrieNodeN).childs.size) {
                                        if (prev.childs[i] == node) {
                                            prev.childs[i] = nodetmp
                                        }
                                    }
                                }
                            }
                            nodetmp.str = node.str + key
                            nodetmp.childs = childs
                            nodetmp.arr = arr
                            size++
                        } else {
                            onNotFound()
                        }
                        return
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
                        var childCount = node.childs.size
                        for (childIdx in 0 until childCount) {
                            if (node.str[node.arr[childIdx]] == key[0]) {
                                var childKeyStart = node.arr[childIdx]
                                var childKeyEnd: Int
                                if (childIdx == childCount - 1) {
                                    childKeyEnd = node.str.length
                                } else {
                                    childKeyEnd = node.arr[childIdx + 1]
                                }
                                var childKey = node.str.substring(childKeyStart, childKeyEnd)
                                SanityCheck.check { childKey.length > 0 }
                                var commonKey = key.commonPrefixWith(childKey)
                                if (commonKey.length == key.length && commonKey.length == childKey.length) {
                                    var tmp = node.arr[childCount + childIdx]
                                    if (tmp != undefinedValue) {
                                        node.arr[childCount + childIdx] = onExist(tmp)
                                    } else if (create) {
                                        node.arr[childCount + childIdx] = onCreate()
                                    } else {
                                        onNotFound()
                                    }
                                    return
                                } else if (commonKey.length == childKey.length) {
                                    if (node.childs[childIdx] != null) {
                                        prev = node
                                        nextnode = node.childs[childIdx]!!
                                        key = key.substring(childKey.length, key.length)
                                        SanityCheck.check { key.length > 0 }
                                        continue@loop
                                    } else {
                                        if (create) {
                                            val newNode = MyMapStringIntPatriciaTrieNodeN()
                                            newNode.childs = arrayOf(null)
                                            newNode.arr = intArrayOf(0, onCreate())
                                            newNode.str = key.substring(commonKey.length, key.length)
                                            SanityCheck.check { newNode.str.length > 0 }
                                            node.childs[childIdx] = newNode
                                            size++
                                        } else {
                                            onNotFound()
                                        }
                                        return
                                    }
                                    /*Coverage Unreachable*/
                                } else {
                                    if (create) {
                                        if (commonKey.length == key.length) {
                                            var otherKey = childKey.substring(commonKey.length, childKey.length)
                                            SanityCheck.check { otherKey.length > 0 }
                                            var newNode = MyMapStringIntPatriciaTrieNodeN()
                                            newNode.childs = arrayOf(node.childs[childIdx])
                                            newNode.str = otherKey
                                            newNode.arr = intArrayOf(0, node.arr[childCount + childIdx])
                                            node.childs[childIdx] = newNode
                                            node.arr[childCount + childIdx] = onCreate()
                                            node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
                                            SanityCheck.check { node.str.length > commonKey.length || childCount == 1 }
                                            for (j in childIdx + 1 until childCount) {
                                                node.arr[j] -= otherKey.length
                                                SanityCheck.check { node.arr[j] < node.str.length }
                                            }
                                        } else {
                                            var otherKey = childKey.substring(commonKey.length, childKey.length)
                                            SanityCheck.check { otherKey.length > 0 }
                                            var newKey = key.substring(commonKey.length, key.length)
                                            SanityCheck.check { newKey.length > 0 }
                                            var newNode = MyMapStringIntPatriciaTrieNode2()
                                            newNode.childs0 = node.childs[childIdx]
                                            newNode.childs1 = null
                                            newNode.str = otherKey + newKey
                                            newNode.arr1 = otherKey.length
                                            newNode.arr2 = node.arr[childCount + childIdx]
                                            newNode.arr3 = onCreate()
                                            node.childs[childIdx] = newNode
                                            node.arr[childCount + childIdx] = undefinedValue
                                            node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
                                            SanityCheck.check { node.str.length > commonKey.length || childCount == 1 }
                                            for (j in childIdx + 1 until childCount) {
                                                node.arr[j] -= otherKey.length
                                                SanityCheck.check { node.arr[j] < node.str.length }
                                            }
                                        }
                                        size++
                                    } else {
                                        onNotFound()
                                    }
                                    return
                                }
                                /*Coverage Unreachable*/
                            }
                        }
                        if (create) {
                            var childs = Array(childCount + 1) {
                                val res: MyMapStringIntPatriciaTrieNode?
                                if (it < childCount) {
                                    res = node.childs[it]
                                } else {
                                    res = null
                                }
                                /*return*/ res
                            }
                            var arr = IntArray(node.arr.size + 2)
                            for (childIdx in 0 until childCount) {
                                arr[childIdx] = node.arr[childIdx]
                                arr[childCount + 1 + childIdx] = node.arr[childCount + childIdx]
                            }
                            arr[childCount] = node.str.length
                            arr[childCount + childCount + 1] = onCreate()
                            node.str += key
                            node.childs = childs
                            node.arr = arr
                            size++
                        } else {
                            onNotFound()
                        }
                        return
                    }
                    else -> {
                        SanityCheck.checkUnreachable()
                    }
                }
                /*Coverage Unreachable*/
            }
            /*Coverage Unreachable*/
        }
    }

    /*inline*/ operator fun get(key: String): Int? {
        var res: Int? = null
        walkInternal(key, { undefinedValue }, {
            res = it
            /*return*/ it
        }, {}, false)
        return res
    }

    /*inline*/ operator fun set(key: String, value: Int) {
        walkInternal(key, { value }, { value }, {}, true)
    }

    /*inline*/ fun getOrCreate(key: String, /*crossinline*/ onCreate: () -> Int): Int {
        var res = undefinedValue
        walkInternal(key, {
            res = onCreate()
            /*return*/ res
        }, {
            res = it
            /*return*/res
        }, {}, true)
        return res
    }

    /*inline*/ fun appendAssumeSorted(key: String, value: Int): Int {
        set(key, value)
        return value
    }

    /*inline*/ fun clear() {
        root = MyMapStringIntPatriciaTrieNodeN()
        rootValue = undefinedValue
        size = 0
    }

    /*inline*/ fun forEach(/*crossinline*/ action: (String, Int) -> Unit) {
        var queue = mutableListOf<Pair<String, MyMapStringIntPatriciaTrieNode>>()
        if (rootValue != undefinedValue) {
            action("", rootValue)
        }
        val roottmp = root
        when (roottmp) {
            is MyMapStringIntPatriciaTrieNode2 -> {
                var childKeyStart = 0
                var childKeyEnd = roottmp.arr1
                var childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
                if (roottmp.arr2 != undefinedValue) {
                    action(childKey, roottmp.arr2)
                }
                if (roottmp.childs0 != null) {
                    queue.add(Pair(childKey, roottmp.childs0!!))
                }
                childKeyStart = roottmp.arr1
                childKeyEnd = roottmp.str.length
                childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
                if (roottmp.arr3 != undefinedValue) {
                    action(childKey, roottmp.arr3)
                }
                if (roottmp.childs1 != null) {
                    queue.add(Pair(childKey, roottmp.childs1!!))
                }
            }
            is MyMapStringIntPatriciaTrieNodeN -> {
                var childCount = roottmp.childs.size
                for (childIdx in 0 until childCount) {
                    var childKeyStart = roottmp.arr[childIdx]
                    var childKeyEnd: Int
                    if (childIdx == childCount - 1) {
                        childKeyEnd = roottmp.str.length
                    } else {
                        childKeyEnd = roottmp.arr[childIdx + 1]
                    }
                    var childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
                    if (roottmp.arr[childCount + childIdx] != undefinedValue) {
                        action(childKey, roottmp.arr[childCount + childIdx])
                    }
                    if (roottmp.childs[childIdx] != null) {
                        queue.add(Pair(childKey, roottmp.childs[childIdx]!!))
                    }
                }
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        while (queue.size > 0) {
            var current = queue.removeAt(0)
            var prefix = current.first
            val node = current.second
            when (node) {
                is MyMapStringIntPatriciaTrieNode2 -> {
                    var childKeyStart = 0
                    var childKeyEnd = node.arr1
                    var childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
                    if (node.arr2 != undefinedValue) {
                        action(childKey, node.arr2)
                    }
                    if (node.childs0 != null) {
                        queue.add(Pair(childKey, node.childs0!!))
                    }
                    childKeyStart = node.arr1
                    childKeyEnd = node.str.length
                    childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
                    if (node.arr3 != undefinedValue) {
                        action(childKey, node.arr3)
                    }
                    if (node.childs1 != null) {
                        queue.add(Pair(childKey, node.childs1!!))
                    }
                }
                is MyMapStringIntPatriciaTrieNodeN -> {
                    var childCount = node.childs.size
                    for (childIdx in 0 until childCount) {
                        var childKeyStart = node.arr[childIdx]
                        var childKeyEnd: Int
                        if (childIdx == childCount - 1) {
                            childKeyEnd = node.str.length
                        } else {
                            childKeyEnd = node.arr[childIdx + 1]
                        }
                        var childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
                        if (node.arr[childCount + childIdx] != undefinedValue) {
                            action(childKey, node.arr[childCount + childIdx])
                        }
                        if (node.childs[childIdx] != null) {
                            queue.add(Pair(childKey, node.childs[childIdx]!!))
                        }
                    }
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
    }

    fun safeToFile(filename: String) {
        var queue = mutableListOf<MyMapStringIntPatriciaTrieNode>()
        File(filename).dataOutputStream { out ->
            out.writeInt(rootValue)
            queue.add(root)
            while (queue.size > 0) {
                val node = queue.removeAt(0)
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
                        out.writeShort(2)
                        for (c in node.str) {
                            out.writeChar(c.toInt())
                        }
                        out.writeChar(0)
                        out.writeInt(0)
                        out.writeInt(node.arr2)
                        if (node.childs0 == null) {
                            out.writeChar(0)
                        } else {
                            out.writeChar(1)
                            queue.add(node.childs0!!)
                        }
                        out.writeInt(node.arr1)
                        out.writeInt(node.arr3)
                        if (node.childs1 == null) {
                            out.writeChar(0)
                        } else {
                            out.writeChar(1)
                            queue.add(node.childs1!!)
                        }
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
                        var childCount = node.childs.size
                        out.writeShort(childCount)
                        for (c in node.str) {
                            out.writeChar(c.toInt())
                        }
                        out.writeChar(0)
                        for (i in 0 until childCount) {
                            out.writeInt(node.arr[i])
                            out.writeInt(node.arr[childCount + i])
                            if (node.childs[i] == null) {
                                out.writeChar(0)
                            } else {
                                out.writeChar(1)
                                queue.add(node.childs[i]!!)
                            }
                        }
                    }
                    else -> {
                        SanityCheck.checkUnreachable()
                    }
                }
            }
        }
    }

    fun loadFromFile(filename: String) {
        var queue = mutableListOf<Pair<Int, MyMapStringIntPatriciaTrieNode>>()
        File(filename).dataInputStream { fis ->
            rootValue = fis.readInt()
            if (rootValue != undefinedValue) {
                size++
            }
            var first = true
            while (queue.size > 0 || first) {
                var childCount = fis.readShort()
                val node: MyMapStringIntPatriciaTrieNode
                if (childCount.toInt() == 2) {
                    node = MyMapStringIntPatriciaTrieNode2()
                } else {
                    node = MyMapStringIntPatriciaTrieNodeN()
                    node.childs = Array<MyMapStringIntPatriciaTrieNode?>(childCount.toInt()) { null }
                    node.arr = IntArray(childCount * 2)
                }
                if (first) {
                    root = node
                    first = false
                } else {
                    var current = queue.removeAt(0)
                    var tmp = current.second
                    when (tmp) {
                        is MyMapStringIntPatriciaTrieNode2 -> {
                            if (current.first == 0) {
                                tmp.childs0 = node
                            } else {
                                tmp.childs1 = node
                            }
                        }
                        is MyMapStringIntPatriciaTrieNodeN -> {
                            tmp.childs[current.first] = node
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
                size = 0
                var c = fis.readChar()
                while (c.toInt() != 0) {
                    node.str += c
                    c = fis.readChar()
                }
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
                        fis.readInt()
                        node.arr2 = fis.readInt()
                        if (node.arr2 != undefinedValue) {
                            size++
                        }
                        var x = fis.readChar()
                        if (x.toInt() == 1) {
                            queue.add(Pair(0, node))
                        }
                        node.arr1 = fis.readInt()
                        node.arr3 = fis.readInt()
                        if (node.arr3 != undefinedValue) {
                            size++
                        }
                        x = fis.readChar()
                        if (x.toInt() == 1) {
                            queue.add(Pair(1, node))
                        }
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
                        for (i in 0 until childCount) {
                            node.arr[i] = fis.readInt()
                            node.arr[childCount + i] = fis.readInt()
                            if (node.arr[childCount + i] != undefinedValue) {
                                size++
                            }
                            var x = fis.readChar()
                            if (x.toInt() == 1) {
                                queue.add(Pair(i, node))
                            }
                        }
                    }
                    else -> {
                        SanityCheck.checkUnreachable()
                    }
                }
            }
        }
    }

    fun debugInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode, depth: Int) {
        SanityCheck {
            when (node) {
                is MyMapStringIntPatriciaTrieNode2 -> {
                    var i = 0
                    if (node.childs0 != null) {
                        i++
                    }
                    if (node.childs1 != null) {
                        i++
                    }
                   SanityCheck.println("debug ${prefix}${node.str}:2@${node.str.length}-${depth}+$i,${2 - i}#2")
                    if (node.childs0 != null) {
                        debugInternal(prefix + " ", node.childs0!!, depth + 1)
                    }
                    if (node.childs1 != null) {
                        debugInternal(prefix + " ", node.childs1!!, depth + 1)
                    }
                }
                is MyMapStringIntPatriciaTrieNodeN -> {
                    var i = 0
                    for (c in node.childs) {
                        if (c != null) {
                            i++
                        }
                    }
                   SanityCheck.println("debug ${prefix}${node.str}:${node.childs.size}@${node.str.length}-${depth}+$i,${node.childs.size - i}#N")
                    for (c in node.childs) {
                        if (c != null) {
                            debugInternal(prefix + " ", c, depth + 1)
                        }
                    }
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
    }

    fun debug() = debugInternal("", root, 0)
}

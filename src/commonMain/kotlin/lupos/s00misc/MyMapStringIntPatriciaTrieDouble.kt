package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapStringIntPatriciaTrieDouble() {
    val undefinedValue = -1
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNode()
    var rootValue: Int = undefinedValue
    var size: Int = 0
        get() = allOutNodes.size
    val allNodes = MyListGeneric<MyMapStringIntPatriciaTrieNode>()
    val allOutNodes = MyListInt()//index in allNodes
    val allOutOffsets = MyListInt()//index in allNodesChilds

    init {
        allNodes.add(root)
    }

    class MyMapStringIntPatriciaTrieNode() {
        var parent = 0
        var str = ""
        var data = IntArray(0) /* offsets in str, child-values, child-pointers  */
    }

    operator fun get(key: Int): String {
        if (key == rootValue) {
            return ""
        }
        var nodeIdx = allOutNodes[key]
        var childIdx = allOutOffsets[key]
        var res = ""
        var node = allNodes[nodeIdx]
        while (true) {
            val childKeyStart = node.data[childIdx]
            val childKeyEnd: Int
            val childCount = node.data.size / 3
            if (childIdx == childCount - 1) {
                childKeyEnd = node.str.length
            } else {
                childKeyEnd = node.data[childIdx + 1]
            }
            res = node.str.substring(childKeyStart, childKeyEnd) + res
            if (nodeIdx == node.parent) {
                return res
            }
            childIdx = 0
            val parent = allNodes[node.parent]
            val parentChildCount = parent.data.size / 3
            val parentChildCount2 = parentChildCount + parentChildCount
            while (parent.data[parentChildCount2 + childIdx] != nodeIdx) {
                childIdx++
            }
            nodeIdx = node.parent
            node = parent
        }
/*Coverage Unreachable*/
    }

    fun debug() {
        SanityCheck {
            println("debug ->")
            if (rootValue != undefinedValue) {
                println("debug rootValue -> $rootValue")
            }
            val it = allNodes.iterator()
            while (it.hasNext()) {
                val node = it.next()
                println("debug ${node.str} ${node.data.map { it }} ${node.parent}")
            }
            println("debug --")
            var it2 = allOutNodes.iterator()
            var it3 = allOutOffsets.iterator()
            while (it2.hasNext()) {
                println("debug ${it2.next()} ${it3.next()}")
            }
            println("debug <-")
        }
    }

    fun walkInternal(_key: String, create: Boolean): Int {
        if (_key == "") {
            if (create && rootValue == undefinedValue) {
                rootValue = allOutNodes.size
                allOutNodes.add(-1)
                allOutOffsets.add(-1)
            }
            return rootValue
        } else {
            var key = _key
            var nextNode = root
            var nextNodeIdx = 0
            loop@ while (true) {
                val node = nextNode
                val nodeIdx = nextNodeIdx
                var childCount = node.data.size / 3
                for (childIdx in 0 until childCount) {
                    if (node.str[node.data[childIdx]] == key[0]) {
                        val childNodeIdx = node.data[childCount + childCount + childIdx]
                        val childNode = allNodes[childNodeIdx]
                        var childKeyStart = node.data[childIdx]
                        var childKeyEnd: Int
                        if (childIdx == childCount - 1) {
                            childKeyEnd = node.str.length
                        } else {
                            childKeyEnd = node.data[childIdx + 1]
                        }
                        var childKey = node.str.substring(childKeyStart, childKeyEnd)
                        var commonKey = key.commonPrefixWith(childKey)
                        if (commonKey.length == key.length && commonKey.length == childKey.length) {
                            var result = node.data[childCount + childIdx]
                            if (result == undefinedValue) {
                                result = allOutNodes.size
                                node.data[childCount + childIdx] = result
                                allOutNodes.add(nodeIdx)
                                allOutOffsets.add(childIdx)
                            }
                            return result
                        } else if (commonKey.length == childKey.length) {
                            if (childNodeIdx != 0) {
                                nextNode = childNode
                                nextNodeIdx = childNodeIdx
                                key = key.substring(childKey.length, key.length)
                                continue@loop
                            } else {
                                var result = undefinedValue
                                if (create) {
                                    //previous key was a prefix of the new key
                                    val newNode = MyMapStringIntPatriciaTrieNode()
                                    val newNodeIdx = allNodes.size
                                    allNodes.add(newNode)
                                    result = allOutNodes.size
                                    allOutNodes.add(newNodeIdx)
                                    allOutOffsets.add(0)
                                    newNode.parent = nodeIdx
                                    newNode.data = intArrayOf(0, result, 0)
                                    newNode.str = key.substring(commonKey.length, key.length)
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
                                    size++
                                }
                                return result
                            }
/*Coverage Unreachable*/
                        } else {
                            var result = undefinedValue
                            if (create) {
                                val otherKey = childKey.substring(commonKey.length, childKey.length)
                                val otherChildIdx = node.data[childCount + childCount + childIdx]
                                val otherResult = node.data[childCount + childIdx]
                                val newNode = MyMapStringIntPatriciaTrieNode()
                                val newNodeIdx = allNodes.size
                                newNode.parent = nodeIdx
                                allNodes.add(newNode)
                                result = allOutNodes.size
                                if (otherChildIdx != 0) {
                                    val otherChild = allNodes[otherChildIdx]
                                    otherChild.parent = newNodeIdx
                                }
                                if (commonKey.length == key.length) {
                                    allOutNodes.add(nodeIdx)
                                    allOutOffsets.add(childIdx)
                                    //new key is a prefix of the old key
                                    newNode.str = otherKey
                                    newNode.data = intArrayOf(0, otherResult, otherChildIdx)
                                    if (otherResult != undefinedValue) {
                                        allOutNodes[otherResult] = newNodeIdx
                                        allOutOffsets[otherResult] = 0
                                    }
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
                                    node.data[childCount + childIdx] = result
                                    node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
                                    for (j in childIdx + 1 until childCount) {
                                        node.data[j] -= otherKey.length
                                    }
                                } else {
                                    allOutNodes.add(newNodeIdx)
                                    allOutOffsets.add(0)
                                    val newKey = key.substring(commonKey.length, key.length)
                                    //both keys share a common prefix - both need to be changed
                                    newNode.str = newKey + otherKey
                                    newNode.data = intArrayOf(0, newKey.length, result, otherResult, 0, otherChildIdx)
                                    if (otherResult != undefinedValue) {
                                        allOutNodes[otherResult] = newNodeIdx
                                        allOutOffsets[otherResult] = 1
                                    }
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
                                    node.data[childCount + childIdx] = undefinedValue
                                    node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
                                    for (j in childIdx + 1 until childCount) {
                                        node.data[j] -= otherKey.length
                                    }
                                }
                                size++
                            }
                            return result
                        }
/*Coverage Unreachable*/
                    }
                }
                var result = undefinedValue
                if (create) {
                    result = allOutNodes.size
                    allOutNodes.add(nodeIdx)
                    allOutOffsets.add(childCount)
                    val data = IntArray(node.data.size + 3)
                    for (i in 0 until childCount) {
                        data[i] = node.data[i]
                        data[childCount + 1 + i] = node.data[childCount + i]
                        data[childCount + childCount + 2 + i] = node.data[childCount + childCount + i]
                    }
                    data[childCount] = node.str.length
                    data[childCount + childCount + 1] = result
                    node.data = data
                    node.str += key
                    size++
                }
                return result
            }
/*Coverage Unreachable*/
        }
/*Coverage Unreachable*/
    }

    operator fun get(key: String): Int? {
        var res = walkInternal(key, false)
        if (res == undefinedValue) {
            return null
        }
        return res
    }

    operator fun set(key: String, value: Int) {
        throw Exception("not implemented")
    }

    fun getOrCreate(key: String): Int {
        var list = mutableListOf<String>()
        SanityCheck {
            for (i in 0 until allOutNodes.size) {
                list.add(this[i])
            }
        }
        var res = walkInternal(key, true)
        SanityCheck {
            //debug()
            if (res == list.size) {
                list.add(key)
            }
            for (i in 0 until allOutNodes.size) {
                var tmp = this[i]
                SanityCheck.check({ tmp == list[i] }, { "old value changed ${list[i]} -> ${tmp}" })
                SanityCheck.check({ this[tmp] == i }, { "error in path .. $tmp $i ${this[tmp]}" })
            }
        }
        return res
    }

    fun appendAssumeSorted(key: String, value: Int): Int {
        return getOrCreate(key)
    }

    fun clear() {
        root = MyMapStringIntPatriciaTrieNode()
        rootValue = undefinedValue
        allNodes.clear()
        allNodes.add(root)
        allOutNodes.clear()
        allOutOffsets.clear()
    }

    fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(rootValue)
            out.writeInt(allNodes.size)
            val nodeIterator = allNodes.iterator()
            while (nodeIterator.hasNext()) {
                val node = nodeIterator.next()
                for (c in node.str) {
                    out.writeChar(c.toInt())
                }
                out.writeChar(0)
                out.writeShort(node.data.size)
                for (i in 0 until node.data.size) {
                    out.writeInt(node.data[i])
                }
            }
        }
    }

    fun loadFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            rootValue = fis.readInt()
            if (rootValue != undefinedValue) {
                size++
            }
            val allNodesSize = fis.readInt()
            for (counter in 0 until allNodesSize) {
                var node: MyMapStringIntPatriciaTrieNode
                if (allNodes.size < counter) {
                    node = MyMapStringIntPatriciaTrieNode()
                    allNodes[counter] = node
                } else {
                    node = allNodes[counter]
                }
                var c = fis.readChar()
                while (c.toInt() != 0) {
                    node.str += c
                    c = fis.readChar()
                }
                val nodeDataSize = fis.readShort()
                node.data = IntArray(nodeDataSize.toInt())
                val valueOffset = nodeDataSize / 3
                val childOffset = valueOffset * 2
                for (i in 0 until nodeDataSize) {
                    val value = fis.readInt()
                    node.data[i] = value
                    if (i >= valueOffset) {
                        if (i < childOffset) {
                            while (value > allOutNodes.size) {
                                allOutNodes.add(0)
                                allOutOffsets.add(0)
                            }
                            if (value != undefinedValue) {
                                allOutNodes[value] = counter
                                allOutOffsets[value] = i
                            }
                        } else {
                            while (value >= allNodes.size) {
                                allNodes.add(MyMapStringIntPatriciaTrieNode())
                            }
                            if (value != undefinedValue) {
                                allNodes[value].parent = counter
                            }
                        }
                    }
                }
            }
        }
        root = allNodes[0]
    }
}

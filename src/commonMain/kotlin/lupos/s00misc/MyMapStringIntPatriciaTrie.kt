package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapStringIntPatriciaTrie(val undefinedValue: Int = Int.MAX_VALUE) {
    var root = MyMapStringIntPatriciaTrieNode()
    var rootValue: Int = undefinedValue
    var size = 0

    class MyMapStringIntPatriciaTrieNode() {
        var str = ""
        var arr = IntArray(0) /*second half: child values, first half offsets in str*/
        var childs = arrayOf<MyMapStringIntPatriciaTrieNode?>()
    }

    inline fun walkInternal(_key: String, crossinline onCreate: () -> Int, crossinline onExist: (Int) -> Int, crossinline onNotFound: () -> Unit, create: Boolean) {
        if (_key == "") {
            rootValue = onExist(rootValue)
        } else {
            var key = _key
            var node = root
            loop@ while (true) {
                require(key.length > 0)
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
                        var commonKey = key.commonPrefixWith(childKey)
                        if (commonKey.length == key.length) {
                            node.arr[childCount + childIdx] = onExist(node.arr[childCount + childIdx])
                            return
                        } else if (commonKey.length == childKey.length) {
                            if (node.childs[childIdx] != null) {
                                node = node.childs[childIdx]!!
                                key = key.substring(childKey.length, key.length)
                                continue@loop
                            } else {
                                if (create) {
                                    var newNode = MyMapStringIntPatriciaTrieNode()
                                    newNode.childs = arrayOf(null)
                                    newNode.arr = intArrayOf(0, onCreate())
                                    newNode.str = key.substring(commonKey.length, key.length)
                                    node.childs[childIdx] = newNode
                                    size++
                                } else {
                                    onNotFound()
                                }
                                return
                            }
                        } else {
                            if (create) {
                                var otherKey = childKey.substring(commonKey.length, childKey.length)
                                var newKey = key.substring(commonKey.length, key.length)
                                var newNode = MyMapStringIntPatriciaTrieNode()
                                newNode.childs = arrayOf(node.childs[childIdx], null)
                                newNode.str = otherKey + newKey
                                newNode.arr = intArrayOf(0, otherKey.length, node.arr[childCount + childIdx], onCreate())
                                node.childs[childIdx] = newNode
                                node.arr[childCount + childIdx] = undefinedValue
                                node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
                                for (j in childIdx + 1 until childCount) {
                                    node.arr[j] -= otherKey.length
                                }
                                size++
                            } else {
                                onNotFound()
                            }
                            return
                        }
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
                    arr[childCount + childCount + 1] = onCreate()
                    arr[childCount] = node.str.length
                    node.str += key
                    node.childs = childs
                    node.arr = arr
                    size++
                } else {
                    onNotFound()
                }
                return
            }
        }
    }

    inline operator fun get(key: String): Int? {
        var res: Int? = null
        walkInternal(key, { undefinedValue }, {
            res = it
            /*return*/ it
        }, {}, false)
        return res
    }

    inline operator fun set(key: String, value: Int) {
        walkInternal(key, { value }, { value }, {}, true)
    }

    inline fun getOrCreate(key: String, onCreate: () -> Int): Int {
        var res = undefinedValue
        walkInternal(key, {
            res = onCreate()
            /*return*/ res
        }, {
            res = it
            /*return*/ it
        }, {}, true)
        return res
    }

    inline fun appendAssumeSorted(key: String, value: Int): Int {
        set(key, value)
        return value
    }

    inline fun clear() {
        root = MyMapStringIntPatriciaTrieNode()
        rootValue = undefinedValue
        size = 0
    }

    inline fun forEach(action: crossinline(String, Int) -> Unit)
    {
        var queue = mutableListOf<Pair<String, MyMapStringIntPatriciaTrieNode>>()
        var node = root
        if (rootValue != undefinedValue) {
            action("", rootValue)
        }
        var childCount = node.childs.size
        for (childIdx in 0 until childCount) {
            var childKeyStart = node.arr[childIdx]
            var childKeyEnd: Int
            if (childIdx == childCount - 1) {
                childKeyEnd = node.str.length
            } else {
                childKeyEnd = node.arr[childIdx + 1]
            }
            var childKey = node.str.substring(childKeyStart, childKeyEnd)
            if (node.arr[childCount + childIdx] != undefinedValue) {
                action(childKey, node.arr[childCount + childIdx])
            }
            if (node.childs[childIdx] != null) {
                queue.add(Pair(childKey, node.childs[childIdx]!!))
            }
        }
        while (queue.size > 0) {
            var current = queue.removeAt(0)
            var prefix = current.first
            node = current.second
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
    }

    fun safeToFile(filename: String) {
        var queue = mutableListOf<MyMapStringIntPatriciaTrieNode>()
        File(filename).dataOutputStream { out ->
            out.writeInt(rootValue)
            queue.add(root)
            while (queue.size > 0) {
                val node = queue.removeAt(0)
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
        }
    }

    fun loadFromFile(filename: String) {
        var queue = mutableListOf<Pair<Int, MyMapStringIntPatriciaTrieNode>>()
        File(filename).dataInputStream { fis ->
            rootValue = fis.readInt()
            var first = true
            while (queue.size > 0 || first) {
                val node = MyMapStringIntPatriciaTrieNode()
                if (first) {
                    root = node
                    first = false
                } else {
                    var current = queue.removeAt(0)
                    current.second.childs[current.first] = node
                }
                size = 0
                if (rootValue != null) {
                    size++
                }
                var childCount = fis.readShort()
                var c = fis.readChar()
                while (c.toInt() != 0) {
                    node.str += c
                    c = fis.readChar()
                }
                node.childs = Array<MyMapStringIntPatriciaTrieNode?>(childCount.toInt()) { null }
                node.arr = IntArray(childCount * 2)
                for (i in 0 until childCount) {
                    node.arr[i] = fis.readInt()
                    node.arr[childCount + i] = fis.readInt()
                    var x = fis.readChar()
                    if (x.toInt() == 1) {
                        queue.add(Pair(i, node))
                    }
                }
            }
        }
    }

}

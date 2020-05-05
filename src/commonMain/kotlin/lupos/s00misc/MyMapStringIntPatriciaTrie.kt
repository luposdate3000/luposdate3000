package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapStringIntPatriciaTrie() {
    class MyMapStringIntPatriciaTrieNode(var key: String, var value: Int?) {
        val children = mutableListOf<MyMapStringIntPatriciaTrieNode>()
    }

    var size: Int = 0
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNode("", null)

    constructor(data: Pair<String, Int>) : this() {
        set(data.first, data.second)
    }

    fun walkInternal(key: String, node: MyMapStringIntPatriciaTrieNode, onCreate: () -> Int?, onUpdate: (MyMapStringIntPatriciaTrieNode) -> Unit): Int? {
        val keyF = key.get(0)
        for (child in node.children) {
            val childF = child.key.get(0)
            if (keyF == childF) {
                val commonKey = key.commonPrefixWith(child.key)
                if (child.key.length == key.length && commonKey.length == key.length) {
                    if (child.value == null) {
                        child.value = onCreate()
                        if (child.value != null) {
                            size++
                        }
                    } else {
                        onUpdate(child)
                    }
                    return child.value
                } else if (commonKey.length == child.key.length) {
                    return walkInternal(key.substring(commonKey.length, key.length), child, onCreate, onUpdate)
                } else {
                    val value = onCreate()
                    if (value != null) {
                        size++
                        val intermediateNode = MyMapStringIntPatriciaTrieNode(commonKey, null)
                        child.key = child.key.substring(commonKey.length, child.key.length)
                        intermediateNode.children.add(child)
                        node.children.remove(child)
                        node.children.add(intermediateNode)
                        var newNode: MyMapStringIntPatriciaTrieNode
                        if (commonKey.length == key.length) {
                            newNode = intermediateNode
                            intermediateNode.value = value
                        } else {
                            newNode = MyMapStringIntPatriciaTrieNode(key.substring(commonKey.length, key.length), value)
                            intermediateNode.children.add(newNode)
                        }
                        return newNode.value
                    } else {
                        return null
                    }
                }
            }
        }
        val value = onCreate()
        if (value != null) {
            size++
            val newNode = MyMapStringIntPatriciaTrieNode(key, value)
            node.children.add(newNode)
            return newNode.value
        } else {
            return null
        }
    }

    inline operator fun get(key: String): Int? {
        if (key == "") {
            return root.value
        } else {
            return walkInternal(key, root, { null }, {})
        }
    }

    inline operator fun set(key: String, value: Int) {
        if (key == "") {
            root.value = value
        } else {
            walkInternal(key, root, { value }, { it.value = value })
        }
    }

    inline fun getOrCreate(key: String, crossinline onCreate: () -> Int): Int {
        var value: Int? = null
        if (key == "") {
            if (root.value == null) {
                root.value = onCreate()
            }
            value = root.value
        } else {
            walkInternal(key, root, {
                value = onCreate()
/*return*/value
            }, { value = it.value })
        }
        return value!!
    }

    fun appendAssumeSorted(key: String, value: Int): Int {
        set(key, value)
        return value
    }

    fun clear() {
        root = MyMapStringIntPatriciaTrieNode("", null)
    }

    fun forEachInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode, action: (String, Int) -> Unit) {
        val nextPrefix = prefix + node.key
        if (node.value != null) {
            action(nextPrefix, node.value!!)
        }
        for (c in node.children) {
            forEachInternal(nextPrefix, c, action)
        }
    }

    fun forEach(action: (String, Int) -> Unit) = forEachInternal("", root, action)

    fun safeToFile(filename: String) {
        var queue = mutableListOf<MyMapStringIntPatriciaTrieNode>()
        File(filename).dataOutputStream { out ->
            out.writeShort(root.children.size)
            if (root.value != null) {
                out.writeChar(1)
                out.writeInt(root.value!!)
            } else {
                out.writeChar(0)
            }
            for (children in root.children) {
                queue.add(children)
            }
            while (queue.size > 0) {
                val node = queue.removeAt(0)
                out.writeShort(node.children.size)
                for (c in node.key) {
                    out.writeChar(c.toInt())
                }
                /*write node.key*/
                if (node.value != null) {
                    out.writeChar(1)
                    out.writeInt(node.value!!)
                } else {
                    out.writeChar(0)
                }
                for (children in node.children) {
                    queue.add(children)
                }
            }
        }
    }

    fun loadFromFile(filename: String) {
        var queueNode = mutableListOf<MyMapStringIntPatriciaTrieNode>()
        var queueCount = MyListInt()
        File(filename).dataInputStream { fis ->
            val len = fis.readShort()
            if (len > 0) {
                queueNode.add(root)
                queueCount.add(len.toInt())
            }
            if (fis.readChar().toInt() == 1) {
                root.value = fis.readInt()
            }
            while (queueCount.size > 0) {
                val parentCount = queueCount[0]--
                val parent = queueNode[0]
                SanityCheck.check { parentCount > 0 }
                if (parentCount == 1) {
                    queueNode.removeAt(0)
                    queueCount.removeAt(0)
                }
                val len = fis.readShort()
                val key = StringBuilder()
                var c = fis.readChar()
                while (c.toInt() > 1) {
                    key.append(c)
                    c = fis.readChar()
                }
                val node: MyMapStringIntPatriciaTrieNode
                if (c.toInt() == 1) {
                    node = MyMapStringIntPatriciaTrieNode(key.toString(), fis.readInt())
                } else {
                    node = MyMapStringIntPatriciaTrieNode(key.toString(), null)
                }
                queueCount.add(len.toInt())
                queueNode.add(node)
                parent.children.add(node)
            }
        }
    }


    fun debugInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode,depth:Int) {
        if (node.value != null) {
	    println("${prefix}${node.key}:${node.children.size}@${node.key.length}-${depth}=${node.value}")
        }else{
	    println("${prefix}${node.key}:${node.children.size}@${node.key.length}-${depth}")
	}
        for (c in node.children) {
            debugInternal(prefix+" ", c,depth+1)
        }
    }

    fun debug() = debugInternal("", root,0)

}

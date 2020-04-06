package lupos.s03resultRepresentation

class PatriciaTrie {
    val undefinedValue = -1L
    var nextValue = 0L
    val root: PatriciaTrieNode = PatriciaTrieNode("", undefinedValue)

    class PatriciaTrieNode(var key: String, var value: Long) {
        val children = mutableListOf<PatriciaTrieNode>()
    }

    fun insert(key: String): Long {
        require(key.length > 0, { "d" })
        return walkInternal(key, root, true)
    }

    fun find(key: String): Long {
        require(key.length > 0, { "e" })
        return walkInternal(key, root, false)
    }

    fun values(): List<Pair<String, Long>> {
        return valuesInternal(root, "")
    }

    fun walkInternal(key: String, node: PatriciaTrieNode, create: Boolean): Long {
        val keyF = key.get(0)
        for (childIndex in 0 until node.children.size) {
            val child = node.children[childIndex]
            val childF = child.key.get(0)
            if (keyF == childF) {
                val commonKey = key.commonPrefixWith(child.key)
                if (child.key.length == key.length && commonKey.length == key.length) {
                    if (child.value == undefinedValue && create) {
                        child.value = nextValue++
                    }
                    return child.value
                } else if (commonKey.length == child.key.length) {
                    return walkInternal(key.substring(commonKey.length, key.length), child, create)
                } else {
                    if (create) {
                        val intermediateNode = PatriciaTrieNode(commonKey, undefinedValue)
                        child.key = child.key.substring(commonKey.length, child.key.length)
                        intermediateNode.children.add(child)
                        node.children.remove(child)
                        node.children.add(intermediateNode)
                        var newNode: PatriciaTrieNode
                        if (commonKey.length == key.length) {
                            newNode = intermediateNode
                        } else {
                            newNode = PatriciaTrieNode(key.substring(commonKey.length, key.length), nextValue++)
                        }
                        intermediateNode.children.add(newNode)
                        return newNode.value
                    } else {
                        return undefinedValue
                    }
                }
            }
        }
        if (create) {
            val newNode = PatriciaTrieNode(key, nextValue++)
            node.children.add(newNode)
            return newNode.value
        } else {
            return undefinedValue
        }
    }

    fun valuesInternal(node: PatriciaTrieNode, prefix: String): List<Pair<String, Long>> {
        val res = mutableListOf<Pair<String, Long>>()
        if (node.value != undefinedValue) {
            res.add(Pair("${prefix}${node.key}", node.value))
        }
        for (child in node.children) {
            res.addAll(valuesInternal(child, prefix + node.key))
        }
        return res
    }

    override fun toString(): String {
        val res = StringBuilder()
        val nodes = values()
        res.append("[")
        for (node in nodes) {
            res.append("${node.first} = ${node.second}\n")
        }
        res.append("]")
        return res.toString()
    }
}

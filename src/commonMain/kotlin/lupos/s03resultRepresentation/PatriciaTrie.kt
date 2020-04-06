package lupos.s03resultRepresentation

class PatriciaTrie {
    val undefinedValue = -1
    var nextValue = 0
    val root: PatriciaTrieNode = PatriciaTrieNode("", undefinedValue)

    class PatriciaTrieNode(var key: String, var value: Int) {
        val children = mutableListOf<PatriciaTrieNode>()
    }

    fun insert(key: String): Int {
        require(key.length > 0, { "d" })
        return walkInternal(key, root, true)
    }

    fun find(key: String): Int {
        require(key.length > 0, { "e" })
        return walkInternal(key, root, false)
    }

    fun values(): List<Pair<String, Int>> {
        return valuesInternal(root, "")
    }

    fun walkInternal(key: String, node: PatriciaTrieNode, create: Boolean): Int {
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

    fun valuesInternal(node: PatriciaTrieNode, prefix: String): List<Pair<String, Int>> {
        val res = mutableListOf<Pair<String, Int>>()
        if (node.value != undefinedValue) {
            res.add(Pair("${prefix}${node.key}", node.value))
        }
        for (child in node.children) {
            res.addAll(valuesInternal(child, prefix + node.key))
        }
        return res
    }

fun getDictionaryMapping(dictionary:ResultSetDictionary):Array<Value>{
val res=Array(nextValue){ResultSetDictionary.undefValue}
getDictionaryMappingInternal(root,"",dictionary,res)
return res
}

    fun getDictionaryMappingInternal(node: PatriciaTrieNode, prefix: String,dictionary:ResultSetDictionary,mapping:Array<Value>) {
	mapping[node.value]=dictionary.createValue(ValueDefinition(node.value))
        for (child in node.children) {
            getDictionaryMapping(child, prefix + node.key,dictionary,mapping)
        }
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

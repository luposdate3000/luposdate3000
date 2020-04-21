package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

class PatriciaTrie {
    val undefinedValue = -1
    var nextValue = 0
    val root: PatriciaTrieNode = PatriciaTrieNode("", undefinedValue)

    class PatriciaTrieNode(var key: String, var value: Int) {
        val children = mutableListOf<PatriciaTrieNode>()
    }

    fun insert(key: String): Int {
        //BenchmarkUtils.start(EBenchmark.IMPORT_PATRICIA_INSERT)
        require(key.length > 0, { "d" })
        val res = walkInternal(key, root, true)
        //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_PATRICIA_INSERT)
        return res
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
                            require(intermediateNode.value == undefinedValue)
                            intermediateNode.value = nextValue++
                        } else {
                            newNode = PatriciaTrieNode(key.substring(commonKey.length, key.length), nextValue++)
                            intermediateNode.children.add(newNode)
                        }
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

    fun getDictionaryMapping(dictionary: ResultSetDictionary): Array<Value> {
        val res = Array(nextValue) { ResultSetDictionary.undefValue }
        //BenchmarkUtils.start(EBenchmark.IMPORT_PATRICIA_MAPPING)
        getDictionaryMappingInternal(root, "", dictionary, res)
        //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_PATRICIA_MAPPING)
        return res
    }

    fun getDictionaryMappingInternal(node: PatriciaTrieNode, prefix: String, dictionary: ResultSetDictionary, mapping: Array<Value>) {
        val newPrefix = prefix + node.key
        if (node.value != undefinedValue) {
            mapping[node.value] = dictionary.createValue(ValueDefinition(newPrefix))
        }
        for (child in node.children) {
            getDictionaryMappingInternal(child, newPrefix, dictionary, mapping)
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

    fun printVerbose(node: PatriciaTrieNode = root, indention: String = ""): String {
        val res = StringBuilder()
        res.append(indention + node.key)
        if (node.value != undefinedValue) {
            res.append(" = " + node.value)
        }
        res.append("\n")
        if (node.children.size > 0) {
            for (c in node.children) {
                res.append(printVerbose(c, indention + " "))
            }
        }
        return res.toString()
    }
}

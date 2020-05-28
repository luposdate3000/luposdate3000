package lupos.s00misc
import lupos.s00misc.Coverage
class MyMapStringIntPatriciaTrieVersion1() {
    class MyMapStringIntPatriciaTrieNode(var key: String, var value: Int?) {
        val children = mutableListOf<MyMapStringIntPatriciaTrieNode>()
    }
    var size: Int = 0
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNode("", null)
    constructor(data: Pair<String, Int>) : this() {
        set(data.first, data.second)
    }
    fun walkInternal(key: String, node: MyMapStringIntPatriciaTrieNode, onCreate: () -> Int?, onUpdate: (MyMapStringIntPatriciaTrieNode) -> Unit): Int? {
Coverage.funStart(1008)
        val keyF = key.get(0)
Coverage.statementStart(1009)
        for (child in node.children) {
Coverage.forLoopStart(1010)
            val childF = child.key.get(0)
Coverage.statementStart(1011)
            if (keyF == childF) {
Coverage.ifStart(1012)
                val commonKey = key.commonPrefixWith(child.key)
Coverage.statementStart(1013)
                if (child.key.length == key.length && commonKey.length == key.length) {
Coverage.ifStart(1014)
                    if (child.value == null) {
Coverage.ifStart(1015)
                        child.value = onCreate()
Coverage.statementStart(1016)
                        if (child.value != null) {
Coverage.ifStart(1017)
                            size++
Coverage.statementStart(1018)
                        }
Coverage.statementStart(1019)
                    } else {
Coverage.ifStart(1020)
                        onUpdate(child)
Coverage.statementStart(1021)
                    }
Coverage.statementStart(1022)
                    return child.value
                } else if (commonKey.length == child.key.length) {
Coverage.statementStart(1023)
                    return walkInternal(key.substring(commonKey.length, key.length), child, onCreate, onUpdate)
                } else {
Coverage.statementStart(1024)
                    val value = onCreate()
Coverage.statementStart(1025)
                    if (value != null) {
Coverage.ifStart(1026)
                        size++
Coverage.statementStart(1027)
                        val intermediateNode = MyMapStringIntPatriciaTrieNode(commonKey, null)
Coverage.statementStart(1028)
                        child.key = child.key.substring(commonKey.length, child.key.length)
Coverage.statementStart(1029)
                        intermediateNode.children.add(child)
Coverage.statementStart(1030)
                        node.children.remove(child)
Coverage.statementStart(1031)
                        node.children.add(intermediateNode)
Coverage.statementStart(1032)
                        var newNode: MyMapStringIntPatriciaTrieNode
Coverage.statementStart(1033)
                        if (commonKey.length == key.length) {
Coverage.ifStart(1034)
                            newNode = intermediateNode
Coverage.statementStart(1035)
                            intermediateNode.value = value
Coverage.statementStart(1036)
                        } else {
Coverage.ifStart(1037)
                            newNode = MyMapStringIntPatriciaTrieNode(key.substring(commonKey.length, key.length), value)
Coverage.statementStart(1038)
                            intermediateNode.children.add(newNode)
Coverage.statementStart(1039)
                        }
Coverage.statementStart(1040)
                        return newNode.value
                    } else {
Coverage.statementStart(1041)
                        return null
                    }
Coverage.statementStart(1042)
                }
Coverage.statementStart(1043)
            }
Coverage.statementStart(1044)
        }
Coverage.statementStart(1045)
        val value = onCreate()
Coverage.statementStart(1046)
        if (value != null) {
Coverage.ifStart(1047)
            size++
Coverage.statementStart(1048)
            val newNode = MyMapStringIntPatriciaTrieNode(key, value)
Coverage.statementStart(1049)
            node.children.add(newNode)
Coverage.statementStart(1050)
            return newNode.value
        } else {
Coverage.statementStart(1051)
            return null
        }
Coverage.statementStart(1052)
    }
    inline operator fun get(key: String): Int? {
Coverage.funStart(1053)
        if (key == "") {
Coverage.ifStart(1054)
            return root.value
        } else {
Coverage.statementStart(1055)
            return walkInternal(key, root, { null }, {})
        }
Coverage.statementStart(1056)
    }
    inline operator fun set(key: String, value: Int) {
Coverage.funStart(1057)
        if (key == "") {
Coverage.ifStart(1058)
            root.value = value
Coverage.statementStart(1059)
        } else {
Coverage.ifStart(1060)
            walkInternal(key, root, { value }, { it.value = value })
Coverage.statementStart(1061)
        }
Coverage.statementStart(1062)
    }
    inline fun getOrCreate(key: String, crossinline onCreate: () -> Int): Int {
Coverage.funStart(1063)
        var value: Int? = null
Coverage.statementStart(1064)
        if (key == "") {
Coverage.ifStart(1065)
            if (root.value == null) {
Coverage.ifStart(1066)
                root.value = onCreate()
Coverage.statementStart(1067)
            }
Coverage.statementStart(1068)
            value = root.value
Coverage.statementStart(1069)
        } else {
Coverage.ifStart(1070)
            walkInternal(key, root, {
Coverage.statementStart(1071)
                value = onCreate()
Coverage.statementStart(1072)
/*return*/value
            }, { value = it.value })
Coverage.statementStart(1073)
        }
Coverage.statementStart(1074)
        return value!!
    }
    fun appendAssumeSorted(key: String, value: Int): Int {
Coverage.funStart(1075)
        set(key, value)
Coverage.statementStart(1076)
        return value
    }
    fun clear() {
Coverage.funStart(1077)
        root = MyMapStringIntPatriciaTrieNode("", null)
Coverage.statementStart(1078)
    }
    fun forEachInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode, action: (String, Int) -> Unit) {
Coverage.funStart(1079)
        val nextPrefix = prefix + node.key
Coverage.statementStart(1080)
        if (node.value != null) {
Coverage.ifStart(1081)
            action(nextPrefix, node.value!!)
Coverage.statementStart(1082)
        }
Coverage.statementStart(1083)
        for (c in node.children) {
Coverage.forLoopStart(1084)
            forEachInternal(nextPrefix, c, action)
Coverage.statementStart(1085)
        }
Coverage.statementStart(1086)
    }
    fun forEach(action: (String, Int) -> Unit) = forEachInternal("", root, action)
    fun safeToFile(filename: String) {
Coverage.funStart(1087)
        var queue = mutableListOf<MyMapStringIntPatriciaTrieNode>()
Coverage.statementStart(1088)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(1089)
            out.writeShort(root.children.size)
Coverage.statementStart(1090)
            if (root.value != null) {
Coverage.ifStart(1091)
                out.writeChar(1)
Coverage.statementStart(1092)
                out.writeInt(root.value!!)
Coverage.statementStart(1093)
            } else {
Coverage.ifStart(1094)
                out.writeChar(0)
Coverage.statementStart(1095)
            }
Coverage.statementStart(1096)
            for (children in root.children) {
Coverage.forLoopStart(1097)
                queue.add(children)
Coverage.statementStart(1098)
            }
Coverage.statementStart(1099)
            while (queue.size > 0) {
Coverage.whileLoopStart(1100)
                val node = queue.removeAt(0)
Coverage.statementStart(1101)
                out.writeShort(node.children.size)
Coverage.statementStart(1102)
                for (c in node.key) {
Coverage.forLoopStart(1103)
                    out.writeChar(c.toInt())
Coverage.statementStart(1104)
                }
Coverage.statementStart(1105)
                /*write node.key*/
Coverage.statementStart(1106)
                if (node.value != null) {
Coverage.ifStart(1107)
                    out.writeChar(1)
Coverage.statementStart(1108)
                    out.writeInt(node.value!!)
Coverage.statementStart(1109)
                } else {
Coverage.ifStart(1110)
                    out.writeChar(0)
Coverage.statementStart(1111)
                }
Coverage.statementStart(1112)
                for (children in node.children) {
Coverage.forLoopStart(1113)
                    queue.add(children)
Coverage.statementStart(1114)
                }
Coverage.statementStart(1115)
            }
Coverage.statementStart(1116)
        }
Coverage.statementStart(1117)
    }
    fun loadFromFile(filename: String) {
Coverage.funStart(1118)
        var queueNode = mutableListOf<MyMapStringIntPatriciaTrieNode>()
Coverage.statementStart(1119)
        var queueCount = MyListInt()
Coverage.statementStart(1120)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(1121)
            var len = fis.readShort()
Coverage.statementStart(1122)
            if (len > 0) {
Coverage.ifStart(1123)
                queueNode.add(root)
Coverage.statementStart(1124)
                queueCount.add(len.toInt())
Coverage.statementStart(1125)
            }
Coverage.statementStart(1126)
            if (fis.readChar().toInt() == 1) {
Coverage.ifStart(1127)
                root.value = fis.readInt()
Coverage.statementStart(1128)
            }
Coverage.statementStart(1129)
            while (queueCount.size > 0) {
Coverage.whileLoopStart(1130)
                val parentCount = queueCount[0]--
Coverage.statementStart(1131)
                val parent = queueNode[0]
Coverage.statementStart(1132)
                SanityCheck.check { parentCount > 0 }
Coverage.statementStart(1133)
                if (parentCount == 1) {
Coverage.ifStart(1134)
                    queueNode.removeAt(0)
Coverage.statementStart(1135)
                    queueCount.removeAt(0)
Coverage.statementStart(1136)
                }
Coverage.statementStart(1137)
                len = fis.readShort()
Coverage.statementStart(1138)
                val key = StringBuilder()
Coverage.statementStart(1139)
                var c = fis.readChar()
Coverage.statementStart(1140)
                while (c.toInt() > 1) {
Coverage.whileLoopStart(1141)
                    key.append(c)
Coverage.statementStart(1142)
                    c = fis.readChar()
Coverage.statementStart(1143)
                }
Coverage.statementStart(1144)
                val node: MyMapStringIntPatriciaTrieNode
Coverage.statementStart(1145)
                if (c.toInt() == 1) {
Coverage.ifStart(1146)
                    node = MyMapStringIntPatriciaTrieNode(key.toString(), fis.readInt())
Coverage.statementStart(1147)
                } else {
Coverage.ifStart(1148)
                    node = MyMapStringIntPatriciaTrieNode(key.toString(), null)
Coverage.statementStart(1149)
                }
Coverage.statementStart(1150)
                queueCount.add(len.toInt())
Coverage.statementStart(1151)
                queueNode.add(node)
Coverage.statementStart(1152)
                parent.children.add(node)
Coverage.statementStart(1153)
            }
Coverage.statementStart(1154)
        }
Coverage.statementStart(1155)
    }
    fun debugInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode, depth: Int) {
Coverage.funStart(1156)
        SanityCheck {
Coverage.statementStart(1157)
            if (node.value != null) {
Coverage.ifStart(1158)
                println("debug ${prefix}${node.key}:${node.children.size}@${node.key.length}-${depth}=${node.value}")
Coverage.statementStart(1159)
            } else {
Coverage.ifStart(1160)
                println("debug ${prefix}${node.key}:${node.children.size}@${node.key.length}-${depth}")
Coverage.statementStart(1161)
            }
Coverage.statementStart(1162)
            for (c in node.children) {
Coverage.forLoopStart(1163)
                debugInternal(prefix + " ", c, depth + 1)
Coverage.statementStart(1164)
            }
Coverage.statementStart(1165)
        }
Coverage.statementStart(1166)
    }
    fun debug() = debugInternal("", root, 0)
}
